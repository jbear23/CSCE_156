package unl.cse.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LibraryDemo {

	private final static Scanner STDIN = new Scanner(System.in);

	private final Library lib;

	public LibraryDemo() {
		this.lib = new Library();
		loadFile();
	}

	private void loadFile() {
		Scanner s = null;
		try {
			s = new Scanner(new File("data/books.txt"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		while (s.hasNext()) {
			String line = s.nextLine();
			String tokens[] = line.split(",");
			String title = tokens[0];
			String auth[] = tokens[1].split(" ");
			Author author = new Author(auth[0], auth[1]);
			String isbn = tokens[2];
			String publishDate = tokens[3];
			Book b = new Book(title, author, isbn, publishDate);
			lib.addBook(b);
		}
		s.close();
	}

	/**
	 * Method that searches for a book.
	 */
	private void searchBookInterface() {
		System.out
				.println("Please enter a Search Option:\n (1) Search By Title (2) Search By Author (3) Keyword Search");
		int userChoice = STDIN.nextInt();
		STDIN.nextLine();
		System.out.print("Enter your search term: ");
		String query = STDIN.nextLine();

		switch (userChoice) {
		case 1:
			printBooks(this.lib.titleSearch(query));
			break;
		case 2:
			printBooks(this.lib.authorSearch(query));
			break;
		case 3:
			printBooks(this.lib.keywordSearch(query));
			break;
		default:
			break;
		}
		return;
	}

	private void printBooks(List<Book> books) {

		System.out.print("\n");
		System.out.println(
				String.format("%-50s %-20s %-15s %-12s %-5s", "TITLE", "AUTHOR", "ISBN", "PUBL. DATE", "BOOK AGE"));
		List<String> output = new ArrayList<>();
		for (Book b : books) {
			String line = String.format("%-50s %-20s %-15s %-12s %-5d", b.getTitle(), b.getAuthor().getSetName(),
					b.getISBN(), b.getPublishDate(), b.getBookAge());
			output.add(line);
		}

		Collections.sort(output, byTitle);

		for (String line : output) {
			System.out.println(line);
		}

		System.out.print("\n\n");
	}

	static Comparator<String> byTitle = new Comparator<String>() {
		public int compare(String a, String b) {
			if (a.equals(b)) {
				return 0;
			}
			String set[] = { a, b };
			Arrays.sort(set);

			if (set[0].equals(b)) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	/**
	 * Method that adds a book.
	 */
	private void addBookInterface() {
		// change this function
		System.out.println("Please enter the details of the book you want to add to the library");
		System.out.println("Enter the title of the book: ");
		String title = input();
		System.out.println("Enter the first name of the author: ");
		String firstName = input();
		System.out.println("Enter the last name of the author: ");
		String lastName = input();
		System.out.println("Enter the ISBN of the book: ");
		String isbn = input();
		System.out.println("Enter the publication date (YYYY-MM-DD)");
		String publishDate = input();
		Author author = new Author(firstName, lastName);
		Book b = new Book(title, author, isbn, publishDate);
		this.lib.addBook(b);
		return;
	}

	private String input() {
		String inp = null;
		while (inp == null || inp.equals("")) {
			inp = STDIN.nextLine();
		}
		return inp;
	}

	/**
	 * Method that acts as the interface to the library software.
	 */
	public void libraryInterface() {
		int userChoice = 0;

		while (userChoice != 4) {
			System.out.println("Welcome to the Arcadia Library.");
			System.out.print("Please enter a choice: (1) Add a book, (2) Find a book, (3) Print Collection (4) Exit:");
			userChoice = STDIN.nextInt();

			switch (userChoice) {
			case 1:
				this.addBookInterface();
				break;
			case 2:
				this.searchBookInterface();
				break;
			case 3:
				printBooks(this.lib.getCollection());
				break;
			default:
				break;
			}

		}
		STDIN.close();
		System.out.println("Thank You for Using Arcadia Library !");

		return;
	}

	/**
	 * Main method
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		LibraryDemo demo = new LibraryDemo();
		demo.libraryInterface();
	}
}
