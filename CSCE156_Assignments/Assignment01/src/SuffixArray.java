import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Josh Bearden
 * 2018/01/14
 * 
 * This program takes a word as input and
 * produces a list of suffixes from that word.
 */

public class SuffixArray {

	public static void main(String[] args) {
		//check for correct number of arguments
		if(args.length != 1) {
			System.out.println("Error: usage is suffixString");
			System.exit(1);
		}
		
		String suffixString = args[0];
		List<String> suffixes = new ArrayList<String>();
		String suffixSubString = suffixString;
		
		//fill suffix list with items
		for(int i=0; i<suffixString.length(); i++) {
			suffixSubString = suffixString.substring(i);
			suffixes.add(suffixSubString);
		}
		
		Collections.sort(suffixes);
		
		System.out.println("Sorted list (" + suffixString + ")");
		System.out.println("====================");
		for(String s : suffixes) {
			System.out.println(s);
		}
	}

}
