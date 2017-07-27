import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * Creates an instance of the opponent randomly
 * Created by claudedaniel on 2/27/17.
 */
public class OpponentMaker implements Serializable{

    /**
     * An array list of all opponents that were read in from the text file.
     */
    ArrayList<Opponent> opponents = new ArrayList<Opponent>();

    /**
     * The constructor that reads in the text file.
     */
    public OpponentMaker(){
        try {
            Scanner read = new Scanner (new File("OpponentList.txt"));
            do {

                String n = read.nextLine();
                int h = Integer.parseInt(read.nextLine());
                String at = read.nextLine();
                at = at.replace("#","\n");
                String wi = read.nextLine();
                wi = wi.replace("#","\n");
                String lo = read.nextLine();
                lo = lo.replace("#","\n");
                Opponent op = new Opponent(n,h,at,wi,lo);
                opponents.add(op);
            } while (read.hasNext());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Creates a random opponent from the available
     * opponents.
     * @returns a brand new created opponent
     */
    public Opponent makeRandomOpponent(){
        Random random = new Random();
        int randomOpponentChoice = random.nextInt(opponents.size());
        Opponent tempOpponent =  opponents.get(randomOpponentChoice);
        String atckSpeech = tempOpponent.getAttackSpeech();
        String winSpeech = tempOpponent.getWinSpeech();
        String lossSpeech = tempOpponent.getLossSpeech();
        String name = tempOpponent.getName();
        int hp = tempOpponent.getHp();
        return new Opponent(name, hp, atckSpeech, winSpeech, lossSpeech);
    }
}
