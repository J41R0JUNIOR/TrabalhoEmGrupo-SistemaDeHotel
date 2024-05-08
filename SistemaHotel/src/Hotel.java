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
        System.out.println(recepcionistas);
        System.out.println(grupos);
        System.out.println(quartos);

    }

    private void dividirGruposHospedes(Grupo grupo) {
        List<Hospede> hospedes = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            
        }
//        Grupo novoGrupo = new Grupo(grupo.getId(),)
    }
}





