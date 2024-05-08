import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
    private Integer id;
    private List<Hospede> grupoHospedes;

    static Lock lock = new ReentrantLock();

    public Recepcionista(Integer id) {
        this.id = id;
//        System.out.println("Recepcionista " + id + " criada!");
        this.start();
    }

    public void run() {
        System.out.println("Recepcionista " + id + " começou o turno");
    }


}
