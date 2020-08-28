/*
  RBTree.h
  20407 Data Structures and Introduction to Algorithms
  Maman 18
*/
#pragma once
#include "RBTreeNode.h"
#include <stdexcept>
#include <iostream>
#include <fstream>

// This is my implementaion for the algorithms provided in
// Introduction to Algorithms, Second Edition
// Hebrew Edition
template <class TKey, class TValue> class RBTree
{
private:
	RBTreeNode<TKey, TValue> *root;
	RBTreeNode<TKey, TValue> *nil;
	void INSERT_FIXUP(RBTreeNode<TKey, TValue> *x);
	void DELETE_FIXUP(RBTreeNode<TKey, TValue> *x);
	void DeleteAllNodes(RBTreeNode<TKey, TValue> *x);
	void LEFT_ROTATE(RBTreeNode<TKey, TValue> *x);
	void RIGHT_ROTATE(RBTreeNode<TKey, TValue> *y);

	// For debugging
	bool VerifyRBPropertyNode(RBTreeNode<TKey, TValue> *x, int *blackHeight);
	void PrintNodes(std::ofstream& f, RBTreeNode<TKey, TValue> *x);
	void PrintEdges(std::ofstream& f, RBTreeNode<TKey, TValue> *x);
	// End for debugging

public:
	RBTree();
	~RBTree();
	RBTreeNode<TKey, TValue> *getRoot();
	RBTreeNode<TKey, TValue> *getNil();
	void setRoot(RBTreeNode<TKey, TValue> *x);

	int HEIGHT();

	RBTreeNode<TKey, TValue> *SEARCH(TKey k);
	RBTreeNode<TKey, TValue> *SEARCH(RBTreeNode<TKey, TValue> *x, TKey k);
	void INSERT(RBTreeNode<TKey, TValue> *x);
	RBTreeNode<TKey, TValue> *DELETE(RBTreeNode<TKey, TValue> *z);
	RBTreeNode<TKey, TValue> *MINIMUM();
	RBTreeNode<TKey, TValue> *MINIMUM(RBTreeNode<TKey, TValue> *x);
	RBTreeNode<TKey, TValue> *MAXIMUM();
	RBTreeNode<TKey, TValue> *MAXIMUM(RBTreeNode<TKey, TValue> *x);
	RBTreeNode<TKey, TValue> *SUCCESSOR(RBTreeNode<TKey, TValue> *x);
	RBTreeNode<TKey, TValue> *PREDECESSOR(RBTreeNode<TKey, TValue> *x);

	// For debugging
	bool VerifyRBProperty();
	void SaveTree(char *fileName);
	// End for debugging
};

// Implementation

template <class TKey, class TValue> RBTree<TKey, TValue>::RBTree()
{
	nil = new RBTreeNode<TKey, TValue>();
	nil->setColor(RBTreeNode<TKey, TValue>::BLACK);
	root = nil;
}

