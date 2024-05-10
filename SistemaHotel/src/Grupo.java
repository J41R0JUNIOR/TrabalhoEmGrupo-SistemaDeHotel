import java.util.ArrayList;
import java.util.List;

public class Grupo extends Thread {
    public List<Hospede> listaHospedes;
    private Integer id;
    public Boolean estaAlocado = false;
    public Integer tentativas = 0;
    public Boolean estaComChave = false;
    public Boolean estaPasseando = false;
    public int numeroQuarto;

    public Grupo(Integer id, List<Hospede> listaHospedes, int numeroQuarto) {
        this.listaHospedes = listaHospedes;
        this.numeroQuarto = numeroQuarto;
        this.id = id;
        this.start(); // Iniciar a execução da thread
    }

    @Override
    public void run() {
        while (true) {
            if (estaAlocado && !estaPasseando) {
                try {
                    Thread.sleep(3000); // Espera por 3 segundos (3000 milissegundos)
                    System.out.println("Grupo " + id + " está indo passear.");
                    estaPasseando = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (estaAlocado && estaPasseando) {
                try {
                    Thread.sleep(2500);
                    System.out.println("Grupo " + id + " está voltando do passeio.");
                    estaPasseando = false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Lógica para quando o grupo não está alocado
            }
        }
    }

    public Boolean getEstaAlocado() {
        return estaAlocado;
    }

    public Integer getGroupId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Hospede> getListaHospedes() {
        return (ArrayList<Hospede>) listaHospedes;
    }

    public void setListaHospedes(List<Hospede> listaHospedes) {
        this.listaHospedes = listaHospedes;
    }
}
