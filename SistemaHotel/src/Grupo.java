import java.util.ArrayList;
import java.util.List;

public class Grupo extends Thread {
    public List<Hospede> listaHospedes;
    private Integer id;
    public Boolean estaAlocado = false;
    public Integer tentativas = 0;
    public Boolean estaComChave = false;
    public Boolean irEmbora = false;
    public int numeroQuarto;

    public Grupo(Integer id, List<Hospede> listaHospedes, int numeroQuarto) {
        this.listaHospedes = listaHospedes;
        this.numeroQuarto = numeroQuarto;
        this.id = id;
        this.start(); // Iniciar a execução da thread
    }

    public void run() {

        while (!irEmbora) {
//            System.out.println("cornoooooo");
            irPassear();
//            irEmbora();

//            try {
//                Thread.sleep(3000);
//                irEmbora();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            //last time
        }

        try {
            Thread.sleep(3000);
            irEmbora();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public void irEmbora(){
        System.out.println("Grupo " + id + " indo embora...");
        irEmbora = true;

    }

    public void irPassear(){

        System.out.println(id + "esta alocado?" + estaAlocado);
        if (estaAlocado) {
            System.out.println(id + "esta alocado? entrou no if" );
                if (estaComChave) {
                    deixarChave();
                    pegarChave();
//                    try {
//                        Thread.sleep(3000);
                        System.out.println("Grupo " + id + " está indo passear.");
//                        Thread.sleep(3000);
//
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }

//                    System.out.println("Grupo " + id + " está indo passear.");
//                    System.out.println("Grupo " + id + " está voltando do passeio");

                }

        } else {
//                System.out.println("grupo nao pode passear");
        }
    }

    public void pegarChave(){
        estaComChave = true;
        System.out.println("Grupo " + id + " está voltando do passeio");
    }

    public void deixarChave(){
        estaComChave = false;
        System.out.println("Grupo " + id + " está indo passear.");
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
