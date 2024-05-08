import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    private List<Quarto> quartos;
    private Hospede hospede;
    private List<Grupo> grupos;


    public Hotel(List<Recepcionista> recepcionistas, List<Grupo> grupos, List<Quarto> quartos) {
        this.recepcionistas = recepcionistas;

        this.quartos = quartos;
        this.grupos = grupos;

    }

    private void dividirGruposHospedes() {

    }
}





