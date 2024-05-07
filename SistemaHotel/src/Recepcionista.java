import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
    Integer id;
    ArrayList<String> reclamacoes = new ArrayList<>();


    private Lock lock;
    Hotel hotel;

    public Recepcionista(Integer id, Hotel hotel) {
        super(String.valueOf(id));
        this.id = id;
        this.hotel = hotel;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            GrupoHospedes grupo;
            hotel.lock.lock();
            try {
                if (!hotel.grupos.isEmpty()) {
                    grupo = hotel.grupos.get(0);
                    if (!grupo.estaPasseando) {
                        hotel.alocarHospedes(grupo, this);
                        hotel.grupos.remove(grupo);
                    } else {
                        grupo.tentativaFalha();
                    }
                } else {
                    break;
                }
            } finally {
                hotel.lock.unlock();
            }
        }
    }





    public void anotarReclamacao(String nomeHospede) {
        String reclamacao = "Hospede " + nomeHospede + " fez uma reclamação!";
        reclamacoes.add(reclamacao);
        System.out.println(reclamacao);
    }
}
