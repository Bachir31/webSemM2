package webSemLB.common;

public enum RdfFormat {
	
	TURTLE("TURTLE"),
	RDFA("RDFA"),
	RDFXML("RDF/XML");
	
	
	private String name="";
	
	private RdfFormat(String name) {
		this.name = name;
	}

	public String toString(){
		return name;
	}
}
