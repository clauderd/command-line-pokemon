/**
 * TThis is the interface for grass type pokemon.
 * It contains the required methods for all the
 * grass type pokemon.
 * @author Claude Daniel 2017
 */
public interface Grass {

    /** The type of this pokemon - fire. */
    int type = 2;

    /** The special attacks menu to be displayed when prompted. */
    String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";

    /** One of the three special attacks for this pokemon. */
    int vineWhip();

    /** One of the three special attacks for this pokemon. */
    int razorLeaf();

    /** One of the three special attacks for this pokemon. */
    int solarBeam();
}
