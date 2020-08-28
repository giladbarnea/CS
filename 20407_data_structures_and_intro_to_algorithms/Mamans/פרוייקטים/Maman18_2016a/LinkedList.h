/*
  LinkedList.h
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#pragma once
#include "LinkedListNode.h"

template <class TKey> class LinkedList
{
private:
	LinkedListNode<TKey> *head;
	LinkedListNode<TKey> *tail;
public:
	LinkedList();
	~LinkedList();

	void INSERT(LinkedListNode<TKey> *x);
	void DELETE(LinkedListNode<TKey> *x);
	bool IsEmpty();
	LinkedListNode<TKey> *getHead();
	LinkedListNode<TKey> *getTail();
};

// Implementation

template <class TKey> LinkedList<TKey>::LinkedList()
{
	this->head = nullptr;
	this->tail = nullptr;
}

template <class TKey> LinkedList<TKey>::~LinkedList()
{
	while (head != nullptr)
	{
		LinkedListNode<TKey> *x = head->getNext();
		delete head;
		head = x;
	}
}

template <class TKey> void LinkedList<TKey>::INSERT(LinkedListNode<TKey> *x)
{
	x->setNext(this->head);
	if (this->head != nullptr)
	{
		this->head->setPrev(x);
	}
	else
	{
		this->tail = x;
	}
	this->head = x;
	x->setPrev(nullptr);
}

template <class TKey> void LinkedList<TKey>::DELETE(LinkedListNode<TKey> *x)
{
	if (x->getPrev() != nullptr)
	{
		x->getPrev()->setNext(x->getNext());
	}
	else
	{
		this->head = x->getNext();
	}

	if (x->getNext() != nullptr)
	{
		x->getNext()->setPrev(x->getPrev());
	}
	else
	{
		this->tail = x->getPrev();
	}
}

template <class TKey> bool LinkedList<TKey>::IsEmpty()
{
	return this->head == nullptr;
}

template <class TKey> LinkedListNode<TKey> *LinkedList<TKey>::getHead()
{
	return this->head;
}

template <class TKey> LinkedListNode<TKey> *LinkedList<TKey>::getTail()
{
	return this->tail;
}
