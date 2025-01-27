import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class Room {

    private String description;
    //usando o hashmap para podermos armazenar as salas e as suas saídas
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;


    //criando uma sala
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public String getExitString() {
        StringBuilder exitString = new StringBuilder("Saídas:");

        Set<String> keys = exits.keySet(); // Obtém todas as direções das saídas
        for (String exit : keys) {
            exitString.append(" ").append(exit); // Concatena a direção
        }

        return exitString.toString(); // Retorna a lista das saídas
    }

    // Retorna os itens da sala
    public String getItemString(){
        if(items.isEmpty()){
            return "Não há itens na sala";
        }
        StringBuilder itemString = new StringBuilder("Itens: ");
        for(Item item: items){
            itemString.append(item.getItemInfo()).append(" ");
        }
        return itemString.toString().trim();
    }
    public String getDescription()
    {
        return description;
    }

    public String getLongDescription() {
        String longDescription = "Você está " + description + ".\n" + getExitString();
        longDescription += "\n" + getItemString(); // Adicionando a lista de itens na descrição
        return longDescription;
    }
}
