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
            Grupo grupoEspera = hotel.procurarGrupoListaEspera();
            Quarto quarto = hotel.procurarQuarto();

            if (grupo == null) {
                System.out.println("Não há mais grupos não alocados.");
                break; // Se não houver grupos não alocados, saia do loop
            }
            else if (quarto == null) {
                System.out.println("Não foi possível encontrar um quarto para o grupo " + grupo.getId());
                for(int i = 0; i < hotel.grupos.size(); i++){
                    if(hotel.grupos.get(i).getId() == grupo.getId()){
                        hotel.grupos.get(i).tentativas ++;
                        System.out.println("O grupo " + hotel.grupos.get(i).getId() + " não pode ser alocado e foi passear");
                        hotel.espera.add(hotel.grupos.get(i));
                        
                        hotel.grupos.remove(i);
                    }
                }
//                break; // Se não houver quartos disponíveis, saia do loop
            } else {

                System.out.println("Há grupos ainda");
                hotel.lock.lock();
                try {
                    System.out.println("encontrou um quarto numero " + quarto.getNumero());
                    hotel.alocarHospedes(grupo, quarto);

                } catch (Exception e) {
                    System.err.println("Erro ao alocar hospedes: " + e.getMessage());
                } finally {
                    hotel.lock.unlock();
                }
            }
        }
    }
}
