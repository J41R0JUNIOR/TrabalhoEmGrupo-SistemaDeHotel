// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        for(int i = 1; i <= 50; i++){
            new Hospede(i).start();
        }
        for (Camareira camareira : hotel.camareiras) {
            camareira.start();
        }
        for (Recepcionista recepcionista : hotel.recepcionistas) {
            recepcionista.start();
        }
    }
}