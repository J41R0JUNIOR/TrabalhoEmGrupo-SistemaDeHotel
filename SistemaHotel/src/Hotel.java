import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    private ArrayList<Quarto> quartos = new ArrayList<>();
    public ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private ArrayList<Camareira> camareiras = new ArrayList<>();
    public ArrayList<GrupoHospedes> grupos = new ArrayList<>();
    public ArrayList<Integer> chavesNaRecepcao = new ArrayList<>();
    public ReentrantLock lock = new ReentrantLock();

    public Hotel(ArrayList<GrupoHospedes> grupoHospedes){
        this.grupos = grupoHospedes;
        criarQuartos(10);
        criarRecepcionistas(5);

        criarCamareiras(10);

//        for(Recepcionista recepcionista: recepcionistas){
//            recepcionista.start();
//        }
    }

    private void criarQuartos(int quantidade){
        for(int i = 1; i <= quantidade ; i++) {
            quartos.add(new Quarto(i));
//            System.out.println("Quarto " + quartos.get(i  - 1).getNumero() + " criado");
        }
//        System.out.println(this.quartos);
    }

    private void criarRecepcionistas(int quantidade){
        for(int i = 1; i <= quantidade ; i++) {
//            System.out.println(grupoHospedes);

            recepcionistas.add(new Recepcionista(i, this));
//            System.out.println("Recepcionista " + recepcionistas.get(i - 1).getName() + " criada");
//            recepcionistas.get(i - 1).start();
        }
    }

    public void criarCamareiras(int quantidade){
        for(int i = 1; i <= quantidade; i++) {
            camareiras.add(new Camareira(i));
//            System.out.println("Camareira " + quartos.get(i  - 1).getNumero() + " criado");
            camareiras.get(i - 1).start();
        }
    }

    public void alocarHospedes(GrupoHospedes grupo, Recepcionista recepcionista) {
        if (quartos != null && !quartos.isEmpty()) {
            Quarto quarto;
            lock.lock();
            try {
            quarto = quartos.remove(0);
            } finally {
                lock.unlock();
            }
            grupo.numeroQuarto = quarto.getNumero();
            grupo.chaveQuarto = quarto.getNumero();
            quarto.adicionarHospedes(grupo.getParticipantes());

//            System.out.println("Grupo " + grupo.idGrupo + " alocado no quarto " + quarto.getNumero() + " pela recepcionista " + recepcionista.id);
            System.out.println("Grupo " + grupo.idGrupo + " alocado no quarto " + quarto.getNumero());

        } else {
            System.out.println("Não há quartos disponíveis para alocar o grupo " + grupo.idGrupo);
            System.out.println(grupo.participantes);
            grupo.tentativaFalha(); // Chama o método tentativaFalha() do grupo
        }
    }
}
