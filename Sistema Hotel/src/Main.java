import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);// Cria uma instância do hotel com 10 quartos
        // Lista para armazenar as threads
        List<Thread> threads = new ArrayList<>();
        // Fila de espera para grupos de hóspedes
        BlockingQueue<List<Hospede>> filaEspera = new ArrayBlockingQueue<>(10);
        // Cria e inicia as threads dos recepcionistas
        for (int i = 1; i <= 8; i++) {
            List<Hospede> grupoHospedes = criarGrupoHospedes(i);
            threads.addAll(grupoHospedes);
            int a = i % 5;
            Recepcionista recepcionista = new Recepcionista("Atendimento nr: " + i + ", Recepcionista: " + a, hotel,
                    grupoHospedes, filaEspera);
            recepcionista.start();
            threads.add(recepcionista);
        }
        // Cria e inicia as threads das camareiras
        List<Camareira> camareiras = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // Alterado para 10 camareiras
            List<Quarto> quartosResponsaveis = new ArrayList<>();
            quartosResponsaveis.add(hotel.getQuartos().get(i)); // Cada camareira é responsável por um quarto
            Camareira camareira = new Camareira("Camareira " + (i + 1), quartosResponsaveis);
            camareira.start();
            camareiras.add(camareira);
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Aguarda a conclusão de cada thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Simula a saída para passear
        System.out
                .println("\n-------------------------------------------------\nHóspedes estão saindo para passear...");
        for (Quarto quarto : hotel.getQuartos()) {
            quarto.devolverChave(); // Devolve a chave na recepção ao sair

        }

        try {
            Thread.sleep(5000); // Simula o tempo que os hóspedes passam passeando (5 segundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Quarto quarto : hotel.getQuartos()) {
            quarto.pegarChave(); // Devolve a chave na recepção ao sair

        }

        System.out.println(
                "\n-------------------------------------------------\nTodos os grupos de hóspedes concluíram sua estadia.");

        Camareira.pausar();
    }

    public static List<Hospede> criarGrupoHospedes(int numeroGrupo) {
        Random random = new Random();
        int numHospedes = random.nextInt(10) + 1; // De 1 a 10 hóspedes
        List<Hospede> grupoHospedes = new ArrayList<>();

        for (int i = 0; i < numHospedes; i++) {
            Hospede hospede = new Hospede(numeroGrupo); // Cria uma instância de Hospede sem um nome específico
            grupoHospedes.add(hospede);
        }
        return grupoHospedes;
    }
}
