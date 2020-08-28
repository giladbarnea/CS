<?php

/**
 * A simple class to represent a single node
 *
 * Holds the node's value and max/min indexes.
 */
class HeapNode
{
    /** @var number     Node value */
    public $value;

    /** @var integer    Node index in the MaxHeap */
    public $maxIdx;

    /** @var integer    Node index in the MinHeap */
    public $minIdx;

    /**
     * Builds a new HeapNode
     *
     * Runtime: O(1)
     *
     * @param   number  $value  Node value
     * @param   integer $i      Initial index (default null)
     */
    public function __construct($value, $i=null)
    {
        $this->value = $value;
        $this->maxIdx = $this->minIdx = $i;
    }
}
