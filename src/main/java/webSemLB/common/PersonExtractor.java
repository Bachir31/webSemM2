package webSemLB.common;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class PersonExtractor extends RDFExtractor {
	
	private static Logger logger = Logger.getLogger(PersonDataImpl.class);
	
	public PersonExtractor() {

	}
	
	@Override
	public RDFNode primaryTopic(String iri) {
		Property propertyFoafPrimaryTopic = null;
		Property propertySchemaPrimaryTopic = null;
		
		if (iri == null) {
			return null;
		}
		
		if (iri.isEmpty()) {
			return null;
		}
		
		if (model == null) {
			model = ModelFactory.createDefaultModel();
		}
		
		if(!model.isEmpty()) {
			model.close();
		}
		
		for (RdfFormat format : RdfFormat.values()) { //try reading IRI in differrent format

			try {
				
				model.read(iri, format.toString());
				
				propertyFoafPrimaryTopic = model.createProperty(CommonIRI.FOAF.toString()+"PrimaryTopic");
				
				NodeIterator it = model.listObjectsOfProperty(model.getResource(iri),propertyFoafPrimaryTopic);

				if (it.hasNext()) {
					return it.next();
				}
				
				propertySchemaPrimaryTopic = model.createProperty(CommonIRI.SCHEMA.toString() + "PrimaryTopic");
				it = model.listObjectsOfProperty(model.getResource(iri),propertySchemaPrimaryTopic);
				
				if (it.hasNext()) {
					return it.next();
				}

				break;
			} catch (Exception e) {
				model.remove(model);
				logger.warn("[PersonExtractor.primaryTopic] Bad format : " + format.toString());
			}
		}
		return null;
	}
	
	public void showPersonData(String iri) {
		
	}

}
