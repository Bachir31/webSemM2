package webSemLB.ClassExtractor.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.semarglproject.jena.rdf.rdfa.JenaRdfaReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
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
		// TODO Auto-generated method stub
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
