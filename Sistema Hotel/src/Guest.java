public class Guest extends Thread { // Class representing a guest in a hotel

    private int groupNumber; // Number of the group the guest belongs to
    private boolean hasExplored = false; // Indicates whether the guest has explored the city

    public Guest(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    // Method to simulate the guest exploring the city
    public void exploreCity() {
        // Checks if the guest has not explored yet
        if (!hasExplored) {
            try {
                hasExplored = true;
                // Simulates the guest exploring the city for 3 seconds
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get the status of whether the guest has explored the city
    public boolean hasExplored() {
        return hasExplored;
    }

    // Method to get the number of the group the guest belongs to
    public int getGroupNumber() {
        return groupNumber;
    }

    // Static method to indicate that a group has returned to the room
    public static void returnGroupToRoom(int groupNumber) {
        // Prints a message indicating that the group has returned to the room
        System.out.println("Group " + groupNumber + " has returned.");
    }

    public void returnToRoom() {
        try {
            Thread.sleep(2000); // Waits for 2 seconds before returning the group to the room
            returnGroupToRoom(groupNumber); // Call to the new static method
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
