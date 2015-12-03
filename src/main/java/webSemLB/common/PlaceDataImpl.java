package webSemLB.common;

import org.apache.log4j.Logger;
import org.semarglproject.vocab.RDFS;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class PlaceDataImpl implements PlaceData{
	
	private static Logger logger = Logger.getLogger(PlaceDataImpl.class);

	public PlaceDataImpl() {
		
	}

	public String name(RDFNode place, Model model) {
		
		Resource resourcePlace = null;
		Statement statement1 = null;
		
		if (place == null || model == null) {
			logger.warn("[PlaceDataImpl.name] argument place or model is null");
			return null;
		}
		
		
		if(place.isResource()){
			resourcePlace = (Resource) place;
			Property propertyFoafName = model.createProperty(CommonIRI.FOAF + "name");
			Property propertySchemeName = model.createProperty(CommonIRI.SCHEMA + "name");
			Property propertyRdfsLabel = model.createProperty(RDFS.LABEL);
			
			if (resourcePlace.hasProperty(propertyFoafName)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyFoafName);
				return statement1.getString();
			}
			
			if (resourcePlace.hasProperty(propertySchemeName)) {
				statement1 = (Statement) resourcePlace.getProperty(propertySchemeName);
				return statement1.getString();
			} 
			
			if (resourcePlace.hasProperty(propertyRdfsLabel)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyRdfsLabel);
				return statement1.getString();
			}
		}
		
		logger.warn("[PlaceDataImpl.name] argument place is not a instance of Resource");
		return null;
	}

	public String latitude(RDFNode place, Model model) {
		Resource resourcePlace = null;
		Statement statement1 = null;
		
		resourcePlace = (Resource) place;
		
		if (place == null || model == null) {
			logger.warn("[PlaceDataImpl.name] argument place or model is null");
			return null;
		}
		
		if(place.isResource()){
			resourcePlace = (Resource) place;
			Property propertyGeoLatitude = model.createProperty(CommonIRI.GEO + "wgs84_pos#lat");
			Property propertyDbpLatitude = model.createProperty(CommonIRI.DBP + "latitude");
			
			if (resourcePlace.hasProperty(propertyGeoLatitude)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyGeoLatitude);
				return statement1.getString();
			}
			
			if (resourcePlace.hasProperty(propertyDbpLatitude)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyDbpLatitude);
				return statement1.getString();
			}
			
		}
		
		logger.warn("[PlaceDataImpl.name] argument place is not a instance of Resource");
		return null;
	}

	public String longitude(RDFNode place, Model model) {
		Resource resourcePlace = null;
		Statement statement1 = null;
		
		resourcePlace = (Resource) place;
		
		if (place == null || model == null) {
			logger.warn("[PlaceDataImpl.name] argument place or model is null");
			return null;
		}
		
		if(place.isResource()){
			resourcePlace = (Resource) place;
			Property propertyGeoLongitude = model.createProperty(CommonIRI.GEO + "wgs84_pos#long");
			Property propertyDbpLongitude = model.createProperty(CommonIRI.DBP + "longitude");
			
			if (resourcePlace.hasProperty(propertyGeoLongitude)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyGeoLongitude);
				return statement1.getString();
			}
			
			if (resourcePlace.hasProperty(propertyDbpLongitude)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyDbpLongitude);
				return statement1.getString();
			}
			
		}
		
		logger.warn("[PlaceDataImpl.name] argument place is not a instance of Resource");
		return null;
	}

	public String picture(RDFNode place, Model model) {
		Resource resourcePlace = null;
		Statement statement1 = null;
		
		resourcePlace = (Resource) place;
		
		if (place == null || model == null) {
			logger.warn("[PlaceDataImpl.name] argument place or model is null");
			return null;
		}
		
		if(place.isResource()){
			resourcePlace = (Resource) place;
			Property propertyFoafDepiction = model.createProperty(CommonIRI.FOAF + "depiction");
			Property propertySchemaImage = model.createProperty(CommonIRI.SCHEMA + "image");
			
			if (resourcePlace.hasProperty(propertyFoafDepiction)) {
				statement1 = (Statement) resourcePlace.getProperty(propertyFoafDepiction);
				return statement1.getString();
			}
			
			if (resourcePlace.hasProperty(propertySchemaImage)) {
				statement1 = (Statement) resourcePlace.getProperty(propertySchemaImage);
				return statement1.getString();
			}
			
		}

		logger.warn("[PlaceDataImpl.name] argument place is not a instance of Resource");
		return null;
	}
	
	
}
