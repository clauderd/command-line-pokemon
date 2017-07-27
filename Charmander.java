import java.io.Serializable;

/**
 * This class creates a brand new Charmander.
 * It extends the Pokemon class.
 * @author Claude Daniel 2017
 */
public class Charmander extends Pokemon implements Fire, Serializable {

    /**
     * The constructor for this pokemon.
     * @param name the name of this pokemon.
     * @param hp the randomized hp for this pokemon.
     * @param level the level of this pokemon (1 or 2).
     */
    public Charmander(String name, int hp, int level) {
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
                System.out.println(getName()+ " spread threw some little flames on the enemy!");
                damage = ember();
            } else if (move == 2) {
                System.out.println(getName()+ " fired magma-like flame at his opponent!");
                damage = fireBlast();
            } else if (move == 3) {
                System.out.println(getName()+ " channelled his inner Bruce Lee and fire punched his opponent!");
                damage = firePunch();
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
    public int ember() {
        return rand.nextInt(3) + 3;
    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int fireBlast() {
        return rand.nextInt(5) + 5;
    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int firePunch() {
        return rand.nextInt(5) + 7;
    }


}
