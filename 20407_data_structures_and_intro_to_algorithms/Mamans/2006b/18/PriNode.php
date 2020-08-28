<?php
require_once 'AvlNode.php';

class PriNode extends AvlNode
{
    public $id;

    // constructs a new priority node
    public function __construct($pri, $id)
    {
        $this->key = $pri;
        $this->id = $id;
    }

    public function toString()
    {
        return $this->key.', '.$this->id;
    }
}
