/* Question #1 */

/*
do( [ 42, is, The, meaning, 4, life] - [ ], L1-[]).
L1 = [42, is].

?- do( [ 42, is, the, Meaning, 4, life] - [ ], L2-[ ]).
L2 = [42, is, the].

?- do( [ integer, 3, do, item, 10, 20, 4.5, var] - [ ], L3-[ ]).
L3 = [20, 10, 3, integer, do, item].
*/

do( L1-T1, L2-T2) :-
do( L1-T1, A-A, L2-T2).
do( [Y|Ys]-T1, Zs-[Y|T3], L2-T2) :-
atom(Y),! ,
do( Ys-T1, Zs-T3, L2-T2).
do( [Y|Ys]-T1, Zs-T3, L2-T2) :-
integer(Y),! ,
do( Ys-T1, [Y|Zs]-T3, L2-T2).
do( _, A-B, A-B).

/* Question #3
word_paths(abc,Paths).
Paths = [[1, 2, 3, 6], [1, 2, 3, 5], [2, 1, 7, 2], [6, 4, 5, 3]].

word_paths(bcd,Paths).
Paths = [[4, 5, 3, 4]].

word_paths(bca,Paths).
Paths = [[1, 7, 2, 1], [2, 3, 6, 4]].
*/
edge(1,7,b).
edge(2,7,c).
edge(1,2,a).
edge(2,3,b).
edge(3,6,c).
edge(4,6,a).
edge(3,4,d).
edge(3,5,c).
edge(4,5,b).

path(From,Visited,[From|Path],[FromL|PathL]):-
	(edge(From,To,FromL);edge(To,From,FromL)),
	not(member(To,Visited)),
	path(To,[To|Visited],Path,PathL).
path(From,_,[From],[]).


findword(Word,Path):-
	atom_chars(Word,Wordlist),
	path(_,[],Path,Wordlist).

word_paths(Word,Paths):-
	findall(Path,findword(Word,Path),Paths).

/* Question #4a */
buildtree(T):-
	T=t(t(t(nil,2,t(nil,3,nil)),4,t(nil,6,nil)),11,t(t(nil,17,t(nil,18,nil)),19,nil)).
buildtree2(T):-
	T=t(t(t(nil,2,t(nil,5,nil)),4,t(nil,6,nil)),11,t(t(nil,17,t(nil,18,nil)),19,nil)).

isaBinaryDictionary(Tree):-
	isaBinary(Tree,_,_).
isaBinary(nil,_,_).
isaBinary(t(L,X,R),Min,Max):-
	(var(Min),!;X>=Min),
	(var(Max),!;X=<Max),
	isaBinary(L,Min,X),
	isaBinary(R,X,Max).

/* Question #4b */
maxBinaryDictionary(Tree,MaxSub):-
	maxBinary(Tree,_,_,MaxSub,_).
maxBinary(nil,_,_,nil,0).
maxBinary(t(nil,X,nil),X,X,t(nil,X,nil),1):-!.
maxBinary(t(nil,X,R),Min,Max,MaxSub,MaxSubNodes):-!,
	maxBinary(R,MinR,MaxR,MaxSubR,MaxSubNodesR),
	(MaxSubR==R,X=<MinR,!,
	 Min=X,
	 Max=MaxR,
	 MaxSub=t(nil,X,R),
	 MaxSubNodes is MaxSubNodesR+1
	;
	   (
	    MaxSub=MaxSubR,
	    MaxSubNodes=MaxSubNodesR
	    )
	).

maxBinary(t(L,X,nil),Min,Max,MaxSub,MaxSubNodes):-!,
	maxBinary(L,MinL,MaxL,MaxSubL,MaxSubNodesL),
	(MaxSubL==L,MaxL=<X,!,
	 Min=MinL,
	 Max=X,
	 MaxSub=t(L,X,nil),
	 MaxSubNodes is MaxSubNodesL+1
	;
	   (
	    MaxSub=MaxSubL,
	    MaxSubNodes=MaxSubNodesL
	    )
	).

maxBinary(t(L,X,R),Min,Max,MaxSub,MaxSubNodes):-
	maxBinary(L,MinL,MaxL,MaxSubL,MaxSubNodesL),
	maxBinary(R,MinR,MaxR,MaxSubR,MaxSubNodesR),
	(MaxSubL==L,MaxSubR==R,MaxL=<X,X=<MinR,!,
	 Min=MinL,
	 Max=MaxR,
	 MaxSub=t(L,X,R),
	 MaxSubNodes is MaxSubNodesL+MaxSubNodesR+1
	;
	   (MaxSubNodesL>=MaxSubNodesR,!,
	    MaxSub=MaxSubL,
	    MaxSubNodes=MaxSubNodesL
	    ;
	    MaxSub=MaxSubR,
	    MaxSubNodes=MaxSubNodesR
	    )
	).






/* utilities */
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
conc([],Ys,Ys).
conc([X|Xs],Ys,[X|Zs]):-
	conc(Xs,Ys,Zs).
