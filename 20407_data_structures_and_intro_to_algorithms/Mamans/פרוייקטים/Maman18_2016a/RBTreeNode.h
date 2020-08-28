/*
  RBTreeNode.h
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#pragma once
#include "datastructures.h"

template <class TKey, class TValue> class RBTreeNode
{
public:
	enum COLOR { BLACK, RED };
private:
	TKey key;
	TValue value;
	RBTreeNode<TKey, TValue> *left = nullptr;
	RBTreeNode<TKey, TValue> *right = nullptr;
	RBTreeNode<TKey, TValue> *parent = nullptr;
	COLOR color = BLACK;
	RBTreeNode<TKey, TValue> *nil = nullptr;
public:
	RBTreeNode();
	~RBTreeNode();

	TKey getKey();
	TValue getValue();
	RBTreeNode<TKey, TValue> *getLeft();
	RBTreeNode<TKey, TValue> *getRight();
	RBTreeNode<TKey, TValue> *getParent();
	COLOR getColor();
	void setKey(TKey k);
	void setValue(TValue v);
	void setLeft(RBTreeNode<TKey, TValue> *x);
	void setRight(RBTreeNode<TKey, TValue> *x);
	void setParent(RBTreeNode<TKey, TValue> *x);
	void setColor(COLOR c);
	void setNil(RBTreeNode<TKey, TValue> *nil);

	int HEIGHT();
};

template <class TKey, class TValue> RBTreeNode<TKey, TValue>::RBTreeNode()
{
	value = nullptr;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue>::~RBTreeNode()
{
	delete this->value;
}

template <class TKey, class TValue> TKey RBTreeNode<TKey, TValue>::getKey()
{
	return this->key;
}

template <class TKey, class TValue> TValue RBTreeNode<TKey, TValue>::getValue()
{
	return this->value;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTreeNode<TKey, TValue>::getLeft()
{
	return this->left;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTreeNode<TKey, TValue>::getRight()
{
	return this->right;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTreeNode<TKey, TValue>::getParent()
{
	return parent;
}

template <class TKey, class TValue> typename RBTreeNode<TKey, TValue>::COLOR RBTreeNode<TKey, TValue>::getColor()
{
	return this->color;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setKey(TKey k)
{
	this->key = k;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setValue(TValue v)
{
	this->value = v;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setLeft(RBTreeNode<TKey, TValue> *x)
{
	this->left = x;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setRight(RBTreeNode<TKey, TValue> *x)
{
	this->right = x;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setParent(RBTreeNode<TKey, TValue> *x)
{
	this->parent = x;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setColor(typename RBTreeNode<TKey, TValue>::COLOR c)
{
	this->color = c;
}

template <class TKey, class TValue> void RBTreeNode<TKey, TValue>::setNil(RBTreeNode<TKey, TValue> *nil)
{
	this->nil = nil;
}

template <class TKey, class TValue> int RBTreeNode<TKey, TValue>::HEIGHT()
{
	int lHeight = left == nil ? 0 : left->HEIGHT() + 1;
	int rHeight = right == nil ? 0 : right->HEIGHT() + 1;
	return lHeight > rHeight ? lHeight : rHeight;
}
