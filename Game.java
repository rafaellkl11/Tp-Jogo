/*
testando 2
Este é um jogo de autoria de Gustavo Oliveira e Rafael Chapman
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
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

    /**
     *  Main play routine.  Loops until end of play.
     */
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
        System.out.println("Thank you for playing.  Good bye.");
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
            System.out.println("I don't know what you mean...");
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

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
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
            currentRoom = nextRoom; // Atualiza a localização do jogador
            printLocationInfo(); // Exibe a nova localização
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
