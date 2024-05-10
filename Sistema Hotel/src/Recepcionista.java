import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Classe que representa um recepcionista em um hotel
public class Recepcionista extends Thread {
    private String nome;
    // Hotel onde a recepcionista trabalha
    private Hotel hotel;
    // Lista de hóspedes que o recepcionista está atendendo
    private List<Hospede> grupoHospedes;
    // Fila de espera para grupos de hóspedes
    private BlockingQueue<List<Hospede>> filaEspera;
    // Mapa para acompanhar o número de tentativas de aluguel de quarto por grupo
    private Map<Integer, Integer> tentativasAluguel = new HashMap<>();
    static Lock lock = new ReentrantLock(); // Locks

    // Construtor do recepcionista
    public Recepcionista(String nome, Hotel hotel, List<Hospede> grupoHospedes,
                         BlockingQueue<List<Hospede>> filaEspera) {
        this.nome = nome;
        this.hotel = hotel;
        this.grupoHospedes = grupoHospedes;
        this.filaEspera = filaEspera;
    }

    // Método sobrescrito da classe Thread, define o comportamento do recepcionista
    @Override
    public void run() {
        lock.lock();
        try {
            try {
                // Obtém o número de membros do grupo de hóspedes
                int numMembros = grupoHospedes.size();
                System.out.println("\n-------------------------------------------------\n" + nome
                        + " está realizando o check-in para o grupo de hóspedes "
                        + grupoHospedes.get(0).getNumeroGrupo() +
                        ", que possui " + numMembros + " membros.");

                // Verifica se o número de membros é maior que o número máximo de membros por
                // quarto
                if (numMembros > Hotel.NUMERO_MAXIMO_HOSPEDES) {
                    System.out.println("\n-------------------------------------------------\nO grupo "
                            + grupoHospedes.get(0).getNumeroGrupo()
                            + " é grande demais para um quarto. Vários quartos serão alocados.");

                    // Divide o grupo em subgrupos que podem caber em quartos individuais
                    List<List<Hospede>> subgrupos = dividirGrupoEmSubgrupos(grupoHospedes);

                    // Aloca cada subgrupo em um quarto
                    for (List<Hospede> subgrupo : subgrupos) {
                        alocaSubgrupo(subgrupo);
                    }
                } else {
                    // Se o número de membros não exceder o número máximo de membros por quarto,
                    // aloca o grupo inteiro em um único quarto
                    alocaSubgrupo(grupoHospedes);
                }

                hotel.liberarQuarto(grupoHospedes); // Libera o quarto quando o grupo sai
                if (!filaEspera.isEmpty()) {
                    List<Hospede> proximoGrupo = filaEspera.poll();
                    if (proximoGrupo != null && !proximoGrupo.isEmpty()) {
                        System.out.println("\n-------------------------------------------------\nO grupo "
                                + proximoGrupo.get(0).getNumeroGrupo() +
                                " foi retirado da fila de espera.");
                        hotel.alocaQuarto(proximoGrupo); // Aloca o próximo grupo da fila de espera
                    } else {
                        System.out.println(
                                "\n-------------------------------------------------\nNão há grupo de hóspedes para retirar da fila de espera.");
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    // Método para dividir o grupo em subgrupos que podem caber em quartos
    // individuais
    private List<List<Hospede>> dividirGrupoEmSubgrupos(List<Hospede> grupo) {
        List<List<Hospede>> subgrupos = new ArrayList<>();
        int numMembros = grupo.size();
        int numQuartosNecessarios = (int) Math.ceil((double) numMembros / Hotel.NUMERO_MAXIMO_HOSPEDES);

        int index = 0;
        for (int i = 0; i < numQuartosNecessarios; i++) {
            int startIndex = index;
            int endIndex = Math.min(index + Hotel.NUMERO_MAXIMO_HOSPEDES, numMembros);
            List<Hospede> subgrupo = grupo.subList(startIndex, endIndex);
            subgrupos.add(subgrupo);
            index = endIndex;
        }

        return subgrupos;
    }

    // Método para alocar um subgrupo em um quarto
    private void alocaSubgrupo(List<Hospede> subgrupo) throws InterruptedException {
        Quarto quarto = hotel.alocaQuarto(subgrupo);
        if (quarto == null) {
            int numeroGrupo = subgrupo.get(0).getNumeroGrupo();
            int tentativas = tentativasAluguel.getOrDefault(numeroGrupo, 1);
            if (tentativas >= 2) {
                System.out.println("\n-------------------------------------------------\nO grupo " + numeroGrupo
                        + " tentou alugar um quarto duas vezes e não conseguiu. Eles deixaram uma reclamação e foram embora.");
                filaEspera.remove(subgrupo); // Remove o grupo da fila de espera
            } else {
                boolean todosJaPassearam = true;
                for (Hospede hospede : subgrupo) {
                    if (!hospede.getJaPasseou()) {
                        todosJaPassearam = false;
                        break;
                    }
                }
                if (!todosJaPassearam) {
                    tentativasAluguel.put(numeroGrupo, tentativas + 1);
                    for (Hospede hospede : subgrupo) {
                        hospede.passearPelaCidade();
                    }
                    // Após o passeio, tenta alugar um quarto novamente
                    if (subgrupo.get(0).getJaPasseou()) {
                        System.out.println("\n-------------------------------------------------\nO grupo " + numeroGrupo
                                + " está passeando pela cidade.");
                        Thread.sleep(3000);
                        System.out.println("\n-------------------------------------------------\nO grupo " + numeroGrupo
                                + " retornou do passeio.");
                        quarto = hotel.alocaQuarto(subgrupo);
                        if (quarto == null) {
                            System.out.println("\n-------------------------------------------------\nO grupo "
                                    + numeroGrupo
                                    + " tentou alugar um quarto após o passeio e não conseguiu. Eles deixaram uma reclamação e foram embora.");
                            filaEspera.remove(subgrupo); // Remove o grupo da fila de espera
                        }
                    }
                }
            }
        } else {
            System.out.println("\n-------------------------------------------------\nAlocando " + subgrupo.size()
                    + " membros do grupo " + subgrupo.get(0).getNumeroGrupo()
                    + " para o quarto " + quarto.getNumero());
        }
    }
}
