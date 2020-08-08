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

def is_symm(v, rel:Callable[[int,int],bool]):
    relation = relate(v,rel)
    for x,y in cartesian_prod(v):
        if rel(y,x) and (x,y) not in relation:
            return False
    return True

def is_anti_symm(v, rel:Callable[[int,int],bool]):
    relation = relate(v,rel)
    for x,y in cartesian_prod(v):
        if rel(y,x) and (x,y) in relation:
            return False
    return True

def is_reflexive(v, rel:Callable[[int,int],bool]):
    for x,y in cartesian_prod(v):
        if not rel(x,x) or not rel(y,y):
            return False
    return True

def is_anti_reflexive(v, rel:Callable[[int,int],bool]):
    for x,y in cartesian_prod(v):
        if rel(x,x) or rel(y,y):
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