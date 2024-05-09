import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
    private Integer id;
    private Hotel hotel;
    static Lock lock = new ReentrantLock();

    public Recepcionista(Integer id, Hotel hotel) {
        this.id = id;
        this.hotel = hotel;
        this.start();
    }

    public void run() {
        while (true) {
            Grupo grupo = hotel.procurarGrupo();
            Quarto quarto = hotel.procurarQuarto();
            if (grupo == null || quarto == null) {
                System.out.println("Não há mais grupos não alocados.");
                break; // Se não houver grupos não alocados, saia do loop
            }else {
                System.out.println("Há grupos ainda");
                hotel.lock.lock();
                try {
                    System.out.println("Entrou no try");

                    System.out.println("encontrou um quarto numero " + quarto.getNumero());
                        hotel.alocarHospedes(grupo, quarto);


                } catch (Exception e) {
                    System.err.println("Erro ao alocar hospedes: " + e.getMessage());
                } finally {
                    hotel.lock.unlock();
                }
            }
        }
//        System.out.println("Recepcionista " + id + " começou o turno");
    }




}
