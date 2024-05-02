import java.util.concurrent.BlockingQueue;

public class Recepcionista extends Thread{
    private int id;
    private BlockingQueue<Hospede> filaEspera;
    private Quarto[] quartos;
    private Camareira[] camareiras;

    public Recepcionista(int id,BlockingQueue<Hospede> filaEspera, Quarto[] quartos, Camareira[] camareiras){
        this.id = id;
        this.filaEspera = filaEspera;
        this.quartos = quartos;
        this.camareiras = camareiras;
    }

    public void run() {

    }
}



