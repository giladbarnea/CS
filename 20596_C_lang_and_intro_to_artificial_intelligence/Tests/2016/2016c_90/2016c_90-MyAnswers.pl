%Question 3
:- op(300, xfx, link).

%transform(T, t(t(nil, a, nil), c, t(nil, b, nil))).
%transform(LT, t(t(t(nil, a, nil), b, t(nil, c, nil)), e, nil)).
%transform(LT, t(t(t(nil, a, nil), b, t(nil, c, nil)), e, t(t(nil, d, nil), f, t(nil, g, nil)))).
%%transform((a link b) link (a link c), T).
transform(LeafTree, Tree):-
    var(LeafTree), !,
    transfromTree(Tree, LeafTree).
transform(LeafTree, Tree):-
    var(Tree),
    transfromLeafTree(LeafTree, Tree).

transfromTree(nil, nil):- !.
transfromTree(t(nil, X, nil), X):- 
    atom(X), X \= nil, !.
transfromTree(t(L, _, R), T1 link T2):-
    transfromTree(L, T1),
    transfromTree(R, T2).

transfromLeafTree(nil, nil):- !.
transfromLeafTree(X, t(nil, X, nil)):-
    atom(X), X \= nil, !.
transfromLeafTree(T1 link T2, t(L, $, R)):-
    transfromLeafTree(T1, L),
    transfromLeafTree(T2, R).