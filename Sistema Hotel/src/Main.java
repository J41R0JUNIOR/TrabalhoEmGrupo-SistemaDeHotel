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
        BlockingQueue<List<Hospede>> waitingQueue = new ArrayBlockingQueue<>(10);
        // Creates and starts threads for receptionists
        for (int i = 1; i <= 8; i++) {
            List<Hospede> guestGroup = createGuestGroup(i);
            threads.addAll(guestGroup);
            int a = i % 5;
            Receptionist receptionist = new Receptionist("Service nr: " + i + ", Receptionist: " + a, hotel,
                    guestGroup, waitingQueue);
            receptionist.start();
            threads.add(receptionist);
        }
        // Creates and starts threads for maids
        List<Housekeeper> housekeepers = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // Changed to 10 housekeepers
            List<Quarto> assignedRooms = new ArrayList<>();
            assignedRooms.add(hotel.getQuartos().get(i)); // Each maid is responsible for one room
            Housekeeper housekeeper = new Housekeeper("Housekeeper " + (i + 1), assignedRooms);
            housekeeper.start();
            housekeepers.add(housekeeper);
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Waits for each thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Simulates going out for a walk
        System.out
                .println("\n-------------------------------------------------\nGuests are going out for a walk...");
        for (Quarto room : hotel.getQuartos()) {
            room.returnKey(); // Returns the key at the reception when going out
        }

        try {
            Thread.sleep(5000); // Simulates the time guests spend walking (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Quarto room : hotel.getQuartos()) {
            room.takeKey(); // Returns the key at the reception when going out
        }

        System.out.println(
                "\n-------------------------------------------------\nAll guest groups have completed their stay.");

        Housekeeper.pause();
    }

    public static List<Hospede> createGuestGroup(int groupNumber) {
        Random random = new Random();
        int numGuests = random.nextInt(10) + 1; // From 1 to 10 guests
        List<Hospede> guestGroup = new ArrayList<>();

        for (int i = 0; i < numGuests; i++) {
            Hospede guest = new Hospede(groupNumber); // Creates an instance of Hospede without a specific name
            guestGroup.add(guest);
        }
        return guestGroup;
    }
}
