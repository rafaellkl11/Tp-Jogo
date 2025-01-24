import java.util.HashMap;

public class Room {

    private String description;
    //usando o hashmap para podermos armazenar as salas e as suas saídas
    private HashMap<String, Room> exits;

    //criando uma sala
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getExitString() {
        return String.join(", ", exits.keySet()); // Lista os nomes das saídas
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }


}
