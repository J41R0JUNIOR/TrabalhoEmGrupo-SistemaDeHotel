public class Hospede extends Thread { // Classe que representa um hóspede em um hotel

    private int numeroGrupo; // Número do grupo ao qual o hóspede pertence
    private boolean jaPasseou = false; // indica se o hóspede já passeou pela cidade

    public Hospede(int numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    // Método para simular o hóspede passeando pela cidade
    public void passearPelaCidade() {
        // Verifica se o hóspede ainda não passeou
        if (!jaPasseou) {
            try {
                jaPasseou = true;
                // Simula o hóspede passeando pela cidade por 3 segundos
                Thread.sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    // Método para obter o status de se o hóspede já passeou pela cidade
    public boolean getJaPasseou() {
        return jaPasseou;
    }

    // Método para obter o número do grupo ao qual o hóspede pertence
    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    // Método estático para indicar que um grupo retornou para o quarto
    public static void retornarGrupoParaQuarto(int numeroGrupo) {
        // Imprime uma mensagem indicando que o grupo retornou para o quarto
        System.out.println("\n-------------------------------------------------\nGrupo " + numeroGrupo + " retornou.");
    }

    public void retornarParaQuarto() {
        try {
            Thread.sleep(2000); // Aguarda 2 segundos antes de retornar o grupo para o quarto
            retornarGrupoParaQuarto(numeroGrupo); // Chamada ao novo método estático
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
