// Name: Yihao Zhou
// USC NetID: 9751577777
// CSCI 455 PA5
// Fall 2020

//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// lookup the node with given key in the given list
// return the pointer to the value of the node
// return NULL if no such node found
int *lookupList(const std::string &key, ListType& list);

// remove the node with given key in the given list
// return true if such node is found and is removed
// return false if such node does not exist
bool removeList(const std::string &key, ListType& list);

// insert the node with given key and value into the given list
// return false if the node already exists
// return true if insert successully
bool insertList(const std::string &key, int& value, ListType& list);

// print out all nodes with keys and values in list
void printAllList(ListType& list);

// keep the following line at the end of the file
#endif
