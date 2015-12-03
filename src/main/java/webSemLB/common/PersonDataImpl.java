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

	public PersonDataImpl() {

	}

	public String name(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;
		Statement statement2 = null;

		if (person == null || model == null) {
			logger.warn("[PersonImpl.name] argument person or model is null");
			return null;
		}
		

		if (person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyFoafName = model.createProperty(CommonIRI.FOAF + "name");
			Property propertyFoafFirstName = model.createProperty(CommonIRI.FOAF + "firstName");
			Property propertyFoafLastName = model.createProperty(CommonIRI.FOAF + "lastName");
			Property propertySchemeName = model.createProperty(CommonIRI.SCHEMA + "name");
			Property propertySchemeGivenName = model.createProperty(CommonIRI.SCHEMA + "givenName");
			Property propertySchemeFamilyName = model.createProperty(CommonIRI.SCHEMA + "familyName");
			Property propertyRdfsLabel = model.createProperty(RDFS.LABEL);

			if (resourcePerson.hasProperty(propertyFoafName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyFoafName);
				return statement1.getString();
			}
			
			if (resourcePerson.hasProperty(propertySchemeName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeName);
				return statement1.getString();
			} 
			
			if (resourcePerson.hasProperty(propertyFoafFirstName)
					&& resourcePerson.hasProperty(propertyFoafLastName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyFoafFirstName);
				statement2 = (Statement) resourcePerson.getProperty(propertyFoafLastName);
				return statement1.getString() + " " + statement2.getString();
			}
			
			if (resourcePerson.hasProperty(propertySchemeGivenName)
					&& resourcePerson.hasProperty(propertySchemeFamilyName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeGivenName);
				statement2 = (Statement) resourcePerson.getProperty(propertySchemeFamilyName);
				return statement1.getString() + " " + statement2.getString();
			}
			
			if (resourcePerson.hasProperty(propertyRdfsLabel)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyRdfsLabel);
				return statement1.getString();
			}
		}
		logger.warn("[PersonImpl.name] argument person is not a instance of Resource");
		return null;
	}

	public String birth(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;

		if (person == null || model == null) {
			logger.warn("[PersonImpl.birth] argument person or model is null");
			return null;
		}

		if (person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyDboBirth = model.createProperty(CommonIRI.DBO + "birthDate");
			Property propertyVcardBirth = model.createProperty(VCARD.BDAY.getURI());
			Property propertySchemeBirth = model.createProperty(CommonIRI.SCHEMA + "birthDate");
			
			if (resourcePerson.hasProperty(propertyDboBirth)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyDboBirth);
				return statement1.getString();
			}
			
			if (resourcePerson.hasProperty(propertyVcardBirth)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyVcardBirth);
				return statement1.getString();
			}
			
			if (resourcePerson.hasProperty(propertySchemeBirth)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeBirth);
				return statement1.getString();
			}
		}
		logger.warn("[PersonImpl.birth] argument person is not a instance of Resource");
		return null;
	}

	public String death(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;

		if (person == null || model == null) {
			logger.warn("[PersonImpl.death] argument person or model is null");
			return null;
		}
		
		if (person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyDboDeath = model.createProperty(CommonIRI.DBO + "deathDate");
			Property propertySchemeDeath = model.createProperty(CommonIRI.SCHEMA + "deathDate");
			
			if (resourcePerson.hasProperty(propertyDboDeath)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyDboDeath);
				return statement1.getString();
			} 
			
			if (resourcePerson.hasProperty(propertySchemeDeath)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeDeath);
				return statement1.getString();
			} 
		}
		logger.warn("[PersonImpl.death] argument person is not a instance of Resource");
		return null;
	}

	public String picture(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;

		if (person == null || model == null) {
			logger.warn("[PersonImpl.picture] argument person or model is null");
			return null;
		}

		if (person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyFoafImg = model.createProperty(CommonIRI.FOAF + "img");
			Property propertyFoafDepiction = model.createProperty(CommonIRI.FOAF + "depiction");
			Property propertyShemeImg = model.createProperty(CommonIRI.SCHEMA + "image");
			
			if (resourcePerson.hasProperty(propertyFoafImg)) {
				statement1 = resourcePerson.getProperty(propertyFoafImg);
				return statement1.getString();
			}
			
			if (resourcePerson.hasProperty(propertyFoafDepiction)) {
				statement1 = resourcePerson.getProperty(propertyFoafDepiction);
				return statement1.getString();
			}
			
			if (resourcePerson.hasProperty(propertyShemeImg)) {
				statement1 = resourcePerson.getProperty(propertyShemeImg);
				return statement1.getString();
			}
		}
		logger.warn("[PersonImpl.picture] argument person is not a instance of Resource");
		return null;
	}

}
