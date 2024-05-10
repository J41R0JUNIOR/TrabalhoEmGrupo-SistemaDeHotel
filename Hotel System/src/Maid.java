import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// This class represents the service of a maid in a hotel
public class Maid extends Thread {
    private String name;
    // Flag to indicate if the maid is active
    private volatile static boolean active = true;
    static Lock lock = new ReentrantLock(); // Locks

    private List<Room> assignedRooms;

    public Maid(String name, List<Room> assignedRooms) {
        super(name);
        this.name = name;
        this.assignedRooms = assignedRooms;
    }

    // Static method to pause all maids
    public static void pause() {
        active = false;
    }

    // Overridden method from the Thread class, defines the behavior of the maid
    @Override
    public void run() {
        try {
            while (active) {
                for (Room room : assignedRooms) {
                    Lock lock = room.getLock();
                    lock.lock(); // Acquires the lock of the room
                    try {
                        // Checks if the room is ready for cleaning
                        if (room.isReadyForCleaning()) {
                            // Prints a message indicating that the maid is cleaning the room
                            System.out.println("" + name
                                    + " is cleaning room " + room.getNumber() + ".");
                            // Cleans the room
                            room.clean();
                            // Waits for 2 seconds before moving to the next room
                            Thread.sleep(2000); // Sleep for 2 seconds
                        }
                    } finally {
                        lock.unlock(); // Releases the lock of the room
                    }
                }
            }
        } catch (InterruptedException e) {
            // Handled exception in case the thread is interrupted during sleep
            e.printStackTrace();
        }
    }
}
