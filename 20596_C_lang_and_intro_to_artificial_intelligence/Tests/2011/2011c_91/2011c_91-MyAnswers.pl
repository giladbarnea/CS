%Question 2
%maxtree_val(t(t(t(t(nil,1,nil),-,t(nil,2,nil)),*,t(t(nil,3,nil),^,t(nil,4,nil))),+,t(t(nil,5,nil),*,t(nil,6,nil))), MaxSubTree)
%maxtree_val(t(t(t(t(nil,1,nil),+,t(nil,2,nil)),*,t(t(nil,3,nil),^,t(nil,4,nil))),+,t(t(nil,5,nil),*,t(nil,6,nil))), MaxSubTree)
maxtree_val(Tree, MaxSubTree):-
    maxtree_val(Tree, _, _, MaxSubTree).

maxtree_val(t(nil, X, nil), X, _, _).
maxtree_val(t(L, X, R), Expression, MaxVal, MaxSubTree):-
    maxtree_val(L, ExpressionL, MaxValL, MaxSubTreeL),
    maxtree_val(R, ExpressionR, MaxValR, MaxSubTreeR),
    build(X, ExpressionL, ExpressionR, Expression),
    Val is Expression,
    (   (   var(MaxValL), var(MaxValR), !,
            MaxVal = Val, MaxSubTree = t(L,X,R))
    	;   
    	(   Val > MaxValL, Val > MaxValR, !,
            MaxVal = Val, MaxSubTree = t(L,X,R))
    	;   
        (   MaxValL > MaxValR, !,
            MaxVal = MaxValL, MaxSubTree = MaxSubTreeL)
    	;   
    	(   MaxVal = MaxValR, MaxSubTree = MaxSubTreeR)).

build(Operator, E1, E2, E):-
    (   Operator = +, E = E1 + E2);
    (   Operator = -, E = E1 - E2);
    (   Operator = /, E = E1 / E2);
    (   Operator = *, E = E1 * E2);
    (   Operator = ^, E = E1 ^ E2).

%Question 4
%balanced(f(a,b,c,d)).
%balanced(f(a(1,2),b,c,d)).
%balanced(f(a(1,2),b(c(d)))).
%balanced(f(b,c(1,e(3)))).
%balanced(f(a(1,2),b(c(d(e))))).
balanced(Term):-
    balanced(Term, _), !.

balanced(Term, 0):-
    (   atomic(Term); var(Term)), !.
balanced(Term, Depth):-
    compound(Term), Term =.. [_| Args],
    balancedList(Args, MinDepth, MaxDepth),
    (   MinDepth is MaxDepth;
    	MinDepth is MaxDepth-1),
    Depth is MaxDepth + 1.

balancedList([], -1, -1).
balancedList([Arg|Rest], MinDepth, MaxDepth):-
    balanced(Arg, Depth),
    balancedList(Rest, MinDRest, MaxDRest),
    (   (   (   MinDRest = -1; Depth < MinDRest), !,
        		MinDepth = Depth)
    	;   
    	(   MinDepth = MinDRest)),
    (   (   Depth > MaxDRest, !, 
        	MaxDepth = Depth)
    	;   
    	(   MaxDepth = MaxDRest)).