%Question 1
%generalize_terms(f(A,A), f(1,1), Term).
%generalize_terms(f(A,A), f(1,2), Term).
%generalize_terms(f(A,A), g(1,2), Term).
%generalize_terms(f, g, Term).
%generalize_terms([a,b,1,A], [a,b,2,4], Term).
%generalize_terms(f(g(1,2), h(g(1,3,2), X, 1), B, c), f(A, h(a, b, 1), d, 1), Term).
%generalize_terms(f(a, b, g(1,2), h(a,b,c)), f(1, X, g(1,2,3), h(1,2,3)), Term).
generalize_terms(Term, Term, Term):- !.
generalize_terms(Term1, Term2, Term):-
    compound(Term1), compound(Term2),
    Term1 =.. [F|Args1],
    Term2 =.. [F|Args2],
    generalize_terms_list(Args1, Args2, Args3),
    Term =.. [F|Args3], !.
generalize_terms(_, _, _).

generalize_terms_list([], [], []).
generalize_terms_list([Arg1|Rest1], [Arg2|Rest2], [Arg3|Rest3]):-
    generalize_terms(Arg1, Arg2, Arg3),
    generalize_terms_list(Rest1, Rest2, Rest3).

generalize_terms_list([_|Rest1], [_|Rest2], [_|Rest3]):-
    generalize_terms_list(Rest1, Rest2, Rest3).

%Question 4
%tree2complete(t(t(t(nil, a, nil), b, t(nil, c, nil)), d, t(t(nil, e, t(nil, h, nil)), f, t(nil, g, nil))), CompleteTree).
%tree2complete(t(t(t(nil, a, nil), b, nil), d, t(t(nil, e, t(nil, h, nil)), f, t(nil, g, nil))), CompleteTree).
tree2complete(Tree, CompleteTree):-
    getMinDepth(Tree, Depth),
    createNewTree(Tree, Depth, CompleteTree).

getMinDepth(nil, 0).
getMinDepth(t(L, _, R), Depth):-
    getMinDepth(L, DepthL),
    getMinDepth(R, DepthR),
    (   (   DepthL < DepthR, !,
            Depth is DepthL + 1)
    	;   
    	(   Depth is DepthR + 1)).

createNewTree(t(L, X, R), Depth, t(NewL, X, NewR)):-
    Depth > 0, !,
    NewDepth is Depth - 1,
    createNewTree(L, NewDepth, NewL),
    createNewTree(R, NewDepth, NewR).
createNewTree(_, _, nil).