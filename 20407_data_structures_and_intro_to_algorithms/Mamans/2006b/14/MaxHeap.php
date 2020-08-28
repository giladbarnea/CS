<?php
require_once 'AbstractHeap.php';

/**
 * Implements a maximum heap
 *
 * Extends AbstractHeap and implements its abstract methods
 */
class MaxHeap extends AbstractHeap
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
        return $a > $b;
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
        $this->nodes[$i]->maxIdx = $i;
    }

}
