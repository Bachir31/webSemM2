package webSemLB.common; // Change this to your own package if you want.

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

public interface PersonData {

	/**
	 * This method finds the name of a person identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>foaf:name</tt> (<tt>http://xmlns.com/foaf/0.1/name</tt>) or, if not available, <tt>schema:name</tt> (<tt>http://schema.org/name</tt>). If it is still not available, it concatenates the values of properties <tt>foaf:firstName</tt> and <tt>foaf:lastName</tt>, or <tt>schema:givenName</tt> and <tt>schema:familyName</tt>. If none of them are available, it uses <tt>rdfs:label</tt>. If it is still not available, it returns <tt>null</tt>
	 * 
	 * @param person the <tt>RDFNode</tt> that identifies the person.
	 * @param model the Jena <tt>Model</tt> from which the name is extracted.
	 * @return The name of the person.
	 */
	public String name(RDFNode person, Model model);

	/**
	 * This method finds the birth date of a person identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>dbo:birthDate</tt> (<tt>http://dbpedia.org/ontology/birthDate</tt>) or, if not available, the property <tt>vcard:bday</tt> (<tt>http://www.w3.org/2006/vcard/ns#bday</tt>) or, the property <tt>schema:birthDate</tt> (<tt>http://schema.org/birthDate</tt>).  If it is not available, it returns <tt>null</tt>.
	 * 
	 * @param person the <tt>RDFNode</tt> that identifies the person.
	 * @param model the Jena <tt>Model</tt> from which the date is extracted.
	 * @return The birth date of the person.
	 */
	public String birth(RDFNode person, Model model);

	/**
	 * This method finds the death date of a person identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>dbo:deathDate</tt> (<tt>http://dbpedia.org/ontology/deathDate</tt>) or, if not available, the property  <tt>schema:deathDate</tt> (<tt>http://schema.org/deathDate</tt>).  If it is not available, it returns <tt>null</tt>.
	 * 
	 * @param person the <tt>RDFNode</tt> that identifies the person.
	 * @param model the Jena <tt>Model</tt> from which the date is extracted.
	 * @return The death date of the person.
	 */
	public String death(RDFNode person, Model model);
	
	/**
	 * This method finds a picture of a person identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>foaf:img</tt> (<tt>http://xmlns.com/foaf/0.1/img</tt>) or, if not available, the property <tt>foaf:depiction</tt>, or again <tt>schema:image</tt> (<tt>http://schema.org/image</tt>). If it is not available, it returns <tt>null</tt>.
	 * 
	 * @param person the <tt>RDFNode</tt> that identifies the person.
	 * @param model the Jena <tt>Model</tt> from which the picture is extracted.
	 * @return The URL of the person's picture.
	 */
	public String picture(RDFNode person, Model model);
}
