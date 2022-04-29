import java.util.Scanner;

/*
 * Josh Bearden
 * 2018/01/14
 * 
 * This program processes a file whose path/name is
 * provided through the command line and produces two
 * reports: a sorted list of publishers (sorted 
 * alphabetically) along with a count of the number
 * of games they have published; and a list of games
 * (sorted alphabetically) along with a count of the
 * number of platforms on which they are available.
 */


public class GameReport {

	public static void main(String[] args) {
		//check for correct number of arguments
		if(args.length != 1) {
			System.out.println("Error: usage is file/pathName");
			System.exit(1);
		}
		
		String filePath = args[0];
		Scanner fp = new Scanner(new File("data/" + filePath));
		//HashMap for games

	}

}
