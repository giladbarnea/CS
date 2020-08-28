<?php

// AVL tree. exact implemention of the pseudocode in the course guide
class Avl
{
    public $root;

    /**
     * Compares two values
     *
     * Runtime: O(1)
     *
     * @param   number  $a  First value
     * @param   number  $b  Second value
     * @return  boolean     True if the first value is greater than the second, false otherwise
     */
    public function compare($a, $b)
    {
        return $a > $b;
    }

    /**
     * Compares two nodes
     */
    public function compareNodes($a, $b)
    {
        return $this->compare($a->key, $b->key);
    }

    // return the height of a node
    public function height($q)
    {
        if (is_null($q)) {
            return -1;
        } else {
            return $q->height;
        }
    }

    // updates the height of a node
    public function computeHeight($q)
    {
        if (!is_null($q)) {
            $q->height = 1 + max($this->height($q->left), $this->height($q->right));
        }
    }

    // returns the balance factor of a node
    public function balance($q)
    {
        return $this->height($q->left) - $this->height($q->right);
    }

    // returns the higher son of a node
    public function higherSon($q)
    {
        if ($this->height($q->left) > $this->height($q->right)) {
            return $q->left;
        } else if ($this->height($q->left) < $this->height($q->right)) {
            return $q->right;
        } else {
            return null;
        }
    }

    // whether a node is unbalanced
    public function unbalanced($q)
    {
        return (abs($this->balance($q)) > 1);
    }

    // returns the minimum node
    public function minimum($x)
    {
        while (!is_null($x->left)) {
            $x = $x->left;
        }

        return $x;
    }

    // returns the maximum node
    public function maximum($x)
    {
        while (!is_null($x->right)) {
            $x = $x->right;
        }

        return $x;
    }

    // returns a node's successor
    public function successor($x)
    {
        if (!is_null($x->right)) {
            return $this->minimum($x->right);
        }

        $y = $x->parent;

        while (!is_null($y) && $x == $y->right) {
            $x = $y;
            $y = $y->parent;
        }

        return $y;
    }

    // returns a node's predecessor
    public function predecessor($x)
    {
        if (!is_null($x->left)) {
            return $this->maximum($x->left);
        }

        $y = $x->parent;

        while (!is_null($y) && $x == $y->left) {
            $x = $y;
            $y = $y->parent;
        }

        return $y;
    }

    // left rotates a node
    protected function leftRotate($x)
    {
        $y = $x->right;
        $x->right = $y->left;

        if (!is_null($y->left)) {
            $y->left->parent = $x;
        }

        $y->parent = $x->parent;

        if (is_null($x->parent)) {
            $this->root = $y;
        } else if ($x == $x->parent->left) {
            $x->parent->left = $y;
        } else {
            $x->parent->right = $y;
        }

        $y->left = $x;
        $x->parent = $y;

        $this->computeHeight($x);
        $this->computeHeight($y);
    }

    // right rotates a node
    protected function rightRotate($x)
    {
        $y = $x->left;
        $x->left = $y->right;

        if (!is_null($y->right)) {
            $y->right->parent = $x;
        }

        $y->parent = $x->parent;

        if (is_null($x->parent)) {
            $this->root = $y;
        } else if ($x == $x->parent->right) {
            $x->parent->right = $y;
        } else {
            $x->parent->left = $y;
        }

        $y->right = $x;
        $x->parent = $y;

        $this->computeHeight($x);
        $this->computeHeight($y);
    }

    // rebalances a node
    protected function rebalance($q)
    {
        $r = $this->higherSon($q);
        $s = $this->higherSon($r);

        if (is_null($s)) {
            if ($r == $q->left) {
                $s = $r->left;
            } else {
                $s = $r->right;
            }
        }

        if ($r == $q->left) {
            if ($s == $r->right) {
                $this->leftRotate($r);
            }
            
            $this->rightRotate($q);
        } else {
            if ($s == $r->left) {
                $this->rightRotate($r);
            }

            $this->leftRotate($q);
        }
    }

    // inserts new node to the tree
    public function insert($z)
    {
        $y = null;
        $x = $this->root;
        $critical = null;

        while (!is_null($x)) {
            if ($this->balance($x) != 0) {
                $critical = $x;
            }

            $y = $x;

            if ($this->compareNodes($x, $z)) {
                $x = $x->left;
            } else {
                $x = $x->right;
            }
        }

        $z->parent = $y;

        if (is_null($y)) {
            $this->root = $z;
        } else if ($this->compareNodes($y, $z)) {
            $y->left = $z;
        } else {
            $y->right = $z;
        }

        while ($y != $critical) {
            $this->computeHeight($y);
            $y = $y->parent;
        }

        if (!is_null($y) && $this->unbalanced($y)) {
            $this->rebalance($y);
        }
    }

    // searches a key in the tree
    public function search($k)
    {
        $x = $this->root;

        while (!is_null($x) && $k != $x->key) {
            if ($this->compare($x->key, $k)) {
                $x = $x->left;
            } else {
                $x = $x->right;
            }
        }

        return $x;
    }

    // deletes a node from the tree
    public function delete($z)
    {
        if (is_null($z->left) || is_null($z->right)) {
            $y = $z;
        } else {
            $y = $this->successor($z);
        }

        if (!is_null($y->left)) {
            $x = $y->left;
        } else {
            $x = $y->right;
        }

        if (!is_null($x)) {
            $x->parent = $y->parent;
        }

        if (is_null($y->parent)) {
            $this->root = $x;
        } else {
            if ($y == $y->parent->left) {
                $y->parent->left = $x;
            } else {
                $y->parent->right = $x;
            }
        }

        if ($y != $z) {
            $this->moveNode($y, $z);
        }

        $r = $y->parent;
        $flag = true;

        while (!is_null($r) && $flag) {
            $tmp = $r->height;
            $this->computeHeight($r);

            if ($this->unbalanced($r)) {
                $this->rebalance($r);
                $r = $r->parent;
            }

            if ($tmp == $r->height) {
                $flag = false;
            }

            $r = $r->parent;
        }

        return $y;
    }

    // moves $y data to $z.
    // used in delete function, when we want to delete $z but $y is the actual node deleted.
    public function moveNode($y, $z)
    {
        $z->key = $y->key;

        // update reference
        $y->ref->ref = $z;
        $z->ref = $y->ref;
    }

    // DEBUG FUNCTION. displays the tree
    public function display()
    {
        $this->displayNode($this->root);
    }

    // DEBUG FUNCTION. displays a node
    public function displayNode($q, $l=1)
    {
        if (is_null($q)) {
            echo "NIL\n";
            return;
        }

        echo $q->toString()."\n";

        echo str_repeat(' ', $l*2).'L: ';
        $this->displayNode($q->left, $l+1);
        echo str_repeat(' ', $l*2).'R: ';
        $this->displayNode($q->right, $l+1);
    }

}

