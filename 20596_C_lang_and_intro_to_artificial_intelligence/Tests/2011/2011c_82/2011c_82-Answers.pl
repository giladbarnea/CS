/* q1
sym_cbal_trees(5,T).
T = t(t(nil, x, t(nil, x, nil)), x, t(t(nil, x, nil), x, nil)) ;
T = t(t(t(nil, x, nil), x, nil), x, t(nil, x, t(nil, x, nil))) ;

sym_cbal_trees(7,T).
T = t(t(t(nil, x, nil), x, t(nil, x, nil)), x, t(t(nil, x, nil), x, t(nil, x, nil))) ;
*/
create_tree(nil,N,N,_).
create_tree(t(L,x,R),N,N3,Max):-
	N<Max,
	N1 is N+1,
	create_tree(L,N1,N2,Max),
	create_tree(R,N2,N3,Max).

create_tree(t(L,x,R),Max):-
	create_tree(L,1,N,Max),
	create_tree(R,N,Max,Max).

symetricbal(nil).
symetricbal(t(L,_,R)):-
	symetricbal(L,_,R,_).

symetricbal(nil,0,nil,0).
symetricbal(t(L1,_,R1),Count5,t(L2,_,R2),Count6):-
	symetricbal(L1,Count1,R2,Count3),
	symetricbal(R1,Count2,L2,Count4),
	similar(Count1,Count2),
	similar(Count3,Count4),
	Count5 is Count1+Count2+1,
	Count6 is Count3+Count4+1.

similar(N,N1):-
	abs(N-N1)=<1.

sym_cbal_trees(N,T):-
	create_tree(T,N),
	symetricbal(T).


/* question #2
?- what([a,b],[1,2],L1).
L1 = [2, b, 1, a] ;

?- what([c,d,e],[3,4,5],L2).
L2 = [5, e, 4, d, 3, c] ;

?- what([f,g,h,i],[6,7,8,9],L3).
L3 = [9, i, 8, h, 7, g, 6, f] ;
*/

what(InList1,InList2,List) :-    % Assume InList1, InList2 are the same size.
	p(InList1,InList2,List-[]).

p([],[],T-T) :- !.
p([X],[Y],[Y,X|T]-T) :- !.
p(InList1, InList2,L2-T) :-
	div(A,B,InList1),
	div(C,D,InList2),
	p(A,C,L1-T),
	p(B,D,L2-L1).

div(List1,List2,List) :-
	length1(N,List),
	N1 is N +1,
	N2 is N1//2,    % // is integer division
	s(N2,List,List1,List2).

s(0,L,[],L).
s(N,[X|L],[X|L1],L2) :-
	N1 is N-1,
	s(N1,L,L1,L2).

length1(0,[]).
length1(N,[_|L]) :-
	length1(N1,L),
	N is N1 + 1.








