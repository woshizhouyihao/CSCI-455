// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA3
// Fall 2020

import java.util.Random;
import java.util.Arrays;
/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   
   // <put instance variables here>
   private boolean[][] mineField;
   private int numMines;
   private int numRow;
   private int numCol;
   private Random random;
   
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      numRow = mineData.length;
      numCol = mineData[0].length;
      mineField = new boolean[numRow][numCol];
      numMines = 0;
      for(int i = 0; i < numRow; i++){ // copy input data
         for(int j = 0; j < numCol; j++){
            mineField[i][j] = mineData[i][j];
            // calculate the total number of mines
            if(mineField[i][j]){
               numMines++;
            }
         }
      }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      if(numRows <= 0 || numCols <= 0 || numMines < 0 || numMines >= (numRows * numCols)/3){
         throw new AssertionError("Invalid input");
      }
      // create empty mine field and save all parameters
      mineField = new boolean[numRows][numCols];
      numRow = numRows;
      numCol = numCols;
      this.numMines = numMines;
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
      if(!inRange(row, col)){
         throw new AssertionError("out of range");
      }
      resetEmpty(); // clean current mine field

      random = new Random();
      // generate mines on mine field
      for(int i = 0; i < numMines; i++){
      	int rowNum = random.nextInt(numRow);
      	int colNum = random.nextInt(numCol);
      	if((rowNum == row && colNum == col) || mineField[rowNum][colNum]){
      		i--; // offset i increment
      	}else{
      		mineField[rowNum][colNum] = true;
      	}
      }

   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in 
         at the beginning of a game.
    */
   public void resetEmpty() {
      for(int i = 0; i < numRow; i++){
      	Arrays.fill(mineField[i], false);
      }
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      if(!inRange(row, col)){
         throw new AssertionError("out of range");
      }
   	int count = 0;
   	
		count += countUp(row, col); // line above
		count += countBottom(row, col); // line below

   	if(col != 0 && mineField[row][col - 1]){ // to the left
   		count++;
   	}
   	if(col != numCols() - 1 && mineField[row][col + 1]){ // to the right
   		count++;
   	}

      return count;
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
   	if(row < numRow && row >= 0 && col < numCol && col >= 0){ // in range
   		return true;
      }
      return false;
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return numRow;
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return numCol;
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      return mineField[row][col];
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMines;
   }
   
   // <put private methods here>
   /**
     Count the number of mines in above the given location, including the diagonal corners
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines above the square at (row, col)
     PRE: inRange(row, col)
   */
   private int countUp(int row, int col){
   	int count = 0;
   	if(row != 0){ // row is not zero
   		if(mineField[row - 1][col]){ // the square just above
   			count++;
   		}
   		if(col != 0 && mineField[row - 1][col - 1]){ // the square above and to the left
   			count++;
   		}
   		if(col != numCol - 1 && mineField[row - 1][col + 1]){ // the square above and to the right
   			count++;
   		}
   	}
   	return count;
   }

   /**
     Count the number of mines in below the given location, including the diagonal corners
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines below the square at (row, col)
     PRE: inRange(row, col)
   */
   private int countBottom(int row, int col){
   	int count = 0;
   	if(row != numRow - 1){
			if(mineField[row + 1][col]){ // the square below
				count++;
			}
			if(col != 0 && mineField[row + 1][col - 1]){ // the square below and to the right
   			count++;
   		}
   		if(col != numCol - 1 && mineField[row + 1][col + 1]){ // the square below and to the right
   			count++;
   		}
		}
		return count;
   }
}

