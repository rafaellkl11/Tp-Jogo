import java.util.Scanner;
import java.util.ArrayList;

public class Player {
    private String name; // Nome do jogador
    private String local; 
    private Room currentRoom; // Localização atual do jogador
    private ArrayList<Item> inventario; // Lista onde os itens do jogador serão armazenados
    private float cargaMaxima = 2.0f;
    private float cargaAtual = 0.1f;
    private boolean comerCogumelo = false;

    //Criar nome do jogador
    public Player() {
        this.name = name;
        this.currentRoom = getDefaultStartingRoom(); // Define a sala padrão como inicial
        this.inventario = new ArrayList<Item>();
    }

    /**
     * Retorna o nome do jogador.
     * @return Nome do jogador.
     */
    public String getName() {
        System.out.println("Digite seu nome: ");
        Scanner teclado = new Scanner (System.in);
        name = teclado.nextLine();
        return name;
    }

    //comer cogumelo que aumenta o peso que pode carregar
    public void consumivel(){
        if(comerCogumelo){
            System.out.println("Ou você já comeu um cogumelo ou então não comeu ainda");
        }
        else{
            comerCogumelo = true;
            cargaMaxima += 1.0f;  // Aumenta a capacidade de carga em 1kg
            System.out.println("Você comeu o cogumelo mágico e agora pode carregar mais peso!");
        }
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
    public String setCurrentRoom(String room) {
        local = room;
        return local;
    }

    /**
     * Retorna a sala padrão inicial para o jogador.
     * @return A sala padrão inicial.
     */
    private Room getDefaultStartingRoom() {
        // Substitua com a lógica ou referência à sala inicial padrão do jogo
        return new Room("Seu ponto de partida, a medula espinhal");
    }

    public float getCargaMaxima(){
        return cargaMaxima;
    }

    public float getCargaAtual(){
        return cargaAtual;
    }

    public void setCargaAtual(float carga){
        cargaAtual = carga;
    }

    // Método para pegar um item, levando em consideração o peso máximo
    public void take(String itemName) {
        Room room = currentRoom;
        Item itemToTake = null;

        // Verifica se o item existe na sala atual
        for (Item item : room.getItems()) {
            if (item.getDescription().equals(itemName)) {
                itemToTake = item;
                break;
            }
        }

        // Se o item não for encontrado
        if (itemToTake == null) {
            System.out.println("Item não encontrado na sala.");
            return;
        }

        // Verifica se o peso do item pode ser adicionado ao peso atual
        if (cargaAtual + itemToTake.getWeight() > cargaMaxima) {
            System.out.println("Não é possível pegar esse item, o peso máximo seria excedido.");
        } else {
            // Adiciona o item ao inventário e atualiza o peso
            addItem(itemToTake);
            System.out.println("Você pegou o " + itemToTake.getDescription() + "!");
        }
    }

    // Método para adicionar um item ao inventário
    private void addItem(Item item) {
        inventario.add(item);  // Adiciona o item ao inventário
        cargaAtual += item.getWeight();  // Atualiza o peso atual do jogador
    }

    // Método para listar todos os itens que o jogador está carregando
    public void showItems() {
        if (inventario.isEmpty()) {
            System.out.println("Você não está carregando nenhum item.");
            return;
        }

        System.out.println("Itens no seu inventário:");
        float totalWeight = 0.0f;

        // Exibe todos os itens e calcula o peso total
        for (Item item : inventario) {
            System.out.println(item.getItemInfo());
            totalWeight += item.getWeight();
        }

        System.out.println("Peso total: " + totalWeight + "kg");
    }

}
