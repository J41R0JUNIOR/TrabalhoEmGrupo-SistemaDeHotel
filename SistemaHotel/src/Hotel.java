import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    private List<Hospede> hospedes;
    private List<List<Hospede>> gruposHospedes;
    private List<Quarto> quartos;
    private Hospede hospede;

    public Hotel(List<Hospede> hospedes) {
        this.recepcionistas = new ArrayList<>();
        this.hospedes = new ArrayList<>(hospedes);
        this.quartos = new ArrayList<>();
        this.gruposHospedes = new ArrayList<>();
    }

    private void dividirGruposHospedes() {
        for (int i = 0; i < hospedes.size(); i += 4) {
            List<Hospede> group = new ArrayList<>();
            for (int j = i; j < i + 4 && j < hospedes.size(); j++) {
                group.add(hospedes.get(j));
            }
            gruposHospedes.add(group);
        }
    }




}
