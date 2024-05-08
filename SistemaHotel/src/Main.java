import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        List<Hospede> hospedes = criarHospedes(50);

        Hotel hotel = new Hotel(hospedes);


        List<Recepcionista> recepcionistas = criarRecepcionista(5);
        List<Quarto> quartos = criarQuartos(10);

        System.out.println(criarGrupos(hospedes).size());


    }

    public static List<Grupo> criarGrupos(List<Hospede> hospedes) {
        // Criar grupos de hóspedes aleatoriamente
        ArrayList<Grupo> grupos = new ArrayList<>();
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

                Grupo novoGrupo = new Grupo(indexGroupId, hospedesGrupo);

                grupos.add(novoGrupo);

                // Index geral do grupo
                indexGroupId += 1;
            }
        }
        return grupos;
    }

    public static List<Hospede> criarHospedes(int quantidade) {
        List<Hospede> criandoHospedes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoHospedes.add(new Hospede(i));
        }
        return criandoHospedes;
    }

    public static List<Recepcionista> criarRecepcionista(int quantidade) {
        List<Recepcionista> criandoRecepcionistas = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoRecepcionistas.add(new Recepcionista(i));
        }
        return criandoRecepcionistas;
    }

    public static List<Quarto> criarQuartos(int quantidade) {
        List<Quarto> criandoQuartos = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            criandoQuartos.add(new Quarto(i));
        }
        return criandoQuartos;
    }
}
