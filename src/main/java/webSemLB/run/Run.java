package webSemLB.run;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import webSemLB.ClassExtractor.impl.ClassifierImpl;
import webSemLB.common.PersonData;
import webSemLB.common.PersonDataImpl;
import webSemLB.common.PersonExtractor;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ClassifierImpl classifier = new ClassifierImpl();
		
		/* test de getAllTypes */
		//classifier.getAllTypes("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi");
		
		/* test  de isOfType */
		/*if(classifier.isOfType("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#madamic", "http://www.emse.fr/~zimmermann/Teaching/SemWeb/other.ttl#Human")){
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}*/
		
		PersonExtractor personExtractor =  new PersonExtractor();
		
		personExtractor.showPersonData("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html");
		/*Model m = ModelFactory.createDefaultModel();
		m.read("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi","RDFA");
		PersonData person = new PersonDataImpl();
		RDFNode r = m.createResource("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#eric");
		String s1 = person.name(r, m);
		String s2 = person.birth(r, m);
		
		if(s1 != null && s2 != null) {
			System.out.println(s1 + " " + s2);
		}*/
	}

}
