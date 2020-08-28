<?php

class AvlNode
{
    public $key;
    public $parent;
    public $left;
    public $right;
    public $height = 0;

    // reference to the node in the 'other' tree
    public $ref;

    // constructs a new avl node
    public function __construct($key)
    {
        $this->key = $key;
    }

    public function toString()
    {
        return $this->key;
    }
}
