/*
  LinkedListNode.h
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#pragma once

template <class TKey> class LinkedListNode
{
private:
	TKey key;
	LinkedListNode<TKey> *prev;
	LinkedListNode<TKey> *next;
public:
	TKey getKey();
	LinkedListNode<TKey> *getPrev();
	LinkedListNode<TKey> *getNext();
	void setKey(TKey k);
	void setPrev(LinkedListNode<TKey> *x);
	void setNext(LinkedListNode<TKey> *x);
};

// Implementation

template <class TKey> TKey LinkedListNode<TKey>::getKey()
{
	return this->key;
}

template <class TKey> LinkedListNode<TKey> *LinkedListNode<TKey>::getPrev()
{
	return this->prev;
}

template <class TKey> LinkedListNode<TKey> *LinkedListNode<TKey>::getNext()
{
	return this->next;
}

template <class TKey> void LinkedListNode<TKey>::setKey(TKey k)
{
	this->key = k;
}

template <class TKey> void LinkedListNode<TKey>::setPrev(LinkedListNode<TKey> *x)
{
	this->prev = x;
}

template <class TKey> void LinkedListNode<TKey>::setNext(LinkedListNode<TKey> *x)
{
	this->next = x;
}
