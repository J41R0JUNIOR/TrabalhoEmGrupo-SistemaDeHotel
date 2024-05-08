import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    private List<Hospede> hospedes;
    private List<Quarto> quartos;
    private Hospede hospede;
    private List<Grupo> grupos;


    public Hotel(List<Hospede> hospedes) {
        this.recepcionistas = new ArrayList<>();
        this.hospedes = new ArrayList<>(hospedes);
        this.quartos = new ArrayList<>();
        this.grupos = new ArrayList<>();

    }

    private void dividirGruposHospedes() {

    }
}





