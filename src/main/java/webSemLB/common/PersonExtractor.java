package webSemLB.common;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class PersonExtractor extends RDFExtractor {

	public PersonExtractor() {
		model = ModelFactory.createDefaultModel();
	}
	
	@Override
	public RDFNode primaryTopic(String iri) {
		
		if (iri == null) {
			return null;
		}
		
		if (iri.isEmpty()) {
			return null;
		}
		
		if(!model.isEmpty()) {
			model.close();
		}
		
		 
		return null;
	}

}
