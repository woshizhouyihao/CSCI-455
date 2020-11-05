// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA4
// Fall 2020

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

/**
* Main program for giving all posssible words in the dictionary
*/

public class WordFinder {
	public static void main(String[] args) {
		String fileName;
		// if no input file by user, use the default "sowpods.txt" file
		try {
			fileName = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			fileName = "sowpods.txt";
		}

		// initialize the anagram dictionary
		AnagramDictionary dict = initAnagramDict(fileName);
		Rack rack = new Rack();
		ScoreTable scoreTable = new ScoreTable();

		System.out.println("Type . to quit.");
		Scanner scanner = new Scanner(System.in);

		while(true) {
			System.out.print("Rack? ");
			String input = scanner.next();
			// if ., exit
			if(input.equals(".")) {
				scanner.close();
				break;
			}
			else { // print our results
				printResult(input, rack, scoreTable, dict);
			}
		}
	}

	/**
      Print out all possible words and scores
      @param input letters entered by user
      @param rack  possible combination of input letters
      @param scoretable  score for possible words
      @param dict  the anagram dictionary of words
    */
	private static void printResult(String input, Rack rack, ScoreTable scoreTable, AnagramDictionary dict) {
		// map to save word and score pairs
		HashMap<String, Integer> matchedWords = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(Character.isLetter(c)) sb.append(c);
		}
		// loop through all possible combinations
		ArrayList<String> subsets = rack.subsets(sb.toString());
		for(String str:subsets) {
			// only look for words length greater than 1
			if(str.length() >= 2) {
				// save score
				int score = scoreTable.score(str);
				// save all words can be built by current letters
				ArrayList<String> correspondWords = dict.getAnagramsOf(str);
				if(correspondWords != null) {
					// put all words in map
					for(String word:correspondWords) {
						matchedWords.put(word, score);
					}
				}
			}
		}

		// sort result by score and apply tie breaker by alphabetical order
		ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<>();
		for(Map.Entry<String, Integer> entry:matchedWords.entrySet()) {
			sortedList.add(entry);
		}
		Collections.sort(sortedList, (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2)->{
			int result = e2.getValue() - e1.getValue();
			if(result == 0) {
				return e1.getKey().compareTo(e2.getKey());
			}
			return result;
		});

		// print out results
		int numOfWords = sortedList.size();
		System.out.printf("We can make %d words from \"%s\"\n", numOfWords, input);
		if(numOfWords != 0) {
			System.out.println("All of the words with their scores (sorted by score):");
		}
		for(Map.Entry<String, Integer> entry:sortedList) {
			int score = entry.getValue();
			String word = entry.getKey();
			System.out.printf("%d: %s\n", score, word);
		}
	}

	/**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
    */
	private static AnagramDictionary initAnagramDict(String fileName) {
		try {
        return new AnagramDictionary(fileName);
      } catch (FileNotFoundException e) {
         System.out.printf("ERROR: Dictionary file \"%s\" does not exist.\n", fileName);
         System.out.println("Exiting program.");
         System.exit(0);
      } catch (IllegalDictionaryException e) {
         System.out.printf("ERROR: Illegal dictionary: dictionary file has a duplicate word: %s\n", e.getMessage());
         System.out.println("Exiting program.");
      	System.exit(0);
      }
      return null;
	}
}