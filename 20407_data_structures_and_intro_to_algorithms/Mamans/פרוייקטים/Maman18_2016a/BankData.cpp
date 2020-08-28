/*
  BankData.cpp
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#include "BankData.h"


BankData::BankData()
{
}


BankData::~BankData()
{
}

Customer *BankData::NewCustomer(std::string name, std::string idNumber, long custNumber, int balance)
{
	// Check existence of a customer with the same custNumber (this is not allowed)
	RBTreeNode<long, Customer *> *searchResult = this->customersByNumber.SEARCH(custNumber);
	if (searchResult != this->customersByNumber.getNil())
		throw std::logic_error("A customer with this number already exists");


	Customer *customer = new Customer(name, idNumber, custNumber);
	customer->balance = balance;

	RBTreeNode<long, Customer*> *nodeById = new RBTreeNode<long, Customer*>;
	nodeById->setKey(custNumber);
	nodeById->setValue(customer);

	this->customersByNumber.INSERT(nodeById);

	RBTreeNode<int, LinkedList<Customer *> *> *nodeByBalance = this->customersByBalance.SEARCH(balance);
	
	LinkedList<Customer *> *list;
	if (nodeByBalance != this->customersByBalance.getNil())
	{
		list = nodeByBalance->getValue();
	}
	else
	{
		list = new LinkedList<Customer *>;
		nodeByBalance = new RBTreeNode<int, LinkedList<Customer *> *>;
		nodeByBalance->setKey(balance);
		nodeByBalance->setValue(list);
		this->customersByBalance.INSERT(nodeByBalance);
	}
	LinkedListNode<Customer *> *listNode = new LinkedListNode<Customer *>;
	listNode->setKey(customer);
	list->INSERT(listNode);
	customer->balanceList = list;
	customer->nodeInBalanceList = listNode;
	customer->nodeInBalanceTree = nodeByBalance;

	return customer;
}

void BankData::RemoveCustomer(long custNumber)
{
	RBTreeNode<long, Customer *> *searchResult = this->customersByNumber.SEARCH(this->customersByNumber.getRoot(), custNumber);
	if (searchResult == this->customersByNumber.getNil())
		throw std::logic_error("No customer with this number");
	
	Customer *customer = searchResult->getValue();

	if (customer->balance != 0)
		throw std::logic_error("Balance must be 0 before leaving the bank");

	LinkedListNode<Customer *> *nodeInBalanceList = customer->nodeInBalanceList;
	LinkedList<Customer *> *balanceList = customer->balanceList;

	balanceList->DELETE(nodeInBalanceList);
	delete nodeInBalanceList;

	if (balanceList->IsEmpty())
	{
		RBTreeNode<int, LinkedList<Customer *> *> *nodeToDelete = this->customersByBalance.DELETE(customer->nodeInBalanceTree);
		delete nodeToDelete;
	}

	delete this->customersByNumber.DELETE(searchResult);
}

void BankData::DepositOrWithdraw(std::string name, long custNumber, int amount)
{
	RBTreeNode<long, Customer *> *searchResult = this->customersByNumber.SEARCH(this->customersByNumber.getRoot(), custNumber);
	if (searchResult == this->customersByNumber.getNil())
		throw std::logic_error("No customer with this number");

	Customer *customer = searchResult->getValue();

	if (customer->name != name)
		throw std::logic_error("Customer name is different than name in the bank's data");

	LinkedListNode<Customer *> *nodeInBalanceList = customer->nodeInBalanceList;
	LinkedList<Customer *> *balanceList = customer->balanceList;

	balanceList->DELETE(nodeInBalanceList);
	delete nodeInBalanceList;

	if (balanceList->IsEmpty())
	{
		RBTreeNode<int, LinkedList<Customer *> *> *nodeToDelete = this->customersByBalance.DELETE(customer->nodeInBalanceTree);
		delete nodeToDelete;
	}

	int balance = customer->balance + amount;
	customer->balance = balance;

	RBTreeNode<int, LinkedList<Customer *> *> *nodeByBalance = this->customersByBalance.SEARCH(balance);

	LinkedList<Customer *> *list;
	if (nodeByBalance != this->customersByBalance.getNil())
	{
		list = nodeByBalance->getValue();
	}
	else
	{
		list = new LinkedList<Customer *>;
		nodeByBalance = new RBTreeNode<int, LinkedList<Customer *> *>;
		nodeByBalance->setKey(balance);
		nodeByBalance->setValue(list);
		this->customersByBalance.INSERT(nodeByBalance);
	}
	LinkedListNode<Customer *> *listNode = new LinkedListNode<Customer *>;
	listNode->setKey(customer);
	list->INSERT(listNode);
	customer->balanceList = list;
	customer->nodeInBalanceList = listNode;
	customer->nodeInBalanceTree = nodeByBalance;
}

int BankData::GetBalanceByNumber(long custNumber)
{
	RBTreeNode<long, Customer *> *searchResult = this->customersByNumber.SEARCH(this->customersByNumber.getRoot(), custNumber);
	if (searchResult == this->customersByNumber.getNil())
		throw std::logic_error("No customer with this number");

	Customer *customer = searchResult->getValue();
	return customer->balance;
}

LinkedList<Customer *> *BankData::GetRichest()
{
	RBTreeNode<int, LinkedList<Customer *> *> *richestNode = this->customersByBalance.MAXIMUM();
	if (richestNode == this->customersByBalance.getNil())
		throw std::logic_error("No customers in the bank!");

	return richestNode->getValue();
}

LinkedList<Customer *> *BankData::GetNegative()
{
	LinkedList<Customer *> *list = new LinkedList<Customer *>;
	auto x = this->customersByBalance.getRoot();
	AddNegativeRecursively(list, x, this->customersByBalance.getNil());
	return list;
}

void BankData::AddNegativeRecursively(LinkedList<Customer *> *list, RBTreeNode<int, LinkedList<Customer *> *> *x, RBTreeNode<int, LinkedList<Customer *> *> *nil)
{
	if (x == nil)
		return;
	AddNegativeRecursively(list, x->getLeft(), nil);
	if (x->getKey() < 0)
	{
		LinkedListNode<Customer *> *node = x->getValue()->getHead();
		while (node != nullptr)
		{
			LinkedListNode<Customer *> *newNode = new LinkedListNode<Customer *>;
			newNode->setKey(node->getKey());
			list->INSERT(newNode);
			node = node->getNext();
		}
		AddNegativeRecursively(list, x->getRight(), nil);
	}
}