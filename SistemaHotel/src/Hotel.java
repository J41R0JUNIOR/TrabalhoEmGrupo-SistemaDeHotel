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

        dividirGruposHospedes(grupos.get(0));



    }

    public void dividirGruposHospedes(Grupo grupo) {
        List<Hospede> hospedes = grupo.getListaHospedes();
        int numHospedesPorGrupo = 4;
        int numNovosGrupos = (int) Math.ceil((double) hospedes.size() / numHospedesPorGrupo);

        for (int i = 0; i < numNovosGrupos; i++) {
            int startIndex = i * numHospedesPorGrupo;
            int endIndex = Math.min(startIndex + numHospedesPorGrupo, hospedes.size());
            List<Hospede> hospedesDoGrupo = hospedes.subList(startIndex, endIndex);

            if (!hospedesDoGrupo.isEmpty()) {
                Grupo novoGrupo = new Grupo(grupo.getId(), hospedesDoGrupo);

                grupos.add(novoGrupo);



            }
        }
    }
}





