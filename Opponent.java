import java.io.Serializable;
import java.util.Random;

/**
 * The basis for an opponent
 * Created by claudedaniel on 2/27/17.
 */
public class Opponent extends Trainer implements Serializable{

    /**
     * The opponent's attack speech.
     */
    String atkSpeech;
    /**
     * The opponent's win speech.
     */
    String winSpeech;
    /**
     * The opponent's loss speech.
     */
    String lossSpeech;
    Random random = new Random();

    /**
     * The constructor for the opponent. Assigns a random
     * pokemon to opponent.
     * @param name Name of the opponent
     * @param hp HP of the opponent
     * @param atk Attack speech of the opponent
     * @param win Win speech of the opponent
     * @param loss Loss speech of the opponent
     */
    public Opponent(String name, int hp, String atk, String win, String loss){
        super(name, hp);
        atkSpeech = atk;
        winSpeech = win;
        lossSpeech = loss;
        addPokemon(PokemonMaker.makeWildPokemon());
    }

    /**
     * Return the attack speech
     * @return the attack speech
     */
    public String getAttackSpeech(){
        return atkSpeech;
    }
    /**
     * Return the win speech
     * @return the win speech
     */
    public String getWinSpeech(){
        return winSpeech;
    }
    /**
     * Return the loss speech
     * @return the loss speech
     */
    public String getLossSpeech(){
        return lossSpeech;
    }

    /**
     * Chooses a random style for th eopponent
     * @return the chosen style
     */
    public int chooseStyle(){
        return (random.nextInt(2)+1);
    }

    /**
     * Chooses a random move for the opponent
     * @param style the style that was already chosen.
     * @return
     */
    public int chooseMove(int style){
        return (random.nextInt(3) + 1);
    }
}
