import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    // Represents the maximum number of guests per room
    public static final int MAX_GUESTS_PER_ROOM = 4;
    // List of hotel rooms
    private List<Room> rooms;
    // Waiting queue for groups of guests
    private BlockingQueue<List<Guest>> waitingQueue;
    static Lock lock = new ReentrantLock(); // Locks

    public Hotel(int numRooms) {
        // Initialize the list of rooms
        rooms = new ArrayList<>();
        // Create the rooms and add them to the list
        for (int i = 1; i <= numRooms; i++) {
            Room room = new Room(i);
            rooms.add(room);
            System.out.println("Room number " + i + " has been created.");
        }
        // Initialize the waiting queue with capacity for 10 groups of guests
        waitingQueue = new ArrayBlockingQueue<>(10);
    }

    public synchronized Room allocateRoom(List<Guest> guestGroup) throws InterruptedException {
        lock.lock();
        try {
            // Method to allocate a room for a group of guests
            for (Room room : rooms) {
                // Check if the room is vacant
                if (room.isVacant()) {
                    // Add the group of guests to the room
                    room.addGuests(guestGroup);
                    return room;
                }
            }
            // If all rooms are occupied, add the group to the waiting queue
            System.out.println("All rooms are occupied. Group " + guestGroup.get(0).getGroupNumber() +
                    " will be placed in the waiting queue.");
            waitingQueue.put(guestGroup);
            return null; // or throw an exception indicating that there are no available rooms
        } finally {
            lock.unlock();
        }
    }

    // Method to release a room occupied by a group of guests
    public synchronized void releaseRoom(List<Guest> guestGroup) {
        lock.lock();
        try {
            for (Room room : rooms) {
                // Check if the room contains all guests from the group
                if (room.containsAllGuests(guestGroup)) {
                    // Remove the group of guests from the room
                    room.removeGuests(guestGroup);
                    // If the room becomes vacant, check if there's a group waiting to occupy it
                    if (room.isVacant()) {
                        List<Guest> nextGroup = waitingQueue.poll();
                        if (nextGroup != null) {
                            try {
                                allocateRoom(nextGroup); // Allocate the room to the next group in the waiting queue
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Method to get the list of hotel rooms
    public synchronized List<Room> getRooms() {
        return rooms;
    }

    // Method to get the next group from the waiting queue
    public synchronized List<Guest> getNextGroupFromWaitingQueue() {
        return waitingQueue.poll();
    }
}
