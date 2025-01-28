/*
testando 2
Este é um jogo de autoria de Gustavo Oliveira e Rafael Chapman
 */
import java.util.Stack;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private CommandWords commandWords;
    private Stack<Room> roomHistory; //pilha pra armazenar as salas

        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        commandWords = new CommandWords();
        parser = new Parser();
        roomHistory = new Stack<>();
    }

    /**
     * primeira tarefa: trocar as salas
     */
    private void createRooms()
    {
        Room cerebro, medulaEspinhal, pulmao, traqueia, laringe, faringe, esofago, estomago, intestinoDelgado, intestinoGrosso, figado, pancreas, rin, bexiga;

        // as "Salas" são os órgãos, e serão organizados de cima pra baixo
        // sistema nervoso:
        medulaEspinhal = new Room("Seu ponto de partida, a medula espinhal");
        cerebro = new Room("Você está no cérebro");
        pulmao = new Room("Você está no pulmão");
        traqueia = new Room("Você está na traqueia");
        laringe = new Room("Você está na laringe");
        faringe = new Room("Você está na faringe");
        esofago = new Room("Você está no esôfago");
        estomago = new Room("Você está no estômago");
        intestinoDelgado = new Room("Você está no intestino delgado");
        intestinoGrosso = new Room("Você está no intestino grosso");
        figado = new Room("Você está no fígado");
        pancreas = new Room("Você está no pâncreas");
        rin = new Room("Você está no rim");
        bexiga = new Room("Você está na bexiga");

        // criando itens(criar mais depois
        // também é necessário trocar o nome dos itens
        Item item1 = new Item("Um antibiótico que tem a forma de uma espada: ", 0.5);
        Item item2 = new Item("Um remédio que virou um escudo: ", 0.9);

        //adicionando
        cerebro.addItem(item1);
        pulmao.addItem(item2);

        // inicializando saídas das salas
        medulaEspinhal.setExit("cerebro", cerebro); // inicia aqui
        cerebro.setExit("medulaEspinhal", medulaEspinhal);

        // sistema respiratório
        cerebro.setExit("laringe", laringe);
        laringe.setExit("traqueia", traqueia);
        traqueia.setExit("pulmao", pulmao);
        pulmao.setExit("traqueia", traqueia);

        // sistema digestório
        cerebro.setExit("faringe", faringe);
        faringe.setExit("esofago", esofago);
        esofago.setExit("estomago", estomago);
        estomago.setExit("intestinoDelgado", intestinoDelgado);
        estomago.setExit("figado", figado);
        estomago.setExit("pancreas", pancreas);

        intestinoDelgado.setExit("intestinoGrosso", intestinoGrosso);
        //fim do sistema digestório

        figado.setExit("estomago", estomago);
        pancreas.setExit("estomago", estomago);

        // sistema urinário
        medulaEspinhal.setExit("rin", rin);
        rin.setExit("bexiga", bexiga);
        bexiga.setExit("rin", rin);

        currentRoom = medulaEspinhal;  // o jogo começa na medula
    }

    public void play() 
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar. Até logo.");
    }
    /*
    * Localização
    * */

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem vindo ao Imunológico.exe");
        System.out.println("Nós iremos matar um vírus >:)");
        System.out.println("Digite 'ajuda' se precisar de ajuda");
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Tem esse comando aqui não!");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            lookAround();
        }
        else if(commandWord.equals("eat")){
            eat();
        }
        else if(commandWord.equals("back")){
            goBack();
        }
        return wantToQuit;
    }
    private void lookAround(){
        System.out.println(currentRoom.getLongDescription());
    }
    private void eat(){
        System.out.println("Você não está com fome agora");
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Você está perdido. Está sozinho.");
        System.out.println("Os comandos disponíveis são:");
        System.out.println(commandWords.showAll());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // Se não houver um órgão especificado, exibe uma mensagem de erro
            System.out.println("Ir para onde?");
            return;
        }
        String organName = command.getSecondWord(); // Nome do órgão fornecido
        Room nextRoom = currentRoom.getExit(organName); // Obtém a sala correspondente

        if (nextRoom == null) {
            System.out.println("Não há conexão com " + organName + "!");
        } else {
            roomHistory.push(currentRoom); //empilha a sala atual
            currentRoom = nextRoom; // Atualiza a localização do jogador
            printLocationInfo(); // Exibe a nova localização
        }
    }

    private void goBack(){
        if (roomHistory.isEmpty()) {
            System.out.println("Você não pode voltar. Está na sala inicial.");
        } else {
            currentRoom = roomHistory.pop();  // Desempilha a sala anterior
            printLocationInfo();              // Exibe a sala de volta
            System.out.println("Você voltou para a sala anterior.");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
