import java.util.ArrayList;

public class GrupoHospedes {
    ArrayList<Hospede> participantes;
    Integer idGrupo;

    GrupoHospedes(ArrayList<Hospede> participantes, Integer idGrupo){
        this.participantes = participantes;
        this.idGrupo = idGrupo;
    }

    public ArrayList<Hospede> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Hospede> participantes) {
        this.participantes = participantes;
    }
}
