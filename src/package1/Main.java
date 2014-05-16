package package1;
import java.util.*;
import java.io.*;
/**
 * Created by Tariq on 16.3.2014.
 */
public class Main {



    public static void main(String[] args) throws IOException{
        Game game = new Game(new Synchronizer());                
        System.out.println("");
        System.out.println("Level ?");
        System.out.println("0 - Easy / 1 - Medium / 2 - Hard / 3 - Expert");
      
        Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
        game.runNewGame(level);



    }
}
