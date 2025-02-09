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
		if (guestsList.size() < guestsCapacity) {
			guestsList.add(g);
			return 0;
		} else {
			guestsList.add(g);
			return guestsList.size() - guestsCapacity;
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

	public Guest search(String firstName, String lastName) {
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

	/**
	 * Search for a guest based on email or phone number. Return the first result.
	 *
	 * @param opt   option to use for searching: 2 for email, 3 for phone number
	 * @param match what is searched for
	 * @return the guest if found, null if not
	 */
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
				if (guest.getPhoneNumber() != null && guest.getPhoneNumber().equalsIgnoreCase(match)) {
					return guest;
				}
			}
		}
		return null;
	}

	/**
	 * Remove a guest based on first and last name. Remove the first result.
	 *
	 * @param firstName first name of the guest
	 * @param lastName  last name of the guest
	 * @return true if removed, false if not
	 */
	public boolean remove(String firstName, String lastName) {
		// TO DO:
	}

	/**
	 * Remove a guest based on email or phone number. Remove the first result.
	 *
	 * @param opt   option to use for searching: 2 for email, 3 for phone number
	 * @param match the match we are searching for
	 * @return true if removed, false if not
	 */
	public boolean remove(int opt, String match) {
		// TO DO:
	}

	// Show the list of guests.
	public void showGuestsList() {
		// TO DO:
	}

	// Show the people on the waiting list.
	public void showWaitingList() {
		// TO DO:
	}

	/**
	 * Show how many free spots are left.
	 *
	 * @return the number of spots left for guests
	 */
	public int numberOfAvailableSpots() {
		// TO DO:
	}

	/**
	 * Show how many guests there are.
	 *
	 * @return the number of guests
	 */
	public int numberOfGuests() {
		// TO DO:
	}

	/**
	 * Show how many people are on the waiting list.
	 *
	 * @return number of people on the waiting list
	 */
	public int numberOfPeopleWaiting() {
		// TO DO:
	}

	/**
	 * Show how many people there are in total, including guests.
	 *
	 * @return how many people there are in total
	 */
	public int numberOfPeopleTotal() {
		// TO DO:
	}

	/**
	 * Find all people based on a partial value search.
	 *
	 * @param match the match we are looking for
	 * @return a list of people matching the criteria
	 */
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
		// TO DO:
	}
}
