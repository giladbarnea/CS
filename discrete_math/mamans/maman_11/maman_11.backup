גלעד ברנע ת.ז. 203627310
# ממ"ן 11

**Note**: I'll be using e.g. _¬(A ∪ B)_
to represent the _complement of (A ∪ B)_
(My editor doesn't support superscript or overline)

## 2

## א
Prove:
%math
(A\B) ∪ (B\C) = (A ∪ B) \ (B ∩ C)
---
#### First: expand left-hand side
(A ∩ ¬ B) ∪ (B ∩ ¬C)  // diff
(A ∪ B) ∩ (A ∪ ¬C) ∩ (¬ B ∪ B) ∩ (¬B ∪ ¬C) // distributivity
(A ∪ B) ∩ (A union ¬ C) ∩ (¬B ∪ ¬C) // (¬B ∪ B) equiv T
**(A ∪ B) ∩ [(A ∩ ¬ B) ∪ ¬ C]** // dist.


#### Second: expand right-hand side
(A ∪ B) ∩ not (B ∩ C)
(A ∪ B) ∩ (¬B ∪ ¬C)
(A n -B) u (A n -C) u (B n -B) u (B n -C)
(A\B) u (A n -C) u (B n -C) // (B n -B) equiv 0
(A\B) u [(A u B) n -C] // dist
[(A\B) u (A u B)] n [(-A n -B) u -C] // dist

// I'll now prove that [(A\-B) u (A u B)] equiv (A u B), 
// then get back to expanding the full statement 
<div class="box">
Since (A\B) <= A \and A <= (A u B) =>


(A\B) <= (A u B)


Therefore


(A\B) u (A u B) = (A u B)
</div>
(A u B) n [(-A n -B) u -C]


/%math

## ב
Prove: 
if P(A) ∨ P(B) = P(C), then (C=A) ∨ (C=B)
---

I'll be proving:
(C⊆A ∧ A⊆C) ∨ (C⊆B ∧ B⊆C)
Since it's equivalent to
(C=A) ∨ (C=B)
---

#### First: proof that C⊆A ∨ C⊆B
C ∈ P(C) // power set definition
P(C) = P(A) ∨ P(B) ⇒ C ∈ (P(A) ∨ P(B))
C ∈ P(A) ∨ C ∈ P(B)
**C⊆A ∨ C⊆B**


#### Second: proof that A⊆C ∨ B⊆C

A ∈ P(A)
P(A) ⊆ P(A) ∪ P(B) // union definition
A ∈ P(A) ∪ P(B)
Given P(C) = P(A) ∪ P(B) ⇒ A ∈ P(C)
**A⊆C**


B ∈ P(B)
P(B) ⊆ P(A) ∪ P(B) // union definition
B ∈ P(A) ∪ P(B)
Given P(C) = P(A) ∪ P(B) ⇒ B ∈ P(C)
**B⊆C**
---
Since C⊆A ∨ C⊆B and A⊆C and B⊆C,
we conclude that: 
C⊆A ∨ C⊆B ∧ A⊆C ∧ B⊆C
Therefore
**(C=A) ∨ (C=B)**
