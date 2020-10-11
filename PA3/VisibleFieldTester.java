// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA3
// Fall 2020

/** 
   VisibleFieldTester
      Test if methods in VisibleField class work as expectation
 */
public class VisibleFieldTester{
	private static boolean[][] smallMineField = 
      {{false, false, false, false}, 
       {true, false, false, false}, 
       {false, true, true, false},
       {false, true, false, true}};

	public static void main(String[] args){
		// create mine field and visible field
		MineField m = new MineField(smallMineField);
		VisibleField v = new VisibleField(m);

		// print out mine field
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.hasMine(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// print out visible field
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(v.getStatus(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// check number of adjacent mines for each squares
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(m.numAdjacentMines(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

		// open the zero adjacent mines squares and see if open the others as expectation
		v.uncover(0, 3);
		for(int i = 0; i < m.numRows(); i++){
			for(int j = 0; j < m.numCols(); j++){
				System.out.print(v.getStatus(i,j) + " ");
			}
			System.out.println();
		}
		System.out.println();

	}
}