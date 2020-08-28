--- Info ---
Course: Data Structures (20433)
Semester: 2006b
Maman: 14
Student: Sagi Bashari <sagi@boom.org.il> 
Id: XX

--- About the maman ---

The required data structure has been implemented using a maximum+minimum heaps.

The main source file is MultiHeap.php. The documentation is inside the code.

A simple UI is provided with run.php.

Two output samples are provided, sample1.txt and sample2.txt.

--- How to run ---

This maman is written in PHP and was tested against PHP v5.1.4.

A PHP command line interpreter can be downloaded from: 
http://www.php.net/downloads.php

Installation under windows is a matter of downloading the ZIP from
http://www.php.net/get/php-5.1.4-Win32.zip/from/a/mirror
and extracting to c:\php.

Once installed, the maman can be executed by running
c:\php\php.exe run.php

From command line, in the maman directory.

--- About PHP ---

PHP is a dynamic scripting language. It's syntax is similar to C and Java.

The important to note a few things about the array and object behavior:

PHP arrays are very flexible, but in this maman they've been used as simple arrays (similar to C arrays). 
Thir first index starts at 0. Their length is dynamic and can be changed at runtime.
To calculate the required runtime it has been assumed that array access has a runtime of O(1).
Similarly, it has been assumed that the count() function, which returns the number of items in an array, has the runtime of O(1).

Arrays are always passed by value, which means after running "$b = $a" $b has a different address than $a. 

Objects, however, are always passed by reference.

You can find much more in the PHP manual at http://www.php.net/manual/en/.
