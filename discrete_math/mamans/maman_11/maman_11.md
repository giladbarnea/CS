# ממ"ן 11

**Note**: I'll be using e.g. ¬(A ∪ B)
to represent the complement of (A ∪ B)
(My editor doesn't support superscript or overline)

## 2

### ב
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
