// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI 455 PA5
// Fall 2020

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

Table::Table() {
	Table::hashSize = Table::HASH_SIZE;
	arr = new ListType[hashSize]();
	numOfEntries = 0;
}


Table::Table(unsigned int hSize) {
	Table::hashSize = hSize;
	arr = new ListType[hashSize]();
	numOfEntries = 0;
}


int * Table::lookup(const string &key) {
	return lookupList(key, arr[hashCode(key)]);
}

bool Table::remove(const string &key) {
	bool result = removeList(key, arr[hashCode(key)]);
	if(result) {
		numOfEntries--;
	}
	return result;
}

bool Table::insert(const string &key, int value) {
	// if exists return false and do nothing
	bool result = insertList(key, value, arr[hashCode(key)]);
	if(result) {
		numOfEntries++;
	}
	return result;
}

int Table::numEntries() const {
   return numOfEntries;
}


void Table::printAll() const {
	for(int i = 0; i < hashSize; i++) {
		printAllList(arr[i]);
	}
}

void Table::hashStats(ostream &out) const {
  out << "number of buckets: " << hashSize << endl;
  int numOfNonEmptyBuckets = 0;
  int longestChain = 0;
  int count;
  for(int i = 0; i < hashSize; i++) {
  		if(arr[i] != NULL) {
  			count = 0;
  			numOfNonEmptyBuckets++;
  			Node *p = arr[i];
  			while(p != NULL) {
  				p = p->next;
  				count++;
  			}
  			longestChain = max(longestChain, count);
  		}
  }
  out << "number of entries: " << numOfEntries << endl;
  out << "number of non-empty buckets: " << numOfNonEmptyBuckets << endl;
  out << "longest chain: " << longestChain << endl;
}


// add definitions for your private methods here
