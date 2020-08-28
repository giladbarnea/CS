/*
  BankData.h
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#pragma once
#include "Customer.h"
#include "datastructures.h"

class BankData
{
private:
	RBTree<long, Customer*> customersByNumber;
	RBTree<int, LinkedList<Customer*>*> customersByBalance;
	void AddNegativeRecursively(LinkedList<Customer *> *list, RBTreeNode<int, LinkedList<Customer *> *> *x, RBTreeNode<int, LinkedList<Customer *> *> *nil);

public:
	BankData();
	~BankData();
	Customer *NewCustomer(std::string name, std::string idNumber, long custNumber, int balance);
	void RemoveCustomer(long custNumber);
	void DepositOrWithdraw(std::string name, long custNumber, int amount);
	int GetBalanceByNumber(long custNumber);
	LinkedList<Customer *> *GetRichest();
	LinkedList<Customer *> *GetNegative();
};
