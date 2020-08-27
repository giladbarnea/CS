%Question 1
%answers(AmericanName, MichaelYears, IsraeliName).
answers(AmericanName, MichaelYears, IsraeliName):-
    solve(List),
    member([AmericanName, american, _], List),
    member([michael, _, MichaelYears], List),
    member([IsraeliName, israeli, _], List).

solve(List):-
    List = [[ariel, _, Z], [yakov, _, X1], [michael, _, Y1], [adam, _, Y2]],
    member([_, american, X2], List),
    member([_, yugoslavian, Y3], List),
    member([_, israeli, _], List),
    member([_, greek, X3], List),
    member([_, _, 3], List),
    member([_, _, 4], List),
    member([_, _, 9], List),
    member([_, _, 10], List),
    X2 > X1, X2 < X3,
    Y1 > Y2 + Y3,
    Z > X3.

%Question 3
%quickselect([46, 12, 34, 78, 89, 23, 43, 56, 76, 13], 5, Val).
%quickselect([46, 12, 34, 78, 89, 23, 43, 56, 76, 13], 8, Val).
quickselect([X|Rest], K, Val):-
    devide(Rest, X, L1, L2),
    length([X|L1], Length1),
    (   (   Length1 > K, !,
            quickselect(L1, K, Val))
    	;   
    	(   Length1 < K, !, K1 = K - Length1,
            quickselect(L2, K1, Val))
    	;   
    	(   Val = X)).

devide([], _, [], []):-!.
devide([X|Xs], D, [X|Rest], L2):-
    X =< D, !,
    devide(Xs, D, Rest, L2).
devide([X|Xs], D, L1, [X|Rest]):-
    X > D,
    devide(Xs, D, L1, Rest).

%Question 4
%build_tree(T), answer(T, List, N).
answer(T,List,N):-
	retractall(mem(_)),!,
	mis(T, List, N),
    not_in_memory(List).

not_in_memory(L):-
	(   (	retract(mem(L)), !, assert(mem(L)), 
       		fail)
		;
			assert(mem(L))).

mis(nil, [], 0).
mis(Tree, List, N):-
    misWithV(Tree, List1, N1),
    misWithoutV(Tree, List2, N2),
    (   (   N1 > N2,
            N = N1,
            List = List1)
    	;   
    	(   N1 < N2,
        	N = N2,
            List = List2)
    	;   
    	(   N1 = N2,
            ((List = List2, N = N2);(List = List1, N = N1)))).

misWithV(t(L, V, R), [V|Rest], N):-
    misWithoutV(L, RestL, NL),
    misWithoutV(R, RestR, NR),
    N is NL + NR + 1,
    append(RestL, RestR, Rest).

misWithoutV(nil, [], 0).
misWithoutV(t(L, _, R), List, N):-
    mis(L, ListL, NL),
    mis(R, ListR, NR),
    N is NL + NR,
    append(ListL, ListR, List).

build_tree(T):-  
    T = t(t(t(t(nil,6,t(nil,7,nil)),3,nil),2,t(nil,4,t(nil,5,nil))),1,t(t(t(nil,10,nil),9,t(t(nil,0,nil),11,nil)),8,nil)).