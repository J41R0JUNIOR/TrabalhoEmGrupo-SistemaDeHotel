public class Recepcionista extends Thread {
    private Integer id;

    public Recepcionista(Integer id) {
        this.id = id;
        System.out.println("Recepcionista " + id + " criada!");
    }

    public void run() {
        System.out.println("Recepcionista " + id + " começou o turno");
    }


}
