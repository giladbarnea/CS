{this program present a menu for the user that he can do some action.
 the action are: build 'd' heap  , insert new key to the heap , extract the
 maximum number , print the heap , and change 'd' of the heap.               }
program maman14;
uses crt;                                               {for clear the screen}
const
     size=100;                                         {the size of the array}
var
   a:array[1..size] of integer;
   d,length,r,key,flag,choose:integer;{d=number of sons,length=length of heap}
   {r=index,key=the new key to insert to heap,flag=check if the heap was build
    choose=the number that the user choose from the menu                     }
   finish:char;            {finish=get char from the user to exit the program}
   label back;
{----------------------------------MENU--------------------------------------}
{this procedure show on the screen the menu and let the user choosing what he
like to do.
RUNNING TIME= o(1)                                                           }
procedure menu;
begin                                                      {of procedure menu}
     writeln;
     writeln ('                   -----------------------------------');
     writeln ('                   |                MENU             |');
     writeln ('                   |         1.  Build-Heap          |');
     writeln ('                   |         2.  Insert              |');
     writeln ('                   |         3.  Extract-Max         |');
     writeln ('                   |         4.  Print-Heap          |');
     writeln ('                   |         5.  Change-d            |');
     writeln ('                   |         6.  Exit                |');
     writeln ('                   -----------------------------------');
     writeln;
     write (' Plz choose a number from the menu: ');
     readln (choose);                {getting the number that the user choose}
end;                                                       {of procedure menu}
{----------------------------------PAUSE-------------------------------------}
{this procedure pause the screen so the user will see the results,it doing
it by getting a char from the user.
RUNNING TIME= o(1)                                                           }
procedure pause;
var
   wait:char;
begin                                                     {of procedure pause}
     write ('Press any key to return to menu');
     read (wait);                                   {get a char from the user}
end;                                                      {of procedure pause}
{----------------------------------ERROR-------------------------------------}
{this procedure tell the user to build heap first ,in case that he didn't do
it yet,and choose another option from the menu that need a heap to work.
RUNNING TIME= o(1)                                                           }
procedure error;
begin                                                     {of procedure error}
     writeln ('     ------ ErroR ----- ');
     writeln ('You need to build a heap first');
     writeln;                                                    {skip a line}
     pause;                                             {call procedure pause}
