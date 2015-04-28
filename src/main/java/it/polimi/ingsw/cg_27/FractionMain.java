package it.polimi.ingsw.cg_27;

public class FractionMain {
	public static void main(String[] args) {
		Fraction f=new Fraction(2,4);
		Fraction g=new Fraction(5);
		int a=2;
		Fraction[] myfractions=new Fraction[5];
		//add the fractions and store the result
		Fraction sum=f.add(g);
		myfractions[0]=f;
		myfractions[1]=g;
		myfractions[4]=sum;
		// Print the result
		System.out.println(myfractions[4].toString());
	}
}
