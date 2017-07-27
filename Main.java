import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * The main class that runs the Pokemon game.
 *
 * @author Claude Daniel 2017
 */

public class Main {

    /**
     * Displays the main recurring menu that the player uses to play the game.
     * Big methods are created separately while small tasks are included within the menu.
     */
    public static void main(String[] args) {

        /** The player's name - to be input by the user */
        String playerName;
        Scanner in = new Scanner(System.in);

        System.out.println("______     _                              \n" +
                "| ___ \\   | |                             \n" +
                "| |_/ /__ | | _____ _ __ ___   ___  _ __  \n" +
                "|  __/ _ \\| |/ / _ \\ '_ ` _ \\ / _ \\| '_ \\ \n" +
                "| | | (_) |   <  __/ | | | | | (_) | | | |\n" +
                "\\_|  \\___/|_|\\_\\___|_| |_| |_|\\___/|_| |_|\n");
        Map currentMap = new Map();
        currentMap.generateArea(1);
        File f = new File( "pokemon.dat" );
        Player player = new Player("Ash Ketchum", 100, currentMap.findStartLocation());
        if( f.exists() ) {
            System.out.println("A saved file was found. Would you like to continue playing?\n1. Yes\n2. No");
            int continueGameChoice = CheckInput.checkIntRange(1, 2);
            if (continueGameChoice == 1) {
                try {
                    ObjectInputStream inSaveData = new ObjectInputStream(new FileInputStream(f));
                    player = (Player)inSaveData.readObject();
                    inSaveData.close();
                } catch (IOException e) {
                    System.out.println("Error processing file.");
                } catch (ClassNotFoundException e) {
                    System.out.println("Could not find class.");
                }
            } else {
                System.out.println("What is your name, young trainer?: ");
                playerName = in.nextLine();
                System.out.println("Welcome to your adventure " + playerName + "!");

                player = new Player(playerName, 100, currentMap.findStartLocation());

                System.out.println("You are now travelling the world looking for Pokemon and battles, and to become the pokemon Champion!");
                System.out.println("Choose your very first Pokemon!");
                System.out.println("1. Charmander\n2. Squirtle\n3. Bulbasaur");
                player.addPokemon(PokemonMaker.makeStartPokemon(CheckInput.checkIntRange(1, 3)));
                System.out.print("You chose ");
                player.displayCurrentPokemon();
                System.out.print("!\nYou now set off on your adventure with your new buddy!");
            }
        } else {
            System.out.println("What is your name, young trainer?: ");
            playerName = in.nextLine();
            System.out.println("Welcome to your adventure " + playerName + "!");

            player = new Player(playerName, 100, currentMap.findStartLocation());

            System.out.println("You are now travelling the world looking for Pokemon and battles, and to become the pokemon Champion!");
            System.out.println("Choose your very first Pokemon!");
            System.out.println("1. Charmander\n2. Squirtle\n3. Bulbasaur");
            player.addPokemon(PokemonMaker.makeStartPokemon(CheckInput.checkIntRange(1, 3)));
            System.out.print("You chose ");
            player.displayCurrentPokemon();
            System.out.print("!\nYou now set off on your adventure with your new buddy!");
        }

        currentMap = new Map();
        currentMap.generateArea(player.getMapNum());
        Pokemon[][] allWildlings = new Pokemon[5][5];
        Opponent[][] allOpponents = new Opponent[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Point p = new Point(j, i);
                if (currentMap.getCharAtLoc(p) == 'w') {
                    allWildlings[i][j] = PokemonMaker.makeWildPokemon();
                } else if(currentMap.getCharAtLoc(p) =='o'){
                    OpponentMaker pitOfEvil = new OpponentMaker();
                    allOpponents[i][j] = pitOfEvil.makeRandomOpponent();
                }
            }
        }

