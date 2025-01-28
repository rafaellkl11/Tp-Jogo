
/**
 * Escreva uma descrição da classe GameMain aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GameMain
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private Game game;
    private Player player;
    /**
     * Construtor para objetos da classe GameMain
*/
    public GameMain()
    {
        Player player = new Player();
        player.getName();
        Game game = new Game();
        game.play();
    }

}
