package webSemLB.common;

import org.apache.log4j.Logger;
import org.semarglproject.vocab.RDFS;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.vocabulary.VCARD;

public class PersonDataImpl implements PersonData {

	private static Logger logger = Logger.getLogger(PersonDataImpl.class);

	private final String NS_FOAF = "http://xmlns.com/foaf/0.1/";
	private final String NS_SCHEME = "http://schema.org/";
	private final String NS_DBO = "http://dbpedia.org/ontology/";

	public PersonDataImpl() {

	}

	public String name(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;
		Statement statement2 = null;

		if (person == null) {
			logger.warn("[PersonImpl.name] argument person is null");
			return null;
		}

		if (person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyFoafName = model.createProperty(NS_FOAF + "name");
			Property propertyFoafFirstName = model.createProperty(NS_FOAF + "firstName");
			Property propertyFoafLastName = model.createProperty(NS_FOAF + "lastName");
			Property propertySchemeName = model.createProperty(NS_SCHEME + "name");
			Property propertySchemeGivenName = model.createProperty(NS_SCHEME + "givenName");
			Property propertySchemeFamilyName = model.createProperty(NS_SCHEME + "familyName");
			Property propertyRdfsLabel = model.createProperty(RDFS.LABEL);

			if (resourcePerson.hasProperty(propertyFoafName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyFoafName);
				return statement1.getString();
			} else if (resourcePerson.hasProperty(propertySchemeName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeName);
				return statement1.getString();
			} else if (resourcePerson.hasProperty(propertyFoafFirstName)
					&& resourcePerson.hasProperty(propertyFoafLastName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyFoafFirstName);
				statement2 = (Statement) resourcePerson.getProperty(propertyFoafLastName);
				return statement1.getString() + " " + statement2.getString();
			} else if (resourcePerson.hasProperty(propertySchemeGivenName)
					&& resourcePerson.hasProperty(propertySchemeFamilyName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeGivenName);
				statement2 = (Statement) resourcePerson.getProperty(propertySchemeFamilyName);
				return statement1.getString() + " " + statement2.getString();
			} else if (resourcePerson.hasProperty(propertyRdfsLabel)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyRdfsLabel);
				return statement1.getString();
			} else {
				return null;
			}
		}
		logger.warn("[PersonImpl.name] argument person is not a instance of Resource");
		return null;
	}

	public String birth(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;

		if (person == null) {
			logger.warn("[PersonImpl.birth] argument person is null");
			return null;
		}

		if (person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyDboBirth = model.createProperty(NS_DBO + "birthDate");
			Property propertyVcardBirth = model.createProperty(VCARD.BDAY.getURI());
			Property propertySchemeBirth = model.createProperty(NS_SCHEME + "birthDate");
			
			if (resourcePerson.hasProperty(propertyDboBirth)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyDboBirth);
				return statement1.getString();
			} else if (resourcePerson.hasProperty(propertyVcardBirth)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyVcardBirth);
				return statement1.getString();
			} else if (resourcePerson.hasProperty(propertySchemeBirth)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeBirth);
				return statement1.getString();
			} else {
				return null;
			}
		}
		return null;
	}

	public String death(RDFNode person, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	public String picture(RDFNode person, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}
