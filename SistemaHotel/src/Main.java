import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Hospede> hospedes = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            hospedes.add(new Hospede(i));
        }

        Hotel hotel = new Hotel(hospedes);

        // Cria e starta as 5 recepcionistas
        for (int i = 1; i <= 5; i++) {
           Recepcionista recepcionistas = new Recepcionista(i);
           recepcionistas.start();
        }

        for (int i = 1; i <= 10; i++) {
            Quarto quarto = new Quarto(i);
        }

    }
}
