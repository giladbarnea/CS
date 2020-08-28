/*
  Customer.h
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#pragma once
#include <string>
#include "datastructures.h"
class Customer
{
public:
	std::string name;
	std::string idNumber;
	long custNumber;
	int balance;
	LinkedListNode<Customer *> *nodeInBalanceList;
	LinkedList<Customer *> *balanceList;
	RBTreeNode<int, LinkedList<Customer *> *> *nodeInBalanceTree;
	Customer(std::string name, std::string idNumber, long custNumber);
	~Customer();
};

