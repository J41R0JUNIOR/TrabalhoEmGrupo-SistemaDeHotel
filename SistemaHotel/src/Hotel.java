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
    public List<Camareira> camareiras;
    public List<Thread> threads = new ArrayList<>();

    public Hotel(List<Grupo> grupos, List<Quarto> quartos) {

        this.quartos = quartos;
        this.recepcionistas = criarRecepcionista(5);
        this.grupos = grupos;

        if(camareiras != null && recepcionistas != null){
        this.threads.addAll(camareiras);
        this.threads.addAll(recepcionistas);
        }


        this.lock = new ReentrantLock();

        // Inicializa os grupos no início
        for (Grupo grupo : grupos) {
            if (grupo.getListaHospedes().size() > 4) {
                dividirGruposHospedes(grupo);
            }
//            System.out.println(grupo.getListaHospedes());
//            System.out.println(grupos.size() + "grupos");

        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Aguarda a conclusão de cada thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        irPassear();




    }

    public void irPassear(){

        for(Quarto quarto : quartos) {
            quarto.devolverChave();
//            for(Camareira camareira: camareiras){
//                camareira.limparQuarto(quarto);
//            }
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
                grupoHospedes.estaComChave = true;
                grupoHospedes.numeroQuarto = quarto.getNumero();
                System.out.println("Hóspedes alocados no quarto " + quarto.getNumero() + " do grupo " + grupoHospedes.getId());
                grupoHospedes.tentativas++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Não foi possível alocar os hóspedes do grupo " + grupoHospedes.getId() + ": o quarto " + quarto.getNumero() + " está cheio.");
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
                Grupo novoGrupo = new Grupo(grupo.getGroupId(), hospedesDoGrupo, 0);
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

    public  List<Camareira> criarCamareiras(int quantidade) {
        List<Camareira> criandoCamareiras = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoCamareiras.add(new Camareira(i, this));
        }
        return criandoCamareiras;
    }


    public List<Grupo> getGrupos() {
        return grupos;
    }
}
