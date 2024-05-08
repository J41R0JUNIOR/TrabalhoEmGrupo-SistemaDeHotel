import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Hospede> hospedes = criarHospedes(50);

        Hotel hotel = new Hotel(hospedes);

        List<Recepcionista> recepcionistas = criarRecepcionista(5);
        List<Quarto> quartos = criarQuartos(10);


    }

    public static List<Hospede> criarHospedes(int quantidade) {
        List<Hospede> criandoHospedes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoHospedes.add(new Hospede(i));
        }
        return criandoHospedes;
    }

    public static List<Recepcionista> criarRecepcionista(int quantidade) {
        List<Recepcionista> criandoRecepcionistas = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoRecepcionistas.add(new Recepcionista(i));
        }
        return criandoRecepcionistas;
    }

    public static List<Quarto> criarQuartos(int quantidade) {
        List<Quarto> criandoQuartos = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoQuartos.add(new Quarto(i));
        }
        return criandoQuartos;
    }
}
