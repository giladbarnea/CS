/* Question #1 */
store_items([X|Xs],1,I2):-!,
	assert(first(X)),
	I22 is I2-1,
	store_items(Xs,0,I22).

store_items([X|_],_,1):-!,
	assert(second(X)).

store_items([_|Xs],I1,I2):-
	I11 is I1-1,
	I22 is I2-1,
	store_items(Xs,I11,I22).

swap(List,I1,I2,NewList):-
	retractall(first(_)),
	retractall(second(_)),
	store_items(List,I1,I2),
	swap2(List,I1,I2,NewList).

swap2([_|Xs],1,I2,[Second|Ys]):-!,
	retract(second(Second)),
	I22 is I2-1,
	swap2(Xs,0,I22,Ys).

swap2([_|Xs],I1,1,[First|Ys]):-!,
	retract(first(First)),
	I11 is I1-1,
	swap2(Xs,I11,0,Ys).

swap2([X|Xs],I1,I2,[X|Ys]):-!,
	I11 is I1-1,
	I22 is I2-1,
	swap2(Xs,I11,I22,Ys).

swap2([],_,_,[]).

/*
swap( [f, b, g, h, c], 2, 4, L).
L = [f, h, g, b, c].
*/

/* Question #2 */
sum(nil,nil).
sum(t(nil,X,nil),t(nil,X,nil)):-!.
sum(t(L,_,R),t(LN,XN,RN)):-
        get1(L,L1),
	get1(R,R1),
	XN is L1+R1,
	sum(L,LN),
	sum(R,RN).

get1(nil,0).
get1(t(_,X,_),X).

buildtree(T):-
	T=t(t(t(nil,25,nil),5,nil),10,t(t(t(nil,6,nil),3,t(nil,2,nil)),15,t(nil,4,nil))).

/*
buildtree(T),sum(T,T1).
T = t(t(t(nil, 25, nil), 5, nil), 10, t(t(t(nil, 6, nil), 3, t(nil, 2, nil)), 15, t(nil, 4, nil))),
T1 = t(t(t(nil, 25, nil), 25, nil), 20, t(t(t(nil, 6, nil), 8, t(nil, 2, nil)), 7, t(nil, 4, nil))).
*/

/* Question #3 */
do_something( L1, L2) :- proc( L1, L2-[]).
proc( [X|L1], L2-L3) :-
proc( X, L2-L4), proc(L1, L4-L3).
proc( [ ], L1-L1) :- !.
proc( X, [X|L1]-L1) :- atomic(X).

/*
?- do_something([a,b,c,d],LA).
LA = [a, b, c, d] ;

?- do_something([[a],[b,[c,d]]],LB).
LB = [a, b, c, d] ;
*/