end;                                                      {of procedure error}
{-------------------------------FILL_ARRAY---------------`--------------------}
{this procedure fill the array with the data,it will stop when the user enter
99 or when the array is full,and than he will get the number of
the sons (d) from the user.
RUNNING TIME= o(size)                                                           }
procedure fill_array;
var
     ind,n:integer;
begin                                                {of procedure fill_array}
     writeln('Plz insert the keys to build the heap.');
     writeln('Enter the value "99" when u finish!');
     ind:=1;
     read (n);                       {get a number from the user for the heap}
     length:=0;
     while (ind<size) and (n<>99) do   {stop when get 99 of the array is full}
           begin                                                    {of while}
                a[ind]:=n;                       {put the number in the array}
                ind:=ind+1;                               {increase the index}
                read (n);                         {get a number from the user}
                length:=length+1;            {increase the length of the heap}
           end;                                                     {of while}
     write ('insert value for d=');
     readln (d);                                   {get the 'd' from the user}
     writeln('length list=',length);   {write the user the length of the heap}
end;                                                 {of procedure fill_array}
{---------------------------------PRINT_HEAP---------------------------------}
{this procesure print the current heap when the user choose 4 from the menu
RUNNING TIME= o(length)                                                        }
procedure print_heap;
var
   r:integer;                                         {the index of the array}
begin                                                {of procedure print_heap}
     write('THE CURRENT HEAP IS: ');
     for r:=1 to length do               {go from 1 to the length of the heap}
       begin                                                     {of loop for}
        write(a[r],' ');                       {write the value in this place}
       end;                                                      {of loop for}
     writeln;
     writeln;
end;                                                 {of procedure print_heap}
{----------------------------------INSERT------------------------------------}
{this procedure getting a key from the user and find his place in the array
by increase the length of the heap and find new place for the key by compare
the new key with his parent,and moving up to the root.                       }
{RUNNING TIME= o(lg length)                                                  }
procedure insert(key:integer);
var
   p,i:integer;                                             {i=index,p=parent}
begin                                                    {of procedure insert}
   length:=length+1;                    {increase in 1 the length of the haep}
   i:=length;                                       {i getting the new length}
   while (i > 1) and                    {it will stop when we get to the root}
         (a[(((i-2) div d)+1)] < key) do   {or when parent > then the new key}
     begin                                                          {of while}
       a[i]:=a[(((i-2) div d)+1)];        {move the parent to his son loction}
       i:=(((i-2) div d)+1);                  {'i' getting the parent loction}
     end;                                                           {of while}
   a[i]:=key;                                 {set the new key in his loction}
end;                                                     {of procedure insert}
{----------------------------------D_HEAPIFY---------------------------------}
{this procedure fix the heap and make him a legal heap,with 'd' sons for each
node,comparing each parent with his sons and change between the parent and the
son in case that the son is bigger that his parent.
RUNNING TIME= O[d (lg length-basis d)]                                       }
procedure d_heapify(i:integer);
var k,j, {k=number of the children,j=keep the son that bigger then his parent}
    c,temp:integer;             {c=keep the current son,temp=help to exchange}
begin                                                 {of procedure d_heapify}
    c:=0;
    j:=i;
    for k:=1 to d do
        begin                                                    {of loop for}
          c:=d*(i-1)+k+1;                          {calculate the current son}
          if (c<=length)and(a[c]>a[j])     {if not end of heap and son>parent}
           then j:=c;{'j' get the place of the son the bigger then his parent}
        end;                                                     {of loop for}
    if j<>i
    then
         begin                                                         {of if}
           temp:=a[i];                                 {change               }
           a[i]:=a[j];                                 {    between          }
           a[j]:=temp;                                 {      a[i] and a[j]  }
           d_heapify(j);                               {call d_heapify with j}
         end;                                                          {of if}
end;                                                  {of procedure d_heapify}
{----------------------------------EXTRACT_MAX-------------------------------}
{this procedure been use when the user choose 3 from the menu ,and it return
to the user the maximum value in the heap and delete this number and the use
d_heapify to make the heap legal again.
RUNNING TIME= o[d (lg length-basis d)]                                       }
procedure extract_max;
var
   max:integer;                           {max=the maximum value in the heap}
begin                                               {of procedure extarct_max}
   max:=a[1];                              {max getting the value of the root}
   a[1]:=a[length];                  {the root get the value of the last leaf}
   length:=length-1;                      {delete the last leaf from the heap}
   d_heapify(1);        {call d_heapify with the root to coreect the the heap}
   writeln('The max number is:',max);        {write to the user the max nuber}
   writeln
end;                                                {of procedure extarct_max}
{--------------------------------BUILD_HEAP----------------------------------}
{this procedure build the heap from the data that the user enter, by strating
from the root and add 1 leaf each time and call heapify to make sure that the
heap is legal(after each leaf that the procedure add)                        }
{RUNNING TIME=O[d (lg length-basis d)]}
procedure build_heap;
var
    i,father:integer;                               {father=parent , i=index }
begin                                                {of procedure build_heap}
  father:= ((length-2) div d)+1;       {find the youngest parent in the heap }
  for i:=father downto 1 do      {moving from the youngest parent to the root}
   d_heapify(i)             {call heapify to make sure that the heap is legal}
end;                                                 {of procedure build_heap}
{----------------------------------CHANGE_D----------------------------------}
{this procedure change the number of the sons that each parent has.
 the procedure get the new 'd' from the user and update the heap according
 the new 'd'                                                                 }
{RUNNING TIME=O[d (lg length-basis d)]}
procedure change_d(d:integer);
var
   i,p:integer;                                           {i=index , p=parent}
begin                                                  {of procedure change_d}
   p:=(((length-2) div d)+1);          {find the youngest parent in the heap }
   for i:=p downto 1 do
   d_heapify(i);
end;                                                   {of procedure change_d}
{--------------------------------MAIN_PROGRAM--------------------------------}
BEGIN                                                        {of main program}
     clrscr;                                                {clear the screen}
     writeln ('                     Asi & Roey present--"THE HEAP" ');
          flag:=0;                   {flag that tell me if any heap was build}
back:menu;
      case choose of               {number that the user choose from the menu}
      {RUNNING TIME OF CASE 1=O[d (lg length-basis d)]}
      1:begin                   {in case that the user choose 1 from the menu}
             fill_array;
             build_heap;
             flag:=1;           {turn on the flag to know that the heap build}
             print_heap;
             pause;                       {wait for any key to return to menu}
             clrscr;
             goto back;                                  {go back to the menu}
        end;
        {RUNNING TIME OF CASE 2=O[ (lg length}
      2:begin                   {in case that the user choose 2 from the menu}
             if flag=1
                then begin
                       write ('Plz write new key to insert: ');
                       readln (key);               {ask for new key from user}
                       insert (key);
                       print_heap;
                       pause;             {wait for any key to return to menu}
                     end
                else begin
                       error;       {error -- no heap exist to insert the key}
                       pause;             {wait for any key to return to menu}
                     end;
        clrscr;
        goto back;
        end;
        {RUNNING TIME OF CASE 3=O[d (lg length-basis d)]}
      3:begin                   {in case that the user choose 3 from the menu}
             if flag=1                                {extract maximum number}
                then begin
                       extract_max;
                       print_heap;
                       pause;             {wait for any key to return to menu}
                     end
                else begin
                       error;                  {error--no heap to extract max}
                     end;
        clrscr;
        goto back;                                       {go back to the menu}
        end;
        {RUNNING TIME OF CASE 4=O[length]}
      4:begin                   {in case that the user choose 4 from the menu}
             if flag=1
                then begin
                       print_heap;
                       pause;             {wait for any key to return to menu}
                     end
                else begin
                       error;                        {error--no heap to print}
                     end;
        clrscr;
        goto back;                                       {go back to the menu}
        end;
        {RUNNING TIME OF CASE 5=O[d (lg length-basis d)]}
      5:begin                   {in case that the user choose 5 from the menu}
             if flag=1
                then begin
                       write ('Plz enter new value for d: ');
                       readln (d);
                       change_d (d);
                       print_heap;
                       pause;             {wait for any key to return to menu}
                     end
                else begin
                       error;                        {no heap to change the d}
                     end;
        clrscr;
        goto back;                                       {go back to the menu}
        end;
        {RUNNING TIME OF CASE 6=O[1]}
      6:begin                   {in case that the user choose 6 from the menu}
             writeln;
             writeln ('             GOODBYE           ');
             writeln ('    MADE BY:   Roey & Asi L.T.D');
             read(finish);
        end;
      else begin     {error--the user enter worng number that not in the menu}
             writeln ('    ---- Worng Number ----');
             writeln ('  Plz enter a correct number');
             writeln;
             pause;                       {wait for any key to return to menu}
             clrscr;
             goto back;                                  {go back to the menu}
           end;
     end;                                                            {of case}
END.                                                         {of main program}
{-----------------------------END OF PROGRAM---------------------------------}

       {TOTAL RUNNING TIME OF ALL PROGRAM = O[d (lg length-basis d)]}
