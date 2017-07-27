import java.io.Serializable;
import java.util.*;
import java.awt.*;

/**
 * Created by claudedaniel on 2/27/17.
 */
public class Player extends Trainer implements Serializable{
    /** The number of pokeballs the player has **/
    private int pokeballs = 10;
    /** The number of potions the player has **/
    private int potions = 10;
    /** The amount of money the player has **/
    private int money = 60;

    private Point location;
    private int mapNum = 1;
    Random random = new Random();

    public Player(String name, int hp, Point start) {
        super(name, hp);
        location = start;
    }

    /**
     * Return the attack speech
     * @return the attack speech
     */
    public String getAttackSpeech(){
        return "\n\"Feel the pain!\"\n";
    }
    /**
     * Return the win speech
     * @return the win speech
     */
    public String getWinSpeech(){
        return "\n\"We won! I will be pokemon master in no time\"\n";
    }
    /**
     * Return the loss speech
     * @return the loss speech
     */
    public String getLossSpeech(){
        return "\n\"Nooooooooooooooooo!\nI will be back.\"\n";
    }



    /**
     * Displays relevant stats for the trainer:
     * Name, HP, potions, pokeballs, money and pokemon.
     */
    public void displayStats() {
        System.out.println("HP: " + this.getHp());
        System.out.println("Potions: " + potions);
        System.out.println("Pokeballs: " + pokeballs);
        System.out.println("Money: " + money);
        System.out.println("Pokemon: ");
        displayAllPokemon();
    }

    /**
     * Allows the player to use a potion on
     * any pokemon in the array list.
     */
    public void usePotion() {
        //System.out.println("Which of your Pokemon would you like to use your potion on?: ");
        //displayAllPokemon();
        //pokemon.get(CheckInput.checkIntRange(1, pokemon.size()) - 1).heal();
        potions--;
        System.out.println("Healing process complete!");
        System.out.println("You used one potion. You now have " + potions + " potions left.");
    }

    /**
     * Adds a potion to the player's inventory.
     */
    public void gainPotion() {
        potions++;
    }

    /**
     * Simply get the number of potions in the
     * player's inventory.
     *
     * @return number of potions.
     */
    public int getNumPotions() {
        return potions;
    }

    /**
     * Removes a pokeball from the inventory.
     */
    public void usePokeball() {
        pokeballs--;
    }
    /**
     * Adds a pokeball to the inventory.
     */
    public void gainPokeball() {
        pokeballs++;
    }

    /**
     * Returns the number of pokeballs in the invetory.
     *
     * @return no. of pokeballs
     */
    public int getNumPokeballs() {
        return pokeballs;
    }
    /**
     * Spend a certain amount of money and remove
     * this amount from the total that the player has.
     *
     * @param m the amount to spend.
     */
    public void spendMoney(int m) {
        System.out.println("You spent " + m + " PokeDollars.");
        money = money - m;
        System.out.println("You now have " + money + " PokeDollars...");
    }

    /**
     * The player receives a random amount of money within
     * the given range and adds it to the total.
     *
     * @return the amount of money gained.
     */
    public int getAmtMoney() {
        int randomAmount = random.nextInt(30) + 1;
        money = money + randomAmount;
        return randomAmount;
    }

    /**
     * Get the current location of the player.
     * @return the location in Point format
     */
    public Point getLocation(){
        return location;
    }

    /**
     * Set the location of the player.
     * @param p the desired location for the player
     * @return whether setting was successful
     */
    public boolean setLocation(Point p){
        location = p;
        return true;
    }

    /**
     * Gets the map number that the playe is currently on
     * @return the Map number
     */
    int getMapNum(){
        return mapNum;
    }

    /**
     * Increments the map number once the player
     * reaches the end of the area.
     */
    void incMapNum(){
        if (mapNum == 3){
            mapNum = 1;
        }else {
            mapNum++;
        }
    }

    /**
     * Go north by one spot.
     * @param m the current map
     * @return the character at the new spot.
     */
    public char goNorth(Map m){
        int x = (int)getLocation().getX();
        int y = (int)getLocation().getY();
        if (y == 0){
            System.out.println("You can't travel that way!");
            return 'z';
        } else {
            y = y-1;
        }
        Point p = new Point(x,y);
        setLocation(p);
        return m.getCharAtLoc(p);

    }

    /**
     * Go south by one spot.
     * @param m the current map
     * @return the character at the new spot.
     */
    public char goSouth(Map m){
        int x = (int)getLocation().getX();
        int y = (int)getLocation().getY();
        if (y == 4){
            System.out.println("You can't travel that way!");
            return 'z';
        } else {
            y = y+1;
        }
        Point p = new Point(x,y);
        setLocation(p);
        return m.getCharAtLoc(p);

    }

    /**
     * Go east by one spot.
     * @param m the current map
     * @return the character at the new spot.
     */
    public char goEast(Map m){
        int x = (int)getLocation().getX();
        int y = (int)getLocation().getY();
        if (x == 4){
            System.out.println("You can't travel that way!");
            return 'z';
        } else {
            x = x+1;
        }
        Point p = new Point(x,y);
        setLocation(p);
        return m.getCharAtLoc(p);
    }

    /**
     * Go west by one spot.
     * @param m the current map
     * @return the character at the new spot.
     */
    public char goWest(Map m){
        int x = (int)getLocation().getX();
        int y = (int)getLocation().getY();
        if (x == 0){
            System.out.println("You can't travel that way!");
            return 'z';
        } else {
            x=x-1;
        }
        Point p = new Point(x,y);
        setLocation(p);
        return m.getCharAtLoc(p);
    }

    /**
     * This is where the player chooses the style
     * of move each time the pokemon attacks.
     *
     * @return the choice of style.
     */
    public int chooseStyle() {
        System.out.println("Choose a fighting style:");
        System.out.println("1. Basic\n2. Special");
        return CheckInput.checkIntRange(1, 2);
    }

    /**
     * Here, the player chooses the move.
     * The move is chosen according to the
     * style.
     *
     * @param style the style that was already chosen.
     * @return the chosen move.
     */
    public int chooseMove(int style) {
        int choice = 0;
        if (style == 1) {
            Pokemon.displayBasicMenu();
            choice = CheckInput.checkIntRange(1, 3);
        }
        if (style == 2) {
            getCurrentPokemon().displaySpecialMenu();
            choice = CheckInput.checkIntRange(1, 3);
        }
        return choice;
    }

}
