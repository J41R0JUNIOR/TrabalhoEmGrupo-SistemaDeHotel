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

    public Grupo procurarGrupo(){
        if(hotel.grupos != null) {
            for (Grupo grupo : hotel.grupos) {
                if (!grupo.getEstaAlocado()) {
                    System.out.println("Grupo nao alocado encontrado: " + grupo.getId());
                    return grupo;
                }
            }
        }
        return null;
    }

    public Quarto procurarQuarto(){
        for (Quarto quarto : hotel.quartos) {
            if (quarto.getHospedes() != null) {
                if (quarto.getHospedes().size() == 0) {
                    System.out.println("encontrou um quarto");
                    return quarto;
                }
            }
        }
        return null;
    }

    private void alocarHospedes(Grupo grupoHospedes, Quarto quarto) {
        if (quarto != null && grupoHospedes != null) {
            if (quarto.getHospedes().size() < 4) {
                quarto.getHospedes().addAll(grupoHospedes.getListaHospedes());
                grupoHospedes.estaAlocado = true;
                System.out.println("Hóspedes alocados no quarto " + quarto.getNumero() + " do grupo " + grupoHospedes.getId());
            } else {
                System.out.println("Não foi possível alocar os hóspedes do grupo " + grupoHospedes.getId() + ": o quarto está cheio.");
            }
        } else {
            System.out.println("Não foi possível alocar os hóspedes do grupo " + grupoHospedes.getId() + ": quarto ou grupo inválido.");
        }
    }





}
