import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread {
    private Integer id;
    private Hotel hotel;
    private Lock lock = new ReentrantLock();
    private Boolean estaLimpando = false;

    public Camareira(Integer id, Hotel hotel) {
        this.id = id;
        this.hotel = hotel;
        this.lock = new ReentrantLock();

    }

    public void run() {
        while (true) {
            for(Quarto quarto : hotel.quartos){
                limparQuarto(quarto);
            }
        }
    }

    public void limparQuarto(Quarto quarto) {

        if (quarto.limpo == false) {
            hotel.lock.lock();
            try {
                System.out.println("Camareira" + id + "esta limpando o quarto " + quarto.getNumero());
                quarto.limpo = true;
            } finally {
                hotel.lock.unlock();
            }
        }

    }


}

