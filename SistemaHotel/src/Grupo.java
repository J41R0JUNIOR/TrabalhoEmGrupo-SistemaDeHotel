import java.util.ArrayList;
import java.util.List;

public class Grupo {
    public List<Hospede> listaHospedes;
    private Integer id;
    public Boolean estaAlocado = false;

    public Grupo(Integer id, List<Hospede> listaHospedes) {
        this.listaHospedes = listaHospedes;
        this.id = id;

    }

    public Boolean getEstaAlocado() {
        return estaAlocado;
    }

    public Integer getId() {
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
