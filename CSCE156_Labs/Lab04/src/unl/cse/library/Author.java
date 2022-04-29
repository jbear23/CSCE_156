package unl.cse.library;

public class Author {
	private String firstName;
	private String lastName;

	/**
	 * Author data
	 * 
	 * @param firstName - String
	 * @param lastName  - String
	 */
	public Author(String firstName, String lastName) {
		Author.isValid(firstName);
		Author.isValid(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Set first name
	 * 
	 * @param firstName - String
	 */
	public void setFirstName(String firstName) {
		Author.isValid(firstName);
		this.firstName = firstName;
	}

	/**
	 * Set last name
	 * 
	 * @param lastName - String
	 */
	public void setLastName(String lastName) {
		Author.isValid(lastName);
		this.lastName = lastName;
	}

	/**
	 * Returns first name
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns last name
	 * 
	 * @return String
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Returns name "Last, Fist"
	 * 
	 * @return String
	 */
	public String getSetName() {
		return String.format("%s, %s", this.lastName, this.firstName);
	}

	/**
	 * Check a string for numbers or <code>null</code>
	 * 
	 * @param check
	 */
	private static void isValid(String check) {
		if (check == null || check.matches(".*\\d+.*")) {
			throw new RuntimeException("Invalid name - name can't be null or contain numbers.");
		}
	}
}
