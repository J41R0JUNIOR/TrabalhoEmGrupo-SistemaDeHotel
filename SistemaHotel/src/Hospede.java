public class Hospede extends Thread{

    private Integer id;
    //    private boolean chave = false;
    private Integer numeroQuarto;

    public Hospede(Integer id){
        this.id = id;
        this.numeroQuarto = numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    @Override
    public long getId() {
        return id;
    }
}