import java.util.Scanner;

public class Player {
    private String name; // Nome do jogador
    private String local; 
    private Room currentRoom; // Localização atual do jogador
    private String [] inventario = new String [2];
    private float [] inventario2 = new float [2];

    /**
     * Construtor para criar um jogador com nome e uma localização padrão inicial.
     * @param name Nome do jogador.
     */
    public Player() {
        this.name = name;
        this.currentRoom = getDefaultStartingRoom(); // Define a sala padrão como inicial
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
    
    public void take(){
        Scanner teclado = new Scanner (System.in);
        String sala = local;
        System.out.println("Digite qual item deseja pegar: ");
        String item = teclado.nextLine();
        System.out.println(sala);
        
        if (sala.equals("Você está no cérebro")){
            if (item.equals("antibiótico")){
                System.out.println("Você pego o antibiótico!");
                inventario[0] = item;
                inventario2[0] += 0.5;
                peso();
            }
            else {
                System.out.println("Não existe esse item");
            }
        }
        
        else if (sala.equals("Você está no pulmão")){
            if (item.equals("remédio")){
                System.out.println("Você pego o remédio!");
                inventario [1] = item; 
                inventario2 [1] += 0.9;
                peso();
            }
            else {
                System.out.println("Não existe esse item");
            }
        }
        else {
            System.out.println("Não existe itens.");
        }
        
    }
    
    public void drop(){
        Scanner teclado = new Scanner (System.in);
        
        for (int i = 0 ; i < inventario.length ; i++){
            System.out.print((i+1)+". "+inventario[i]+" ");
        }
        
        System.out.println("Digite o número do item que deseja dropar: ");
        int num = teclado.nextInt();
        
        inventario[(num-1)] = null;
    }
    
    public void peso(){
        if ((inventario2[0]+inventario2[1]) > 2){
            if(inventario2[0] > 1){
                inventario2[0] = 1;}
                
            if(inventario2[1] > 1){
                inventario2[1] = 1;
            }
        }
    }
    
    public void consumivel(){
        
    }
}
