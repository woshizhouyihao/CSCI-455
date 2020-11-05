// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA4
// Fall 2020

/**
* Score table for letters and calculate the score for each word
*/
public class ScoreTable {
	private int[] scoreChart;

	// create the letter score table
	public ScoreTable() {
		// store letter values corresponding to the position in arrays
		scoreChart = new int[]{1, 3, 3, 2, 1,
						  			  4, 2, 4, 1, 8,
						  			  5, 1, 3, 1, 1,
						  			  3, 10, 1, 1, 1,
						  			  1, 4, 4, 8, 4, 10};
	}
	
	/**
      Calculate the score for input words
      @param s string to process
      @return string score
    */
	public int score(String token) {
		int len = token.length();
		int sum = 0;
		for(int i = 0; i < len; i++) {
			char c = token.charAt(i);
			sum += scoreChart[Character.toUpperCase(c) - 'A'];
		}
		return sum;
	}
}