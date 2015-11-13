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

import webSemLB.ClassExtractor.service.Classifier;
import webSemLB.common.RdfFormat;

public class ClassifierImpl implements Classifier {
	private static Logger logger = Logger.getLogger(ClassifierImpl.class);
	private Model model;

	public ClassifierImpl() {
		JenaRdfaReader.inject();
		model = ModelFactory.createDefaultModel();

	}

	public Collection<String> retrieveTypes(String iri) {
		HashSet<Resource> classSet = new HashSet<Resource>();
		ArrayDeque<Resource> queue = null;
		if (iri == null) {
			return null;
		}

		if (iri.isEmpty()) {
			return null;
		}
		
		for (RdfFormat format : RdfFormat.values()) {

			try {
				model.read(iri, format.toString());
				
				NodeIterator it = model.listObjectsOfProperty(RDF.type);

				while (it.hasNext()) {
					RDFNode object = it.next();
					
					if ( object instanceof Resource) {
						logger.info("Find IRI Class <" + object.toString() + ">");
						classSet.add((Resource) object);
					}
				}
				
				queue = new ArrayDeque<Resource>(classSet);
				
				while(!queue.isEmpty()) {
					retrieveSuperClasses(queue.pop().toString());
				}
				
				break;
			} catch (Exception e) {
				model.remove(model);
				logger.warn("Bad format : " + format.toString());
			}
		} // TODO Auto-generated method stub
		return null;
	}

	public Collection<String> retrieveSuperClasses(String iri) {
		System.out.println("##" +iri); 	// this line is a test code
										//need to implement function 
		return null;
	}

	public Collection<String> getAllTypes(String url) {

		if (url == null) {
			return null;
		}

		if (url.isEmpty()) {
			return null;
		}
		this.retrieveTypes(url);
		
		
		logger.info("End function getAllTypes");

		return null;
	}

	public boolean isOfType(String entityIRI, String classIRI) {
		// TODO Auto-generated method stub
		return false;
	}

}
