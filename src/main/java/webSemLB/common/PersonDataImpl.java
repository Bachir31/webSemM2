package webSemLB.common;

import org.apache.log4j.Logger;
import org.semarglproject.vocab.RDFS;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

import webSemLB.ClassExtractor.impl.ClassifierImpl;

public class PersonDataImpl implements PersonData {
	
	private static Logger logger = Logger.getLogger(PersonDataImpl.class);
	
	public PersonDataImpl() {
		
	}

	public String name(RDFNode person, Model model) {
		Resource resourcePerson = null;
		Statement statement1 = null;
		Statement statement2 = null;
		
		if(person == null) {
			logger.warn("[PersonImpl.name] argument person is null");
			return null;
		}
		
		if(person.isResource()) {
			resourcePerson = (Resource) person;
			Property propertyFoafName = model.createProperty("http://xmlns.com/foaf/0.1/name");
			Property propertyFoafFirstName = model.createProperty("http://xmlns.com/foaf/0.1/firstName");
			Property propertyFoafLastName = model.createProperty("http://xmlns.com/foaf/0.1/lastName");
			Property propertySchemeName = model.createProperty("http://schema.org/name");
			Property propertySchemeGivenName = model.createProperty("http://schema.org/givenName");
			Property propertySchemeFamilyName = model.createProperty("http://schema.org/familyName");
			Property propertyRdfsLabel = model.createProperty(RDFS.LABEL);
			
			if(resourcePerson.hasProperty(propertyFoafName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyFoafName);
				return statement1.getString();
			} else if(resourcePerson.hasProperty(propertySchemeName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeName);
				return statement1.getString();
			} else if(resourcePerson.hasProperty(propertyFoafFirstName) && resourcePerson.hasProperty(propertyFoafLastName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertyFoafFirstName);
				statement2 = (Statement) resourcePerson.getProperty(propertyFoafLastName);
				return statement1.getString() + " " + statement2.getString();
			} else if(resourcePerson.hasProperty(propertySchemeGivenName) && resourcePerson.hasProperty(propertySchemeFamilyName)) {
				statement1 = (Statement) resourcePerson.getProperty(propertySchemeGivenName);
				statement2 = (Statement) resourcePerson.getProperty(propertySchemeFamilyName);
				return statement1.getString() + " " + statement2.getString();
			} else if(resourcePerson.hasProperty(propertyRdfsLabel)) {
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
		// TODO Auto-generated method stub
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
