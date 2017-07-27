import java.io.Serializable;

/**
 * This class creates a brand new Oddish.
 * It extends the Pokemon class.
 * @author Claude Daniel 2017
 */
public class Oddish extends Pokemon implements Grass, Serializable {

    /**
     * The constructor for this pokemon.
     * @param name the name of this pokemon.
     * @param hp the randomized hp for this pokemon.
     * @param level the level of this pokemon (1 or 2).
     */
    public Oddish(String name, int hp, int level)  {
        super(name, hp, level);
    }

    /**
     * Gets the type of this particular pokemon
     * @return the type of the pokemon.
     */
    public int getType() {
        return type;
    }

    /**
     * Simply prints out the menu of special
     * attacks for this pokemon.
     */
    void displaySpecialMenu() {
        System.out.println(specialMenu);
    }

    /**
     * Contains process of choosing and using special moves.
     * There are unique descriptions of each attack.
     * @param move choice of the special move.
     * @return the damage caused by the spacial attack.
     */
    int specialFight(int move) {
        {
            int damage = 0;
            if (move == 1) {
                System.out.println(getName()+ " whipped his opponent with his hidden whip!");
                damage = vineWhip();
            } else if (move == 2) {
                System.out.println(getName()+ " threw some sharp leaves at his opponents!");
                damage = razorLeaf();
            } else if (move == 3) {
                System.out.println(getName()+ " charged up and unleashed a powerful beam of light!");
                damage = solarBeam();
            } else {
                System.out.println("Please enter a valid command.");
            }
            return damage;
        }

    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int vineWhip() {
        return rand.nextInt(3) + 3;
    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int razorLeaf() {
        return rand.nextInt(5) + 5;
    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int solarBeam() {
        return rand.nextInt(5) + 7;
    }


}
