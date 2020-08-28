<?php
require_once 'HeapNode.php';
require_once 'MaxHeap.php';
require_once 'MinHeap.php';

/**
 * MultiHeap is a data structure that supports the required methods.
 *
 * It is implemented using a maximum heap and a minimum heap.
 *
 * Each of these heaps stores the same values but in a different order.
 * Their value is a *reference* to a single HeapNode object.
 */
class MultiHeap
{
    /** @var    MaxHeap     Holds the maximum heap */
    private $max;

    /** @var    MinHeap     Holds the minimum heap */
    private $min;

    /**
     * Builds a new MultiHeap
     *
     * Runtime: O(n)
     *
     * @param   array   $list   A list of numbers
     */
    public function __construct($list)
    {
        // convert the numbers to HeapNode objects
        $nodes = array();

        foreach ($list as $i => $value) {
            $nodes[$i] = new HeapNode($value, $i);
        }

        // create a max/min heaps with these nodes
        $this->max = new MaxHeap($nodes);
        $this->min = new MinHeap($nodes);
    }

    /**
     * Insert a new value to the MultiHeap
     *
     * Runtime: O(lg n)
     *
     * @param   number  $value  Value to insert
     */
    public function insert($value)
    {
        // convert to HeapNode
        $node = new HeapNode($value);

        // insert to max/min heaps
        $this->max->insert($node);
        $this->min->insert($node);
    }

    /**
     * Find the maximal value
     *
     * Runtime: O(1)
     *
     * @return  number
     */
    public function findMax()
    {
        // return max's greatest
        return $this->max->getGreatest()->value;
    }

    /**
     * Find the minimal value
     *
     * Runtime: O(1)
     *
     * @return  number
     */
    public function findMin()
    {
        // return min's greatest
        return $this->min->getGreatest()->value;
    }

    /**
     * Delete the maximal value
     *
     * Runtime: O(lg n)
     *
     * @return  number  The value that has been deleted
     */
    public function deleteMax()
    {
        // delete max's greatest
        $node = $this->max->deleteGreatest();

        // delete from min
        $this->min->delete($node->minIdx);

        return $node->value;
    }

    /**
     * Delete the minimal value
     *
     * Runtime: O(lg n)
     *
     * @return  number  The value that has been deleted
     */
    public function deleteMin()
    {
        // delete min's greatest
        $node = $this->min->deleteGreatest();

        // delete from max
        $this->max->delete($node->maxIdx);

        return $node->value;
    }

    /**
     * Returns a string representation of this object
     *
     * @return string
     */
    public function toString()
    {
        return
            'Max: '.$this->max->toString()."\n".
            'Min: '.$this->min->toString()
        ;
    }
}
