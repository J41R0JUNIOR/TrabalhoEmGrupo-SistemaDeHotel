public class Camareira extends  Thread{
    private Integer id;

    public Camareira(Integer id){
        super(String.valueOf(id));
        this.id = id;
    }

    @Override
    public void run() {

    }

    public void limparQuarto(){

    }
}

