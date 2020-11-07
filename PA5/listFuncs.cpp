// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI 455 PA5
// Fall 2020


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}

//*************************************************************************
// put the function definitions for your list functions below

int *lookupList(const std::string &key, ListType& list) {
	ListType p = list;
	while(p != NULL) {
		if(p->key == key) {
			return &(p->value);
		}
		p = p->next;
	}
   return NULL; // no such node found
}

bool removeList(const std::string &key, ListType& list) {
	ListType t; // temp node for handling memory leak
	ListType p = list;
	// handle first node
	if(p->key == key) { // if such node found at the first
		t = p;
		list = p->next;
		delete t;
		return true;
	}
	// handle the rest
	while(p->next != NULL) {
		if(p->next->key == key) {
			t = p->next;
			if(p->next->next != NULL) { // middle node
				p->next = p->next->next;
			} else { // the last one
				p->next = NULL;
			}
			delete t;
			return true;
		}
		p = p->next;
	}
   return false; // no key present
}

bool insertList(const std::string &key, int& value, ListType& list) {
	// if the node with given key already exists
	if(lookupList(key, list) != NULL) {
		return false;
	}
	// insert the node to the head of the list
	list = new Node(key, value, list);
	return true;
}

void printAllList(ListType& list){
	ListType p = list;
	while(p != NULL) {
		cout << p->key << " " << p->value << endl;
		p = p->next;
	}
}