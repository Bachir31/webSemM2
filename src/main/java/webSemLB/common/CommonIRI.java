package webSemLB.common;

public enum CommonIRI {

	FOAF("http://xmlns.com/foaf/0.1/"),
	SCHEMA("http://schema.org/"),
	DBO("http://dbpedia.org/ontology/"),
	GEO("http://www.w3.org/2003/01/geo/"),
	DBP("http://dbpedia.org/property/");
	
	private String iri="";
	
	private CommonIRI(String iri) {
		this.iri = iri;
	}

	public String toString(){
		return iri;
	}
}
