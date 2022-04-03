/**
 * @(#)Level.java
 * Sudoku Levels class
 *
 * @author Anýl Ýlaða
 * @version 1.00 2021/5/8
 */

public class Level {
	private int[][] answer;
	private int[][] draft;
	private int[][] progress;
	private int size;
	private int diff;

	/**
	 * Creates a Level object.
	 * @param diff the difficulty of the level.
	 * @param s the size of the level ( s x s chart ).
	 * @param answer double dimensional array which contains the correct answers.
	 * @param draft double dimensional array which is the first version of the level.
	 */
    public Level( int diff, int s, int[][] answer, int[][] draft) {
    	this.answer = answer;
    	this.draft = draft;
		size = s;
    	this.diff = diff;
    	progress = new int[9][9];
    	copyDraft();
    }

    /*Overloaded constructor.
     */
	public Level(){
	}

	/**
	 * Tries if the number given by the user is correct.
	 * @param r row number given by user.
	 * @param c the coloumn number given by user.
	 * @param num the number given by user.
	 * @return returns if the number is correct or not.
	 */
    public boolean tryNumber( int r,int c, int num ){
		if( answer[r-1][c-1] == num  )
			return true;
		return false;
    }

	/**
	 * Adds the number to the progress.
	 * @param r row number given by user.
	 * @param c the coloumn number given by user.
	 * @param num the number given by user.
     */
    public void addNumber( int r, int c, int num ){
    	progress[r-1][c-1] = num;
    }

	/**
	 * Prints the progress as a chart.
     */
    public void printProgress(){
    	for( int r = 0; r < size; r++){// row number
    		for( int c = 0; c < size; c++){// coloumn
    			System.out.print("|");
    			if( progress[r][c] == 0){//prints - if it number empty
    				System.out.print(".");
    			}
    			else{ System.out.print(progress[r][c]);}

    			if ( c == 2 || c == 5 || c == 8 )
    				System.out.print("|");
    		}
    		System.out.println();
    		if( r == 2 || r == 5 || r == 8)
    			System.out.println("---------------------");
    	}
    }

	/**
	 * Copies the draft to the progress.
     */
	public void copyDraft(){
		for( int r = 0; r < size; r++ ){
			for( int c = 0; c < size; c++){
				progress[r][c] = draft[r][c];
			}
		}
	}

    /**
	 * Getter method that returns difficulty
	 * @return difficulty of the level.
     */
    public int getDifficulty(){
    	return diff;
    }

	/**
	 * Getter method that returns answers.
	 * @return two dimensional array - answer.
     */
    public int getAnswer( int r, int c){
    	return answer[r][c];
    }

	/**
	 * Getter method that returns progress.
	 * @return two dimensional array - progress.
     */
	public int getProgress(int r,int c){
		return progress[r][c];
	}

	/**
	 * Returns the String representation of the Level object
	 * @return the String representation of the Level: difficulty.
	 */
	public String toString(){
		return "Sudoku level difficulty: " + diff;
	}
}