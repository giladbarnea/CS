<?php
require_once 'HeapNode.php';

/**
 * Implements the heap logic that is common to both maximum and minimum heaps.
 *
 * The methods are based on the algorithms that is descrbied in the book,
 * and therefore their runtime is known.
 *
 * The MaxHeap and MinHeap extend this class and implement it's abstract methods.
 */
abstract class AbstractHeap
{
    /** @param  array   Holds the HeapNode objects */
    protected $nodes = array();

    /**
     * Builds a new heap
     *
     * Runtime: O(n)
     *
     * @param   array   $nodes  Array of HeapNode objects
     */
	public function __construct($nodes)
    {
        $this->nodes = $nodes;

        for ($i = intval($this->size() / 2); $i--; $i >= 0) {
            $this->heapify($i);
        }
    }

    /**
     * The well known heapify algorithm
     *
     * Runtime: O(lg n)
     *
     * @param   integer $i  Node index
     */
    public function heapify($i)
    {
        $left = $this->leftNode($i);
        $right = $this->rightNode($i);

        if ($left < $this->size() && $this->compare($this->nodes[$left]->value, $this->nodes[$i]->value)) {
            $greatest = $left;
        } else {
            $greatest = $i;
        }

        if ($right < $this->size() && $this->compare($this->nodes[$right]->value, $this->nodes[$greatest]->value)) {
            $greatest = $right;
        }

        if ($greatest != $i) {
            $this->swapNodes($i, $greatest);
            $this->heapify($greatest);
        }
    }

    /**
     * Inserts a node to the heap
     *
     * Runtime: O(lg n)
     *
     * @param   HeapNode    $node   The new node
     */
    public function insert($node)
    {
        $i = $this->size();

        while ($i > 0 && $this->compare($node->value, $this->nodes[$this->parentNode($i)]->value)) {
            $this->setNode($i, $this->nodes[$this->parentNode($i)]);
            $i = $this->parentNode($i);
        }

        $this->setNode($i, $node);
    }

    /**
     * Get the node with the greatest value
     *
     * Runtime: O(1)
     *
     * @return  HeapNode
     */
    public function getGreatest()
    {
        if ($this->size() < 1) {
            throw new Exception('Heap Underflow');
        }

        return $this->nodes[0];
    }

    /**
     * Delete a specific node
     *
     * Runtime: O(lg n)
     *
     * @param   integer $i  Node index
     * @return  HeapNode    The node that has been deleted
     */
    public function delete($i)
    {
        $node = $this->nodes[$i];

        $this->nodes[$i] = $this->nodes[$this->size() - 1];
        unset($this->nodes[$this->size() - 1]);

        $this->heapify($i);

        return $node;
    }

    /**
     * Delete the node with the greatest value
     *
     * Runtime: O(lg n)
     *
     * @return  HeapNode    The node that has been deleted
     */
    public function deleteGreatest()
    {
        if ($this->size() < 1) {
            throw new Exception('Heap Underflow');
        }

        return $this->delete(0);
    }

    /**
     * Returns the number of nodes in the heap
     *
     * The nodes are stored using a dynamic PHP array,
     * so we can just return the length of the array.
     *
     * The runtime is the same as PHP count() runtime,
     * which is assumed to be O(1).
     *
     * @return  integer     Heap size
     */
    public function size()
    {
        return count($this->nodes);
    }

    /**
     * Compares two values
     *
     * Runtime: O(1)
     *
     * @param   number  $a  First value
     * @param   number  $b  Second value
     * @return  boolean     True if the first value is greater than the second, false otherwise
     */
    abstract public function compare($a, $b);

    /**
     * Returns a node's parent index
     *
     * Runtime: O(1)
     *
     * @param   integer $i  Node index
     * @return  integer     Parent index
     */
    public function parentNode($i)
    {
        return intval(($i - 1) / 2);
    }

    /**
     * Returns a node's left child index
     *
     * Runtime: O(1)
     *
     * @param   integer $i  Node index
     * @return  integer     Left child index
     */
    public function leftNode($i)
    {
        return (2 * $i) + 1;
    }

    /**
     * Returns a node's right child index
     *
     * Runtime: O(1)
     *
     * @param   integer $i  Node index
     * @return  integer     Right child index
     */
    public function rightNode($i)
    {
        return 2 * ($i + 1);
    }

    /**
     * Swap nodes $a and $b
     *
     * Runtime: O(1)
     *
     * @param   integer $a  First node index
     * @param   integer $b  Second node index
     */
    public function swapNodes($a, $b)
    {
        $temp = $this->nodes[$a];
        $this->setNode($a, $this->nodes[$b]);
        $this->setNode($b, $temp);
    }

    /**
     * Sets a node's value and updates its reference
     *
     * Runtime: O(1)
     *
     * @param   integer     $i      Node index
     * @param   HeapNode    $node   The node
     */
    public function setNode($i, $node) {
        $this->nodes[$i] = $node;
        $this->updateNodeIdx($i);
    }

    /**
     * Update a node's index reference
     *
     * Runtime: O(1)
     * 
     * @param   integer $i  Node index
     */
    abstract public function updateNodeIdx($i);

    /**
     * Returns a string representation of the heap
     *
     * @return string
     */
    public function toString()
    {
        $str = '';

        foreach ($this->nodes as $node) {
            if ($str !== '') {
                $str .= ', ';
            }

            $str .= $node->value;
        }

        return $str;
    }
}
