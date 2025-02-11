package managementSystem;

import java.util.ArrayList;
import java.util.List;

class GuestsList {

	private ArrayList<Guest> guestsList;
	private int guestsCapacity;

	public GuestsList(int guestsCapacity) {
		this.guestsList = new ArrayList<Guest>();
		this.guestsCapacity = guestsCapacity;
	}

	public int add(Guest g) {
		if (isOnTheListAlready(g)) {
			return -1;
		}
		guestsList.add(g);

		if (guestsList.indexOf(g) < guestsCapacity) {
			return 0;
		} else {
			return guestsList.indexOf(g) - guestsCapacity + 1;
		}
	}

	private boolean isOnTheListAlready(Guest g) {
		for (Guest guest : guestsList) {
			if (g.equals(guest)) {
				return true;
			}
		}
		return false;
	}

	public Guest search(String lastName, String firstName) {
		if (firstName == null || lastName == null) {
			return null;
		}

		for (Guest guest : guestsList) {
			if (firstName.equals(guest.getFirstName()) && lastName.equals(guest.getLastName())) {
				return guest;
			}
		}
		return null;
	}

	public Guest search(int opt, String match) {
		if (match == null) {
			return null;
		}

		if (opt == 2) {
			for (Guest guest : guestsList) {
				if (guest.getEmail() != null && guest.getEmail().equalsIgnoreCase(match)) {
					return guest;
				}
			}
		} else if (opt == 3) {
			for (Guest guest : guestsList) {
				if (guest.getPhoneNumber() != null && guest.getPhoneNumber().equals(match)) {
					return guest;
				}
			}
		}
		return null;
	}

	public boolean remove(String firstName, String lastName) {
		Guest guestToDelete = search(firstName, lastName);
		if (guestToDelete == null) {
			return false;
		}
		return guestsList.remove(guestToDelete);
	}

	public boolean remove(int opt, String match) {
		Guest guestToDelete = search(opt, match);
		if (guestToDelete == null) {
			return false;
		}

		return guestsList.remove(guestToDelete);
	}

	public void showGuestsList() {
		if (guestsList.isEmpty()) {
			System.out.println("Guest List is empty.");
		}
		int guestsIndex = Math.min(guestsCapacity, guestsList.size());

		for (int i = 0; i < guestsIndex; i++) {
			System.out.println(guestsList.get(i));
		}
	}

	// Show the people on the waiting list.
	public void showWaitingList() {
		if (guestsList.size() <= guestsCapacity) {
			System.out.println("Waiting List is empty.");
			return;
		}
		for (int i = guestsCapacity; i < guestsList.size(); i++) {
			System.out.println(guestsList.get(i));
		}
	}

	public int numberOfAvailableSpots() {
		return Math.max(0, guestsCapacity - guestsList.size());
	}

	public int numberOfGuests() {
		return Math.min(guestsCapacity, guestsList.size());
	}

	public int numberOfPeopleWaiting() {
		return Math.max(0, guestsList.size() - guestsCapacity);
	}

	public int numberOfPeopleTotal() {
		return guestsList.size();
	}

	public List<Guest> partialSearch(String match) {
		List<Guest> foundGuests = new ArrayList<Guest>();

		if (match == null) {
			return foundGuests;
		}
		String lowerMatch = match.toLowerCase();
		for (Guest guest : guestsList) {
			if (guest.getFirstName().toLowerCase().contains(lowerMatch)
					|| guest.getLastName().toLowerCase().contains(lowerMatch)
					|| guest.getEmail().toLowerCase().contains(lowerMatch) || guest.getPhoneNumber().contains(match)) {
				foundGuests.add(guest);
			}
		}
		return foundGuests;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
