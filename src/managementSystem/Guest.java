package managementSystem;

import java.util.Objects;

class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

	public static String formatName(String word) {
		if (word == null || word.isEmpty()) {
			return word;
		}
		word = word.trim();
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}

	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = formatName(lastName).trim();
		this.firstName = formatName(firstName).trim();
		this.email = email.toLowerCase().trim();
		this.phoneNumber = phoneNumber.trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {		
		this.lastName = formatName(lastName).trim();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = formatName(firstName).trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber.trim();
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastName, firstName, email, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Guest other = (Guest) obj;

		return Objects.equals(email, other.email)
				|| Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				|| Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Nume: " + lastName + " " + firstName + ", Email: " + email + ", Telefon: " + phoneNumber;
	}

	public String fullName() {
		return lastName + " " + firstName;
	}
}
