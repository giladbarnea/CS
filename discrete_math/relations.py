from itertools import product as prod
from typing import Callable, Union, Tuple, Set
from operator import le, lt, ge, gt, ne  # lower-equal, lower-than, greater-equal, greater-than, not-equal
from random import randint
import inspect

cartesian_prod = lambda v: prod(v, v)
universal_rel = lambda a, b: True
empty_rel = lambda a, b: False
identity_rel = lambda a, b: a == b
opposite_rel = lambda a, b: a == -b

# inverse_rel = lambda a,b:
R = Callable[[int, int], bool]
Prod = Set[Tuple[int, int]]


def relate(rel: R, v1, v2=None, *, quiet=True):
    """Creates a relation between v1×v2. If only one set is given, creates a relation between v1×v1 i.e. cartesian product.
    In code terms, just a filter on v1×v2.
    Examples:
    
    >>> V = {1, 2, 3}
    >>> relate(lambda a, b: a > b, V)
    {(3, 1), (3, 2), (2, 1)}
    
    >>> relate(gt, V) # same as example above
    {(3, 1), (3, 2), (2, 1)}
    
    >>> V_cart_prod = set(cartesian_prod(V))
    >>> relate(universal_rel, V) == V_cart_prod
    True
    """
    relation = set()
    if v2 is None:
        if not quiet:
            print(f'relation({inspect.getsourcelines(rel)}, v1 ({len(v1)}) × v1)')
        product = prod(v1, v1)
    else:
        if not quiet:
            print(f'relation({inspect.getsourcelines(rel)}, v1 ({len(v1)}) × v2 (({len(v1)}))')
        product = prod(v1, v2)
    for x, y in product:
        if rel(x, y):
            relation.add((x, y))
            if not quiet:
                print(f'\t<x={x}, y={y}>')
    if not quiet:
        print()
    return relation


def multiply(rel1: Prod, rel2: Prod = None, *, quiet=True):
    """
    >>> U = relate(universal_rel, {-1,0,1})
    >>> I = relate(identity_rel, {-1,0,1})
    >>> multiply(U, I) == U
    True
    """
    mutiplied = set()
    if rel2 is None:
        rel2 = rel1
    for a, b in rel1:
        for b_, c in rel2:
            if b_ == b:
                mutiplied.add((a, c))
                if not quiet:
                    print(f'<a={a}, c={c}> (b={b})')
    return mutiplied


def inverse(rel: Prod):
    inverted = set()
    for a, b in rel:
        inverted.add((b, a))
    return inverted


def relate_sqr(rel: Union[R, Prod], v=None, quiet=True, alt=False):
    """Computes the squared relation.
    ⟨a,c⟩ ∈ 𝑹×𝑹: {⟨a,c⟩ | ∃b ∈ A (⟨a,b⟩ ∈ 𝑹 ∧ ⟨b,c⟩ ∈ 𝑹)}
    
    >>> V = randset(30)
    >>> opposite_of_opposite = relate_sqr(opposite_rel, V)
    >>> I = relate(identity_rel, V)
    >>> I == opposite_of_opposite
    True

    >>> r2 = relate_sqr({(1, 1), (2, 2)}, {1, 2})
    identity_rel
    
    """
    r2 = set()
    if callable(rel):
        if not alt:
            for a, c in cartesian_prod(v):
                for b in v:
                    if rel(a, b) and rel(b, c):
                        r2.add((a, c))
                        if not quiet:
                            print(f'<a={a}, c={c}> (b={b})')
        else:
            return relate_sqr(relate(rel, v), v, quiet=quiet, alt=False)
    
    else:
        # p111 / q15
        r2 = multiply(rel, rel, quiet=quiet)
        if v is not None:
            try:
                known_rels = dict(
                        universal_rel=universal_rel,
                        empty_rel=empty_rel,
                        identity_rel=identity_rel,
                        opposite_rel=opposite_rel,
                        lower_equal=le,
                        lower_then=lt,
                        greater_equal=ge,
                        greater_then=gt,
                        not_equal=ne
                        )
                print(next(name for name, rel in known_rels.items() if r2 == relate(rel, v)))
            except StopIteration:
                pass
    return r2


def is_symmetric(rel: R, v1, v2=None, quiet=True):
    """A relation is symmetric if for every pair <a,b> in R, also <b,a> in R
    In other words: rel(a,b) is True and rel(b,a) is True
    >>> symmetric = [universal_rel, empty_rel, identity_rel, opposite_rel, ne]
    >>> all(is_symmetric(_,randset(100)) for _ in symmetric)
    |R|: ...
    |R|: ...
    True
    
    >>> not_symmetric = [lt, le, gt, ge]
    >>> any(is_symmetric(_,randset(100)) for _ in not_symmetric)
    |R|: ...
    |R|: ...
    False
    
    """
    relation = relate(rel, v1, v2, quiet=quiet)
    print(f'|R|: {(rel_len := len(relation))} ({(rel_len / len((set(prod(v1, v2 if v2 else v1))))) * 100}%)')
    for x, y in relation:
        if not rel(y, x):
            if not quiet:
                print(f'<x={x}, y={y}> not in R but <y,x> is in R: fail')
            return False
        if not quiet:
            print(f'<x={x}, y={y}> ok (rel(<y={y}, x={x}>) is True)')
    return True


