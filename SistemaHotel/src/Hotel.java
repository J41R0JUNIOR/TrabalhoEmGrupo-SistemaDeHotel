import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    public List<Quarto> quartos;
    public List<Grupo> grupos;
    public Lock lock;
    public List<Grupo> espera =  new ArrayList<>();

    public Hotel(List<Grupo> grupos, List<Quarto> quartos) {

        this.quartos = quartos;
        this.recepcionistas = criarRecepcionista(5);
        this.grupos = grupos;
        this.lock = new ReentrantLock();

        // Inicializa os grupos no início
        for (Grupo grupo : grupos) {
            if (grupo.getListaHospedes().size() > 4) {
                dividirGruposHospedes(grupo);
            }
//            System.out.println(grupo.getListaHospedes());
            System.out.println(grupos.size() + "grupos");
        }
    }

    public Grupo procurarGrupo() {
        if (grupos != null) {
            for (Grupo grupo : grupos) {
                if (!grupo.getEstaAlocado()) {
                    return grupo;
                }
            }
        }
        return null;
    }

    public Grupo procurarGrupoListaEspera() {
        if (espera != null) {
            for (Grupo grupo : espera) {
                if (!grupo.getEstaAlocado()) {
                    return grupo;
                }
            }
        }
        return null;
    }

    public void alocarHospedes(Grupo grupoHospedes, Quarto quarto) {
        if (grupoHospedes != null && quarto != null) {
            if (quarto.getHospedes().size() + grupoHospedes.getListaHospedes().size() <= 4) {
                quarto.getHospedes().addAll(grupoHospedes.getListaHospedes());
                grupoHospedes.estaAlocado = true;
                System.out.println("Hóspedes alocados no quarto " + quarto.getNumero() + " do grupo " + grupoHospedes.getId());
                grupoHospedes.tentativas++;
            } else {
                System.out.println("Não foi possível alocar os hóspedes do grupo " + grupoHospedes.getId() + ": o quarto está cheio.");
            }
        } else {
            System.out.println("Não foi possível alocar os hóspedes do grupo " + grupoHospedes.getId() + ": quarto ou grupo inválido.");
        }
    }


    public Quarto procurarQuarto() {
        for (Quarto quarto : quartos) {
            if (quarto != null && (quarto.getHospedes() == null || quarto.getHospedes().isEmpty())) {
//                System.out.println("Encontrou um quarto vazio");
                return quarto;
            }
        }
        return null;
    }


    public void dividirGruposHospedes(Grupo grupo) {
        List<Hospede> hospedes = grupo.getListaHospedes();
        int numHospedesPorGrupo = 4;
        int numNovosGrupos = (int) Math.ceil((double) hospedes.size() / numHospedesPorGrupo);

        List<Grupo> novosGrupos = new ArrayList<>();
        for (int i = 0; i < numNovosGrupos; i++) {
            int startIndex = i * numHospedesPorGrupo;
            int endIndex = Math.min(startIndex + numHospedesPorGrupo, hospedes.size());
            List<Hospede> hospedesDoGrupo = hospedes.subList(startIndex, endIndex);

            if (!hospedesDoGrupo.isEmpty()) {
                Grupo novoGrupo = new Grupo(grupo.getId(), hospedesDoGrupo);
                novosGrupos.add(novoGrupo);
            }
        }

        grupos.addAll(novosGrupos);
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
