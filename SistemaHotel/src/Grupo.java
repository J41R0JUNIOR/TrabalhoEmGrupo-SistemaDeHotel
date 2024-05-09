public class Grupo extends Thread {
    public List<Hospede> listaHospedes;
    private Integer id;
    public Boolean estaAlocado = false;
    public Integer tentativas = 0;

    public Grupo(Integer id, List<Hospede> listaHospedes) {
        this.listaHospedes = listaHospedes;
        this.id = id;
        this.start(); // Iniciar a execução da thread
    }

    @Override
   public void run() {
        while (true) {
            if (estaAlocado) {
                try {
                    Thread.sleep(3000); // Espera por 3 segundos (3000 milissegundos)
                    System.out.println("Grupo " + id + " está indo passear.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
