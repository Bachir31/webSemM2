package webSemLB.common;

public enum CommonIRI {

	FOAF("http://xmlns.com/foaf/0.1/"),
	SCHEMA("http://schema.org/"),
	DBO("http://dbpedia.org/ontology/");
	
	private String iri="";
	
	private CommonIRI(String iri) {
		this.iri = iri;
	}

	public String toString(){
		return iri;
	}
}
