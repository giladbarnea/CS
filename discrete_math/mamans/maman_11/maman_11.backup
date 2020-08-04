גלעד ברנע ת.ז. 203627310
# ממ"ן 11

**Note**: Sometimes I'll be using e.g. ¬A to represent the complement of A
(My editor doesn't fully support superscript or overline)

<line>

## 2

### א
Prove:
%math
(A\B) ∪ (B\C) = (A ∪ B) \ (B ∩ C)
<thin>
#### First: expand left-hand side (A\B) ∪ (B\C)
(A ∩ ¬B) ∪ (B ∩ ¬C)  // diff
(A ∪ B) ∩ (A ∪ ¬C) ∩ (¬B ∪ B) ∩ (¬B ∪ ¬C) // distributivity
(A ∪ B) ∩ (A union ¬C) ∩ (¬B ∪ ¬C) // (¬B ∪ B) equiv T
**(A ∪ B) ∩ [(A ∩ ¬B) ∪ ¬C]** // dist.


#### Second: expand right-hand side (A ∪ B) \ (B ∩ C)
(A ∪ B) ∩ not (B ∩ C)
(A ∪ B) ∩ (¬B ∪ ¬C)
(A n -B) u (A n -C) u (B n -B) u (B n -C) // dist
(A\B) u (A n -C) u (B n -C) // (B n -B) equiv 0
(A\B) u [(A u B) n -C] // dist
[(A\B) u (A u B)] n [(A n -B) u -C] // dist

// I'll now prove that [(A\B) u (A u B)] equiv (A u B), 
// then get back to expanding the full statement 

<box>
Since (A\B) <= A \and A <= (A u B) =>
(A\B) <= (A u B)
Therefore
(A\B) u (A u B) = (A u B)
</box>

**(A u B) n [(A n -B) u -C]**
<thin>
We see that left-hand side equiv right-hand side, therefore
**(A\B) ∪ (B\C) = (A ∪ B) \ (B ∩ C)**
/%math
<line>
### ב
Prove: 
if P(A) ∨ P(B) = P(C), then (C=A) ∨ (C=B)
<thin>

I'll be proving:
(C⊆A ∧ A⊆C) ∨ (C⊆B ∧ B⊆C)
Since it's equivalent to
(C=A) ∨ (C=B)
<thin>

#### First: proof that C⊆A ∨ C⊆B
C ∈ P(C) // power set definition
P(C) = P(A) ∨ P(B) ⇒ C ∈ (P(A) ∨ P(B))
C ∈ P(A) ∨ C ∈ P(B)
**C⊆A ∨ C⊆B**


#### Second: proof that A⊆C ∨ B⊆C

A ∈ P(A)
P(A) ⊆ P(A) ∪ P(B) // union definition
A ∈ P(A) ∪ P(B)
Given P(C) = (P(A) ∪ P(B)) ⇒ A ∈ P(C)
**A⊆C**


B ∈ P(B)
P(B) ⊆ P(A) ∪ P(B) // union definition
B ∈ P(A) ∪ P(B)
Given P(C) = (P(A) ∪ P(B)) ⇒ B ∈ P(C)
**B⊆C**
<thin>
Since C⊆A ∨ C⊆B and A⊆C and B⊆C,
we conclude that: 
C⊆A ∨ C⊆B ∧ A⊆C ∧ B⊆C
Therefore
**(C=A) ∨ (C=B)**
<line>
### ג
Prove:
%math
if A,B are finite \and |P(A)| = 2*|P(A\B)|, then |A n B| = 1
<thin>
#### (1)
A\B equiv A \ (A n B) // by definition

#### (2)
We know that for any two sets X,Y, if Y<=X then |X\Y| = |X| - |X n Y|
Certainly (A n B) <= A, so
|A \ (A n B)| = |A| - |A n B|.

#### (3)
Assuming |A n B| = 1, if follows that:
|A| - |A n B| = |A| - 1, therefore using (1) \and (2):
|A\B| = |A \ (A n B)| = |A| - |A n B| = |A| - 1, so
|P(A\B)| = 2^|A\B| = 2^(|A| - 1)

#### (4): Expanding 2*|P(A\B)|
2*|P(A\B)| = 2*2^(|A| - 1) = 2^|A|

#### (5)
|P(A)| = 2^|A| // by definition

#### (6)
|P(A)| = 2*|P(A\B)|

/%math
---
## 3
### א

%math
Prove: if (A < B), then (A u -B) != U
<thin>
Since A is a __proper__ subset of B, then (B\A) != 0.
Expanding (B\A):
(B\A) = 
(B n -A) = 
(-A n B) =  // comm.
not (A u -B)  // DeMorgan
Therefore not (A u -B) ≠ ∅
<thin> 
Since the complement of a given set X is the universal set (U) if \and only if X=0, it follows that the complement of a given set Y is __not__ U if \and only if Y!=0.
Because not (A u -B) ≠ ∅, then the complement of not (A u -B) != U, 
therefore (A u -B) != U.  
/%math
<line>

###ב
%math
Prove: if (-A sd B) = (-B sd C), then A=C
<thin>
We know that (-A sd B) = (-B sd A), because:
(-A n -B) u (B n A) = (-B n -A) u (A n B) // symm diff definition
<thin>
It's given that (-A sd B) = (-B sd C), so
(-B sd A) = (-B sd C) // (-B sd A) = (-A sd B)
<thin>
Since for any sets X, Y, Z; if (X sd Y) = (X sd Z), then X = Z; if follows that
A=C 
/%math