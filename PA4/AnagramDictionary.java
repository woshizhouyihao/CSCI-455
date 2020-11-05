// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA4
// Fall 2020

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   private Scanner scanner;
   // use sorted letters as key to store a list of all words with same token
   private HashMap<String, ArrayList<String>> dict;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      // read in file
      File file = new File(fileName);
      scanner = new Scanner(file);
      dict = new HashMap<>();
      
      while(scanner.hasNext()) {
      	// word
         String temp = scanner.next();
         char[] charArray = temp.toCharArray();
         Arrays.sort(charArray);
         // Anagram token
         String token = String.valueOf(charArray);
         if(dict.containsKey(token) && dict.get(token).contains(temp)) {
         	// if dict has repeated words
            throw new IllegalDictionaryException(temp);
         } else {
            if(!dict.containsKey(token)) {
            	// save token and current words
               dict.put(token, new ArrayList<String>(){{add(temp);}});
            } else {
            	// save the current words to its correpsonding group
               dict.get(token).add(temp);
            }
         }
      }
   }

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      char[] charArray = s.toCharArray();
      Arrays.sort(charArray); // make the input letters sorted
      String token = String.valueOf(charArray);
      return dict.get(token);
   }
}
