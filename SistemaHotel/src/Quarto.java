import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
    private final int numero;
    private final int capacidadeMaxima = 4;
    private int ocupacaoAtual;
    private ArrayList<Hospede> hospedes;
    private boolean limpo;
    private final Lock lock;

    public Quarto(int numero) {
        this.numero = numero;
        this.ocupacaoAtual = 0;
        this.limpo = false;
//        System.out.println("Quarto " + numero + " criado!");

        this.lock = new ReentrantLock();
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public int getOcupacaoAtual() {
        return ocupacaoAtual;
    }

    public void setOcupacaoAtual(int ocupacaoAtual) {
        this.ocupacaoAtual = ocupacaoAtual;
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