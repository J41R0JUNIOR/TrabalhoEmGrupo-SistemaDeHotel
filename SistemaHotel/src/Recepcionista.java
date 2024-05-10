import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
    private Integer id;
    private Hotel hotel;
    static Lock lock = new ReentrantLock();

    public Recepcionista(Integer id, Hotel hotel) {
        this.id = id;
        this.hotel = hotel;
        this.start();
    }

    public void run() {
        while (true) {
            Grupo grupo = hotel.procurarGrupo();
            Grupo grupoEspera = hotel.procurarGrupoListaEspera();
            Quarto quarto = hotel.procurarQuarto();

            if (grupo == null) {
                System.out.println("Não há mais grupos não alocados.");
                break; // Se não houver grupos não alocados, saia do loop
            } else if (quarto == null) {
                System.out.println("Não foi possível encontrar um quarto para o grupo " + grupo.getId());
                int i = 0;
                while (i < hotel.grupos.size()) {
                    Grupo currentGrupo = hotel.grupos.get(i);
                    if (currentGrupo.getId() == grupo.getId()) {
                        currentGrupo.tentativas++;
                        System.out.println("O grupo " + currentGrupo.getId() + " não pode ser alocado e foi passear");
                        hotel.espera.add(currentGrupo);

                        // Remove o grupo da lista de grupos
                        hotel.grupos.remove(i);
                    } else {
                        i++; // Incrementa o contador se o grupo atual não corresponder
                    }
                }
            } else {
                hotel.lock.lock();
                try {
                    hotel.alocarHospedes(grupo, quarto);
                } catch (Exception e) {
                    System.err.println("Erro ao alocar hospedes: " + e.getMessage());
                } finally {
                    hotel.lock.unlock();
                }
            }
        }
    }
}