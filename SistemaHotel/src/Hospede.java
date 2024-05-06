import java.util.Random;

public class Hospede extends Thread{
    Integer id;
    Integer groupId;
    Integer numeroQuarto;
    Integer quantidadeTentativas;
    Boolean irEmbora;
    Boolean reclamarNarecepcao;
    Boolean estaPasseando;
    public Hospede(Integer id){
        super(String.valueOf(id));
        this.id = id;
        this.groupId = 0;
        this.numeroQuarto = 0;
        this.quantidadeTentativas = 0;
        this.irEmbora = false;
        this.reclamarNarecepcao = false;
        this.estaPasseando = false;
    }

    @Override
    public void run() {
        while(true){
            if(numeroQuarto != 0){
                Random random = new Random();
                int randomNumber = random.nextInt(2);

                if (randomNumber == 0) {
                    estaPasseando = true;
                } else {
                    //desocupar o quarto e ir embora
                    irEmbora = true;
                    break;
                }
            }
            else if(getQuantidadeTentativas() > 2){
//                reclamarNaRecepcao();
                this.reclamarNarecepcao = true;
                break;

            }else{
                tentarSerAtendido();
            }
        }
    }

//    public void reclamarNaRecepcao(){
//
//    }

    public void tentarSerAtendido(){
        this.quantidadeTentativas += 1;
    }

    public void sairPassear(){

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
