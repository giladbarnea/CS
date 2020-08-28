<?php
set_time_limit(5);
error_reporting(E_ALL | E_STRICT);

class Tree {
    public $key;
    public $left;
    public $right;

    public function __construct($key) {
        $this->key = $key;
    }

    public static function insert(&$T, $z)
    {
        $y = null;
        $x = $T;

        while (!is_null($x)) {
            $y = $x;

            if ($z->key < $x->key) {
                $x = $x->left;
            } else {
                $x = $x->right;
            }
        }

        if (is_null($y)) {
            $T = $z;
        } else if ($z->key < $y->key) {
            $y->left = $z;
        } else {
            $y->right = $z;
        }
    }

    public static function recInOrder($node)
    {
        if (!is_null($node)) {
            self::recInOrder($node->left);
            echo $node->key.' ';
            self::recInOrder($node->right);
        }
    }

    public static function recPostOrder($node)
    {
        if (!is_null($node)) {
            self::recPostOrder($node->left);
            self::recPostOrder($node->right);
            echo $node->key.' ';
        }
    }

    public static function iterPostOrder($T)
    {
        $S = array();

        $node = $T;
        $state = 0;

        do {
            if (!is_null($node)) {
                if ($state == 0) {
                    array_push($S, array($node, 1));
                    $node = $node->left;
                    $state = 0;
                } else if ($state == 1) {
                    array_push($S, array($node, 2));
                    $node = $node->right;
                    $state = 0;
                } else if ($state == 2) {
                    echo $node->key.' ';
                    $node = null;
                }
            }
            
            if (is_null($node) && !empty($S)) {
                list($node, $state) = array_pop($S);
            }
        } while (!is_null($node));
    }

    public static function irisIterPostOrder($tree) {
        $S = array();
        array_push($S, $tree);
        $last = $tree;

        while (!empty($S)) {
            $tree = array_pop($S);

//            if (is_null($tree)) {
  //              echo "null tree!\n";
    //            continue;
      //      }

            echo 'dbg: '.$tree->key."\n";

            if ($last == $tree->right) {
                echo $tree->key.' ';
            } else {
                array_push($S, $tree);
                array_push($S, $tree->right);
                array_push($S, $tree->left);
            }

            $last = $tree;
        }
    }
}

$T = null;

//$list = array(12, 18, 5, '3', 15, 13, 9, 17, 2, 19);
//$list = array(10, 7, 15, 3, 9);
$list = array(1);

//for ($i=0; $i<20; $i++) { $list[] = rand(0, 999); }

foreach ($list as $k) {
    Tree::insert($T, new Tree($k));
}

//print_r($T);die;

echo "Rec In:\n";
Tree::recInOrder($T);
echo "\n\n";

echo "Rec Post:\n";
Tree::recPostOrder($T);
echo "\n\n";

echo "Iter Post:\n";
Tree::iterPostOrder($T);
echo "\n\n";

echo "Iris Iter Post:\n";
Tree::irisIterPostOrder($T);
echo "\n\n";


?>
