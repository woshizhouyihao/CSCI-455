// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA3
// Fall 2020

/** 
   MineFieldTester
      Test if methods in MineField class work as expectation
 */
public class MineFieldTester{
	public static void main(String[] args){
		// new MineField
		MineField m = new MineField(3,3,2);
		// print out MineField
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.hasMine(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// place mine on random location
		m.populateMineField(1, 2);
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.hasMine(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// check randomness
		m.populateMineField(1, 2);
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.hasMine(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// check number of adjacent mines
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.numAdjacentMines(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// reset the minefield
		m.resetEmpty();
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.hasMine(i,j) + " ");
			}
			System.out.println();
		}
	}
}