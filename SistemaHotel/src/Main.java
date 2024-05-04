import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<Hospede> hospedes = new ArrayList<>();

        for(int i = 1; i <= 50; i++){
            hospedes.add(new Hospede(i));
            System.out.println("hospede " + hospedes.get(i-1).getName() + " adiconado");
        }

        Hotel hotel = new Hotel();

        // Inicia os threads dos recepcionistas
        for (Recepcionista recepcionista : hotel.recepcionistas) {
            recepcionista.start();
        }

        // Inicia os threads dos hóspedes
        for (Hospede hospede : hotel.hospedes) {
            hospede.start();
        }

        // Simula a chegada dos hóspedes ao hotel
        for (Hospede hospede : hotel.hospedes) {
            hotel.adicionarHospede(hospede);
        }
    }
}