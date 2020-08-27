%Question 3
%word_paths(abc, Paths).
%word_paths(bcd, Paths).
%word_paths(bca, Paths).
edge(1, 7, b).
edge(2, 7, c).
edge(1, 2, a).
edge(2, 3, b).
edge(3, 6, c).
edge(4, 6, a).
edge(3, 4, d).
edge(3, 5, c).
edge(4, 5, b).

word_paths(Word, Paths):-
    name(Word, AsciiList),
    chars(AsciiList, Chars),
    findall(X, path(Chars, _, X), Paths).

path([], _, []).
path([Letter|Rest], Last, [Start, Next|RestPath]):-
    var(Last),
    (   edge(Start, Next, Letter); edge(Next, Start, Letter)),
    path(Rest, Next, RestPath).
path([Letter|Rest], Last, [Next|RestPath]):-
    not(var(Last)),
    (   edge(Last, Next, Letter); edge(Next, Last, Letter)),
    path(Rest, Next, RestPath).

chars([], []):-!.
chars([X|Xs], [Y|Ys]):-
    name(Y, [X]),
    chars(Xs, Ys).

%Question 4
%Part A
%isaBinaryDictionary(t(t(t(nil, 2, t(nil, 3, nil)), 4, t(nil, 6, nil)), 11, t(t(nil, 17, t(nil, 18, nil)), 19, nil))).
%isaBinaryDictionary(t(t(t(nil, 2, t(nil, 5, nil)), 4, t(nil, 6, nil)), 11, t(t(nil, 17, t(nil, 18, nil)), 19, nil))).
isaBinaryDictionary(Tree):-
    isaBinaryDictionary(Tree, _, _).

isaBinaryDictionary(t(L, X, R), Min, Max):-
    (   var(Min), !; X > Min), !,
    (   var(Max), !; X < Max),
    isaBinaryDictionary(L, Min, X),
    isaBinaryDictionary(R, X, Max).
isaBinaryDictionary(nil, _, _).

%Part B
%maxBinaryDictionary(t(t(t(nil, 2, t(nil, 3, nil)), 4, t(nil, 6, nil)), 11, t(t(nil, 17, t(nil, 18, nil)), 19, nil)), MaxSub).
%maxBinaryDictionary(t(t(t(nil, 2, t(nil, 5, nil)), 4, t(nil, 6, nil)), 11, t(t(nil, 17, t(nil, 18, nil)), 19, nil)), MaxSub).
maxBinaryDictionary(Tree, MaxSub):-
    maxBinaryDictionary(Tree, _, MaxSub).

maxBinaryDictionary(nil, 0, nil):- !.
maxBinaryDictionary(Tree, NumOfKids, Tree):-
    isaBinaryDictionary(Tree), !,
    size(Tree, NumOfKids).
maxBinaryDictionary(t(L, _, R), NumOfKids, MaxSub):-
    maxBinaryDictionary(L, NumOfKidsL, MaxSubL),
    maxBinaryDictionary(R, NumOfKidsR, MaxSubR),
    (   (   NumOfKidsL > NumOfKidsR, !,
            MaxSub = MaxSubL,
        	NumOfKids = NumOfKidsL)
    	;   
    	(   MaxSub = MaxSubR,
            NumOfKids = NumOfKidsR)).

size(nil, 0):- !.
size(t(L, _, R), NumOfKids):-
    size(L, NumOfKidsL),
    size(R, NumOfKidsR),
    NumOfKids is  NumOfKidsL + NumOfKidsR + 1.