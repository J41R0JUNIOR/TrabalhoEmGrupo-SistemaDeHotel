import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Class representing a room in a hotel
public class Room {
    private int number;
    // List of guests staying in the room
    private List<Guest> guests;
    // Indicates whether the room is ready for cleaning
    private boolean readyForCleaning = false;
    private Lock lock; // Locks

    // Constructor for the room
    public Room(int number) {
        this.number = number;
        this.guests = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    // Method to return the room key at the reception
    public void returnKey() {
        // Prints a message indicating that the guests returned the key at the reception
        System.out.println("\n-------------------------------------------------\nGuests of room " + number
                + " returned the key at the reception and went out.");
        readyForCleaning = true;
        if (readyForCleaning == false) {
            System.out.println("\n-------------------------------------------------\nGuests of room " + number
                    + " took the key at the reception and came back to the room.");
        }
    }

    public void takeKey() {
        if (readyForCleaning == false) {
            System.out.println("\n-------------------------------------------------\nGuests of room " + number
                    + " took the key at the reception and came back to the room.");
        }
    }

    // Method to check if the room is ready for cleaning
    public boolean isReadyForCleaning() {
        return readyForCleaning;
    }

    // Method to clean the room
    public void clean() {
        // Prints a message indicating that the room has been cleaned
        System.out.println("\n-------------------------------------------------\nRoom " + number + " has been cleaned.");
        readyForCleaning = false;
    }

    // Method to get the room number
    public int getNumber() {
        return number;
    }

    // Method to get the list of guests in the room
    public List<Guest> getGuests() {
        return guests;
    }

    // Method to check if the room is vacant
    public boolean isVacant() {
        return guests.isEmpty();
    }

    // Method to add new guests to the room
    public void addGuests(List<Guest> newGuests) {
        guests.addAll(newGuests);
    }

    // Method to remove guests from the room
    public void removeGuests(List<Guest> guestsToRemove) {
        guests.removeAll(guestsToRemove);
    }

    // Method to check if the room contains all the specified guests
    public boolean containsAllGuests(List<Guest> guests) {
        List<Guest> roomGuests = getGuests();
        return roomGuests.containsAll(guests);
    }

    // Method to get the Lock
    public Lock getLock() {
        return lock;
    }
}
