// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI455 PA2
// Fall 2020

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class BookshelfKeeperProg
 *
 * User side BookshelfKeeper. Used to read in user input and check if the input is valid 
 * or not. The program will give user instructions and provide error messages to users.
 */	
public class BookshelfKeeperProg{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Bookshelf bookshelf;
		BookshelfKeeper bookshelfkeeper;

		System.out.println("Please enter initial arrangement of books followed by newline:");
		bookshelf = createAndValidateBookshelf(sc);
		bookshelfkeeper = createAndValidateBookshelfKeeper(bookshelf);

		action(sc, bookshelfkeeper);
	}

	// read in user input to creat a new bookshelf and give error message if input is not valid
	private static Bookshelf createAndValidateBookshelf(Scanner sc){
		// read in input
		ArrayList<Integer> books = new ArrayList<>();
		String s = sc.nextLine();
		Scanner sc1 = new Scanner(s);
		while(sc1.hasNextInt()){
			int i = sc1.nextInt();
			books.add(i);
		}
		// check if input valid. if not return null, else return a new bookshelf
		for(int i = 0; i < books.size(); i++){
      	if(books.get(i) <= 0){
      		System.out.println("ERROR: Height of a book must be positive.");
      		return null;
      	}
      }
      return new Bookshelf(books);
	}

	// create a new bookshelfkeeper with input bookshelf and validate if the bookshelfkeeper is 
	// valid or not
	private static BookshelfKeeper createAndValidateBookshelfKeeper(Bookshelf bookshelf){
      if(bookshelf != null){
      	// chechk if the bookshelf is valid
	      for(int i = 1; i < bookshelf.size(); i++){
	         if(bookshelf.getHeight(i) < bookshelf.getHeight(i - 1)){
	         	System.out.println("ERROR: Heights must be specified in non-decreasing order.");
	         	return null;
	         }
	      }
	      // print out bookshelf and next instructions
	      System.out.println(bookshelf.toString() + " " + 0 + " " + 0);
			System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
			return new BookshelfKeeper(bookshelf);
		}else{
			return null;
		}
	}

	// action method for what actions needed to do corresponding to user's request
	private static void action(Scanner sc, BookshelfKeeper bookshelfkeeper){
		while(sc.hasNext()){
			int operations = 0;
			String input = sc.next();
			// put command
			if(input.equals("put")){
				put(sc, bookshelfkeeper);
			}
			// pick command
			else if(input.equals("pick")){
				pick(sc, bookshelfkeeper);
			}
			// end command
			else if(input.equals("end")){
				System.out.println("Exiting Program.");
				break;
			}
			// other invalid command
			else{
				System.out.println("ERROR: Operation should be either pick or put.");
				sc.nextLine();
			}
		}
	}

	// put method read in input height and check if valid
	private static void put(Scanner sc, BookshelfKeeper bookshelfkeeper){
		int put = sc.nextInt();
		if(bookshelfkeeper != null && put > 0){
			int operations = bookshelfkeeper.putHeight(put);
			System.out.println(bookshelfkeeper.toString() + " " + operations + " " + bookshelfkeeper.getTotalOperations());
		}else{
			System.out.println("ERROR: Height of a book must be positive.");
		}
	}

	// pick method read in input height and check if valid
	private static void pick(Scanner sc, BookshelfKeeper bookshelfkeeper){
		int pick = sc.nextInt();
		if(bookshelfkeeper != null && pick >= 0 && pick < bookshelfkeeper.getNumBooks()){
			int operations = bookshelfkeeper.pickPos(pick);
			System.out.println(bookshelfkeeper.toString() + " " + operations + " " + bookshelfkeeper.getTotalOperations());
		} else{
			System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
		}
	}
}