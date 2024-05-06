import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Hospede> hospedes = new ArrayList<>();

        for(int i = 1; i <= 50; i++){
            hospedes.add(new Hospede(i));
            System.out.println("Hóspede " + hospedes.get(i-1).getName() + " adicionado");
        }

        // Criar grupos de hóspedes aleatoriamente
        ArrayList<GrupoHospedes> grupos = new ArrayList<>();
        Random rand = new Random();
        int maxGrupos = 10;

        // Índice para percorrer a lista de hóspedes
        int indexHospede = 0;

        while (!hospedes.isEmpty()) {
            ArrayList<Hospede> hospedesGrupo = new ArrayList<>();

            // Gera uma quantidade aleatória de hóspedes para o grupo atual
            int tamanhoGrupo = rand.nextInt(hospedes.size()) + 1;

            // Adiciona hóspedes ao grupo
            for (int i = 0; i < tamanhoGrupo; i++) {
                hospedesGrupo.add(hospedes.remove(0)); // Remove e adiciona o primeiro hóspede da lista
            }

            grupos.add(new GrupoHospedes(hospedesGrupo));
            System.out.println("Grupo de Hóspedes criado com " + tamanhoGrupo + " membros");
        }
        
        Hotel hotel = new Hotel(grupos);
    }
}
