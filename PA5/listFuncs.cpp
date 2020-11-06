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

int *lookupList(const std::string &key, Node*& arr) {
	Node *p = arr;
	while(p != NULL) {
		if(p->key == key) {
			return &(p->value);
		}
		p = p->next;
	}
   return NULL;
}

bool removeList(const std::string &key, Node*& arr) {
	Node *t;
	Node *p = arr;
	// handle first node
	if(p->key == key) {
		t = p;
		arr = p->next;
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

bool insertList(const std::string &key, int& value, Node*& arr) {
	arr = new Node(key, value, arr);
	return true;
}

void printAllList(Node** arr, int size){
	for(int i = 0; i < size; i++) {
		if(arr[i] != NULL) {
			Node* p = arr[i];
			while(p != NULL) {
				cout << p->key << " " << p->value << endl;
				p = p->next;
			}
		}
	}
}