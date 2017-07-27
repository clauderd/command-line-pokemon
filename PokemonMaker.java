import java.io.Serializable;
import java.util.*;

/**
 * This class is used to create starter and
 * wild pokemon.
 *
 * @author Claude Daniel 2017
 */

public class PokemonMaker implements Serializable{

    /**
     * This method creates a completely random
     * wild pokemon of random type and hp.
     *
     * @return the created pokemon.
     */
   public static Pokemon makeWildPokemon() {
        Random rand = new Random();
        int choiceOfWildPokemon = rand.nextInt(6) + 1;
        int randomLevelChoice = rand.nextInt(2) + 1;
        Pokemon theChosenOne = null;
        switch (choiceOfWildPokemon) {
            case 1:
                theChosenOne = new Charmander("Charmander", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                break;
            case 2:
                theChosenOne = new Squirtle("Squirtle", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                break;
            case 3:
                theChosenOne = new Bulbasaur("Bulbasaur", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                break;
            case 4:
                theChosenOne = new Oddish("Oddish", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                break;
            case 5:
                theChosenOne = new Ponyta("Ponyta", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                break;
            case 6:
                theChosenOne = new Staryu("Staryu", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                break;
        }
        return theChosenOne;
    }

    /**
     * This method created a starter pokemon
     * of the player's choosing.
     *
     * @param start the choice of starter pokemon.
     * @return the created starter pokemon.
     */
    public static Pokemon makeStartPokemon(int start) {
        Random rand = new Random();
        Pokemon theChosenOne = null;
        switch (start) {
            case 1:
                theChosenOne = new Charmander("Charmander", rand.nextInt(21) + 50, 2);
                break;
            case 2:
                theChosenOne = new Squirtle("Squirtle", rand.nextInt(21) + 50, 2);
                break;
            case 3:
                theChosenOne = new Bulbasaur("Bulbasaur", rand.nextInt(21) + 50, 2);
        }
        return theChosenOne;
    }

    /**
     * This ethod creates a pokemon of
     * the user's choice.
     *
     * @param type the type of pokemon wanted.
     * @return the created pokemon.
     */
    public static Pokemon makeTypePokemon(int type) {
        Random rand = new Random();
        int randomLevelChoice = rand.nextInt(2) + 1;
        int thePokemonOfType = rand.nextInt(2);
        Pokemon theChosenOne = null;
        switch (type) {
            case 1:
                if (thePokemonOfType == 0) {
                    theChosenOne = new Charmander("Charmander", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                } else {
                    theChosenOne = new Ponyta("Ponyta", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                }
                break;
            case 2:
                if (thePokemonOfType == 0) {
                    theChosenOne = new Squirtle("Squirtle", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                } else {
                    theChosenOne = new Staryu("Staryu", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                }
                break;
            case 3:
                if (thePokemonOfType == 0) {
                    theChosenOne = new Bulbasaur("Bulbasaur", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                } else {
                    theChosenOne = new Oddish("Oddish", (rand.nextInt(6) + 20) * randomLevelChoice, randomLevelChoice);
                }
                break;
        }
        return theChosenOne;
    }
}