        int choice = 0;
        do {
            System.out.println("\nWhat would you like to do?");
            System.out.print("1. Travel\n2. Switch Pokemon\n3. Heal Current Pokemon\n4. View Stats\n5. Quit Game\n");
            choice = CheckInput.checkIntRange(1, 5);
            switch (choice) {
                case 1:
                    char event = 'z';
                    do {
                        System.out.println("Where would you like to go?\n");
                        currentMap.displayMap(player.getLocation());
                        System.out.println("\nChoose a direction:\n1. North\n2. South\n3. East\n4. West");
                        int directionChoice = CheckInput.checkIntRange(1, 4);
                        switch (directionChoice) {
                            case 1:
                                event = player.goNorth(currentMap);
                                break;
                            case 2:
                                event = player.goSouth(currentMap);
                                break;
                            case 3:
                                event = player.goEast(currentMap);
                                break;
                            case 4:
                                event = player.goWest(currentMap);
                                break;
                        }
                        switch (event) {
                            case 's':
                            case 'n':
                                System.out.println("You encounter nothing worthwhile.");
                                currentMap.reveal(player.getLocation());
                                break;
                            case 'c':
                                arriveAtTown(player);
                                currentMap.reveal(player.getLocation());
                                break;
                            case 'w':
                                encounterWildPokemon(player, currentMap, allWildlings[(int) player.getLocation().getY()][(int) player.getLocation().getX()]);
                                currentMap.reveal(player.getLocation());
                                break;
                            case 'f':
                                System.out.println("You have cleared this area!");
                                player.incMapNum();
                                System.out.println("Would you like to save your progress?\n1. Yes\n2. No");
                                int saveChoice = CheckInput.checkIntRange(1, 2);

                                if (saveChoice == 1){
                                    try{
                                        File outFile = new File( "pokemon.dat" );
                                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
                                        out.writeObject(player);
                                        out.close();
                                    }catch( IOException e ) {
                                        System.out.println("Error processing file.");
                                    }
                                }


                                System.out.println("You have arrived at a new map!");
                                currentMap = new Map();
                                currentMap.generateArea(player.getMapNum());
                                allWildlings = new Pokemon[5][5];
                                allOpponents = new Opponent[5][5];
                                for (int i = 0; i < 5; i++) {
                                    for (int j = 0; j < 5; j++) {
                                        Point p = new Point(j, i);
                                        if (currentMap.getCharAtLoc(p) == 'w') {
                                            allWildlings[i][j] = PokemonMaker.makeWildPokemon();
                                        } else if(currentMap.getCharAtLoc(p) =='o'){
                                        OpponentMaker pitOfEvil = new OpponentMaker();
                                        allOpponents[i][j] = pitOfEvil.makeRandomOpponent();
                                    }
                                    }
                                }
                                currentMap.reveal(player.getLocation());
                                break;
                            case 'o':
                                PokemonBattles.opponentBattle(player, allOpponents[(int) player.getLocation().getY()][(int) player.getLocation().getX()], currentMap);
                                currentMap.reveal(player.getLocation());
                                break;
                        }
                    } while (event == 'z');
                    break;
                case 2:
                    switchPokemon(player);
                    break;
                case 3:
                    healMainPokemon(player);
                    player.usePotion();
                    break;
                case 4:
                    System.out.println(player.getName() + "'s Stats:");
                    player.displayStats();
                    break;
                case 5:
                    System.out.println("See you soon!");
                    System.exit(0);
            }
        } while (choice != 5);

    }

    /**
     * Allows the user to switch the current pokemon and choose
     * a different one that the player has with him.
     *
     * @param player the current player.
     */
    public static void switchPokemon(Trainer player) {
        System.out.println("These are all your pokemon: ");
        player.displayAllPokemon();
        System.out.println("Which would you like to set as your main Pokemon?");
        int switcheroo = player.setCurrentPokemon(CheckInput.checkIntRange(1, player.getNumberOfPokemon()));
        if (switcheroo == -1) {
            switchPokemon(player);
        } else {
            player.displayCurrentPokemon();
            System.out.println(" is now your main buddy!");
        }
    }

    /**
     * Heals the plaer's currently selected pokemon.
     * Won't work if there aren;t enough potions.
     *
     * @param player the current player.
     */
    public static void healMainPokemon(Player player) {
        if (player.getNumPotions() > 0) {
            player.healCurrentPokemon();
            player.displayCurrentPokemon();
            System.out.println(" is all healed up!");
        } else System.out.println("You don't have enough potions! Buy some at a Pokemart.");
    }

    /**
     * Allows the player to visit a town.
     * Here, the player can shop at the Pokemart or
     * go to a Pokemon center.
     *
     * @param player the current player.
     */
    public static void arriveAtTown(Player player) {
        System.out.println("You have encountered a town!");
        int townChoice = 0;
        do {
            System.out.println("What would you like to do here?");
            System.out.println("1. Go to the Pokemon Center\n2. Go to the Pokemon Mart\n3. Leave");
            townChoice = CheckInput.checkIntRange(1, 3);
            switch (townChoice) {
                case 1:
                    player.healAllPokemon();
                    System.out.println("You went to the Pokemon Center and healed all your pokemon! You return to the town square.");
                    break;
                case 2:
                    goToPokeMart(player);
                    break;
                case 3:
                    System.out.println("You leave the town. Back to your adventure!");
                    break;
            }
        } while (townChoice != 3);
    }

    /**
     * Contains the whole process of meeting and fighting
     * a wild pokemon. The pokemon can be caught as well.
     *
     * @param player the current player.
     */
    public static void encounterWildPokemon(Player player, Map m, Pokemon wildling) {
        Random rand = new Random();
        System.out.print("You encounter a wild ");
        wildling.displayStats();
        System.out.print("!\n");
        int battleChoice = 0;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Fight!\n2. Use Potion\n3. Throw Pokeball\n4. Run Away!");
            battleChoice = CheckInput.checkIntRange(1, 4);
            switch (battleChoice) {
                case 1:
                    if (player.getCurrentPokemon().getHp() == 0) {
                        System.out.println("Your current Pokemon is fainted and so you take all damage!");
                        playerTakesDamage(player, wildling);
                        break;
                    }
                    System.out.print("I choose you ");
                    player.displayCurrentPokemon();
                    System.out.println("!");
                    PokemonBattles.pokemonBattle(player, wildling, m);
                    if (wildling.getHp() <= 0 || player.getCurrentPokemon().getHp() == 0) {
                        battleChoice = 10;
                    }
                    break;
                case 2:
                    healMainPokemon(player);
                    player.usePotion();
                    break;
                case 3:
                    if (player.getNumPokeballs() < 1) {
                        System.out.println("You don't have any Pokeballs!");
                        break;
                    } else {
                        System.out.println("You threw a Pokeball!");
                        player.usePokeball();
                        System.out.println("\"Shake, shake, shake\"");
                        if (wildling.getHp() < (0.5 * wildling.getMaxHp())) {
                            System.out.print("You successfully caught the wild " + wildling.getName());
                            player.addPokemon(wildling);
                            m.removeOppAtLoc(player.getLocation());
                            battleChoice = 10;
                            break;
                        } else if (wildling.getHp() < (0.75 * wildling.getMaxHp())) {
                            if (rand.nextInt(2) == 1) {
                                System.out.print("You successfully caught the wild " + wildling.getName() + "!");
                                player.addPokemon(wildling);
                                m.removeOppAtLoc(player.getLocation());
                                battleChoice = 10;
                                break;
                            } else {
                                System.out.println(wildling.getName() + " escaped!");
                            }
                        } else {
                            if (rand.nextInt(3) == 1) {
                                System.out.print("You successfully caught the wild " + wildling.getName());
                                player.addPokemon(wildling);
                                m.removeOppAtLoc(player.getLocation());
                                battleChoice = 10;
                                break;
                            } else {
                                System.out.println(wildling.getName() + " escaped!");
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("You ran away!");
                    battleChoice = 10;
                    break;
            }
        } while (battleChoice != 10);
    }



    /**
     * Contains the process of encountering a Team Rocket
     * grunt and fighting his pokemon. This pokemon cannot
     * be caught.
     *
     * @param player the current player.
     */
    public static void encounterTeamRocketGrunt(Player player, Map m) {
        Random rand = new Random();
        Pokemon gruntPokemon = PokemonMaker.makeWildPokemon();
        System.out.println("You hear something strange.");
        System.out.println("They are footsteps moving slowly among the leaves. CRCH... CRCH..CRCH");
        System.out.println("A team rocket grunt jumps out of the shadows and confronts you!");
        System.out.print("He challenges you to a battle along with his buddy ");
        gruntPokemon.displayStats();
        System.out.print("!\n");
        int battleChoice = 0;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Fight!\n2. Use Potion\n3. Throw Pokeball\n4. Run Away!");
            battleChoice = CheckInput.checkIntRange(1, 4);
            switch (battleChoice) {
                case 1:
                    if (player.getCurrentPokemon().getHp() == 0) {
                        System.out.println("Your current Pokemon is fainted and so you take all damage!");
                        playerTakesDamage(player, gruntPokemon);
                        break;
                    }
                    System.out.print("I choose you ");
                    player.displayCurrentPokemon();
                    System.out.println("!");
                    PokemonBattles.pokemonBattle(player, gruntPokemon, m);
                    if (gruntPokemon.getHp() <= 0 || player.getCurrentPokemon().getHp() == 0) {
                        battleChoice = 10;
                    }
                    break;
                case 2:
                    healMainPokemon(player);
                    player.usePotion();
                    break;
                case 3:
                    System.out.println("You can't capture another trainer's pokemon! Nice try!");
                    break;
                case 4:
                    System.out.println("You ran away!");
                    battleChoice = 10;
                    break;
            }
        } while (battleChoice != 10);
    }

    /**
     * This method is to be used with encounterWildPokemon()
     * and encounterTeamRocketGrunt() in order to allow the player
     * to take damage when the main pokemon has 0 HP.
     *
     * @param player the current player.
     */
    public static void playerTakesDamage(Player player, Pokemon opponentPokemon) {
        Random rand = new Random();
        int damageToPlayer = opponentPokemon.fight(rand.nextInt(2) + 1, rand.nextInt(3) + 1);
        player.loseHp(damageToPlayer);
        System.out.println(player.getName() + " lost " + damageToPlayer + ".");
        if (player.getHp() > 0) {
            System.out.println(player.getName() + " now only has " + player.getHp() + " HP.");
        } else {
            System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
            System.exit(0);
        }
    }

    /**
     * Allows the player to go to the pokemart
     * and buy potions or pokeballs.
     *
     * @param player
     */
    public static void goToPokeMart(Player player) {
        int shopChoice = 0;
        do {
            System.out.println("Welcome to the Poke Mart.  What would you like to do here?");
            System.out.println("1. Buy a Pokeball ($5)\n2. Buy a Potion ($3)\n3. Leave Pokemart and return to town square");
            shopChoice = CheckInput.checkIntRange(1, 3);
            switch (shopChoice) {
                case 1:
                    player.gainPokeball();
                    player.spendMoney(5);
                    break;
                case 2:
                    player.gainPotion();
                    player.spendMoney(3);
                    break;
                case 3:
                    System.out.println("You leave the Pokemart and return to the town square.");
            }
        } while (shopChoice != 3);
    }
}