/**
 * @(#)Game.java
 * Game class
 *
 * @author Anýl Ýlaða
 * @version 1.00 2021/5/8
 */
import java.util.Scanner;

public class Game {
	private int chance;
	private Level[] lvls;
	private Level currLevel;

	/**
	 * Creates a Game object.
	 * Initializes level.
	 */
	public Game(){
		int[][] answer1 = {//Initializing Levels.
		{1,4,5,7,6,2,3,8,9},
    	{6,9,8,4,3,5,1,7,2},
    	{2,7,3,8,1,9,4,5,6},
    	{5,8,2,3,4,6,7,9,1},
        {7,3,9,2,8,1,6,4,5},
    	{4,6,1,5,9,7,8,2,3},
    	{3,5,7,1,2,4,9,6,8},
    	{9,1,4,6,5,8,2,3,7},
    	{8,2,6,9,7,3,5,1,4}};
		int[][] draft1 = {
		{0,4,0,0,6,0,0,8,9},
    	{0,9,8,0,0,5,0,7,0},
    	{2,0,0,0,1,9,0,5,0},
    	{0,8,0,3,0,0,7,0,0},
        {0,3,9,0,8,1,6,4,0},
    	{0,0,1,0,0,7,0,2,0},
    	{0,5,0,1,2,0,0,0,8},
    	{0,1,0,6,0,0,2,3,0},
    	{8,2,0,0,7,0,0,1,0}};
		int[][] answer2 = {
		{8,6,5,4,2,7,9,1,3},
    	{2,4,3,9,1,5,6,8,7},
    	{7,9,1,6,8,3,2,5,4},
    	{4,3,7,5,9,8,1,6,2},
        {5,8,6,1,3,2,4,7,9},
    	{9,1,2,7,6,4,5,3,8},
    	{3,7,4,2,5,6,8,9,1},
    	{6,2,9,8,7,1,3,4,5},
    	{1,5,8,3,4,9,7,2,6}};
		int[][] draft2 = {
		{0,6,0,4,0,7,0,1,0},
    	{2,4,0,9,0,5,6,0,0},
    	{7,0,0,6,8,0,2,0,4},
    	{0,3,7,0,0,8,0,0,2},
        {0,0,6,1,3,0,4,0,0},
    	{9,1,0,0,0,0,5,3,0},
    	{0,0,0,0,0,0,0,9,1},
    	{0,0,0,0,0,0,0,0,0},
    	{0,5,0,0,4,0,0,0,0}};
		int[][] answer3 = {
		{0,4,0,0,6,0,0,8,9},
    	{0,9,8,0,0,5,0,7,0},
    	{2,0,0,0,1,9,0,5,0},
    	{0,8,0,3,0,0,7,0,0},
        {0,3,9,0,8,1,6,4,0},
    	{0,0,1,0,0,7,0,2,0},
    	{0,5,0,1,2,0,0,0,8},
    	{0,1,0,6,0,0,2,3,0},
    	{8,2,0,0,7,0,0,1,0}};
		int[][] draft3 = {
		{0,0,7,0,1,0,0,6,5},
    	{9,0,0,7,0,0,2,0,0},
    	{0,8,0,0,0,0,0,0,0},
    	{0,9,0,0,0,1,0,0,7},
        {0,0,4,0,0,0,9,1,0},
    	{0,3,0,0,0,0,0,0,0},
    	{0,0,1,0,4,0,0,2,9},
    	{0,0,0,0,2,0,0,5,0},
    	{0,5,0,0,0,0,3,0,0}};
        lvls = new Level[3];
		lvls[0] = new Level( 1, 9, answer1, draft1 );
		lvls[1] = new Level( 2, 9, answer2, draft2 );
		lvls[2] = new Level( 3, 9, answer3, draft3 );
		Level currLevel = new Level();
	}

	/**
	 * Lets the user play the game according to the rules below.
	 * Only use the numbers 1 to 9,
	 * Avoid trying to guess the solution to the puzzle,
     * Only use each number once in each row, column, & grid,
     * You have 5 chances.
	 */
    public void play() {
    	chance = 5;
    	selectLevel();
		Scanner in = new Scanner( System.in );
		int r,
			c,
			num;
		boolean isOver = false;

		while( !isOver ){
			currLevel.printProgress();
			System.out.println(" Please input row, coloumn and the number you want to input.");
			System.out.print("Row: ");
			r = in.nextInt();
			System.out.print("\nColoumn: ");
			c = in.nextInt();
			System.out.print("\nNumber: ");
			num = in.nextInt();

			if ( !currLevel.tryNumber( r, c, num )){
				chance--;
				System.out.println("False -- Remaning chances: " + chance );
			}
			else
				currLevel.addNumber( r, c, num );

			if( chance > 0 ){
				isOver = isWin();
			}
			else{
				isOver = true;
			}
		}

		if( isWin() ){
			currLevel.printProgress();
			System.out.println( "Congratulations You Win!");
		}
		else
			System.out.println( "Sorry you lost.");
		currLevel.copyDraft();
    }

	/**
	 * A function that determines the result.
	 * @return Returns true if player wins.
	 */
	public boolean isWin(){
		for( int r = 0; r < 9; r++ ){
 			for( int c = 0; c < 9; c++){
 				if( currLevel.getAnswer(r,c) != currLevel.getProgress(r,c)){
 					return false;
 				}
 			}
 		}
		return true;
	}

	/**
	 * Method that lets the user select a level.
	 * There are 3 levels.( Easy / Medium / Hard )
	 */
	public void selectLevel(){
		Scanner input = new Scanner( System.in );
    	String diff;
		boolean valid = false;

			while( !valid ){
    		System.out.print("Choose a difficulty (1)/(2)/(3): ");
    		diff = input.next();

    		if (diff.equals("1")){
    			currLevel = lvls[0];
    			valid = true;
    		}
    		else if (diff.equals("2")){
    			currLevel = lvls[1];
    			valid = true;
    		}
    		else if (diff.equals("3")){
    			currLevel = lvls[2];
    			valid = true;
    		}
    		else
    			System.out.println("Please try again...");
    	}
	}
}