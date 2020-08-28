<?php

function NumAppCount($arr, $p, $r, $num)
{
	if ($p > $r) {
		return 0;
	}

	$sum = NumAppCount($arr, $p, $r-1, $num);

	if ($arr[$r] == $num) {
		$sum++;
	}

	return $sum;
}

function NumAppK($arr, $p, $r, $num, $k)
{
	if ($k < 0) {
		return false;
	} elseif ($p > $r) {
		return ($k == 0);
	} else {
		if ($arr[$r] == $num) {
			$k--;
		}

		return NumAppK($arr, $p, $r-1, $num, $k);
	}
}

$arr = array(5, 1, 9, 9, 1, 5, 3, 7, 2, 4, 9, 3, 10, 5, 3, 7, 8);

//$num = 7;
//$app = NumAppCount($arr, 0, count($arr)-1, $num);
//echo "The number $num appers $app times\n";

$num = 9;
$count = 3;

if (NumAppK($arr, 0, count($arr)-1, $num, $count)) {
	echo "The number $num appers $count times\n";
} else {
	echo "The number $num doesn't apper $count times\n";
}


?>
