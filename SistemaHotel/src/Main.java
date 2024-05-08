import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Hospede> hospedes = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            hospedes.add(new Hospede(i));
        }

        Hotel hotel = new Hotel(hospedes);

        hotel.run();



    }
}
