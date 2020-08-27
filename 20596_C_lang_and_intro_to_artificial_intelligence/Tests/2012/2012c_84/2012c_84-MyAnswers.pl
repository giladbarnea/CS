%Question 1
%match(h(2,3,B,X,Y,g(X,Z),1)).
%match(X).
%match(1).
match(Term):-
   	atomic(Term), !.
match(Term):-
    var(Term), !,
    Term = 1.
match(Term):-
    match(Term, 1, _).

match(Term, Temp, Count):-
    Term =.. [_|Args],
    matchList(Args, Temp, Count).

matchList([], Count, Count).
matchList([Arg|Rest], Temp, Count):-
    var(Arg), !,
    Arg = Temp,
    Temp1 is Temp + 1,
    matchList(Rest, Temp1, Count).
matchList([Arg|Rest], Temp, Count):-
    compound(Arg), !,
    match(Arg, Temp, NewTemp),
    matchList(Rest, NewTemp, Count).
matchList([_|Rest], Temp, Count):-
    %The Current Argument is Atomic
    matchList(Rest, Temp, Count).

%Another Solution
match(Term):-
    match(Term, 1, _).

match(Term, Temp, Temp):-
    atomic(Term), !.
match(Term, Temp, Count):-
    var(Term), !,
    Term = Temp,
    Count is Temp + 1.
match(Term, Temp, Count):-
    Term =.. [_|Args],
    matchList(Args, Temp, Count).

matchList([], Count, Count).
matchList([Arg|Rest], Temp, Count):-
    match(Arg, Temp, NewTemp),
    matchList(Rest, NewTemp, Count).


%Question 3
%Tree = t(t(t(t(t(nil, 0, nil), 11, nil), 9, t(nil, 10, nil)), 8, nil), 1, t(t(t(nil, 5, nil), 4, nil), 2, t(nil, 3, t(nil, 6, t(nil, 7, nil))))), lowest_ancestor(Tree, [7, 5], Ancestor).
%Tree = t(t(t(t(t(nil, 0, nil), 11, nil), 9, t(nil, 10, nil)), 8, nil), 1, t(t(t(nil, 5, nil), 4, nil), 2, t(nil, 3, t(nil, 6, t(nil, 7, nil))))), lowest_ancestor(Tree, [7, 0], Ancestor).
%Tree = t(t(t(t(t(nil, 0, nil), 11, nil), 9, t(nil, 10, nil)), 8, nil), 1, t(t(t(nil, 5, nil), 4, nil), 2, t(nil, 3, t(nil, 6, t(nil, 7, nil))))), lowest_ancestor(Tree, [8, 10, 0], Ancestor).
lowest_ancestor(Tree, List, Ancestor):-
    node(Tree, List, Ancestor, Depth),
    not((node(Tree, List, _, Depth2), Depth2 > Depth)), !.

node(t(L, X, R), SubList, X, 0):-
    subTreeList(t(L, X, R), List),
    subList(SubList, List).
node(t(L, _, R), SubList, Ancestor, Depth):-
    (   node(L, SubList, Ancestor, DepthL),
        Depth is DepthL+1)
    ;   
    (   node(R, SubList, Ancestor, DepthR),
        Depth is DepthR+1).

subTreeList(nil, []).
subTreeList(t(L, X, R), [X|Rest]):-
    subTreeList(L, RestL),
    subTreeList(R, RestR),
    append(RestL, RestR, Rest).

subList(SubList, List):-
    findall(X, (member(X, List), member(X, SubList)), NewList),
    length(NewList, Length),
    length(SubList, Length).