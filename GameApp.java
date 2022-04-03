/**
 * @(#)GameApp.java
 *
 *
 * @author Anýl Ýlaða
 * @version 1.00 2021/5/8
 */
import java.util.Scanner;

public class GameApp {
    public static void main(String[] args) {
    	//Variables, Game Object and Scanner.
		Game g = new Game();
		Scanner input = new Scanner(System.in);
		boolean valid;
		String in;

		//Initializing variables
		in = "";
		valid = false;

		//Lets the player play until the game is over and he/she wants to quit.
		do{
			g.play();
			while( !valid ){
				System.out.print("Want to play Again?(y/n): ");
				in = input.nextLine();
				if( in.equalsIgnoreCase("y") || in.equalsIgnoreCase("n")){
					valid = true;
				}
			}
		}while( in.equalsIgnoreCase("y") );
	}
}