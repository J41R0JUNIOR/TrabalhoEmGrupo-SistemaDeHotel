import java.util.ArrayList;

public class Hotel {
    private final ArrayList<Quarto> quartos = new ArrayList<Quarto>();
    private final ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private final ArrayList<Camareira> camareiras = new ArrayList<>();
//    private final Lock lockRecepcao;
//    private final Lock lockCamareiras;

    public Hotel(){

    //adicionando os quartos
    for(int i = 1; i <= 10; i++) {

        quartos.add(new Quarto(i));
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
