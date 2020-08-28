<?php

function NumRec($n)
{
    if ($n == 0 || $n == 1) {
        return 1;
    }

    return NumRec($n - 1) + NumRec($n - 2);
}

function NumDyn($n)
{
    $a = array();
    $a[0] = $a[1] = 1;

    for ($i=2; $i <= $n; $i++) {
        $a[$i] = $a[$i-1] + $a[$i-2];
    }

    return $a[$n];
}

$n = 25;

echo "NumRec($n) = ".NumRec($n)."\n";
echo "NumDyn($n) = ".NumDyn($n)."\n";
