import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
    private final int numero;
    private final int capacidadeMaxima = 4;
    private int ocupacaoAtual;
    private boolean chaveNaRecepcao;
    private final Lock lock;

    public Quarto(int numero) {
        this.numero = numero;
        this.ocupacaoAtual = 0;
        this.chaveNaRecepcao = true;
        this.lock = new ReentrantLock();
    }
}

