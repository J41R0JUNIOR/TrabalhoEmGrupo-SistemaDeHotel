import java.util.List;

public class Grupo {
    private List<Hospede> listaHospedes;
    private Integer id;

    public Grupo(Integer id ,List<Hospede> listaHospedes) {
        this.listaHospedes = listaHospedes;
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Hospede> getListaHospedes() {
        return listaHospedes;
    }

    public void setListaHospedes(List<Hospede> listaHospedes) {
        this.listaHospedes = listaHospedes;
    }

}
