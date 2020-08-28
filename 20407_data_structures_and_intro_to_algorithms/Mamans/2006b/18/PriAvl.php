<?php
require_once 'Avl.php';
require_once 'PriNode.php';

class PriAvl extends Avl
{
    public $max;

    /**
     * Compares two nodes
     */
    public function compareNodes($a, $b)
    {
        return (($a->key > $b->key) || (($a->key == $b->key) && ($a->id < $b->id)));
    }

    // inserts a node to the tree. modified to update the max reference
    public function insert($z)
    {
        // regular insert
        parent::insert($z);

        // update max
        if (is_null($this->max) || $this->compareNodes($z, $this->max)) {
            $this->max = $z;
        }   
    }

    // deletes a node from the tree. modified to update the max reference
    public function delete($z)
    {
        // update max
        if ($z == $this->max) {
            $this->max = $this->predecessor($z);
        }
        
        return parent::delete($z);
    }

    // moves $y data to $z.
    // used in delete function, when we want to delete $z but $y is the actual node deleted.
    public function moveNode($y, $z)
    {
        // regular move
        parent::moveNode($y, $z);

        // move id
        $z->id = $y->id;
    }
}
