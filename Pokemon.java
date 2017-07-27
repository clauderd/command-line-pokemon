import java.io.*;
import java.util.*;

/**
 * This class creates the Pokemon object.
 * It extends the entity class.
 *
 * @author Claude Daniel 2017
 */

public abstract class Pokemon extends Entity implements Serializable {
    /** The level of the pokemon. */
    private int level;
    /** The general basic fight menu. */
    private static final String basicMenu = "1. Slam\n2. Tackle\n3. Punch";
    /** The hp of the pokemon. */
    private int hp;
    Random rand = new Random();

    /**
     * The constructor for a pokemon.
     *
     * @param name  the name of the pokemon.
     * @param hp    the hp of the pokemon.
     * @param level the level of the pokemon (1 or 2)
     */
    public Pokemon(String name, int hp, int level) {
        super(name, hp);
        this.level = level;
    }

    /**
     * An abstract method for the getter for the pokemon's type.
     *
     * @return the type of the pokemon.
     */
    abstract int getType();

    /**
     * An abstract method for displaying the special menu.
     */
    abstract void displaySpecialMenu();

    /**
     * An abstract method for the process of using special attacks.
     *
     * @param move the choice of special attack.
     * @return the damage from the attack.
     */
    abstract int specialFight(int move);

    /**
     * Gets the level of the pokemon.
     *
     * @return level of the pokemon.
     */
    int getLevel() {
        return level;
    }

    /**
     * Increases the level of the pokemon by 1.
     * This method is only called when the current level
     * of the pokemon is 1.
     */
    void gainLevel() {
        level = level + 1;
    }

    /**
     * Displays the pokemon's name,
     * level and hp.
     */
    void displayStats() {
        System.out.print(getName() + " [Level: " + level + ", HP: " + getHp() + "]");
    }

    /**
     * Displays the choices for basic attacks
     * for the pokemon.
     */
    static void displayBasicMenu() {
        System.out.printf(basicMenu);
    }

    /**
     * The process of choosing and using basic attacks.
     * Every pokemon regardless of type can access these.
     *
     * @param move the choice of basic attack.
     * @return the damage caused by the chosen attack.
     */
    int basicFight(int move) {
        int damage = 0;
        if (move == 1) {
            System.out.println(getName() + " ran full speed ahead and slammed the opponent!");
            damage = slam();
        } else if (move == 2) {
            System.out.println(getName() + " used tackle since it is still a beginner!");
            damage = tackle();
        } else if (move == 3) {
            System.out.println(getName() + " went karate-mode and used punch!");
            damage = punch();
        } else {
            System.out.println("Please enter a valid command.");
        }
        return damage;
    }

    /**
     * The process to choose a style and initiate
     * the fight.
     *
     * @param style choice of style.
     * @param move  choice of attack.
     * @return the final damage (adjusted for the level
     * of the attacking pokemon).
     */
    int fight(int style, int move) {
        int finaldamage = 0;
        if (style == 1) {
            finaldamage = basicFight(move);
        } else if (style == 2) {
            finaldamage = specialFight(move);
        } else {
            System.out.println("Please enter a valid command.");
        }
        return finaldamage * level;
    }

    /**
     * A simple basic attack available to all pokemon.
     *
     * @return a random damage within the specified range.
     */
    int slam() {
        return rand.nextInt(3) + 3;
    }

    /**
     * A simple basic attack available to all pokemon.
     *
     * @return a random damage within the specified range.
     */
    int tackle() {
        return rand.nextInt(3) + 5;
    }

    /**
     * A simple basic attack available to all pokemon.
     *
     * @return a random damage within the specified range.
     */
    int punch() {
        return rand.nextInt(5) + 7;
    }
}
