import java.io.Serializable;
import java.util.*;

/**
 * This class contains the entire battling process.
 * It also has the random pokemon and trainer encounters.
 *
 * @author Claude Daniel 2017
 */

public class PokemonBattles implements Serializable{
    final static double[][] fightTable = {{1, 0.5, 2}, {2, 1, 0.5}, {0.5, 2, 1}};

    /**
     * The battle between the player and an
     * opposing pokemon.
     *
     * @param player the current player.
     * @param poke   the opposing pokemon.
     */
    public static void pokemonBattle(Player player, Pokemon poke, Map m) {
        Random rand = new Random();
        int damageToOpponent = (int) (player.battle() * fightTable[player.getCurrentPokemon().getType()][poke.getType()]);
        if (poke.getHp() > damageToOpponent) {
            System.out.println("The enemy " + poke.getName() + " lost " + poke.loseHp(damageToOpponent) + " HP!");
            poke.displayStats();
            System.out.println();
        } else {
            poke.loseHp(poke.getHp());
            System.out.println("The opposing pokemon fainted!");
            m.removeOppAtLoc(player.getLocation());
            player.getWinSpeech();
            if (player.getCurrentPokemon().getLevel() == 1) {
                if (rand.nextInt(3) < 2) {
                    player.getCurrentPokemon().gainLevel();
                    player.getCurrentPokemon().setMaxHp(player.getCurrentPokemon().getMaxHp() * 2);
                    System.out.print("Your ");
                    player.displayCurrentPokemon();
                    System.out.println(" gained a level! It is now level " + player.getCurrentPokemon().getLevel() + "!");
                    player.displayCurrentPokemon();
                    System.out.println("s Max HP has increased to " + player.getCurrentPokemon().getMaxHp() + "!");
                }
            }
            return;
        }

        int damageToPlayerPokemon = (int) (poke.fight(rand.nextInt(2) + 1, rand.nextInt(3) + 1) * fightTable[poke.getType()][player.getCurrentPokemon().getType()]);
        if (player.getCurrentPokemon().getHp() > damageToPlayerPokemon) {
            System.out.print("Your ");
            player.displayCurrentPokemon();
            System.out.println(" lost " + player.getCurrentPokemon().loseHp(damageToPlayerPokemon) + "!");
            player.getCurrentPokemon().displayStats();
            System.out.println();
        } else {
            System.out.print("Your ");
            player.displayCurrentPokemon();
            System.out.println(" lost " + player.getCurrentPokemon().loseHp(player.getCurrentPokemon().getHp()) + "!");
            player.getCurrentPokemon().displayStats();
            System.out.println("\nYour pokemon fainted!");
            System.out.println();
            int playerLoses = player.loseHp(damageToPlayerPokemon - player.getCurrentPokemon().getHp());
            System.out.println(player.getName() + " lost " + playerLoses + ".");
            if (player.getHp() > 0) {
                System.out.println(player.getName() + " now only has " + player.getHp() + " HP.");
                System.out.println("You ran away to fight another day!");
                return;
            } else {
                System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
                player.getLossSpeech();
                return;
            }
        }

    }

    /**
     * Battle an opponent
     * @param player the current player
     * @param oppo the opponent to battle
     * @param m the current map
     */
    public static void opponentBattle(Player player, Opponent oppo, Map m){
        Pokemon gruntPokemon = oppo.getCurrentPokemon();
        System.out.println("You have made eye contact with a trainer: "+oppo.getName());
        System.out.println("\"" + oppo.getAttackSpeech() + "\"");
        System.out.println(oppo.getName() + " sent out: ");
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
                        Random rand = new Random();
                        int damageToPlayer = gruntPokemon.fight(rand.nextInt(2) + 1, rand.nextInt(3) + 1);
                        player.loseHp(damageToPlayer);
                        System.out.println(player.getName() + " lost " + damageToPlayer + ".");
                        if (player.getHp() > 0) {
                            System.out.println(player.getName() + " now only has " + player.getHp() + " HP.");
                        } else {
                            System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
                            return;
                        }
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
                    if (player.getNumPotions() > 0) {
                        player.healCurrentPokemon();
                        player.displayCurrentPokemon();
                        System.out.println(" is all healed up!");
                    } else System.out.println("You don't have enough potions! Buy some at a Pokemart.");
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
     * A random encounter with an angry pokemon.
     * The pokemon causes damage directly to the player's hp.
     *
     * @param player the current player.
     */
    public static int angryPokemon(Player player) {
        Random rand = new Random();
        int quitChoice = 0;
        int randomChoice = rand.nextInt(2);
        switch (randomChoice) {
            case 0:
                System.out.println("A wild Geodude punched you and ran away!\nYou lost " + player.loseHp(rand.nextInt(5) + 1) + " HP.");
                if (player.getHp() < 1) {
                    System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
                    player.getLossSpeech();
                    quitChoice = 5;
                }
                break;
            case 1:
                System.out.println("You accidentally stepped on a Sandlslash and cut yourself!\nYou lost " + player.loseHp(rand.nextInt(3) + 1) + " HP.");
                if (player.getHp() < 1) {
                    System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
                    player.getLossSpeech();
                    quitChoice = 5;
                }
                break;
        }
        return quitChoice;
    }

    /**
     * A random encounter with an angry person.
     * The person causes damage directly to the player's hp.
     *
     * @param player the current player.
     */
    public static int angryPerson(Player player) {
        Random rand = new Random();
        int quitChoice = 0;
        int randomChoice = rand.nextInt(2);
        switch (randomChoice) {
            case 0:
                System.out.println("Misty has found you!\nShe asks for her bike and whacks you in the head when you say you don't have it.\nYou lost " + player.loseHp(rand.nextInt(3) + 1) + " HP.");
                if (player.getHp() < 1) {
                    System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
                    player.getLossSpeech();
                    quitChoice = 5;
                }
                break;
            case 1:
                System.out.println("You meet an ethnically ambiguous trainer called Brock. He throws some rocks at you and runs away!\nYou lost " + player.loseHp(rand.nextInt(7) + 1) + " HP.");
                if (player.getHp() < 1) {
                    System.out.println(player.getName() + " has lost all HP!\n-----GAME OVER-----");
                    player.getLossSpeech();
                    quitChoice = 5;
                }
                break;

        }
        return quitChoice;
    }
}
