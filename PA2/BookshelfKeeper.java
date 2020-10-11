// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI455 PA2
// Fall 2020

import java.util.Stack;
import java.util.ArrayList;
/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
      Representation invariant:

      <put rep. invar. comment here>
      Bookshelfkeeper needs to be sorted
      Input books should be positive height
      Request position should be in range

   */
   
   // <add instance variables here>
   private Bookshelf bookshelf;
   private int totalOperations = 0;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf();
      assert isValidBookshelfKeeper(); // check if the bookshelfkeeper is valid
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf = sortedBookshelf;
      assert isValidBookshelfKeeper(); // check if the bookshelfkeeper is valid
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: position must be in the range [0, getNumBooks()).
    */
   public int pickPos(int position) {
      assert position >= 0 && position < getNumBooks() : "Position not in bookshelf.";
      int count = 0;
      int size = getNumBooks();
      if(position + 1 > size / 2){
         count = pickFront(position);
      }
      else{
         count = pickLast(position);
      }
      totalOperations += count; // update total operations
      return count;   // return minimum steps to accomplish the task
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      assert height > 0 : "Not Valid Height.";
      if(getNumBooks() == 0){ // bookshelf size 0 case
      	bookshelf.addFront(height);
      	totalOperations += 1;
      	return 1;
      }
      int size = getNumBooks();
      int count = 0;
      // start from the front, check how many books we need to take off before put in the input
      for(int i = 0; i < size; i++){ 
         if(bookshelf.getHeight(i) < height) count++;
      }
      if(count <= size / 2){ // based on count decide from which end we take off books
         putFront(height, count);
      }else{
         putLast(height, count);
      }
      // mathematical figure out the minimum steps
      int doFront = count * 2 + 1;
      int doLast = (size - count) * 2 + 1;
      int result = Math.min(doFront, doLast);
      totalOperations += result;
      return result;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
       return totalOperations;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
       return bookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      return bookshelf.toString();
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
   	if(getNumBooks() <= 1) return true;
      for(int i = 1; i < getNumBooks(); i++){
         if(bookshelf.getHeight(i) < bookshelf.getHeight(i - 1)) return false;
      }
      return true;
   }

   // add any other private methods here

   /**
   * @param position which book to take out
   * pick out books and add back in from the front
   */
   private int pickFront(int position){
      int count = 0;
      Stack<Integer> stack = new Stack<>();
      int size = getNumBooks();
      for(int i = position; i < size; i++){
            stack.push(bookshelf.removeLast());
            count++;
         }
      stack.pop();
      while(!stack.isEmpty()){
         bookshelf.addLast(stack.pop());
         count++;
      }
      return count;
   }

   /**
   * @param position which book to take out
   * pick out books and add back in from the last
   */
   private int pickLast(int position){
      int count = 0;
      Stack<Integer> stack = new Stack<>();
      int size = getNumBooks();
      for(int i = 0; i < position + 1; i++){
            stack.add(bookshelf.removeFront());
            count++;
         }
      stack.pop();
      while(!stack.isEmpty()){
         bookshelf.addFront(stack.pop());
         count++;
      }
      return count;
   }

   /**
   * @param height which book to take out
   * @param count number of books having height shorter than input
   * pick out books and add back in from the front
   */
   private void putFront(int height, int count){
      int size = getNumBooks();
      Stack<Integer> stack = new Stack<>();
      for(int i = 0; i < count; i++){
            stack.push(bookshelf.removeFront());
         }
      bookshelf.addFront(height);
      while(!stack.isEmpty()){
         bookshelf.addFront(stack.pop());
      }
   }

   /**
   * @param height which book to take out
   * @param count number of books having height shorter than input
   * pick out books and add back in from the last
   */
   private void putLast(int height, int count){
      int size = getNumBooks();
      Stack<Integer> stack = new Stack<>();
      for(int i = count; i < size; i++){
            stack.push(bookshelf.removeLast());
         }
      bookshelf.addLast(height);
      while(!stack.isEmpty()){
         bookshelf.addLast(stack.pop());
      }
   }
}
