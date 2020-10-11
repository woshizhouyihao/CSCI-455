// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI455 PA2
// Fall 2020

import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

    /**
      Representation invariant:

      <put rep. invar. comment here>
      Books' height have to be positive
   */
   
   // <add instance variables here>
   private ArrayList<Integer> books;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      books = new ArrayList<>();
      assert isValidBookshelf();  // look whether the bookshelf is valid
   }
   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */

   public Bookshelf(ArrayList<Integer> pileOfBooks) {
   	books = new ArrayList<>(); // create new books list and add books into bookshelf
   	for(Integer x : pileOfBooks){
   		books.add(x);
   	}
      assert isValidBookshelf(); // look whether whether the bookshelf is valid
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
   	assert height > 0; // check if input is valid
      books.add(0, height);
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
   	assert height > 0; // check if input is valid
      books.add(height);
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
   	assert size() > 0 : "Bookshelf is empty"; // check if bookshelf is empty
   	return books.remove(0);      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
   	assert size() > 0 : "Bookshelf is empty"; // check if bookshelf is empty
      return books.remove(size() - 1); 
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
   	assert position >= 0 && position < size() : "Position not in Bookshelf"; // check if request in range
      return books.get(position);      
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      return books.size();
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      return books.toString();
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      for(int i = 1; i < size(); i++){
      	if(getHeight(i) < getHeight(i - 1)) return false;
      }
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      for(int i = 0; i < books.size(); i++){
      	if(getHeight(i) <= 0) return false;
      }
      return true;  // dummy code to get stub to compile

   }

}
