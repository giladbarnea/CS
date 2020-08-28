<?php

function FindMax($arr, $p, $r) {
	$max = $p;

	for ($i=$p+1; $i <= $r; $i++) {
		if ($arr[$i] > $arr[$max]) {
			$max = $i;
		}
	}

	return $max;
}

function FindMax2($arr, $p, $r)
{
	if ($p == $r) {
		return $p;
	}

	$q = intval(($p+$r)/2);
	
	$max1 = FindMax2($arr, $p, $q);
	$max2 = FindMax2($arr, $q+1, $r);

	if ($arr[$max2] > $arr[$max1]) {
		return $max2;
	} else {
		return $max1;
	}
}

$arr = array(11, 5, 2, 99, 7, 3, 44);

$max = FindMax2($arr, 0, 6);

echo "The max is $arr[$max] at $max\n";

?>
