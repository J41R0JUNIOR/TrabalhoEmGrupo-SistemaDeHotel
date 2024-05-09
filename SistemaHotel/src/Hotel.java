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

    public Hotel(List<Grupo> grupos, List<Quarto> quartos) {

        this.quartos = quartos;
        this.recepcionistas = criarRecepcionista(5);
        this.grupos = grupos;

//        if(camareiras != null && recepcionistas != null){
//        this.threads.addAll(camareiras);
//        this.threads.addAll(recepcionistas);
//        }


        this.lock = new ReentrantLock();

        // Inicializa os grupos no início
//        for (Grupo grupo : grupos) {
//            if (grupo.getListaHospedes().size() > 4) {
//                dividirGruposHospedes(grupo);
//            }
////            System.out.println(grupo.getListaHospedes());
////            System.out.println(grupos.size() + "grupos");
//        }
//
//        for (Thread thread : threads) {
//            try {
//                thread.join(); // Aguarda a conclusão de cada thread
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


//        for(Grupo grupo: grupos){
//            System.out.println("IIIIIIIIIII" + grupo.getGroupId());
//            grupo.irPassear();
//        }

        
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
        if (quarto != null && quarto.getHospedes().isEmpty() && grupoHospedes.getListaHospedes().size() <= 4) {
            lock.lock(); // Adquire o bloqueio antes de acessar a região crítica
            try {
                quarto.getHospedes().addAll(grupoHospedes.getListaHospedes());
                grupoHospedes.estaAlocado = true;
                grupoHospedes.estaComChave = true;
                grupoHospedes.numeroQuarto = quarto.getNumero();
                System.out.println("Hóspedes do grupo " + grupoHospedes.getId() +" alocados no quarto " + quarto.getNumero());
                grupoHospedes.tentativas++;

                grupoHospedes.irPassear();

            } finally {
                lock.unlock(); // Libera o bloqueio após a conclusão da operação na região crítica
            }
        } else {
            System.out.println("Não foi possível alocar os hóspedes do grupo " + grupoHospedes.getId() + ": o quarto está cheio ou não existe.");
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
