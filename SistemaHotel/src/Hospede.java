import java.util.Random;

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
        while(true){

            if(numeroQuarto != 0){
                Random random = new Random();
                int randomNumber = random.nextInt(2);

                if (randomNumber == 0) {
                    sairPassear();
                } else {
                    irEmbora();
                }
            }
            if(getQuantidadeTentativas() > 2){
                irEmbora();
            }else{
                tentarSerAtendido();
            }
        }
    }

    public void tentarSerAtendido(){

    }

    public void sairPassear(){

    }

    public void irEmbora(){

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
