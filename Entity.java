import java.io.*;

/**
 * This Entity class is built upon by
 * the Pokemon and Trainer classes.
 *
 * @author Claude Daniel 2017
 */
public abstract class Entity implements Serializable {
    /** The entity's name */
    private String name;
    /** The entity's hp */
    private int hp;
    /** The entity's max HP. */
    private int maxHp;

    /**
     * This is the constructor
     *
     * @param name The name of the entity (trainer or pokemon).
     * @param hp   The hp of the entity.
     */
    public Entity(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
    }

    /**
     * Getter for the entity name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the entity hp.
     *
     * @return the hp.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Getter for the entity's max hp.
     *
     * @return the max hp.
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Setter for the entity hp.
     * It is worth noting that this is used to
     * double the max hp when the pokemon levels up.
     *
     * @param maxHp desired max HP.
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Used to have the entity take damage.
     *
     * @param hit the damage received.
     * @return the damage received.
     */
    public int loseHp(int hit) {
        hp = hp - hit;
        return hit;
    }

    /**
     * Completely heals the entity to
     * its max HP.
     */
    public void heal() {
        hp = maxHp;
    }
}
