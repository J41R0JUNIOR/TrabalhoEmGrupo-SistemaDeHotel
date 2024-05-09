import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    // Representa o número máximo de hóspedes por quarto
    public static final int NUMERO_MAXIMO_HOSPEDES = 4;
    // Lista de quartos do hotel
    private List<Quarto> quartos;
    // Fila de espera para grupos de hóspedes
    private BlockingQueue<List<Hospede>> filaEspera;
    static Lock lock = new ReentrantLock(); // Locks

    public Hotel(int numQuartos) {
        // Inicializa a lista de quartos
        quartos = new ArrayList<>();
        // Cria os quartos e adiciona à lista
        for (int i = 1; i <= numQuartos; i++) {
            Quarto quarto = new Quarto(i);
            quartos.add(quarto);
            System.out.println(
                    "\n-------------------------------------------------\nQuarto número " + i + " foi criado.");
        }
        // Inicializa a fila de espera com capacidade para 10 grupos de hóspedes
        filaEspera = new ArrayBlockingQueue<>(10);
    }

    public synchronized Quarto alocaQuarto(List<Hospede> grupoHospedes) throws InterruptedException {
        lock.lock();
        try {
            // Método para alocar um quarto para um grupo de hóspedes
            for (Quarto quarto : quartos) {
                // Verifica se o quarto está vago
                if (quarto.estaVago()) {
                    // Adiciona o grupo de hóspedes ao quarto
                    quarto.adicionarHospedes(grupoHospedes);
                    return quarto;
                }
            }
            // Se todos os quartos estiverem ocupados, adiciona o grupo à fila de espera
            System.out.println(
                    "\n-------------------------------------------------\nTodos os quartos estão ocupados. O grupo "
                            + grupoHospedes.get(0).getNumeroGrupo() +
                            " será colocado na fila de espera.");
            filaEspera.put(grupoHospedes);
            return null; // ou lançar uma exceção indicando que não há quartos disponíveis
        } finally {
            lock.unlock();
        }
    }

    // Método para liberar um quarto ocupado por um grupo de hóspedes
    public synchronized void liberarQuarto(List<Hospede> grupoHospedes) {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                // Verifica se o quarto contém todos os hóspedes do grupo
                if (quarto.containsAllHospedes(grupoHospedes)) {
                    // Remove o grupo de hóspedes do quarto
                    quarto.removerHospedes(grupoHospedes);
                    // Se o quarto ficar vago, verifica se há um grupo esperando para ocupá-lo
                    if (quarto.estaVago()) {
                        List<Hospede> proximoGrupo = filaEspera.poll();
                        if (proximoGrupo != null) {
                            try {
                                alocaQuarto(proximoGrupo); // Aloca o quarto para o próximo grupo na fila de espera
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Método para obter a lista de quartos do hotel
    public synchronized List<Quarto> getQuartos() {
        return quartos;
    }

    // Método para obter o próximo grupo da fila de espera
    public synchronized List<Hospede> getProximoGrupoFilaEspera() {
        return filaEspera.poll();
    }
}
