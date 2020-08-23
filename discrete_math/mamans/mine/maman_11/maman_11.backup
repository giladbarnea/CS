גלעד ברנע ת.ז. 203627310
# ממ"ן 11

היי,
אני ממש מצטער על ההגשה המאוחרת.
זה לא הולך להיות הרגל, קרה משהו ספציפי שגרר את זה. 
מקווה שזה לא מאוחר מידי


שוב מתנצל, ותודה!

<line>

## 1
א: לא נכון
ב: נכון
ג: לא נכון
ד: נכון
ה: לא נכון
ו: לא נכון
ז: נכון
ח: לא נכון

<line>

## 2

### א
Prove:
%math
(A\B) ∪ (B\C) = (A ∪ B) \ (B ∩ C)
<thin>
#### First: expanding left-hand side (A\B) ∪ (B\C)
(A ∩ ¬B) ∪ (B ∩ ¬C)  // difference definition
(A ∪ B) ∩ (A ∪ ¬C) ∩ (¬B ∪ B) ∩ (¬B ∪ ¬C) // distributivity
(A ∪ B) ∩ (A union ¬C) ∩ (¬B ∪ ¬C) // (¬B ∪ B) equiv T
**(A ∪ B) ∩ [(A ∩ ¬B) ∪ ¬C]** // dist.

#### Second: expanding right-hand side (A ∪ B) \ (B ∩ C)
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
// More formally: (C⊆A ∨ C⊆B) ∧ (A⊆C ∧ B⊆C)
it follows that: 
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

#### (6): Putting it all together
__|P(A)| = 2*|P(A\B)|__

/%math
---
## 3
### א

%math
Prove: if (A < B), then (A u -B) != U
<thin>
Since A is a __proper__ subset of B, then (B\A) != 0.
Expanding (B\A) = (B n -A) = (-A n B) = not (A u -B)  // DeMorgan
Therefore not (A u -B) ≠ ∅
<thin> 
Since the complement of a given set X is the universal set (U) if \and only if X=0, it follows that the complement of a given set Y is __not__ U if \and only if Y!=0.
Because not (A u -B) ≠ ∅, then the complement of not (A u -B) != U, 
therefore __(A u -B) != U__. 
/%math
<line>

###ב
%math
Prove: if (-A sd B) = (-B sd C), then A=C.
<thin>
We know that (-A sd B) = (-B sd A), because:
(-A sd B) = (-A n -B) u (A n ¬¬B) = 
(-A n -B) u (B n A) // double negation 
Similarly, 
(-B sd A) = (-B n -A) u (¬¬B n A) = 
(-A n -B) u (B n A) // double negation, comm.
<thin>
It's given that (-A sd B) = (-B sd C), so
(-B sd A) = (-B sd C) // replaced (-A sd B) with (-B sd A)
<thin>
Since for any sets X, Y, Z:
(X sd Y) = (X sd Z) => X = Z
It follows that
(-B sd A) = (-B sd C) => A = C
Therefore __A = C__.
/%math