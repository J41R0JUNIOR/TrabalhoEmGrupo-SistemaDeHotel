import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);// Creates an instance of the hotel with 10 rooms
        // List to store threads
        List<Thread> threads = new ArrayList<>();
        // Waiting queue for groups of guests
        BlockingQueue<List<Guest>> waitingQueue = new ArrayBlockingQueue<>(10);

        // Creates and starts threads for receptionists
        for (int i = 1; i <= 8; i++) {
            List<Guest> guestGroup = createGuestGroup(i);
            threads.addAll(guestGroup);
            int a = i % 5;
            Receptionist receptionist = new Receptionist("Service nr: " + i + ", Receptionist: " + a, hotel,
                    guestGroup, waitingQueue);
            receptionist.start();
            threads.add(receptionist);
        }

        // Creates and starts threads for maids
        List<Maid> housekeepers = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // Changed to 10 housekeepers
            List<Room> assignedRooms = new ArrayList<>();
            assignedRooms.add(hotel.getRooms().get(i)); // Each maid is responsible for one room
            Maid housekeeper = new Maid("Housekeeper " + (i + 1), assignedRooms);
            housekeeper.start();
            housekeepers.add(housekeeper);
        }

        // Waits for each thread to finish to show the rest
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Simulates going out for a walk
        System.out.println("Guests are going out for a walk...");
        for (Room room : hotel.getRooms()) {
            room.returnKey(); // Returns the key at the reception when going out
        }

        try {
            Thread.sleep(5000); // Simulates the time guests spend walking (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Room room : hotel.getRooms()) {
            room.takeKey(); // Returns the key at the reception when going out
        }
        System.out.println("All guest groups have completed their stay.");
        Maid.pause();
    }
    

    public static List<Guest> createGuestGroup(int groupNumber) {
        Random random = new Random();
        int numGuests = random.nextInt(10) + 1; // From 1 to 10 guests
        List<Guest> guestGroup = new ArrayList<>();

        for (int i = 0; i < numGuests; i++) {
            Guest guest = new Guest(groupNumber); // Creates an instance of Hospede without a specific name
            guestGroup.add(guest);
        }
        return guestGroup;
    }
}
