package webSemLB.common; // Change this to your own package if you want.


import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Model;


public abstract class RDFExtractor {

	/**
	 * The Jena Model that will be extracted from a given IRI
	 */
	Model model;
		
	/**
	 * This method finds the primary topic of an entity given by its IRI.
	 * That is, it finds any object of a triple that starts with the given IRI and has predicate <tt>foaf:primaryTopic</tt> (<tt>http://xmlns.com/foaf/0.1/primaryTopic</tt>).
	 * This method must also initialise the model <tt>m</tt> by dereferencing the input IRI and trying to read RDF in Turtle, RDF/XML, or HTML+RDFa, depending on what is available.
	 * If it is not available, it returns <tt>null</tt>
	 * 
	 * @param iri the IRI of an entity.
	 * @return An RDFNode that corresponds to the primary topic of what is identified by the input IRI.
	 */
	public abstract RDFNode primaryTopic(String iri);
	
}
