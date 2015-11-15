package webSemLB.run;

import webSemLB.ClassExtractor.impl.ClassifierImpl;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassifierImpl classifier = new ClassifierImpl();
		
		classifier.getAllTypes("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi");
		
		if(classifier.isOfType("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#madamic", "http://www.emse.fr/~zimmermann/Teaching/SemWeb/vocab.ttl#Scientist")){
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}
	}

}
