package managementSystem;

import java.util.Objects;

class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		return "Nume: " + firstName + " " + lastName + ", Email: " + email + ", Telefon: " + phoneNumber;
	}

	public String fullName() {
		return firstName + " " + lastName;
	}
}
