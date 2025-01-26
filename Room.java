import java.util.HashMap;
import java.util.Set;

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
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys){
            exitString += " " + exit;
        }
        return exitString; // Lista os nomes das saídas
    }


    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getLongDescription(){
        return "You are "+ description + ".\n" + getExitString();

    }


}
