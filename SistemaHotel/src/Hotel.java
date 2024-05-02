import java.util.ArrayList;

public class Hotel {
    public ArrayList<Quarto> quartosLivres = new ArrayList<Quarto>();
    public ArrayList<Quarto> quartosOcupados = new ArrayList<Quarto>();
    public ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    public ArrayList<Camareira> camareiras = new ArrayList<>();
//    private  Lock lockRecepcao;
//    private  Lock lockCamareiras;

    public Hotel(){

    //adicionando os quartos
    for(int i = 1; i <= 10; i++) {

        quartosLivres.add(new Quarto(i));
    }

    //adicionando os recepcionistaas
//    for(int i = 1; i <= 10; i++) {
//        recepcionistas.add(new Recepcionista(i));
//    }
//
    //adicionando as camareiras
    for(int i = 1; i <= 10; i++) {
        camareiras.add(new Camareira(i));
    }





    }
}
