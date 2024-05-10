import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Class representing a receptionist in a hotel
public class Receptionist extends Thread {
    private String name;
    // Hotel where the receptionist works
    private Hotel hotel;
    // List of guests the receptionist is attending to
    private List<Guest> guestGroup;
    // Waiting queue for guest groups
    private BlockingQueue<List<Guest>> waitingQueue;
    // Map to track the number of room rental attempts per group
    private Map<Integer, Integer> rentalAttempts = new HashMap<>();
    static Lock lock = new ReentrantLock(); // Locks

    // Receptionist constructor
    public Receptionist(String name, Hotel hotel, List<Guest> guestGroup,
                        BlockingQueue<List<Guest>> waitingQueue) {
        this.name = name;
        this.hotel = hotel;
        this.guestGroup = guestGroup;
        this.waitingQueue = waitingQueue;
    }

    // Overridden method from the Thread class, defining the receptionist's behavior
    @Override
    public void run() {
        lock.lock();
        try {
            try {
                // Get the number of members in the guest group
                int numMembers = guestGroup.size();
                System.out.println("" + name
                        + " is checking in the guest group "
                        + guestGroup.get(0).getGroupNumber() +
                        ", which has " + numMembers + " members.");

                // Check if the number of members is greater than the maximum number of members per room
                if (numMembers > Hotel.MAX_GUESTS_PER_ROOM) {
                    System.out.println("The group "
                            + guestGroup.get(0).getGroupNumber()
                            + " is too large for a single room. Multiple rooms will be allocated.");

                    // Divide the group into subgroups that can fit in individual rooms
                    List<List<Guest>> subgroups = divideGroupIntoSubgroups(guestGroup);

                    // Allocate each subgroup to a room
                    for (List<Guest> subgroup : subgroups) {
                        allocateSubgroup(subgroup);
                    }
                } else {
                    // If the number of members does not exceed the maximum number of members per room,
                    // allocate the entire group to a single room
                    allocateSubgroup(guestGroup);
                }

                hotel.releaseRoom(guestGroup); // Release the room when the group leaves
                if (!waitingQueue.isEmpty()) {
                    List<Guest> nextGroup = waitingQueue.poll();
                    if (nextGroup != null && !nextGroup.isEmpty()) {
                        System.out.println("The group "
                                + nextGroup.get(0).getGroupNumber() +
                                " has been removed from the waiting queue.");
                        hotel.allocateRoom(nextGroup); // Allocate the next group from the waiting queue
                    } else {
                        System.out.println("There are no guest groups to remove from the waiting queue.");
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    // Method to divide the group into subgroups that can fit in rooms
    private List<List<Guest>> divideGroupIntoSubgroups(List<Guest> group) {
        List<List<Guest>> subgroups = new ArrayList<>();
        int numMembers = group.size();
        int numRoomsNeeded = (int) Math.ceil((double) numMembers / Hotel.MAX_GUESTS_PER_ROOM);

        int index = 0;
        for (int i = 0; i < numRoomsNeeded; i++) {
            int startIndex = index;
            int endIndex = Math.min(index + Hotel.MAX_GUESTS_PER_ROOM, numMembers);
            List<Guest> subgroup = group.subList(startIndex, endIndex);
            subgroups.add(subgroup);
            index = endIndex;
        }

        return subgroups;
    }

    // Method to allocate a subgroup to a room
    private void allocateSubgroup(List<Guest> subgroup) throws InterruptedException {
        Room room = hotel.allocateRoom(subgroup);
        if (room == null) {
            int groupNumber = subgroup.get(0).getGroupNumber();
            int attempts = rentalAttempts.getOrDefault(groupNumber, 1);
            if (attempts >= 2) {
                System.out.println("The group " + groupNumber
                        + " tried to rent a room twice and failed. They left a complaint and departed.");
                waitingQueue.remove(subgroup); // Remove the group from the waiting queue
            } else {
                boolean allExplored = true;
                for (Guest guest : subgroup) {
                    if (!guest.hasExplored()) {
                        allExplored = false;
                        break;
                    }
                }
                if (!allExplored) {
                    rentalAttempts.put(groupNumber, attempts + 1);
                    for (Guest guest : subgroup) {
                        guest.exploreCity();
                    }
                    // After exploring, try to rent a room again
                    if (subgroup.get(0).hasExplored()) {
                        System.out.println("The group " + groupNumber
                                + " is exploring the city.");
                        Thread.sleep(3000);
                        System.out.println("The group " + groupNumber
                                + " returned from exploring.");
                        room = hotel.allocateRoom(subgroup);
                        if (room == null) {
                            System.out.println("The group "
                                    + groupNumber
                                    + " tried to rent a room after exploring and failed. They left a complaint and departed.");
                            waitingQueue.remove(subgroup); // Remove the group from the waiting queue
                        }
                    }
                }
            }
        } else {
            System.out.println("Allocating " + subgroup.size()
                    + " members of the group " + subgroup.get(0).getGroupNumber()
                    + " to room " + room.getNumber());
        }
    }
}
