import java.util.Scanner;

public class Player {
    private String name; // Nome do jogador
    private Room currentRoom; // Localização atual do jogador

    /**
     * Construtor para criar um jogador com nome e uma localização padrão inicial.
     * @param name Nome do jogador.
     */
    public Player() {
        System.out.println("Digite seu nome: ");
        Scanner teclado = new Scanner (System.in);
        name = teclado.nextLine();
        this.name = name;
        this.currentRoom = getDefaultStartingRoom(); 
        System.out.println(name);// Define a sala padrão como inicial
    }

    /**
     * Retorna o nome do jogador.
     * @return Nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Atualiza o nome do jogador.
     * @param name Novo nome do jogador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna a localização atual do jogador.
     * @return Sala atual do jogador.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Atualiza a localização atual do jogador.
     * @param room Nova sala do jogador.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Retorna a sala padrão inicial para o jogador.
     * @return A sala padrão inicial.
     */
    private Room getDefaultStartingRoom() {
        // Substitua com a lógica ou referência à sala inicial padrão do jogo
        return new Room("Seu ponto de partida, a medula espinhal");
    }
}
