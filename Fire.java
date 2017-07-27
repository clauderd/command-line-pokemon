
/**
 * TThis is the interface for fire type pokemon.
 * It contains the required methods for all the
 * fire type pokemon.
 * @author Claude Daniel 2017
 */
public interface Fire {

    /** The type of this pokemon - fire. */
    int type = 0;

    /** The special attacks menu to be displayed when prompted. */
    String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";

    /** One of the three special attacks for this pokemon. */
    int ember();

    /** One of the three special attacks for this pokemon. */
    int fireBlast();

    /** One of the three special attacks for this pokemon. */
    int firePunch();
}
