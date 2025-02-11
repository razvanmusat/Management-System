package managementSystem;

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

	private static void addNewGuest(Scanner sc, GuestsList list) {
		System.out.println("Insert last name: ");
		String lastName = sc.nextLine();
		System.out.println("Insert first name: ");
		String firstName = sc.nextLine();
		System.out.println("Insert email: ");
		String email = sc.nextLine();
		System.out.println("Insert phone number: ");
		String phoneNumber = sc.nextLine();

		Guest guest = new Guest(lastName, firstName, email, phoneNumber);
		int addResult = list.add(guest);

		if (addResult == -1) {
			System.out.println(guest.fullName() + " is already on the list!");
		} else if (addResult == 0) {
			System.out.println(guest.fullName() + " Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
		} else {
			System.out.println(
					guest.fullName() + " Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "
							+ addResult + " Te vom notifica daca un loc devine disponibil");
		}
	}

	private static void checkGuest(Scanner sc, GuestsList list) {
		class Messages {
			private static void messages() {
				System.out.println("Insert option 1 to search by name");
				System.out.println("Insert option 2 to search by mail");
				System.out.println("Insert option 3 to search by phone number\nYour choice is...");
			}
		}
		Messages.messages();
		int option = sc.nextInt();
		sc.nextLine();

		while (option != 1 && option != 2 && option != 3) {
			System.out.println("Invalid option. Try again!");
			Messages.messages();
			option = sc.nextInt();
			sc.nextLine();
		}

		Guest guest = null;

		switch (option) {
		case 1:
			System.out.println("Insert last name: ");
			String lastName = sc.nextLine();
			System.out.println("Insert first name: ");
			String firstName = sc.nextLine();
			guest = list.search(Guest.formatName(lastName), Guest.formatName(firstName));
			break;
		case 2:
			System.out.println("Insert email: ");
			String email = sc.nextLine();
			guest = list.search(option, email.toLowerCase().trim());
			break;
		case 3:
			System.out.println("Insert phone number: ");
			String phoneNumber = sc.nextLine();
			guest = list.search(option, phoneNumber.trim());
			break;
		default:
			break;
		}
		if (guest != null) {
			System.out.println(guest);
		} else {
			System.out.println("Guest not found!");
		}
	}

	private static void removeGuest(Scanner sc, GuestsList list) {
		// TO DO:
	}

	private static void updateGuest(Scanner sc, GuestsList list) {
		// TO DO:
	}

	private static void searchList(Scanner sc, GuestsList list) {
		// TO DO:

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
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