template <class TKey, class TValue> RBTree<TKey, TValue>::~RBTree()
{
	DeleteAllNodes(root);
	delete nil;
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::DeleteAllNodes(RBTreeNode<TKey, TValue> *x)
{
	if (x != nil)
	{
		DeleteAllNodes(x->getLeft());
		DeleteAllNodes(x->getRight());
		delete x;
	}
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::getRoot()
{
	return root;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::getNil()
{
	return nil;
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::setRoot(RBTreeNode<TKey, TValue> *x)
{
	this->root = x;
}

template <class TKey, class TValue> int RBTree<TKey, TValue>::HEIGHT()
{
	if (root == nil)
		return -1;
	else
		return root->HEIGHT();
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::SEARCH(TKey k)
{
	return SEARCH(this->root, k);
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::SEARCH(RBTreeNode<TKey, TValue> *x, TKey k)
{
	if (x == nil || k == x->getKey())
		return x;
	if (k < x->getKey())
		return SEARCH(x->getLeft(), k);
	else
		return SEARCH(x->getRight(), k);
}

// pg. 236
template <class TKey, class TValue> void RBTree<TKey, TValue>::INSERT(RBTreeNode<TKey, TValue> *z)
{
	RBTreeNode<TKey, TValue> *y = nil;
	RBTreeNode<TKey, TValue> *x = root;
	while (x != nil)
	{
		y = x;
		if (z->getKey() < x->getKey())
			x = x->getLeft();
		else
			x = x->getRight();
	}
	z->setParent(y);
	if (y == nil)
	{
		setRoot(z);
	}
	else
	{
		if (z->getKey() < y->getKey())
			y->setLeft(z);
		else
			y->setRight(z);
	}
	z->setLeft(nil);
	z->setRight(nil);
	z->setColor(RBTreeNode<TKey, TValue>::RED);
	z->setNil(nil);
	INSERT_FIXUP(z);
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::INSERT_FIXUP(RBTreeNode<TKey, TValue> *z)
{
	while (z->getParent()->getColor() == RBTreeNode<TKey, TValue>::RED)
	{
		if (z->getParent() == z->getParent()->getParent()->getLeft())
		{
			RBTreeNode<TKey, TValue> *y = z->getParent()->getParent()->getRight();
			if (y->getColor() == RBTreeNode<TKey, TValue>::RED)
			{
				z->getParent()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				y->setColor(RBTreeNode<TKey, TValue>::BLACK);
				z->getParent()->getParent()->setColor(RBTreeNode<TKey, TValue>::RED);
				z = z->getParent()->getParent();
			}
			else
			{
				if (z == z->getParent()->getRight())
				{
					z = z->getParent();
					LEFT_ROTATE(z);
				}
				z->getParent()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				z->getParent()->getParent()->setColor(RBTreeNode<TKey, TValue>::RED);
				RIGHT_ROTATE(z->getParent()->getParent());
			}
		}
		else
		{
			RBTreeNode<TKey, TValue> *y = z->getParent()->getParent()->getLeft();
			if (y->getColor() == RBTreeNode<TKey, TValue>::RED)
			{
				z->getParent()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				y->setColor(RBTreeNode<TKey, TValue>::BLACK);
				z->getParent()->getParent()->setColor(RBTreeNode<TKey, TValue>::RED);
				z = z->getParent()->getParent();
			}
			else
			{
				if (z == z->getParent()->getLeft())
				{
					z = z->getParent();
					RIGHT_ROTATE(z);
				}
				z->getParent()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				z->getParent()->getParent()->setColor(RBTreeNode<TKey, TValue>::RED);
				LEFT_ROTATE(z->getParent()->getParent());
			}
		}
	}
	root->setColor(RBTreeNode<TKey, TValue>::BLACK);
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::DELETE(RBTreeNode<TKey, TValue> *z)
{
	RBTreeNode<TKey, TValue> *y;
	if (z->getLeft() == this->nil || z->getRight() == this->nil)
		y = z;
	else
		y = SUCCESSOR(z);

	RBTreeNode<TKey, TValue> *x;
	if (y->getLeft() != this->nil)
		x = y->getLeft();
	else
		x = y->getRight();

	x->setParent(y->getParent());
	if (y->getParent() == this->nil)
	{
		this->setRoot(x);
	}
	else
	{
		if (y == y->getParent()->getLeft())
			y->getParent()->setLeft(x);
		else
			y->getParent()->setRight(x);
	}

	RBTreeNode<TKey, TValue>::COLOR ycolor = y->getColor();
	if (y != z)
	{
		// This part is implemented in a different way then the algorithm in the book, to ensure that the node returned for deletion is the input node z
		y->setParent(z->getParent());
		if (z->getParent() == this->nil)
			this->root = y;
		else if (z->getParent()->getLeft() == z)
			z->getParent()->setLeft(y);
		else
			z->getParent()->setRight(y);
		y->setLeft(z->getLeft());
		z->getLeft()->setParent(y);
		y->setRight(z->getRight());
		z->getRight()->setParent(y);
		y->setColor(z->getColor());
		y = z;
	}

	if (ycolor == RBTreeNode<TKey, TValue>::BLACK)
		this->DELETE_FIXUP(x);
	return y;
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::DELETE_FIXUP(RBTreeNode<TKey, TValue> *x)
{
	while (x != this->root && x->getColor() == RBTreeNode<TKey, TValue>::BLACK)
	{
		if (x == x->getParent()->getLeft())
		{
			RBTreeNode<TKey, TValue> *w = x->getParent()->getRight();
			if (w->getColor() == RBTreeNode<TKey, TValue>::RED)
			{
				w->setColor(RBTreeNode<TKey, TValue>::BLACK);
				x->getParent()->setColor(RBTreeNode<TKey, TValue>::RED);
				this->LEFT_ROTATE(x->getParent());
				w = x->getParent()->getRight();
			}
			if (w->getLeft()->getColor() == RBTreeNode<TKey, TValue>::BLACK && w->getRight()->getColor() == RBTreeNode<TKey, TValue>::BLACK)
			{
				w->setColor(RBTreeNode<TKey, TValue>::RED);
				x = x->getParent();
			}
			else
			{
				if (w->getRight()->getColor() == RBTreeNode<TKey, TValue>::BLACK)
				{
					w->getLeft()->setColor(RBTreeNode<TKey, TValue>::BLACK);
					w->setColor(RBTreeNode<TKey, TValue>::RED);
					this->RIGHT_ROTATE(w);
					w = x->getParent()->getRight();
				}
				w->setColor(x->getParent()->getColor());
				x->getParent()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				w->getRight()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				this->LEFT_ROTATE(x->getParent());
				x = this->root;
			}
		}
		else
		{
			RBTreeNode<TKey, TValue> *w = x->getParent()->getLeft();
			if (w->getColor() == RBTreeNode<TKey, TValue>::RED)
			{
				w->setColor(RBTreeNode<TKey, TValue>::BLACK);
				x->getParent()->setColor(RBTreeNode<TKey, TValue>::RED);
				this->RIGHT_ROTATE(x->getParent());
				w = x->getParent()->getLeft();
			}
			if (w->getRight()->getColor() == RBTreeNode<TKey, TValue>::BLACK && w->getLeft()->getColor() == RBTreeNode<TKey, TValue>::BLACK)
			{
				w->setColor(RBTreeNode<TKey, TValue>::RED);
				x = x->getParent();
			}
			else
			{
				if (w->getLeft()->getColor() == RBTreeNode<TKey, TValue>::BLACK)
				{
					w->getRight()->setColor(RBTreeNode<TKey, TValue>::BLACK);
					w->setColor(RBTreeNode<TKey, TValue>::RED);
					this->LEFT_ROTATE(w);
					w = x->getParent()->getLeft();
				}
				w->setColor(x->getParent()->getColor());
				x->getParent()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				w->getLeft()->setColor(RBTreeNode<TKey, TValue>::BLACK);
				this->RIGHT_ROTATE(x->getParent());
				x = this->root;
			}
		}
	}

	x->setColor(RBTreeNode<TKey, TValue>::BLACK);
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::LEFT_ROTATE(RBTreeNode<TKey, TValue> *x)
{
	if (x->getRight() == nil)
		throw std::logic_error("x->getRight() is nil");
	RBTreeNode<TKey, TValue> *y = x->getRight();
	x->setRight(y->getLeft());
	if (y->getLeft() != nil)
		y->getLeft()->setParent(x);
	y->setParent(x->getParent());
	if (x->getParent() == nil)
	{
		this->setRoot(y);
	}
	else
	{
		if (x == x->getParent()->getLeft())
			x->getParent()->setLeft(y);
		else
			x->getParent()->setRight(y);
	}
	y->setLeft(x);
	x->setParent(y);
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::RIGHT_ROTATE(RBTreeNode<TKey, TValue> *y)
{
	if (y->getLeft() == nil)
		throw std::logic_error("y->getLeft() is nil");
	RBTreeNode<TKey, TValue> *x = y->getLeft();
	y->setLeft(x->getRight());
	if (x->getRight() != nil)
		x->getRight()->setParent(y);
	x->setParent(y->getParent());
	if (y->getParent() == nil)
	{
		this->setRoot(x);
	}
	else
	{
		if (y == y->getParent()->getRight())
			y->getParent()->setRight(x);
		else
			y->getParent()->setLeft(x);
	}
	x->setRight(y);
	y->setParent(x);
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::MINIMUM()
{
	if (this->root == this->nil)
		return this->nil;
	else
		return this->MINIMUM(this->root);
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::MINIMUM(RBTreeNode<TKey, TValue> *x)
{
	while (x->getLeft() != this->nil)
		x = x->getLeft();
	return x;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::MAXIMUM()
{
	if (this->root == this->nil)
		return this->nil;
	else
		return this->MAXIMUM(this->root);
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::MAXIMUM(RBTreeNode<TKey, TValue> *x)
{
	while (x->getRight() != this->nil)
		x = x->getRight();
	return x;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::SUCCESSOR(RBTreeNode<TKey, TValue> *x)
{
	if (x->getRight() != this->nil)
		return MINIMUM(x->getRight());
	RBTreeNode<TKey, TValue> *y = x->getParent();
	while (y != this->nil && x == y->getRight())
	{
		x = y;
		y = y->getParent();
	}
	return y;
}

template <class TKey, class TValue> RBTreeNode<TKey, TValue> *RBTree<TKey, TValue>::PREDECESSOR(RBTreeNode<TKey, TValue> *x)
{
	if (x->getLeft() != this->nil)
		return MAXIMUM(x->getLeft());
	RBTreeNode<TKey, TValue> *y = x->getParent();
	while (y != this->nil && x == y->getLeft())
	{
		x = y;
		y = y->getParent();
	}
	return y;
}

//////////////////////////////////////////////////////////
// All the following methods are for debugging purposes //
//////////////////////////////////////////////////////////

/* This method checks if the tree is a valid red-black tree */
template <class TKey, class TValue> bool RBTree<TKey, TValue>::VerifyRBProperty()
{
	int blackHeight = 0;
	bool RBProp = VerifyRBPropertyNode(root, &blackHeight);
	return RBProp;
}

template <class TKey, class TValue> bool RBTree<TKey, TValue>::VerifyRBPropertyNode(RBTreeNode<TKey, TValue> *x, int *blackHeight)
{
	*blackHeight = 0;
	if (x == nil)
	{
		return x->getColor() == RBTreeNode<TKey, TValue>::BLACK;
	}
	else
	{
		// x is root
		if (x->getParent() == nil)
		{
			if (x->getColor() != RBTreeNode<TKey, TValue>::BLACK)
				return false;
		}
		if (x->getParent()->getColor() == RBTreeNode<TKey, TValue>::RED)
		{
			if (x->getColor() != RBTreeNode<TKey, TValue>::BLACK)
				return false;
		}
		RBTreeNode<TKey, TValue> *left = x->getLeft();
		RBTreeNode<TKey, TValue> *right = x->getRight();
		int leftBlackHeight;
		int rightBlackHeight;
		bool leftRBProp = VerifyRBPropertyNode(left, &leftBlackHeight);
		bool rightRBProp = VerifyRBPropertyNode(right, &rightBlackHeight);
		if (!leftRBProp || !rightRBProp)
			return false;
		leftBlackHeight += left->getColor() == RBTreeNode<TKey, TValue>::BLACK;
		rightBlackHeight += right->getColor() == RBTreeNode<TKey, TValue>::BLACK;
		if (leftBlackHeight != rightBlackHeight)
			return false;
		*blackHeight = leftBlackHeight;
		return true;
	}
}

/* This method saves the tree in a tgv file that can be opened wih yEd Graph Editor */
template <class TKey, class TValue> void RBTree<TKey, TValue>::SaveTree(char *fileName)
{
	std::ofstream f;
	f.open(fileName, std::ios::out | std::ios::trunc);

	PrintNodes(f, root);
	f << "#" << std::endl;
	PrintEdges(f, root);
	f.close();
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::PrintNodes(std::ofstream& f, RBTreeNode<TKey, TValue> *x)
{
	f << x->getKey() << " ";
	if (x->getColor() == RBTreeNode<int>::BLACK)
		f << "!!";
	f << x->getKey();
	if (x->getColor() == RBTreeNode<int>::BLACK)
		f << "!!";
	f << " " << std::endl;
	if (x->getLeft() != nil)
		PrintNodes(f, x->getLeft());
	if (x->getRight() != nil)
		PrintNodes(f, x->getRight());
}

template <class TKey, class TValue> void RBTree<TKey, TValue>::PrintEdges(std::ofstream& f, RBTreeNode<TKey, TValue> *x)
{
	if (x->getLeft() != nil)
	{
		f << x->getKey() << " " << x->getLeft()->getKey() << std::endl;
		PrintEdges(f, x->getLeft());
	}
	if (x->getRight() != nil)
	{
		f << x->getKey() << " " << x->getRight()->getKey() << std::endl;
		PrintEdges(f, x->getRight());
	}
}
