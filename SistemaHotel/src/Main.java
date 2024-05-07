import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Hospede> hospedes = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            hospedes.add(new Hospede(i));
        }

        // Criar grupos de hóspedes aleatoriamente
        ArrayList<GrupoHospedes> grupos = new ArrayList<>();
        Random rand = new Random();

        int indexGroupId = 1;
        while (!hospedes.isEmpty()) {
            ArrayList<Hospede> hospedesGrupo = new ArrayList<>();

            // Gera uma quantidade aleatória de hóspedes para o grupo atual
            if (hospedes.size() > 0) {
                int tamanhoGrupo = rand.nextInt(hospedes.size()) + 1;

                if (tamanhoGrupo > 4) {
                    tamanhoGrupo = 4;
                }

                // Adiciona hóspedes ao grupo
                for (int i = 0; i < tamanhoGrupo; i++) {
                    hospedes.get(0).groupId = indexGroupId;
                    hospedesGrupo.add(hospedes.remove(0)); // Remove e adiciona o primeiro hóspede da lista
                }

                GrupoHospedes novoGrupo = new GrupoHospedes(hospedesGrupo, indexGroupId);

                grupos.add(novoGrupo);

                // Index geral do grupo
                indexGroupId += 1;
            }
        }

        // Embaralhar os grupos
        Collections.shuffle(grupos);

        System.out.println(grupos.size());
        Hotel hotel = new Hotel(grupos);
    }
}
