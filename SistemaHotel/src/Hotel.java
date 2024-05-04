import java.util.ArrayList;

public class Hotel {
    public ArrayList<Quarto> quartos = new ArrayList<Quarto>();
    public ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    public ArrayList<Camareira> camareiras = new ArrayList<>();

    public ArrayList<Hospede> hospedes = new ArrayList<>();

    public Hotel(ArrayList<Hospede> hospedes){
        criarRecepcionistas(5);
        criarQuartos(10);
        criarCamareiras(10);
        this.hospedes = hospedes;

    }

    private void limparQuarto(){

    }

    // Adiciona um novo hóspede ao hotel
    public synchronized void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
        System.out.println("Hóspede " + hospede.getName() + " adicionado ao hotel");
    }

    // Retorna o próximo hóspede a ser alocado
    public synchronized Hospede proximoHospede() {
        if (!hospedes.isEmpty()) {
            return hospedes.remove(0);
        }
        return null; // Retorna null se não houver mais hóspedes a serem alocados
    }

    private void criarRecepcionistas(int quantidade){
        //adicionando os recepcionistas
        for(int i = 1; i <= quantidade ; i++) {
            recepcionistas.add(new Recepcionista(i, this));
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
