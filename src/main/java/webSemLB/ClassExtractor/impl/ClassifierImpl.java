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
		
		for (RdfFormat format : RdfFormat.values()) {

			try {
				model.read(iri, format.toString());
				
				NodeIterator it = model.listObjectsOfProperty(RDF.type);

				while (it.hasNext()) {
					RDFNode object = it.next();
					
					if ( object instanceof Resource) {
						logger.info("[rdf:type] IRI Class <" + object.toString() + ">");
						typesSet.add(object.toString());
					}
				}
				
				
				break;
			} catch (Exception e) {
				model.remove(model);
				logger.warn("Bad format : " + format.toString());
			}
		} // TODO Auto-generated method stub
		return typesSet;
	}

	public Collection<String> retrieveSuperClasses(String iri) {
		HashSet<String> classSet = null;
		
		if(iri == null) {
			return null;
		}
		
		if(iri.isEmpty()) {
			return null;
		}
		
		classSet = new HashSet<String>();
		//JenaRdfaReader.inject();
		Model submodel = ModelFactory.createDefaultModel();
		for (RdfFormat format : RdfFormat.values()) {

			try {
				submodel.read(iri, format.toString());
				
				NodeIterator it = submodel.listObjectsOfProperty(submodel.getResource(iri), RDFS.subClassOf);
				//NodeIterator it = submodel.listObjectsOfProperty(, p)

				while (it.hasNext()) {
					RDFNode object = it.nextNode();
					
					if ( object instanceof Resource) {
						logger.info("[rdfs:subClassOf] IRI Class <" + object.toString() + ">");
						classSet.add(object.toString());
					}
				}
				
				break;
			} catch (Exception e) {
				model.remove(model);
				logger.warn("Bad format : " + format.toString());
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
		allTypeSet.addAll(retrieveTypes(url));
		
		queue.addAll(allTypeSet);
		
		while(!queue.isEmpty()) {
			Collection<String> classSet = retrieveSuperClasses(queue.poll());
			
			classSet.removeAll(allTypeSet);
			classSet.removeAll(queue);
			allTypeSet.addAll(classSet);
			queue.addAll(classSet);
			
		}
		
		System.out.println(allTypeSet.toString());
		logger.info("End function getAllTypes");

		return allTypeSet;
	}

	public boolean isOfType(String entityIRI, String classIRI) {
		// TODO Auto-generated method stub
		return false;
	}

}
