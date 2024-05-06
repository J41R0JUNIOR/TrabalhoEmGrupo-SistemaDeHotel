import java.util.ArrayList;

public class Hotel {
    private ArrayList<Quarto> quartos = new ArrayList<>();
    public ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private ArrayList<Camareira> camareiras = new ArrayList<>();

    public ArrayList<GrupoHospedes> grupoHospedes = new ArrayList<>();



    public Hotel(ArrayList<GrupoHospedes> grupoHospedes){
        criarRecepcionistas(5);
        criarQuartos(10);
        criarCamareiras(10);
        this.grupoHospedes = grupoHospedes;

    }

    public  void adicionarHospedes(int quantidadeGrupo){

    }


    private void limparQuarto(Quarto quarto){
        //limpando o quarto
        if (!quarto.isLimpo()){
            quarto.setLimpo(true);
        }
    }

    private void criarRecepcionistas(int quantidade){
        //adicionando os recepcionistas
        for(int i = 1; i <= quantidade ; i++) {
            recepcionistas.add(new Recepcionista(i));
            System.out.println("Recepcionista " + recepcionistas.get(i - 1).getName() + " criada");
            recepcionistas.get(i - 1).start();
        }
    }

    private void criarQuartos(int quantidade){
        //adicionando os quartos
        for(int i = 1; i <= quantidade ; i++) {
            quartos.add(new Quarto(i));
            System.out.println("Quarto " + quartos.get(i  - 1).getNumero() + " criado");
        }
    }

    public void criarCamareiras(int quantidade){
        //adicionando as camareiras
        for(int i = 1; i <= quantidade; i++) {
            camareiras.add(new Camareira(i));
            System.out.println("Camareira " + quartos.get(i  - 1).getNumero() + " criado");
            camareiras.get(i - 1).start();
        }
    }
}
