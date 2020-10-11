// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA3
// Fall 2020

import java.util.Arrays;
/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private MineField mineField;
   private int[][] visibleField;
   private int numRow;
   private int numCol;
   private int numGuessMines; // number of guess mines left
   private int numCoveredSquares; // number of covered squares left
   private int numActualMines; // number of actual mines
   private boolean gameOver; // game over or not

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      numGuessMines = mineField.numMines();
      numActualMines = mineField.numMines();
      numRow = mineField.numRows();
      numCol = mineField.numCols();
      numCoveredSquares = numRow * numCol;
      visibleField = new int[numRow][numCol];

      for(int i = 0; i < numRow; i++){ // initialize the visible field with all squares COVERED
        Arrays.fill(visibleField[i], COVERED);
      }

      gameOver = false;
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      numGuessMines = mineField.numMines();
      numActualMines = mineField.numMines();
      numCoveredSquares = numRow * numCol;

      for(int i = 0; i < numRow; i++){ // reset all squares in visible field to covered
        Arrays.fill(visibleField[i], COVERED);
      }

      gameOver = false;
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineField;
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      if(!getMineField().inRange(row, col)){
         throw new AssertionError("out of Range");
      }
      return visibleField[row][col];
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return numGuessMines;

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      if(!getMineField().inRange(row, col)){
         throw new AssertionError("out of Range");
      }
      if(visibleField[row][col] == COVERED){ // COVERED to MINE_GUESS
         visibleField[row][col] = MINE_GUESS;
         numGuessMines--;
      } else if(visibleField[row][col] == MINE_GUESS){ // MINE_GUESS to QUESTION
         visibleField[row][col] = QUESTION;
         numGuessMines++;
      } else if(visibleField[row][col] == QUESTION){ // QUESTION to COVERED
         visibleField[row][col] = COVERED;
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      if(!getMineField().inRange(row, col)){
         throw new AssertionError("out of Range");
      }
      if(mineField.hasMine(row, col)){ // game over when make mine explode
         uncoverAllSquares(row, col); // uncover all mines location and whether GUESS is right or wrong
         gameOver = true;
         return false;
      }
      else{
         int adjacentMines = mineField.numAdjacentMines(row, col);
         if(adjacentMines != 0){ // nonzero adjacent square
            visibleField[row][col] = adjacentMines;
            numCoveredSquares--;
            if(numActualMines == numCoveredSquares){ // if number of covered squares left equal to number of mines, game over
               gameOver = true;
            }
            return true;
         }else{
            uncoverZeroAdjacentMines(row, col); // flood-fill, open all adjacent zero squares till nonzero ones
            if(numActualMines == numCoveredSquares){ // if number of covered squares left equal to number of mines, game over
               gameOver = true;
            }
            return true;
         }

      }
   }   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game over
    */
   public boolean isGameOver() {
      return gameOver;       // DUMMY CODE so skeleton compiles
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      if(!getMineField().inRange(row, col)){
         throw new AssertionError("out of Range");
      }
      int fieldValue = visibleField[row][col];
      if(fieldValue == COVERED || fieldValue == MINE_GUESS || fieldValue == QUESTION){
         return false;
      }
      return true;
   }
   
 
   // <put private methods here>
   /**
      Mutator: uncover all mines location and show whether guess is right or wrong
      @param row of the square
      @param col of the square
      PRE: getMineField().inRange(row, col)
    */
   private void uncoverAllSquares(int row, int col){
      for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
               int fieldValue = visibleField[i][j];
               if(fieldValue < 0 || fieldValue > 8){
                  if(mineField.hasMine(i, j) && visibleField[i][j] != MINE_GUESS){
                     visibleField[i][j] = MINE;
                  }
                  if(!mineField.hasMine(i, j) && visibleField[i][j] == MINE_GUESS){
                     visibleField[i][j] = INCORRECT_GUESS;
                  }
               }
            }
         }
      visibleField[row][col] = EXPLODED_MINE;
   }

   /**
      Mutator: uncover all zero squares and nonzero squares on the boundary
               also calculate the number of covered squares left 
      @param row of the square
      @param col of the square
      PRE: getMineField().inRange(row, col)
    */
   private void uncoverZeroAdjacentMines(int row, int col){
      if(row < 0 || row > numRow - 1 || col < 0 || col > numCol - 1){
         return;
      }

      int fieldValue = visibleField[row][col];
      if(fieldValue == MINE_GUESS || fieldValue >= 0 && fieldValue <= 8){
         return;
      }

      int numberAdjacentMines = mineField.numAdjacentMines(row, col);
      numCoveredSquares--;
      if(numberAdjacentMines != 0){
         visibleField[row][col] = numberAdjacentMines;
         return;
      }
      visibleField[row][col] = 0;
      
      uncoverZeroAdjacentMines(row + 1, col);
      uncoverZeroAdjacentMines(row - 1, col);
      uncoverZeroAdjacentMines(row, col + 1);
      uncoverZeroAdjacentMines(row, col - 1);

      uncoverZeroAdjacentMines(row + 1, col + 1);
      uncoverZeroAdjacentMines(row + 1, col - 1);
      uncoverZeroAdjacentMines(row - 1, col + 1);
      uncoverZeroAdjacentMines(row - 1, col - 1);
   }
}