def is_anti_symmetric(rel: R, v1, v2=None, *, quiet=True):
    """A relation is anti symmetric if for every pair <a,b> in R, <b,a> is not in R
    In other words: rel(a,b) is True but rel(b,a) is False
    >>> anti_symmetric = [lt, gt, empty_rel]
    >>> all(is_anti_symmetric(_,randset(100)) for _ in anti_symmetric)
    |R|: ...
    |R|: ...
    True
    
    >>> not_anti_symmetric = [universal_rel, identity_rel, opposite_rel, ne, le, ge, lambda a,b:b<a**2]
    >>> any(is_anti_symmetric(_,randset(100)) for _ in not_anti_symmetric)
    |R|: ...
    False
    """
    relation = relate(rel, v1, v2, quiet=quiet)
    print(f'|R|: {(rel_len := len(relation))} ({(rel_len / len((set(prod(v1, v2 if v2 else v1))))) * 100}%)')
    for x, y in relation:
        if rel(y, x):
            if not quiet:
                print(f'<x={x}, y={y}> in R but <y={y}, x={x}> is too: fail')
            return False
        if not quiet:
            print(f'<x={x}, y={y}> ok (rel(<y={y}, x={x}>) is False)')
    return True


def is_reflexive(rel: R, v, *, quiet=True):
    """A relation is reflexive if every x in v satisfies rel(x,x)
    >>> reflexive = [universal_rel, identity_rel, le, ge]
    >>> all(is_reflexive(_,randset(100)) for _ in reflexive)
    ...
    True
    >>> not_reflexive = [ne, lt, gt, empty_rel, opposite_rel]
    >>> any(is_reflexive(_,randset(100)) for _ in not_reflexive)
    ...
    False
    
    """
    for x in v:
        if not rel(x, x):
            if not quiet:
                print(f'<{x},{x}>: fail')
            return False
        if not quiet:
            print(f'<{x},{x}>: ok')
    return True


def is_anti_reflexive(rel: R, v, *, quiet=True):
    """A relation is anti reflexive if no x in v satisfies rel(x,x)
    >>> anti_reflexive = [ne, lt, gt, empty_rel]
    >>> all(is_anti_reflexive(_,randset(100)) for _ in anti_reflexive)
    ...
    True
    >>> not_anti_reflexive = [universal_rel, identity_rel, opposite_rel, le, ge]
    >>> any(is_anti_reflexive(_,randset(100)) for _ in not_anti_reflexive)
    ...
    False
    
    """
    for x in v:
        if rel(x, x):
            if not quiet:
                print(f'<{x},{x}>: fail')
            return False
        if not quiet:
            print(f'<{x},{x}>: ok')
    return True


def is_empty_relation(rel: R, v1, v2=None, *, quiet=True):
    if v2 is None:
        product = set(prod(v1, v1))
    else:
        product = set(prod(v1, v2))
    for x, y in product:
        if rel(x, y) and (x, y) in product:
            return False
    return True


def is_universal_relation(rel: R, v1, v2=None, *, quiet=True):
    if v2 is None:
        product = set(prod(v1, v1))
    else:
        product = set(prod(v1, v2))
    for x, y in product:
        if rel(x, y) and (x, y) not in product:
            return False
    return True


def is_identity_relation(rel: Union[R, Prod], v1, v2=None, *, quiet=True):
    """
    >>> is_identity_relation(lambda a,b: a == b, {-1,0,1})
    True
    
    >>> is_identity_relation({(-1,-1), (0,0), (1,1)}, {-1,0,1})
    True
    
    >>> is_identity_relation({(-1,-1), (0,0), (1,1)}, {-1,0,1}, {-1,0,1,2})
    True
    """
    identity = relate(identity_rel, v1, v2, quiet=quiet)
    if callable(rel):
        relation = relate(rel, v1, v2, quiet=quiet)
        return identity == relation
    return identity == rel


def randset(length, start=None, stop=None) -> set:
    """
    `start` is inclusive, `stop` is exclusive.
    >>> randset(10) == {-4, -3, -2, -1, 0, 1, 2, 3, 4}
    True

    >>> randset(5, 1, 4)
    {1, 2, 3}
    
    >>> 15 <= sum(randset(5, 1, 7)) <= 20
    True
    """
    if start is None and stop is None:
        if length % 2 == 0:
            length -= 1
        half = length // 2
        start = -half
        stop = half + 1
        tmp = set(range(start, stop))
        return tmp
    tmp = set()
    stop -= 1  # randint stop is inclusive
    while (tmp_len := len(tmp)) < length and tmp_len < stop - start + 1:
        tmp.add(randint(start, stop))
    return tmp
