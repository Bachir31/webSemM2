package webSemLB.common;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class PersonDataImpl implements PersonData {
	
	public PersonDataImpl() {
		
	}

	public String name(RDFNode person, Model model) {
		
		
		
		if(person == null) {
			return null;
		}
		
		
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
