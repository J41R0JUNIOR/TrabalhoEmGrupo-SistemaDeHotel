# Trabalho Em Grupo (Sistema De Hotel)

# Controle de Reserva de Quartos em Hotel

Este projeto em Java 17 simula um sistema de reserva e controle de quartos em um hotel, utilizando threads para representar diferentes entidades envolvidas, como hóspedes, camareiras e recepcionistas.

## Participantes
- Gabriel de Oliveira Batista (UC22100698) 
- Guilherme Akio Suguino Sampaio (UC22100169)
- Jairo Pereira de Sousa Júnior (UC22101494)
- Ítalo Cezar Eugênio de Santana (UC22102126)



## Utilização de Branches e "Code With Me"
- **Branches:** Utilizamos o Git para controle de versão e trabalhamos com branches para desenvolvimento paralelo e colaborativo. Cada funcionalidade ou parte do projeto foi desenvolvida em uma branch separada, permitindo uma organização mais eficiente do trabalho.
- **Code With Me:** Aproveitamos o recurso "Code With Me do IntelliJ para facilitar a colaboração em tempo real entre os membros da equipe. Isso nos permitiu compartilhar o ambiente de desenvolvimento e codificar juntos, aumentando a produtividade e a eficiência da equipe.

Essas práticas contribuíram significativamente para a organização e o sucesso do projeto, permitindo uma colaboração fluida e eficaz entre os membros da equipe.
## Funcionalidades e Entidades

### Entidades Representadas:
- **Quarto:** O hotel possui no mínimo 10 quartos, cada um com capacidade para até 4 hóspedes e uma única chave.
- **Hóspede:** Representado por uma thread, com no mínimo 50 hóspedes.
- **Camareira:** Representada por uma thread, com no mínimo 10 camareiras.
- **Recepcionista:** Representada por uma thread, com no mínimo 5 recepcionistas.
- **Hotel:** Representando o hotel em sí, é nele onde fica o core de toda a aplicação.

### Regras do Sistema:
- Vários recepcionistas trabalham juntos para alocar hóspedes apenas em quartos vagos.
- O hotel conta com várias camareiras para limpeza dos quartos.
- Caso um grupo ou uma família possua mais do que 4 membros, eles são divididos em vários quartos.
- Quando os hóspedes saem do hotel para passear, deixam a chave na recepção.
- Uma camareira só pode entrar em um quarto se estiver vago ou os hóspedes não estiverem presentes.
- A limpeza dos quartos é feita sempre após os hóspedes saírem.
- Um quarto vago em limpeza não pode ser alocado para um novo hóspede.
- Se não houver quartos vagos, um hóspede espera em uma fila.
- Se a espera for longa, o hóspede passeia pela cidade e tenta novamente.
- Se após duas tentativas não conseguir um quarto, o hóspede deixa uma reclamação e vai embora.

## Critérios de Avaliação

### Coletivos (3 pontos):
- **Criação das Entidades Propostas:** 0.4
- **Implementação das Funcionalidades/Regras:** 2.0
- **Estruturação e Organização do Código:** 0.5
- **Organização do GitHub (README, Comentários, etc.):** 0.1

### Individuais (1 ponto):
- **Participação no Desenvolvimento do Projeto:** 0.8
- **Participação na Apresentação Final do Projeto:** 0.2

## Observações
- O trabalho é em grupo, mas plágio ou cópia resultará em nota 0.
- Projetos com erro de sintaxe ou não executáveis receberão nota 0.
- A entrega é até 09/05, 23:59, pelo AVA.
- O envio deve conter apenas um link para o repositório público no GitHub, sem arquivos zipados.
  
*Este projeto foi desenvolvido como parte da AT3/N1 - Atividade Prática Coletiva - Bimestre N1.*
