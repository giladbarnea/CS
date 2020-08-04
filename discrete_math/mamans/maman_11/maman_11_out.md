גלעד ברנע ת.ז. 203627310

# ממ"ן 11



**Note**: Sometimes I'll be using e.g. ¬A to represent the complement of A

(My editor doesn't fully support superscript or overline)



<div class="line"></div>



## 2



### א

Prove:


(A\B) ∪ (B\C) = (A ∪ B) \ (B ∩ C)

<div class="thin-line"></div>

#### First: expand left-hand side (A\B) ∪ (B\C)

(A ∩ ¬B) ∪ (B ∩ ¬C)  <span style="padding-left: 25pt; color: rgb(75,75,75)">// diff</span>

(A ∪ B) ∩ (A ∪ ¬C) ∩ (¬B ∪ B) ∩ (¬B ∪ ¬C) <span style="padding-left: 25pt; color: rgb(75,75,75)">// distributivity</span>

(A ∪ B) ∩ (A ∪ ¬C) ∩ (¬B ∪ ¬C) <span style="padding-left: 25pt; color: rgb(75,75,75)">// (¬B ∪ B) ≡ T</span>

**(A ∪ B) ∩ [(A ∩ ¬B) ∪ ¬C]** <span style="padding-left: 25pt; color: rgb(75,75,75)">// dist.</span>

<br>

#### Second: expand right-hand side (A ∪ B) \ (B ∩ C)

(A ∪ B) ∩ <span style="text-decoration: overline">(B ∩ C)</span>

(A ∪ B) ∩ (¬B ∪ ¬C)

(A ∩ ¬B) ∪ (A ∩ ¬C) ∪ (B ∩ ¬B) ∪ (B ∩ ¬C) <span style="padding-left: 25pt; color: rgb(75,75,75)">// dist</span>

(A\B) ∪ (A ∩ ¬C) ∪ (B ∩ ¬C) <span style="padding-left: 25pt; color: rgb(75,75,75)">// (B ∩ ¬B) ≡ ∅</span>

(A\B) ∪ [(A ∪ B) ∩ ¬C] <span style="padding-left: 25pt; color: rgb(75,75,75)">// dist</span>

[(A\B) ∪ (A ∪ B)] ∩ [(A ∩ ¬B) ∪ ¬C] <span style="padding-left: 25pt; color: rgb(75,75,75)">// dist</span>



<span style="color: rgb(75,75,75)">// I'll now prove that [(A\B) ∪ (A ∪ B)] ≡ (A ∪ B), </span>

<span style="color: rgb(75,75,75)">// then get back to expanding the full statement </span>



<div class="box">

Since (A\B) ⊆ A and A ⊆ (A ∪ B) ⇒<br>(A\B) ⊆ (A ∪ B)<br>Therefore<br>(A\B) ∪ (A ∪ B) = (A ∪ B)<br></div>



**(A ∪ B) ∩ [(A ∩ ¬B) ∪ ¬C]**

<div class="thin-line"></div>

We see that left-hand side ≡ right-hand side, therefore

**(A\B) ∪ (B\C) = (A ∪ B) \ (B ∩ C)**


<div class="line"></div>

### ב

Prove: 

if P(A) ∨ P(B) = P(C), then (C=A) ∨ (C=B)

<div class="thin-line"></div>



I'll be proving:

(C⊆A ∧ A⊆C) ∨ (C⊆B ∧ B⊆C)

Since it's equivalent to

(C=A) ∨ (C=B)

<div class="thin-line"></div>



#### First: proof that C⊆A ∨ C⊆B

C ∈ P(C) <span style="padding-left: 25pt; color: rgb(75,75,75)">// power set definition</span>

P(C) = P(A) ∨ P(B) ⇒ C ∈ (P(A) ∨ P(B))

C ∈ P(A) ∨ C ∈ P(B)

**C⊆A ∨ C⊆B**

<br>

#### Second: proof that A⊆C ∨ B⊆C



A ∈ P(A)

P(A) ⊆ P(A) ∪ P(B) <span style="padding-left: 25pt; color: rgb(75,75,75)">// union definition</span>

A ∈ P(A) ∪ P(B)

Given P(C) = (P(A) ∪ P(B)) ⇒ A ∈ P(C)

**A⊆C**

<br>

B ∈ P(B)

P(B) ⊆ P(A) ∪ P(B) <span style="padding-left: 25pt; color: rgb(75,75,75)">// union definition</span>

B ∈ P(A) ∪ P(B)

Given P(C) = (P(A) ∪ P(B)) ⇒ B ∈ P(C)

**B⊆C**

<div class="thin-line"></div>

Since C⊆A ∨ C⊆B and A⊆C and B⊆C,

we conclude that: 

C⊆A ∨ C⊆B ∧ A⊆C ∧ B⊆C

Therefore

**(C=A) ∨ (C=B)**

<div class="line"></div>

### ג

Prove:


if A,B are finite and |P(A)| = 2·|P(A\B)|, then |A ∩ B| = 1

<div class="thin-line"></div>

#### (1)

A\B ≡ A \ (A ∩ B) <span style="padding-left: 25pt; color: rgb(75,75,75)">// by definition</span>



#### (2)

We know that for any two sets X,Y, if Y⊆X then |X\Y| = |X| - |X ∩ Y|

Certainly (A ∩ B) ⊆ A, so

|A \ (A ∩ B)| = |A| - |A ∩ B|.



#### (3)

Assuming |A ∩ B| = 1, if follows that:

|A| - |A ∩ B| = |A| - 1, therefore using (1) and (2):

|A\B| = |A \ (A ∩ B)| = |A| - |A ∩ B| = |A| - 1, so

|P(A\B)| = 2^|A\B| = 2^(|A| - 1)



#### (4): Expanding 2·|P(A\B)|

2·|P(A\B)| = 2·2^(|A| - 1) = 2^|A|



#### (5)

|P(A)| = 2^|A| <span style="padding-left: 25pt; color: rgb(75,75,75)">// by definition</span>



#### (6)

|P(A)| = 2·|P(A\B)|




---

## 3

### א


Prove: if (A ⊂ B), then (A ∪ ¬B) ≠ U

<div class="thin-line"></div>

Since A is a __proper__ subset of B, then (B\A) ≠ ∅.

Expanding (B\A):

(B ∩ ¬A)

(¬A ∩ B) <span style="padding-left: 25pt; color: rgb(75,75,75)">// comm.</span>

<span style="text-decoration: overline">(A ∪ ¬B)</span> <span style="padding-left: 25pt; color: rgb(75,75,75)">// DeMorgan</span>


