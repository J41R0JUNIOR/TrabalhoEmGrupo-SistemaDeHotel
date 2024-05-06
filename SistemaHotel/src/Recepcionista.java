import java.util.ArrayList;

public class Recepcionista extends Thread{
    Integer id;
    Boolean estaOcupada;
    ArrayList<String> reclamacoes = new ArrayList<>();
    ArrayList<Hospede> grupoEmAtendimento = new ArrayList<>();
    ArrayList<Quarto> quartosDisponiveis; // Lista de quartos disponíveis no hotel

    public Recepcionista(Integer id){
        super(String.valueOf(id));
        this.id = id;
        this.estaOcupada = false;
    }

    @Override
    public void run() {
        while(true){
            // Coloque aqui a lógica de atendimento da recepcionista
//            grupoEmAtendimento.get(0).
        }
    }


    // Método para definir os quartos disponíveis
    public void setQuartosDisponiveis(ArrayList<Quarto> quartosDisponiveis) {
        this.quartosDisponiveis = quartosDisponiveis;
    }

    public void alocarHospedes(GrupoHospedes grupo){
        if (!estaOcupada) {
            estaOcupada = true;
            // Para cada hóspede no grupo
            for (Hospede hospede : grupo.getParticipantes()) {
                // Verifica se ainda há quartos disponíveis
                if (!quartosDisponiveis.isEmpty()) {
                    // Atribui o próximo quarto disponível ao hóspede
                    Quarto quarto = quartosDisponiveis.remove(0); // Remove e retorna o primeiro quarto disponível
                    hospede.setNumeroQuarto(quarto.getNumero());
                    System.out.println("Hóspede " + hospede.getName() + " alocado no quarto " + quarto.getNumero());
                    // Aqui você pode adicionar mais lógica, como definir o grupoEmAtendimento para o grupo atual
                } else {
                    // Se não houver quartos disponíveis, você pode lidar com isso de acordo com sua lógica de negócios
                    System.out.println("Não há quartos disponíveis para alocar o hóspede " + hospede.getName());
                }
            }
            estaOcupada = false;
        } else {
            System.out.println("A recepcionista está ocupada. Aguarde um momento.");
        }
    }

    public void anotarReclamacao(String nomeHospede){
        String reclamacao = "Hospede " + nomeHospede + " fez uma reclamação!";
        reclamacoes.add(reclamacao);
        System.out.println(reclamacao);
    }
}
