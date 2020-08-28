<?php
require_once 'AbstractHeap.php';

/**
 * Implements a minimum heap
 *
 * Extends AbstractHeap and implements its abstract methods
 */
class MinHeap extends AbstractHeap
{
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
        return $a < $b;
    }

    /**
     * Update a node's index reference
     *
     * Runtime: O(1)
     * 
     * @param   integer $i  Node index
     */
    public function updateNodeIdx($i)
    {
        $this->nodes[$i]->minIdx = $i;
    }
}
