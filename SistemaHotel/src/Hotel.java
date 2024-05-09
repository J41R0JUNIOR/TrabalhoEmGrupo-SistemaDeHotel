import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    public List<Quarto> quartos;
    private Hospede hospede;
    public List<Grupo> grupos;
    public Lock lock;


    public Hotel(List<Grupo> grupos, List<Quarto> quartos) {
        this.recepcionistas = criarRecepcionista(5);;
        this.quartos = quartos;
        this.grupos = grupos;
        this.lock = new ReentrantLock();
//        System.out.println(recepcionistas);
//        System.out.println(grupos);
//        System.out.println(quartos);

        for(Grupo grupo: grupos){
            if(grupo.getListaHospedes().size() > 4){
                dividirGruposHospedes(grupo);
            }
            System.out.println(grupo.getListaHospedes());
        }

    }

    public void dividirGruposHospedes(Grupo grupo) {
//        System.out.println("dividindo");
        List<Hospede> hospedes = grupo.getListaHospedes();
        int numHospedesPorGrupo = 4;
        int numNovosGrupos = (int) Math.ceil((double) hospedes.size() / numHospedesPorGrupo);

        for (int i = 0; i < numNovosGrupos; i++) {
            int startIndex = i * numHospedesPorGrupo;
            int endIndex = Math.min(startIndex + numHospedesPorGrupo, hospedes.size());
            List<Hospede> hospedesDoGrupo = hospedes.subList(startIndex, endIndex);

            if (!hospedesDoGrupo.isEmpty()) {
                Grupo novoGrupo = new Grupo(grupo.getId(), hospedesDoGrupo);
//                System.out.println(novoGrupo.getListaHospedes() + "novo grupo");
                grupos.add(novoGrupo);
            }
        }
    }

    public List<Recepcionista> criarRecepcionista(int quantidade) {
        List<Recepcionista> criandoRecepcionistas = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoRecepcionistas.add(new Recepcionista(i, this));
        }
        return criandoRecepcionistas;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
}





