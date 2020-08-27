/* Question #2 */
buildtree(T):-
   T=t(t(t(t(nil,1,nil),-,t(nil,2,nil)),*,t(t(nil,3,nil),^,t(nil,4,nil))),+,t(t(nil,5,nil),*,t(nil,6,nil))).

/*
buildtree(T), maxtree_val(T,Value,Sub,SubVal).
T = t(t(t(t(nil, 1, nil), -, t(nil, 2, nil)), *, t(t(nil, 3, nil), ^, t(nil, 4, nil))), +, t(t(nil, 5, nil), *, t(nil, 6, nil))),
Value = -51,
Sub = t(t(nil, 3, nil), ^, t(nil, 4, nil)),
SubVal = 81.
*/

compute(X,Op,Y,Result):-
	Z=..[Op,X,Y],
	Result is Z.

maxtree_val(Tree,Sub):-
	maxtree_val(Tree,_,Sub,_).

maxtree_val(t(nil,Value,nil),Value,t(nil,Value,nil),Value):-!.
maxtree_val(t(L,Op,R),Value,SubTree,SubVal):-
	maxtree_val(L,ValueL,SubTreeL,SubValL),
	maxtree_val(R,ValueR,SubTreeR,SubValR),
	(SubValL >= SubValR,!,
	 SubTreeB=SubTreeL,
	 SubValB=SubValL
	;
	 SubTreeB=SubTreeR,
	 SubValB=SubValR
	),
	compute(ValueL,Op,ValueR,Value),
	(Value >= SubValB, !,
	 SubTree=t(L,Op,R),
	 SubVal=Value
	;
	SubTree=SubTreeB,
	 SubVal=SubValB
	).

/* Question #3
?-h( [a, 1, B, c(1,g(2)), a(34), b(2,a(b(7))), d(1), 11], L).
L = [a, 1, B, a(34), d(1), 11].
*/
   h( [], []).
   h( [X|Xs], [X|Ys]):-
        v( X, 1),!,
        h( Xs,Ys).
   h( [_|Xs],Ys):-
        h( Xs,Ys).

   v( Term, D):-
       d( Term, D1),
       D1=<D.

   d( Term, 0):-
        atomic( Term),!
        ;
        var( Term),!.
   d( Term, D):-
        Term=..[ _|Args],
        d1( Args, 0, D1),
        D is D1+1.

  d1( [], D, D).
  d1( [X|Xs], CurrD,D):-
         d(X,D1),
         max(CurrD,D1,D2),
         d1(Xs,D2,D).

   max( X,Y,X):-
          X>=Y,!.
   max( _,Y,Y).

/* Question 2 part c */
   h2( [], []).
   h2( [X|Xs], [X|Ys]):-
        v2( X, 1),
        h2( Xs,Ys).
   h2( [X|Xs],Ys):-
	v3(X,1),
        h2( Xs,Ys).

   v2( Term, D):-
       d( Term, D1),
       D1=<D.

   v3( Term, D):-
       d( Term, D1),
       D1>D.

   d2( Term, 0):-
        atomic( Term).
   d2( Term, 0):-
        var( Term).
   d2( Term, D):-
	compound(Term),
        Term=..[ _|Args],
        d3( Args, 0, D1),
        D is D1+1.

  d3( [], D, D).
  d3( [X|Xs], CurrD,D):-
         d2(X,D1),
         max2(CurrD,D1,D2),
         d3(Xs,D2,D).

   max2( X,Y,X):-
          X>=Y.
   max2( X,Y,Y):-
	X<Y.

/* Question #4
?- kinun(f(a,b,c,d),K).
K = 1.

?- kinun(f(a(1,2),b,c,d),K).
K = 2.

?- kinun(f(a(1,2),b(c(d))),K).
K = 3.

?- kinun(f(b,c(1,e(3))),K).
false.

?- kinun(f(b(1),c(1,e(3))),K).
K = 3.

?- kinun(f(a(1,2),b(c(d(e)))),K).
false.

?- kinun(f(a(1,b(2)),b(c(d(e)))),K).
K = 4.
*/

balanced(Term):-
	kinun(Term,_).

kinun(Term,0):-
	atomic(Term),!
	;
	var(Term),!.

kinun(Term,Kinun):-
	Term=..[_|Xs],
	kinun_list(Xs,_,_,Kinun_max),
	Kinun is Kinun_max+1.

kinun_list([X|Xs],Min,Max,Kinun_max):-
	kinun(X,Kinun),
	check_min_max(Kinun,Min,Max,Min1,Max1),
	kinun_list(Xs,Min1,Max1,Kinun_max1),
	max(Kinun,Kinun_max1,Kinun_max).
kinun_list([],_,_,0).

check_min_max(Kinun,Min,Max,Min1,Max1):-
	(var(Min),!,Min=Kinun
	;
	true),
	(var(Max),!,Max=Kinun
	;
	true),
	Kinun=<Min+1,
	Kinun>=Max-1,
	(Kinun<Min,!,Min1=Kinun
	;
	Min1=Min),
        (Kinun>Max,!,Max1=Kinun
	;
	Max1=Max).








