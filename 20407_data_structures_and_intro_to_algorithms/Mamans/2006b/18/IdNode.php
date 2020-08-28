<?php
require_once 'AvlNode.php';

class IdNode extends AvlNode
{
    // constructs a new id node
    public function __construct($key)
    {
        $this->key = $key;
    }   
}
