import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    private List<Hospede> hospedes;
    private List<Quarto> quartos;

    public Hotel(List<Hospede> hospedes) {
        this.recepcionistas = new ArrayList<>();
        this.hospedes = new ArrayList<>(hospedes);
        this.quartos = new ArrayList<>();
    }

    private void criarRecepcionistas() {
        for (int i = 1; i <= 5; i++) {
            recepcionistas.add(new Recepcionista(i));
        }
    }

    private void startRecepcionistas() {
        for (Recepcionista recepcionista : recepcionistas) {
            recepcionista.start();
        }
    }

    private void criarQuartos(){
        for (int i = 1; i <= 10; i++) {
            quartos.add(new Quarto(i));
        }
    }

    public void run() {
        criarRecepcionistas();
        criarQuartos();
        startRecepcionistas();
    }
}
