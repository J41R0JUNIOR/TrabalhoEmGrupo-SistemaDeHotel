public class Hospede extends Thread{
    Integer id;
    Integer numeroQuarto;
    Integer quantidadeTentativas;

    public Hospede(Integer id){
        this.id = id;
        this.numeroQuarto = 0;
        this.quantidadeTentativas = 0;
    }

    @Override
    public void run() {

    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public Integer getQuantidadeTentativas() {
        return quantidadeTentativas;
    }

    public void setQuantidadeTentativas(Integer quantidadeTentativas) {
        this.quantidadeTentativas = quantidadeTentativas;
    }
}