import java.util.ArrayList;

public class GrupoHospedes {
    ArrayList<Hospede> participantes;
    Integer idGrupo;
    Integer qtdTentativas;
    Integer numeroQuarto;
    Boolean estaPasseando;

    GrupoHospedes(ArrayList<Hospede> participantes, Integer idGrupo){
        this.participantes = participantes;
        this.idGrupo = idGrupo;
        this.qtdTentativas = 0;
        this.numeroQuarto = 0;
        this.estaPasseando = false;
    }

    public ArrayList<Hospede> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Hospede> participantes) {
        this.participantes = participantes;
    }

    public void tentativaFalha(){
        qtdTentativas += 1;

        if(qtdTentativas >= 2){
            irEmbora();
        }else{
//            irPassear();
            this.estaPasseando = true;
        }
    }

    public void irEmbora(){

    }

    public void irPassear(){

    }
}
