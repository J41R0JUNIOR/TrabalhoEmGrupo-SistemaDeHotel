import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
    private final int numero;
    private final int capacidadeMaxima = 4;
    private ArrayList<Hospede> hospedes;
    public boolean limpo;
    private final Lock lock;

    public Quarto(int numero) {
        this.numero = numero;
        this.limpo = false;
        this.hospedes = new ArrayList<>();
//        System.out.println("Quarto " + numero + " criado!");

        this.lock = new ReentrantLock();
    }

    public void devolverChave() {
        // Imprime uma mensagem indicando que os hóspedes devolveram a chave na recepção
        System.out.println("Os hóspedes do quarto " + numero
                + " devolveram a chave na recepção e foram passear.");
        limpo = false;

//        while (!limpo){
//            System.out.println("Hóspedes do quarto " + numero + " esperando");
//        }

        if (limpo == false) {
            System.out.println("Hóspedes do quarto " + numero
                    + " pegaram a chave na recepção e voltaram pro quarto.");
        }
    }

    public void removerHospedes(ArrayList<Hospede> hospedesParaRemover) {
        lock.lock(); // Adquire o bloqueio antes de acessar a região crítica
        try {
            Iterator<Hospede> iterator = hospedes.iterator();
            while (iterator.hasNext()) {
                Hospede hospede = iterator.next();
                if (hospedesParaRemover.contains(hospede)) {
                    iterator.remove(); // Remove o hóspede da lista
                }
            }
        } finally {
            lock.unlock(); // Libera o bloqueio após a conclusão da operação na região crítica
        }
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public ArrayList<Hospede> getHospedes() {
        return (ArrayList<Hospede>) hospedes;
    }

    public void setHospedes(ArrayList<Hospede> hospedes) {
        this.hospedes = hospedes;
    }

    public boolean isLimpo() {
        return limpo;
    }

    public void setLimpo(boolean limpo) {
        this.limpo = limpo;
    }

    //Funções do quarto

    public void adicionarHospedes(ArrayList<Hospede> hospedes){
        lock.lock();
        try{
            if(hospedes.size() <= capacidadeMaxima){
                this.hospedes = hospedes;
            }
        }finally {
            lock.unlock();
        }
    }
}