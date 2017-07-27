import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * The Map class used to generate Maps
 * based on the available text files.
 *
 * Created by claudedaniel on 2/25/17.
 */
public class Map implements Serializable{

    /**
     * A 2D char array to contain the
     * events at each location
     */
    char[][] map = new char[5][5];
    /**
     * A 2D boolean array to identify whether
     * a spot on the map has been visited.
     */
    boolean [][] revealed = new boolean[5][5];


    /**
     * The constructor for the Map.
     */
    public Map(){

    }

    /**
     * Populates the map
     * @param mapNum the map number from 1 to 3.
     */
    public void generateArea(int mapNum){
        String pathName = "";
        switch(mapNum){
            case 1:
                pathName = "Area1.txt";
                break;
            case 2:
                pathName = "Area2.txt";
                break;
            case 3:
                pathName = "Area3.txt";
                break;
        }

        try {
            Scanner read = new Scanner (new File(pathName));
            for (int i = 0; i<5; i++)
                for (int j = 0; j<5; j++)
                    map[i][j] = read.next().charAt(0);

            read.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");;
        }

    }

    /**
     * Retrieves the char (event) at a
     * particular location on the map.
     * @param p The point at which the char is located.
     * @return the char.
     */
    public char getCharAtLoc(Point p){
        int x = (int)p.getX();
        int y = (int)p.getY();
        return map[y][x];

    }

    /**
     * Prints out the map
     * @param p the current location of the player
     */
    public void displayMap(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(i==y && j==x){
                    System.out.print("* ");
                } else if(map[i][j]=='c' || map[i][j] == 's'){
                    System.out.print(map[i][j]+" ");

                } else {
                    if (revealed[i][j] == true) {
                        System.out.print(map[i][j] + " ");
                    } else {
                        System.out.print("X ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Finds the start location at this particular map.
     * @return the start locaiton in Point format.
     */
    public Point findStartLocation(){
        Point p = new Point();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 's') {
                    p.setLocation(j,i);
                }
            }
        }
        return p;
    }

    /**
     * reveals an already visited spot.
     * @param p the point to reveal.
     */
    public void reveal(Point p){
        int x = (int)p.getX();
        int y = (int)p.getY();
        revealed[y][x] = true;
    }

    /**
     * remove a killed or captured opponent
     * or wild pokemon frm the map.
     * @param p the point to remove
     */
    public void removeOppAtLoc(Point p){
        int x = (int)p.getX();
        int y = (int)p.getY();
        map[y][x] = 'n';
    }


}