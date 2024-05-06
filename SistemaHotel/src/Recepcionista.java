import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Recepcionista extends Thread{
    Integer id;
    Boolean estaOcupada;
    ArrayList<String> reclamacoes = new ArrayList<>();
    ArrayList<Hospede> grupoEmAtendimento = new ArrayList<>();


    public Recepcionista(Integer id){
        super(String.valueOf(id));

        this.id = id;
        this.estaOcupada = false;


    }

    @Override
    public void run() {
        while(true){

        }
    }

    public void alocarHospedesEmQuartosVagos(){

    }

    public void anotarReclamacao(String nomeHospede){
        String reclamacao = "Hospede " + nomeHospede + " fez uma reclamação!";
        reclamacoes.add(reclamacao);
        System.out.println(reclamacao);
    }
}



