import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Classe que representa um quarto em um hotel
public class Quarto {
    private int numero;
    // Lista de hóspedes hospedados no quarto
    private List<Hospede> hospedes;
    // Indica se o quarto está pronto para limpeza
    private boolean prontoParaLimpeza = false;
    private Lock lock; // Locks

    // Construtor do quarto
    public Quarto(int numero) {
        this.numero = numero;
        this.hospedes = new ArrayList<>();
        this.lock = new ReentrantLock();

    }

    // Método para devolver a chave do quarto na recepção
    public void devolverChave() {
        // Imprime uma mensagem indicando que os hóspedes devolveram a chave na recepção
        System.out.println("\n-------------------------------------------------\nHóspedes do quarto " + numero
                + " devolveram a chave na recepção e foram passear.");
        prontoParaLimpeza = true;
        if (prontoParaLimpeza == false) {
            System.out.println("\n-------------------------------------------------\nHóspedes do quarto " + numero
                    + " pegaram a chave na recepção e voltaram pro quarto.");
        }
    }

    public void pegarChave() {

        if (prontoParaLimpeza == false) {
            System.out.println("\n-------------------------------------------------\nHóspedes do quarto " + numero
                    + " pegaram a chave na recepção e voltaram pro quarto.");
        }
    }

    // Método para verificar se o quarto está pronto para limpeza
    public boolean estaProntoParaLimpeza() {
        return prontoParaLimpeza;
    }

    // Método para limpar o quarto
    public void limpar() {
        // Imprime uma mensagem indicando que o quarto foi limpo
        System.out.println("\n-------------------------------------------------\nO quarto " + numero + " foi limpo.");
        prontoParaLimpeza = false;
    }

    // Método para obter o número do quarto
    public int getNumero() {
        return numero;
    }

    // Método para obter a lista de hóspedes do quarto
    public List<Hospede> getHospedes() {
        return hospedes;
    }

    // Método para verificar se o quarto está vago
    public boolean estaVago() {
        return hospedes.isEmpty();
    }

    // Método para adicionar novos hóspedes ao quarto
    public void adicionarHospedes(List<Hospede> novosHospedes) {
        hospedes.addAll(novosHospedes);
    }

    // Método para remover hóspedes do quarto
    public void removerHospedes(List<Hospede> hospedesARemover) {
        hospedes.removeAll(hospedesARemover);
    }

    // Método para verificar se o quarto contém todos os hóspedes especificados
    public boolean containsAllHospedes(List<Hospede> hospedes) {
        List<Hospede> quartoHospedes = getHospedes();
        return quartoHospedes.containsAll(hospedes);
    }

    // Método para pegar Lock
    public Lock getLock() {
        return lock;
    }
}

