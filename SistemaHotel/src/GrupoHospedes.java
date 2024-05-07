import java.util.ArrayList;

public class GrupoHospedes extends Thread{
    ArrayList<Hospede> participantes;
    Integer idGrupo;
    Integer qtdTentativas;
    Integer numeroQuarto;
    Boolean estaPasseando;
    Boolean querAtendimento;
    Boolean irEmbora;
    Integer chaveQuarto;


    GrupoHospedes(ArrayList<Hospede> participantes, Integer idGrupo){
        this.participantes = participantes;
        this.idGrupo = idGrupo;
        this.qtdTentativas = 0;
        this.numeroQuarto = 0;
        this.chaveQuarto = 0;
        this.estaPasseando = false;
        this.querAtendimento = false;
        this.irEmbora = false;
        this.start();
    }

    @Override
    public void run() {
        while(!irEmbora){

        }
    }

    public ArrayList<Hospede> getParticipantes() {
        return participantes;
    }

    public void tentativaFalha(){
        qtdTentativas += 1;

        if(qtdTentativas >= 2){
            irEmbora = true;
            this.suspend();
        }else{
            irPassear();

        }
    }

    public void irEmbora(){

    }

    public void irPassear(){
        this.estaPasseando = true;
    }
}
