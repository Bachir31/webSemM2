package webSemLB.run;

import webSemLB.ClassExtractor.impl.ClassifierImpl;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassifierImpl classifier = new ClassifierImpl();
		
		classifier.getAllTypes("http://www.emse.fr/~zimmermann/Teaching/SemWeb/w3cstaff.html#shadi");
	}

}
