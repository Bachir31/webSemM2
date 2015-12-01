package webSemLB.common; // Change this to your own package if you want.

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

public interface PlaceData {

	/**
	 * This method finds the name of a place identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>foaf:name</tt> (<tt>http://xmlns.com/foaf/0.1/name</tt>) or <tt>schema:name</tt> (<tt>http://schema.org/name</tt>) or, if not available, <tt>rdfs:label</tt>. If none are available, it returns <tt>null</tt>
	 * 
	 * @param place the RDFNode that identifies the place
	 * @param model the Jena <tt>Model</tt> from which the name is extracted
	 * @return The name of the place.
	 */
	public String name(RDFNode place, Model model);

	/**
	 * This method finds the latitude of a place identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>geo:lat</tt> (<tt>http://www.w3.org/2003/01/geo/wgs84_pos#lat</tt>) or <tt>dbp:latitude</tt> (<tt>http://dbpedia.org/property/latitude</tt>).  If it is not available, it returns <tt>null</tt>.
	 * 
	 * @param place the RDFNode that identifies the place
	 * @param model the Jena <tt>Model</tt> from which the latitude is extracted
	 * @return The latitude of the place, as a <tt>String</tt> representing its decimal value.
	 */
	public String latitude(RDFNode place, Model model);

	/**
	 * This method finds the longitude of a place identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>geo:long</tt> (<tt>http://www.w3.org/2003/01/geo/wgs84_pos#long</tt>) or <tt>dbp:longitude</tt> (<tt>http://dbpedia.org/property/longitude</tt>).  If it is not available, it returns <tt>null</tt>.
	 * 
	 * @param place the RDFNode that identifies the place
	 * @param model the Jena <tt>Model</tt> from which the longitude is extracted
	 * @return The longitude of the place, as a <tt>String</tt> representing its decimal value.
	 */
	public String longitude(RDFNode place, Model model);
	
	/**
	 * This method finds a picture of a place identified by the input RDFNode, from the given Jena model.
	 * For this, it finds the value of the property <tt>foaf:depiction</tt> (<tt>http://xmlns.com/foaf/0.1/depiction</tt>) or, if not available, the property <tt>schema:image</tt> (<tt>http://schema.org/image</tt>). If it is not available, it returns <tt>null</tt>.
	 * 
	 * @param place the RDFNode that identifies the place
	 * @param model the Jena <tt>Model</tt> from which the picture is extracted
	 * @return The URL of the place's picture.
	 */
	public String picture(RDFNode place, Model model);
}
