package webSemLB.common;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

import webSemLB.ClassExtractor.impl.ClassifierImpl;
import webSemLB.ClassExtractor.service.Classifier;

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

		if (!model.isEmpty()) {
			model.close();
		}

		for (RdfFormat format : RdfFormat.values()) { // try reading IRI in
														// differrent format

			try {

				model.read(iri, format.toString());

				propertyFoafPrimaryTopic = model.createProperty(CommonIRI.FOAF.toString() + "PrimaryTopic");

				NodeIterator it = model.listObjectsOfProperty(model.getResource(iri), propertyFoafPrimaryTopic);

				if (it.hasNext()) {
					return it.next();
				}

				propertySchemaPrimaryTopic = model.createProperty(CommonIRI.SCHEMA.toString() + "PrimaryTopic");
				it = model.listObjectsOfProperty(model.getResource(iri), propertySchemaPrimaryTopic);

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

		RDFNode primaryTopic = null;
		PersonData personData = new PersonDataImpl();
		Classifier classifier = new ClassifierImpl();

		if (iri == null) {
			logger.info("[PersonExtractor.showPersonData] Given IRI is null !");
			return;
		}

		primaryTopic = primaryTopic(iri);

		if (primaryTopic == null) {
			System.out.println("#########################primaryTopic is null");

			ResIterator it = model.listSubjects();

			while (it.hasNext()) {

				Resource resource = it.nextResource();

				if (classifier.isOfType(resource.getURI(), CommonIRI.FOAF.toString() + "Person")
						|| classifier.isOfType(resource.getURI(), CommonIRI.SCHEMA.toString() + "Person")) {
					
					String name = personData.name(resource, model);
					if (name != null) {
						logger.info("[PersonExtrator.showPersonData] name : " + name);
					}

					String birth = personData.birth(resource, model);
					if (birth != null) {
						logger.info("[PersonExtrator.showPersonData] birth : " + birth);
					}

					String death = personData.death(resource, model);
					if (death != null) {
						logger.info("[PersonExtrator.showPersonData] death : " + death);
					}

					String picture = personData.picture(resource, model);
					if (picture != null) {
						logger.info("[PersonExtrator.showPersonData] picture : " + picture);
					}
				}

			}
			return;
		}

		if (primaryTopic.isResource()) {
			if (classifier.isOfType(primaryTopic.asResource().getURI(), CommonIRI.FOAF.toString() + "Person")
					|| classifier.isOfType(primaryTopic.asResource().getURI(),
							CommonIRI.SCHEMA.toString() + "Person")) {

				String name = personData.name(primaryTopic, model);
				if (name != null) {
					logger.info("[PersonExtrator.showPersonData] name : " + name);
				}

				String birth = personData.birth(primaryTopic, model);
				if (birth != null) {
					logger.info("[PersonExtrator.showPersonData] birth : " + birth);
				}

				String death = personData.death(primaryTopic, model);
				if (death != null) {
					logger.info("[PersonExtrator.showPersonData] death : " + death);
				}

				String picture = personData.picture(primaryTopic, model);
				if (picture != null) {
					logger.info("[PersonExtrator.showPersonData] picture : " + picture);
				}
			}
		}
	}

}
