import java.util.ArrayList;

public class Hotel {
    public ArrayList<Quarto> quartos = new ArrayList<Quarto>();
    public ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    public ArrayList<Camareira> camareiras = new ArrayList<>();
    
    public Hotel(){
        criarRecepcionistas(5);
        criarQuartos(10);
        criarCamareiras(10);

    }

    private void criarRecepcionistas(int quantidade){
        //adicionando os recepcionistas
        for(int i = 1; i <= quantidade ; i++) {
            recepcionistas.add(new Recepcionista(i));
            System.out.println("Recepcionista " + recepcionistas.get(i - 1).getName() + " criada");
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

        }
    }
}
