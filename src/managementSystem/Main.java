package managementSystem;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static void showCommands() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi");
		System.out.println("add          - Adauga o noua persoana (inscriere)");
		System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
		System.out.println("remove       - Sterge o persoana existenta din lista");
		System.out.println("update       - Actualizeaza detaliile unei persoane");
		System.out.println("guests       - Lista de persoane care participa la eveniment");
		System.out.println("waitlist     - Persoanele din lista de asteptare");
		System.out.println("available    - Numarul de locuri libere");
		System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
		System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
		System.out.println("subscribe_no - Numarul total de persoane inscrise");
		System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
		System.out.println("save         - Salveaza lista cu invitati");
		System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
		System.out.println("reset        - Sterge informatiile salvate despre invitati");
		System.out.println("quit         - Inchide aplicatia");
	}

	private static boolean validEmail(String email) {
		if (email.length() < 6) {
			return false;
		}
		if (!email.contains(String.valueOf('@'))) {
			return false;
		}
		if (!email.contains(String.valueOf('.'))) {
			return false;
		}
		return true;
	}

	private static boolean validPhoneNumber(String phoneNumber) {
		phoneNumber = phoneNumber.trim();
		if (phoneNumber.length() != 10) {
			return false;
		}
		if (!phoneNumber.matches("[0-9]+")) {
			return false;
		}
		return true;
	}

	private static void addNewGuest(Scanner sc, GuestsList list) {
		System.out.println("Insert last name: ");
		String lastName = sc.nextLine();
		System.out.println("Insert first name: ");
		String firstName = sc.nextLine();
		System.out.println("Insert email: ");
		String email = sc.nextLine();
		while (!validEmail(email)) {
			System.err.println("The email address you provided is invalid. Please try again.");
			email = sc.nextLine();
		}
		System.out.println("Insert phone number: ");
		String phoneNumber = sc.nextLine();
		while (!validPhoneNumber(phoneNumber)) {
			System.err.println("The phone number you provided is invalid. Please try again.");
			phoneNumber = sc.nextLine();
		}
		Guest guest = new Guest(lastName, firstName, email, phoneNumber);
		int addResult = list.add(guest);

		if (addResult == -1) {
			System.out.println(guest.fullName() + " is already on the list!");
		} else if (addResult == 0) {
			System.out.println("Congratulations " + guest.fullName()
					+ "! Your spot at the event is confirmed. We look forward to your presence.");
		} else {
			System.out.println(guest.fullName() + ", you're number " + addResult
					+ " on the waiting list. We'll notify you when a spot opens up.");
		}
	}

	private static Guest guestSearch(Scanner sc, GuestsList list) {
		System.out.println("Insert option 1 to search by name");
		System.out.println("Insert option 2 to search by mail");
		System.out.print("Insert option 3 to search by phone number\nYour choice is... ");
		int option = sc.nextInt();
		sc.nextLine();

		while (option != 1 && option != 2 && option != 3) {
			System.out.println("Invalid option. Try again!");
			option = sc.nextInt();
			sc.nextLine();
		}
		Guest guest = null;

		switch (option) {
		case 1:
			System.out.println("Search by last name: ");
			String lastName = sc.nextLine();
			System.out.println("Search by first name: ");
			String firstName = sc.nextLine();
			guest = list.search(Guest.formatName(lastName).trim(), Guest.formatName(firstName).trim());
			break;
		case 2:
			System.out.println("Search by email: ");
			String email = sc.nextLine();
			while (!validEmail(email)) {
				System.err.println("The email address you provided is invalid. Please try again.");
				email = sc.nextLine();
			}
			guest = list.search(option, email.toLowerCase().trim());
			break;
		case 3:
			System.out.println("Search by phone number: ");
			String phoneNumber = sc.nextLine();
			while (!validPhoneNumber(phoneNumber)) {
				System.err.println("The phone number you provided is invalid. Please try again.");
				phoneNumber = sc.nextLine();
			}
			guest = list.search(option, phoneNumber.trim());
			break;
		default:
			break;
		}
		return guest;

	}

	private static void checkGuest(Scanner sc, GuestsList list) {
		Guest guest = guestSearch(sc, list);
		if (guest != null) {
			System.out.println(guest);
		} else {
			System.out.println("Guest not found!");
		}
	}

	private static void removeGuest(Scanner sc, GuestsList list) {
		Guest guest = guestSearch(sc, list);
		int index = list.getIndexOf(guest);
		int listSize = list.getGuestsList().size();
		boolean removed = list.remove(guest);		

		if (removed) {
			if (listSize == 1) {
				System.out.println(guest.fullName() + " was successfully removed.");
				System.out.println("The guest list is now empty.");
			} else if (index < list.getGuestsCapacity()) {
				System.out.println(guest.fullName() + " was successfully removed.");
				System.out.println(list.getGuestsList().get(list.getGuestsCapacity() - 1).fullName()
						+ " Congratulations! Your spot at the event is confirmed. We look forward to your presence.");
			} else {
				System.out.println(guest.fullName() + " was successfully removed.");
			}
		} else {
			System.out.println("Guest not found for removal.");
		}
	}

	private static void updateGuest(Scanner sc, GuestsList list) {
		Guest guest = guestSearch(sc, list);
		if (guest == null) {
			System.out.println("Guest not found!");
			return;
		}
		System.out.println(
				"Insert option 1 for update name\nInsert option 2 for update mail\nInsert option 3 for update phone number");
		int option = sc.nextInt();
		sc.nextLine();
		if (option == 1) {
			System.out.println("Update last name:");
			guest.setLastName(sc.nextLine());
			System.out.println("Update first name:");
			guest.setFirstName(sc.nextLine());
		} else if (option == 2) {
			System.out.println("Update mail:");
			String email = sc.nextLine();
			while (!validEmail(email)) {
				System.err.println("The email address you provided is invalid. Please try again.");
				email = sc.nextLine();
			}
			guest.setEmail(email);
		} else if (option == 3) {
			System.out.println("Update phone number:");
			String phoneNumber = sc.nextLine();
			while (!validPhoneNumber(phoneNumber)) {
				System.err.println("The phone number you provided is invalid. Please try again.");
				phoneNumber = sc.nextLine();
			}
			guest.setPhoneNumber(phoneNumber);
		} else {
			while (option != 1 || option != 2 || option != 3) {
				System.out.println("Wrong option, try again.");
				option = sc.nextInt();
			}
		}
		System.out.println("Guest updated.");
	}

	private static void searchList(Scanner sc, GuestsList list) {
		System.out.println("Enter search parameters:");
		List<Guest> searchResult = list.partialSearch(sc.nextLine());
		for (Guest result : searchResult) {
			System.out.println(result);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
		while (size == 0) {
			System.out.println("The guest list cannot be empty. Please try again.");
			size = scanner.nextInt();
		}
		scanner.nextLine();

		GuestsList list = new GuestsList(size);

		boolean running = true;
		while (running) {
			String command = scanner.nextLine();

			switch (command) {
			case "help":
				showCommands();
				break;
			case "add":
				addNewGuest(scanner, list);
				break;
			case "check":
				checkGuest(scanner, list);
				break;
			case "remove":
				removeGuest(scanner, list);
				break;
			case "update":
				updateGuest(scanner, list);
				break;
			case "guests":
				list.showGuestsList();
				break;
			case "waitlist":
				list.showWaitingList();
				break;
			case "available":
				System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
				break;
			case "guests_no":
				System.out.println("Numarul de participanti: " + list.numberOfGuests());
				break;
			case "waitlist_no":
				System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
				break;
			case "subscribe_no":
				System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
				break;
			case "search":
				searchList(scanner, list);
				break;
			case "quit":
				System.out.println("Aplicatia se inchide...");
				scanner.close();
				running = false;
				break;
			default:
				System.out.println("Comanda introdusa nu este valida.");
				System.out.println("Incercati inca o data.");

			}
		}
	}
}
