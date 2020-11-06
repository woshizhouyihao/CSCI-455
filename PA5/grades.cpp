// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI 455 PA5
// Fall 2020

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);
   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*

   while(true) {
   	cout << "cmd> ";
   	string command;
   	string name;
   	int score;
   	cin >> command;
   	if(command == "insert") {
   		cin >> name;
   		cin >> score;
   		if(!grades->insert(name, score)) {
   			cout << "Name existed." << endl;
   		}

   	} else if(command == "change") {
   		cin >> name;
   		cin >> score;
   		if(!((*grades->lookup(name)) = score)) {
   			cout << "Name not exists." << endl;
   		}
   	} else if(command == "lookup") {
   		cin >> name;
   		int* p;
   		if(!(p = grades->lookup(name))) {
   			cout << "Name not exists." << endl;
   		} else {
   			cout << *p << endl;
   		}
   	} else if(command == "remove"){
   		cin >> name;
   		if(!grades->remove(name)) {
   			cout << "Name not exists." << endl;
   		}
   	} else if(command == "print") {
   		grades->printAll();
   	} else if(command == "size") {
   		cout << grades->numEntries() << endl;
   	} else if(command == "stats") {
   		grades->hashStats(cout);
   	} else if(command == "help") {
   		cout << "[insert, change, lookup, remove, print, size, stats, help, quit]" << endl;
   	} else if(command == "quit") {
   		break;
   	} else {
   		cout << "ERROR: invalid command" << endl;
   		cout << "[insert, change, lookup, remove, print, size, stats, help, quit]" << endl;
   	}
   }

   return 0;
}
