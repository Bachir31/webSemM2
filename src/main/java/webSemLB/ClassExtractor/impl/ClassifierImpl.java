package webSemLB.ClassExtractor.impl;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import webSemLB.ClassExtractor.service.Classifier;
import webSemLB.common.RdfFormat;

public class ClassifierImpl implements Classifier {
	private static Logger logger = Logger.getLogger(ClassifierImpl.class);
	private Model model;
	private HashSet<String> allTypeSet = null;
	private ArrayDeque<String> queue = null;

	public ClassifierImpl() {
		JenaRdfaReader.inject();
		model = ModelFactory.createDefaultModel();
		allTypeSet = new HashSet<String>();
		queue = new ArrayDeque<String>();
	}

	public Collection<String> retrieveTypes(String iri) {

		HashSet<String> typesSet = null;

		if (iri == null) {
			return null;
		}

		if (iri.isEmpty()) {
			return null;
		}

		typesSet = new HashSet<String>();

		for (RdfFormat format : RdfFormat.values()) { //try reading IRI in differrent format

			try {
				model.read(iri, format.toString());

				NodeIterator it = model.listObjectsOfProperty(RDF.type);

				while (it.hasNext()) {
					RDFNode object = it.next();

					if (object instanceof Resource) {
						typesSet.add(object.toString());
					}
				}

				break;
			} catch (Exception e) {
				model.remove(model);
				logger.warn("[retrieveTypes] Bad format : " + format.toString());
			}
		}
		return typesSet;
	}

	public Collection<String> retrieveSuperClasses(String iri) {
		HashSet<String> classSet = null;
		Model submodel = null;

		if (iri == null) {
			return null;
		}

		if (iri.isEmpty()) {
			return null;
		}

		classSet = new HashSet<String>();
		submodel = ModelFactory.createDefaultModel();
		
		for (RdfFormat format : RdfFormat.values()) { //try reading IRI in differrent format

			try {
				submodel.read(iri, format.toString());

				NodeIterator it = submodel.listObjectsOfProperty(submodel.getResource(iri), RDFS.subClassOf);//search all object with Resource = iri and property = "subClassOf"

				while (it.hasNext()) {
					RDFNode object = it.nextNode();

					if (object instanceof Resource) {
						classSet.add(object.toString());
					}
				}

				break;
			} catch (Exception e) {
				model.remove(model);//erase RDF graph and reinitialize him
				logger.warn("[retrieveSuperClasses] Bad format : " + format.toString());
			}
		}

		return classSet;
	}

	public Collection<String> getAllTypes(String url) {

		if (url == null) {
			return null;
		}

		if (url.isEmpty()) {
			return null;
		}
		
		allTypeSet.addAll(retrieveTypes(url)); //initialize set of classes
		queue.addAll(allTypeSet); //add all element in allTypeSet into queue structure

		while (!queue.isEmpty()) {
			Collection<String> classSet = retrieveSuperClasses(queue.poll()); 	// dequeues the first IRI in queue and retrieves super class of this IRI
																				// store the result in a collection
			classSet.removeAll(allTypeSet); //remove all in elements present in AllTypeSet collection to resultCollection
			classSet.removeAll(queue);		//remove all in elements present in queue collection to resultCollection
			allTypeSet.addAll(classSet);	//add the rest of result collection to AllTypeSet collection
			queue.addAll(classSet);			//add the rest of result collection to queue

		}

		logger.info("[getAllTypes] | size : " + allTypeSet.size() + " | Items : " + allTypeSet.toString());

		return allTypeSet;
	}

	public boolean isOfType(String entityIRI, String classIRI) {
		if (entityIRI == null || classIRI == null) {
			logger.warn("Null entries in arguments of isOfType");
			return false;
		}

		if (entityIRI.isEmpty() || classIRI.isEmpty()) {
			logger.warn("Empty entries in arguments of isOfType");
			return false;
		}
		Collection<String> result = getAllTypes(entityIRI);
		
		return result.contains(classIRI);
	}

}
