from itertools import product as prod
from typing import Callable, Union
from operator import eq, le, lt, ge, gt, ne  # equal, lower-equal, lower-than, greater-equal, greater-than, not-equal
from random import randint
import inspect

cartesian_prod = lambda v: prod(v, v)
universal_rel = lambda a, b: True
empty_rel = lambda a, b: False
identity_rel = lambda a, b: a == b
opposite_rel = lambda a, b:a == -b

# inverse_rel = lambda a,b:
R = Callable[[int, int], bool]
def relate(v, rel: R, quiet=True):
    """Creates a relation. In code terms, just a filter on the cartesian product.
    Examples:
    
    >>> V = {1, 2, 3}
    >>> relate(V, lambda a, b: a > b)
    {(3, 1), (3, 2), (2, 1)}
    
    >>> relate(V, gt) # same as example above
    {(3, 1), (3, 2), (2, 1)}
    
    >>> V_cart_prod = set(cartesian_prod(V))
    >>> relate(V, universal_rel) == V_cart_prod
    True
    """
    if not quiet:
        print(f'relation(... ({len(v)}), {inspect.getsourcelines(rel)})')
    relation = set()
    for x, y in cartesian_prod(v):
        if rel(x, y):
            relation.add((x, y))
            if not quiet:
                print(f'\t<x={x}, y={y}>')
    if not quiet:
        print()
    return relation


def relate_sqr(v, rel, quiet=True):
    """Computes the squared relation.
    
    >>> r2 = relate_sqr(randset(30), opposite_rel)
    >>> r = relate(randset(30), identity_rel)
    >>> r == r2
    True

    >>> r2 = relate_sqr({1, 2}, {(1, 1), (2, 2)})
    identity_rel
    
    """
    r2 = set()
    if callable(rel):
        for x, y in cartesian_prod(v):
            for z in v:
                if rel(x, z) and rel(z, y):
                    r2.add((x, y))
                    if not quiet:
                        print(f'<x={x}, y={y}> (z={z})')
    else:
        # p111 / q15
        for x1, z1 in rel:
            for z2, y2 in rel:
                if z2 == z1:
                    r2.add((x1,y2))
                    if not quiet:
                        print(f'<x={x1}, y={y2}> (z={z1})')
    
        try:
            known_rels = dict(
                        universal_rel=universal_rel,
                        empty_rel=empty_rel,
                        identity_rel=identity_rel,
                        opposite_rel=opposite_rel,
            )
            print(next(name for name, rel in known_rels.items() if r2 == relate(v,rel)))
        except StopIteration:
            pass
    return r2


def is_symmetric(v, rel: R,quiet=True):
    """A relation is symmetric if for every pair <a,b> in R, also <b,a> in R
    In other words: rel(a,b) is True and rel(b,a) is True
    >>> symmetric = [universal_rel, empty_rel, eq, ne]
    >>> all(is_symmetric(randset(100), _) for _ in symmetric)
    |R|: ...
    |R|: ...
    True
    
    >>> not_symmetric = [lt, le]
    >>> any(is_symmetric(randset(100), _) for _ in not_symmetric)
    |R|: ...
    |R|: ...
    False
    
    """
    relation = relate(v, rel,quiet=quiet)
    print(f'|R|: {(rel_len := len(relation))} ({(rel_len / len((card_prod := set(cartesian_prod(v))))) * 100}%)')
    for x, y in relation:
        assert not (rel(y, x) ^ ((y,x) in relation))
        if not rel(y, x):
            if not quiet:
                print(f'<x={x}, y={y}> not in R but <y,x> is in R: fail')
            return False
        if not quiet:
            print(f'<x={x}, y={y}> ok (rel(<y={y}, x={x}>) is True)')
    return True


def is_anti_symmetric(v, rel: R,quiet=True):
    """A relation is anti symmetric if for every pair <a,b> in R, <b,a> is not in R
    In other words: rel(a,b) is True but rel(b,a) is False
    >>> anti_symmetric = [lt, empty_rel]
    >>> all(is_anti_symmetric(randset(100), _) for _ in anti_symmetric)
    |R|: ...
    |R|: ...
    True
    
    >>> not_anti_symmetric = [lambda a,b:b<a**2]
    >>> any(is_anti_symmetric(randset(100), _) for _ in not_anti_symmetric)
    |R|: ...
    False
    """
    relation = relate(v, rel,quiet=quiet)
    print(f'|R|: {(rel_len := len(relation))} ({(rel_len / len((card_prod := set(cartesian_prod(v))))) * 100}%)')
    for x, y in relation:
        assert not (rel(y, x) ^ ((y,x) in relation))
        if rel(y, x):
            if not quiet:
                print(f'<x={x}, y={y}> in R but <y={y}, x={x}> is too: fail')
            return False
        if not quiet:
            print(f'<x={x}, y={y}> ok (rel(<y={y}, x={x}>) is False)')
    return True


def is_reflexive(v, rel: R,quiet=True):
    """A relation is reflexive if every x in v satisfies rel(x,x)
    >>> reflexive = [universal_rel, identity_rel, le]
    >>> all(is_reflexive(randset(100), _) for _ in reflexive)
    ...
    True
    >>> not_reflexive = [ne, lt, empty_rel]
    >>> any(is_reflexive(randset(100), _) for _ in not_reflexive)
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


def is_anti_reflexive(v, rel: R,quiet=True):
    """A relation is anti reflexive if no x in v satisfies rel(x,x)
    >>> anti_reflexive = [ne, lt, empty_rel]
    >>> all(is_anti_reflexive(randset(100), _) for _ in anti_reflexive)
    ...
    True
    >>> not_anti_reflexive = [eq, le]
    >>> any(is_anti_reflexive(randset(100), _) for _ in not_anti_reflexive)
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


def is_empty_relation(v, rel: R,quiet=True):
    cart_prod = set(cartesian_prod(v))
    for x, y in cartesian_prod(v):
        if rel(x, y) and (x, y) in cart_prod:
            return False
    return True


def is_universal_relation(v, rel: R,quiet=True):
    cart_prod = set(cartesian_prod(v))
    for x, y in cartesian_prod(v):
        if rel(x, y) and (x, y) not in cart_prod:
            return False
    return True


def randset(length, start=None, stop=None) -> set:
    """Example: randset(10) # returns a set of length 10 containing random numbers ranging from -10 to 10
    """
    if start is None and stop is None:
        if length % 2 == 0:
            length -= 1
        half = length // 2
        start = -half
        stop = half + 1
        tmp = set(range(start,stop))
        return tmp
    tmp = set()
    while (tmp_len := len(tmp)) < length and tmp_len < stop - start:
        tmp.add(randint(start, stop))
    return tmp

