from itertools import product as prod
from typing import Callable
from operator import eq, le, lt, ge, gt, ne
from functools import partial
from random import randint
cartesian_prod = lambda v:prod(v,v)
universal_rel = lambda a,b:True
empty_rel = lambda a,b:False
identity_rel = lambda a,b:a==b
# inverse_rel = lambda a,b:
def relate(v, rel:Callable[[int,int],bool], quiet=True):
    relation = set()
    for x,y in cartesian_prod(v):
        if rel(x,y): 
            relation.add((x,y))
            if not quiet:
                print(f'<x,y> = <{x},{y}>')
    return relation

def relate_sqr(v, rel:Callable[[int,int],bool], quiet=True):
    r2 = set()
    for x,y in cartesian_prod(v): 
        for z in v: 
            if rel(x,z) and rel(z,y): 
                r2.add((x,y))
                if not quiet:
                    print(f'<x,y> = <{x},{y}> (z: {z})')
    return r2

def is_symmetric(v, rel:Callable[[int,int],bool]):
    """A relation is symmetric if for every pair <a,b> in R, also <b,a> in R
    >>> symmetric = [universal_rel, empty_rel, eq, ne]
    >>> all(is_symmetric(randset(300), _) for _ in symmetric)
    True
    
    >>> not_symmetric = [lt, le]
    >>> any(is_symmetric(randset(300), _) for _ in not_symmetric)
    False
    
    """
    relation = relate(v,rel)
    for x,y in cartesian_prod(v):
        if rel(y,x) and (x,y) not in relation:
            return False
    return True

def is_anti_symmetric(v, rel:Callable[[int,int],bool]):
    """A relation is anti symmetric if for every pair <a,b> in R, <b,a> is not in R
    >>> anti_symmetric = [lt, empty_rel]
    >>> all(is_anti_symmetric(randset(300), _) for _ in anti_symmetric)
    True
    
    >>> not_anti_symmetric = [lambda a,b:b<a**2]
    >>> any(is_anti_symmetric(randset(300), _) for _ in not_anti_symmetric)
    False
    """
    relation = relate(v,rel)
    for x,y in cartesian_prod(v):
        if rel(y,x) and (x,y) in relation:
            return False
    return True

def is_reflexive(v, rel:Callable[[int,int],bool]):
    """A relation is reflexive if every x in v satisfies rel(x,x)
    >>> reflexive = [universal_rel, identity_rel, le]
    >>> all(is_reflexive(randset(300), _) for _ in reflexive)
    True
    >>> not_reflexive = [ne, lt, empty_rel]
    >>> any(is_reflexive(randset(300), _) for _ in not_reflexive)
    False
    
    """
    for x in v:
        if not rel(x,x):
            return False
    return True

def is_anti_reflexive(v, rel:Callable[[int,int],bool]):
    """A relation is anti reflexive if no x in v satisfies rel(x,x)
    >>> anti_reflexive = [ne, lt, empty_rel]
    >>> all(is_anti_reflexive(randset(300), _) for _ in anti_reflexive)
    True
    >>> not_anti_reflexive = [eq, le]
    >>> any(is_anti_reflexive(randset(300), _) for _ in not_anti_reflexive)
    False
    
    """
    for x in v:
        if rel(x,x):
            return False
    return True

def is_empty(v, rel:Callable[[int,int],bool]):
    cart_prod = set(cartesian_prod(v))
    for x,y in cartesian_prod(v):
        if rel(x,y) and (x,y) in cart_prod:
            return False
    return True

def is_universal(v, rel:Callable[[int,int],bool]):
    cart_prod = set(cartesian_prod(v))
    for x,y in cartesian_prod(v):
        if rel(x,y) and (x,y) not in cart_prod:
            return False
    return True
    


def randset(length, start=None, stop=None):
    if start is None and stop is None:
        start = -length
        stop = length
    tmp = set()
    while len(tmp) < length and len(tmp) < stop-start:
        tmp.add(randint(start,stop))
    return tmp

# universal relation: is_symm((_randset:=randset(300)), lambda a,b:partial(choice,list(_randset)))