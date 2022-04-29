/*
 * Josh Bearden
 * 2019/01/14
 * 
 * This program takes arguments pertaining to
 * an element from the periodic table and produces
 * a table detailing the years the element takes
 * to decay to half the original mass and how much
 * mass remains at each year.
 */

public class Isotope {

	public static void main(String[] args) {
		//check for correct number of arguments
		if(args.length != 5) {
			System.out.println("Error: usage is atomicNumber elementName elementSymbol halfLife initMass");
			System.exit(1);
		}
		
		//set values to keep input from the command line
		int atomicNum = Integer.parseInt(args[0]);
		String elementName = args[1];
		String elementSymbol = args[2];
		double halfLife = Double.parseDouble(args[3]);
		double initMass = Double.parseDouble(args[4]);
		
		//format standard output
		System.out.println(elementName + "(" + atomicNum + "-" + elementSymbol + ")");
		System.out.println("Elapsed Years\t Amount");
		System.out.println("======================");
		System.out.println("   -\t\t " + initMass + "g");
		
		//create variable to substitute for power of e
		double cValue = Math.pow(Math.E, -0.693);
		double tempMass = initMass;
		int i = 1;
		while(tempMass > 0.5 * initMass) {
			//compute mass remaining after each iteration
			tempMass = initMass * Math.pow(cValue, (i / halfLife));
			System.out.printf("   %d\t\t %.2fg\n", i, tempMass);
			i++;
		}
	}

}
