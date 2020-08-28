<?php
set_time_limit(0);
error_reporting(E_ALL | E_STRICT);

require_once 'MultiHeap.php';

/**
 * A simple UI to the MultiHeap
 */
class MultiHeap_UI
{
    /** Holds the MultiHeap object */
    private $heap;

    /* @var array   A list of supported commands */
    private $commands = array(
        1   => 'build',
        2   => 'insert',
        3   => 'findMax',
        4   => 'findMin',
        5   => 'deleteMax',
        6   => 'deleteMin',
        7   => 'output',
        8   => 'quit'
    );

    public function run()
    {
        echo "Enter command: ";

        while (($buf = $this->getLine()) !== false) {
            if (!isset($this->commands[$buf])) {
                echo "Invalid command \n";
            } else {
                $cmd = $this->commands[$buf];

                if ($cmd == 'quit') {
                    break;
                }

                if ($cmd != 'build' && !$this->heap) {
                    echo "You must build a heap first\n";
                } else {
                    $this->$cmd();
                }
            }


            if ($this->heap) {
                echo "\n".$this->heap->toString()."\n";
            }

            echo "\nEnter command: ";
        }

        echo "\nGoodbye\n";
    }


    public function getLine()
    {
        $buf = fgets(STDIN);

        // return false on EOF
        if ($buf === false) {
            return false;
        }

        return trim($buf);
    }

    public function build()
    {
        echo "Enter a list of numbers, separated by space: ";

        $list = explode(' ', $this->getLine());

        foreach ($list as $num) {
            if (!is_numeric($num)) {
                echo "Invalid list\n";
                return;
            }
        }

        $this->heap = new MultiHeap($list);

        echo "A MultiHeap has been built.\n";
    }

    public function output()
    {
        echo $this->heap->toString()."\n";
    }

    public function insert()
    {
        echo "Enter a number: ";

        $num = $this->getLine();

        if (!is_numeric($num)) {
            echo "Invalid number\n";
            return;
        }

        $this->heap->insert($num);

        echo "The number has been inserted.\n";
    }

    public function findMax()
    {
        echo "The maximum is ".$this->heap->findMax()."\n";
    }

    public function findMin()
    {
        echo "The minimum is ".$this->heap->findMin()."\n";
    }

    public function deleteMax()
    {
        $max = $this->heap->deleteMax();

        echo "The maximum, $max, has been deleted\n";
    }

    public function deleteMin()
    {
        $min = $this->heap->deleteMin();

        echo "The minimum, $min, has been deleted\n";
    }

}

$ui = new MultiHeap_UI;
$ui->run();

?>
