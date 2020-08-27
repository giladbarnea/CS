/*
Exam 2012c
Question #1
Solution without using assert/retract

?- match(h(2,3,B,X,Y,g(X,Z),1)).
B = 1,
X = 2,
Y = 3,
Z = 4.

?- match(X).
X = 1.

?- match (1).
true.
*/

match(Term):-
	match(Term,0,_).

match(Term,Num,Num1):-
	compound(Term),!,
	Term=..[_|Xs],
	match_list(Xs,Num,Num1)
	;
	match_atomic(Term,Num,Num1).

match_atomic(Term,Num,Num1):-
	var(Term),!,
	Num1 is Num+1,
	Term is Num1
	;
	Num1 is Num.

match_list([X|Xs],Num,Num2):-
	match(X,Num,Num1),
	match_list(Xs,Num1,Num2).
match_list([],Num,Num).


/*
Question #1 solution using assert/retract
*/

match2(Term):-
	retractall(count(_)),
	assert(count(0)),
	match3(Term).
match3(Term):-
	compound(Term),!,
	Term=..[_|Xs],
	match_list2(Xs)
	;
	match_atomic2(Term).

match_atomic2(Term):-
	var(Term),!,
	retract(count(Num)),
	Num1 is Num+1,
	assert(count(Num1)),
	Term is Num1
	;
	true.

match_list2([X|Xs]):-
	match3(X),
	match_list2(Xs).
match_list2([]).

/* question #2*/
what(R1-T1,R2-T2):-
	what(R1-T1,A-A,B-B,R2-T2).
what([four(X)|Xs]-T,Ys-[four(X)|T2],Zs-T3,R2-T4):-
	!,what(Xs-T,Ys-T2,Zs-T3,R2-T4).
what([two(X)|Xs]-T,Ys-T2,Zs-T3,R2-T4):-
	!,what(Xs-T,Ys-T2,[two(X)|Zs]-T3,R2-T4).
what(_,A-B,B-C,A-C).

/*
what([two(2),four(4),four(mult(2,2)),two(sum(1,1)),two(one_plus_one),four(sum(1,3)),two,four(sum(2,2)),three(3)]-[],L-[]).
L = [four(4), four(mult(2, 2)), four(sum(1, 3)), two(one_plus_one), two(sum(1, 1)), two(2)]
*/

/* question #3 */

lowest_ancestor(T,List,Ancestor):-
	lowest_ancestor(T,List,List,_,Ancestor).

lowest_ancestor(t(L,X,R),Olist,List,List3,Ancestor):-
	lowest_ancestor(L,Olist,List,List1,AncestorL),
	(Olist==List,AncestorL>0,!,Ancestor=AncestorL
	;
	lowest_ancestor(R,Olist,List1,List2,AncestorR),
	(Olist==List,AncestorR>0,!,Ancestor=AncestorR
	;
	del(X,List2,List3),
	(Olist==List,List3==[],!,Ancestor is X
	;
        Ancestor is -1
	))).
lowest_ancestor(nil,_,List,List,-1).

del(X,[X|Xs],Xs):-!.
del(X,[Y|Ys],[Y|Zs]):-!,
	del(X,Ys,Zs).
del(_,[],[]).

buildtree(T):-
	T=t(t(t(t(t(nil,0,nil),11,nil),9,t(nil,10,nil)),8,nil),1,t(t(t(nil,5,nil),4,nil),2,t(nil,3,t(nil,6,t(nil,7,nil))))).

/*
?- buildtree(T), lowest_ancestor(T,[7,5],Ancestor).
Ancestor = 2.

?- buildtree(T), lowest_ancestor(T,[7,0],Ancestor).
Ancestor = 1.

?- buildtree(T), lowest_ancestor(T,[8,10,0],Ancestor).
Ancestor = 8.
*/

/* utilities */
conc([],Ys,Ys).
conc([X|Xs],Ys,[X|Zs]):-
	conc(Xs,Ys,Zs).

inorder(t(L,X,R),Xs):-
	inorder(L,Ls),
	inorder(R,Rs),
	conc(Ls,[X|Rs],Xs).
inorder(nil,[]).


preorder(t(L,X,R),[X|Xs]):-
	preorder(L,Ls),
	preorder(R,Rs),
	conc(Ls,Rs,Xs).
preorder(nil,[]).

postorder(t(L,X,R),Res):-
	postorder(L,Ls),
	postorder(R,Rs),
	conc(Ls,Rs,Temp),
	conc(Temp,[X],Res).
postorder(nil,[]).

















