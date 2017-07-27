import java.io.Serializable;
import java.util.*;

/**
 * This trainer class has all methods
 * related to the trainer object.
 * It is used to create a Trainer object.
 *
 * @author Claude Daniel 2017
 */
public abstract class Trainer extends Entity implements Serializable {
    /** The array list containing all of the palyer's pokemon.**/
    private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

    /** The index of the current pokemon **/
    private int currentPokemon = 0;
    Random random = new Random();

    /**
     * The constructor.
     *
     * @param name The trainer's name
     * @param hp   The trainer's hp
     */
    public Trainer(String name, int hp) {
        super(name, hp);
    }

    /**
     * Displays all the pokemon in the array list
     * along with their stats.
     */
    public void displayAllPokemon() {
        for (int i = 0; i < pokemon.size(); i++) {
            System.out.println(i + 1 + ". " + pokemon.get(i).getName() + " [Level: " + pokemon.get(i).getLevel() + ", HP: " + pokemon.get(i).getHp() + "]");
        }
    }

    /**
     * Displays the current pokemon
     */
    public void displayCurrentPokemon() {
        Pokemon current = pokemon.get(currentPokemon);
        System.out.print(current.getName());
    }

    /**
     * lets the user retrieve the current pokemon
     * from the array list.
     *
     * @return the current pokemon.
     */
    public Pokemon getCurrentPokemon() {
        return pokemon.get(currentPokemon);
    }

    /**
     * Allows the player to switch the default or
     * current pokemon.
     *
     * @param cur the desired pokemon to be
     *            set as current.
     * @return the index of the current pokemon.
     */
    public int setCurrentPokemon(int cur) {
        if (pokemon.get(cur - 1).getHp() <= 0) {
            System.out.println("This pokemon has fainted! Choose another pokemon!");
            return -1;
        } else {
            currentPokemon = cur - 1;
        }
        return currentPokemon;
    }

    /**
     * Adds a pokemon to the player's list of pokemon.
     *
     * @param p the pokemon to add to the list.
     */
    public void addPokemon(Pokemon p) {
        pokemon.add(p);
    }

    /**
     * Allows the player to heal the current pokemon.
     */
    public void healCurrentPokemon() {
        pokemon.get(currentPokemon).heal();
    }

    /**
     * Allows the user to heal all the pokemon.
     * This is called when the player visits a
     * pokemon center.
     */
    public void healAllPokemon() {
        for (int i = 0; i < pokemon.size(); i++) {
            pokemon.get(i).heal();
        }
    }


    /**
     *
     * Abstract method for where the player chooses the style
     * of move each time the pokemon attacks.

     */
    abstract int chooseStyle() ;

    /**
     * Abstract method for choosing the move.
     * The move is chosen according to the
     * style.
     *
     * @param style the style that was already chosen.
     * @return the chosen move.
     */
    abstract int chooseMove(int style);

    /**
     * This is where the chosen style and move
     * are put into effect to retrieve the
     * relevant damage.
     *
     * @return the damage from the move.
     */
    public int battle() {
        int style = chooseStyle();
        int move = chooseMove(style);
        return pokemon.get(currentPokemon).fight(style, move);
    }

    /**
     * Returns the number of pokemon the player owns.
     *
     * @return no. of pokemon.
     */
    public int getNumberOfPokemon() {
        return pokemon.size();
    }
    /** An abstract method for getting the attack speech */
    abstract String getAttackSpeech();

    /** An abstract method for getting the attack speech */
    abstract String getWinSpeech();

    /** An abstract method for getting the attack speech */
    abstract String getLossSpeech();

}