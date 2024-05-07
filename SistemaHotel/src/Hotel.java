import java.util.ArrayList;

public class Hotel {
    private ArrayList<Quarto> quartos = new ArrayList<>();
    public ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private ArrayList<Camareira> camareiras = new ArrayList<>();
    public ArrayList<GrupoHospedes> grupoHospedes = new ArrayList<>();
    public ArrayList<Integer> chavesNaRecepcao = new ArrayList<>();

    public Hotel(ArrayList<GrupoHospedes> grupoHospedes){
        criarRecepcionistas(5);
        criarQuartos(10);
        criarCamareiras(10);
        this.grupoHospedes = grupoHospedes;

        for (Recepcionista recepcionista : recepcionistas) {
            recepcionista.setQuartosDisponiveis(quartos);
        }

//        while (grupoHospedes != null){
//            delegarAlocacaoHospedes();
//        }
    }

//    public void delegarAlocacaoHospedes() {
//        int indexRecepcionista = 0;
//
//        // Para cada grupo de hóspedes
//        for (GrupoHospedes grupo : grupoHospedes) {
//            // Obtém a próxima recepcionista
//            Recepcionista recepcionista = recepcionistas.get(indexRecepcionista);
//
////           if(!recepcionista.estaOcupada) {
//               recepcionista.alocarHospedes(grupo);
//               indexRecepcionista = (indexRecepcionista + 1) % recepcionistas.size();
//               System.out.println("Grupo sendo atendeido pela recepcionista" + recepcionista.getId());
////           }else{
////               grupo.tentativaFalha();
////           }
//        }
//    }

    private void criarRecepcionistas(int quantidade){
        for(int i = 1; i <= quantidade ; i++) {
            recepcionistas.add(new Recepcionista(i, grupoHospedes));
//            System.out.println("Recepcionista " + recepcionistas.get(i - 1).getName() + " criada");
            recepcionistas.get(i - 1).start();
        }
    }

    private void criarQuartos(int quantidade){
        for(int i = 1; i <= quantidade ; i++) {
            quartos.add(new Quarto(i));
//            System.out.println("Quarto " + quartos.get(i  - 1).getNumero() + " criado");
        }
    }

    public void criarCamareiras(int quantidade){
        for(int i = 1; i <= quantidade; i++) {
            camareiras.add(new Camareira(i));
//            System.out.println("Camareira " + quartos.get(i  - 1).getNumero() + " criado");
            camareiras.get(i - 1).start();
        }
    }
}
