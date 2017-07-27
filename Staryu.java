import java.io.Serializable;

/**
 * This class creates a brand new Staryu.
 * It extends the Pokemon class.
 * @author Claude Daniel 2017
 */
public class Staryu extends Pokemon implements Water, Serializable {

    /**
     * The constructor for this pokemon.
     * @param name the name of this pokemon.
     * @param hp the randomized hp for this pokemon.
     * @param level the level of this pokemon (1 or 2).
     */
    public Staryu(String name, int hp, int level)  {
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
                System.out.println("Staryu used Water Gun! No one knows where the water came from!");
                damage = waterGun();
            } else if (move == 2) {
                System.out.println("Staryu huffed and puffed and unleashed some bubbles.");
                damage = bubbleBeam();
            } else if (move == 3) {
                System.out.println("Staryu summons a Waterfall upon the enemy!");
                damage = waterfall();
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
    public int waterGun() {
        return rand.nextInt(3) + 3;
    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int bubbleBeam() {
        return rand.nextInt(5) + 5;
    }

    /**
     * One of the pokemon's special attacks.
     * @return a random damage within the given range.
     */
    public int waterfall() {
        return rand.nextInt(5) + 7;
    }


}
