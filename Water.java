/**
 * TThis is the interface for water type pokemon.
 * It contains the required methods for all the
 * water type pokemon.
 * @author Claude Daniel 2017
 */
public interface Water {

    /** The type of this pokemon - fire. */
    int type = 1;

    /** The special attacks menu to be displayed when prompted. */
    String specialMenu = "1. Water Gun\n2. Bubble Beam\n3. Waterfall";

    /** One of the three special attacks for this pokemon. */
    int waterGun();

    /** One of the three special attacks for this pokemon. */
    int bubbleBeam();

    /** One of the three special attacks for this pokemon. */
    int waterfall();
}
