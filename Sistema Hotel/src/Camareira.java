import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// É a classe que representa o serviço de uma camareira em um hotel
public class Camareira extends Thread {
    private String nome;
    // Flag para indicar se a camareira está ativa
    private volatile static boolean ativo = true;
    static Lock lock = new ReentrantLock(); // Locks

    private List<Quarto> quartosResponsaveis;

    public Camareira(String nome, List<Quarto> quartosResponsaveis) {
        this.nome = nome;
        this.quartosResponsaveis = quartosResponsaveis;
    }

    // Método para obter o nome da camareira
    public String getNome() {
        return nome;
    }

    // Método estático para pausar todas as camareiras
    public static void pausar() {
        ativo = false;
    }

    // Método sobrescrito da classe Thread, define o comportamento da camareira
    @Override
    public void run() {
        try {
            while (ativo) {
                for (Quarto quarto : quartosResponsaveis) {
                    Lock lock = quarto.getLock();
                    lock.lock(); // Adquire o lock do quarto
                    try {
                        // Verifica se o quarto está pronto para limpeza
                        if (quarto.estaProntoParaLimpeza()) {
                            // Imprime uma mensagem indicando que a camareira está limpando o quarto
                            System.out.println("\n-------------------------------------------------\n" + nome
                                    + " está limpando o quarto " + quarto.getNumero() + ".");
                            // Realiza a limpeza do quarto
                            quarto.limpar();
                            // Aguarda 2 segundos antes de prosseguir para o próximo quarto
                            Thread.sleep(2000); // Sleep for 2 seconds
                        }
                    } finally {
                        lock.unlock(); // Libera o lock do quarto
                    }
                }
            }
        } catch (InterruptedException e) {
            // Exceção tratada no caso de interrupção da thread durante o sono
            e.printStackTrace();
        }
    }
}
