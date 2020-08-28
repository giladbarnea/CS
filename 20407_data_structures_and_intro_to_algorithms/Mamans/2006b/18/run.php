<?php
set_time_limit(0);
error_reporting(E_ALL | E_STRICT);

require_once 'TaskMgr.php';

/**
 * A simple UI to the TaskMgr
 */
class TaskMgr_UI
{
    /** Holds the TaskMgr object */
    private $mgr;

    /* @var array   A list of supported commands */
    private $commands = array(
        'init'      => 'init',
        'insert'    => 'insert',
        'changep'   => 'changePri',
        'delete'    => 'delete',
        'next'      => 'next',
        'debug'     => 'debug',
        'quit'      => 'quit'
    );

    public function run()
    {
        while (($buf = $this->getLine()) !== false) {
            $args = explode(' ', $buf);
            $cmd = array_shift($args);

            if (!isset($this->commands[$cmd])) {
                echo "Invalid command \n";
            } else {
                $func = $this->commands[$cmd];

                if ($func == 'quit') {
                    break;
                }

                if ($func != 'init' && !$this->mgr) {
                    echo "You must init the task manager first\n";
                } else {
                    $this->$func($args);
                }
            }
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

    public function init()
    {
        $this->mgr = new TaskMgr;

        echo "Initialized.\n";
    }

    public function insert($args)
    {
        echo $this->mgr->insert($args[0], $args[1])."\n";
    }

    public function changePri($args)
    {
        echo $this->mgr->changePri($args[0], $args[1])."\n";
    }

    public function delete($args)
    {
        echo $this->mgr->delete($args[0])."\n";
    }

    public function next()
    {
        echo $this->mgr->next()."\n";
    }

    public function debug()
    {
        echo "\n";
        $this->mgr->priAvl->display();
        echo "\n";
        $this->debugRec($this->mgr->priAvl->root);
    }

    public function debugRec($x)
    {
        if (is_null($x)) {
            return;
        }

        $this->debugRec($x->left);
        echo $x->toString()." = ".$x->ref->toString()."\n";
        $this->debugRec($x->right);
    }
}

$ui = new TaskMgr_UI;
$ui->run();

?>
