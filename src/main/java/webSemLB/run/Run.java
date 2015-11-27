package webSemLB.run;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import webSemLB.ClassExtractor.impl.ClassifierImpl;
import webSemLB.common.PersonData;
import webSemLB.common.PersonDataImpl;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassifierImpl classifier = new ClassifierImpl();
		
		classifier.getAllTypes("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi");
		
		if(classifier.isOfType("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#madamic", "http://www.emse.fr/~zimmermann/Teaching/SemWeb/other.ttl#Human")){
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}
		
		/*
		Model m = ModelFactory.createDefaultModel();
		m.read("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi","RDFA");
		PersonData person = new PersonDataImpl();
		RDFNode r = m.createResource("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#eric");
		String s = person.name(r, m);
		
		if(s != null) {
			System.out.println(s);
		}*/
	}

}
