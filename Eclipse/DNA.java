package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Small application to count the number of occurrences of a subsequence (given
 * as a command line argument) in the hardcoded nucleotide file.
 * 
 * @author Thomas Hillebrandt & Joshua Bearden
 *
 */
public class DNA {

	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("Please provide a subsequence to search for.");
			System.exit(1);
		}

		String fileName = "data/H1N1nucleotide.txt";
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String dna = "";

		while (s.hasNext()) {
			dna += s.next().trim();
		}
		s.close();

		String subsequence = args[0].toUpperCase();

		int count = 0;

		for (int p = 0; p < dna.length() - subsequence.length(); p++) {
			if (dna.toUpperCase().substring(p, p + subsequence.length()).equals(subsequence)) {
				count++;
			}
		}

		System.out.println(subsequence + " appears " + count + " times");
	}
}
