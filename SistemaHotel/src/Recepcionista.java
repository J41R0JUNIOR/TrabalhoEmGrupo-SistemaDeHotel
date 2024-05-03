import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Recepcionista extends Thread{
    Integer id;
    Boolean estaOcupada;


    public Recepcionista(Integer id){
        super(String.valueOf(id));

        this.id = id;
        this.estaOcupada = false;


    }

    @Override
    public void run() {

    }

    public void alocarHospedesEmQuartosVagos(){

    }
}



