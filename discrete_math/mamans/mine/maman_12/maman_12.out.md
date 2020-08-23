גלעד ברנע ת.ז. 203627310

# ממ"ן 11

## 1

### א


Prove:  
AΔB⊆D ∧ BΔC⊆D → AΔC⊆D


---


Since (X→Z) ∧ (Y→Z) ≡ (X∨Y)→Z, also:  
(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D. Proof:

<div class="box">
(X→Z) ∧ (Y→Z)
<br>
Z∨¬X ∧ Z∨¬Y
<br>
Z∨<span class="over">(X∨Y)</span>
<br>
(X∨Y) → Z
<br>
<br>
AΔB⊆D ∧ BΔC⊆D
<br>
(x∈(AΔB) → x∈D) ∧ (x∈(BΔC) → x∈D)
<br>
x∈(AΔB ∪ BΔC) → x∈D
<br>
(AΔB ∪ BΔC) ⊆ D
<br>
Therefore:
<br>
(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D <span class="comment">&#9;(1)</span>
</div>


---


I'll prove that AΔC ⊆ (AΔB ∪ BΔC), then it wo∪ld follow by transience
that AΔC⊆C


---


Expanding (AΔB ∪ BΔC):



<br>


Expanding AΔC:  
(A∩<span class="over">C</span>) ∪ (<span class="over">A</span>∩C)  
(A∪C) ∩ (<span class="over">A</span>∪<span class="over">C</span>)  
__AΔC ≡ (A∪C) ∩ (<span class="over">A</span>∪<span
class="over">C</span>)__ <span class="comment">&#9;(3) </span>


---


Define:  
P = B; Q = (A∪C); R = (<span class="over">A</span>∪<span
class="over">C</span>)

So proving Q∧R → (¬P∨Q ∨ P∧R),  
will prove that AΔC ⊆ (AΔB ∪ BΔC)

---


Proving Q∧R → (¬P∨Q ∨ P∧R) is always tr∪e:  
Premise: (Q∧R); Conclusion: (¬P∨Q ∨ P∧R).  
Premise → Conclusion is always tr∪e if the following holds:  
Whenever the Premise is tr∪e, also the Conclusion is tr∪e.  
Assuming that Premise is tr∪e:  
Q∧R ≡ 𝚻 ⟹  
Q≡𝚻 ∧ R≡𝚻  
Using that in the Conclusion:  
(¬P∨Q ∨ P∧R) ≡ (¬P∨𝚻 ∨ P∧𝚻) ≡ ¬P∨P ≡ 𝚻  
Therefore the conclusion is dependent ∪pon the premise, therefore  
__Q∧R → (¬P∨Q ∨ P∧R) ≡ 𝚻__ <span class="comment">&#9;(4) </span>


---


P, Q and R are placeholders (defined above), so I'll ∪se their "real"
val∪es:  
Q∧R → (¬P∨Q ∨ P∧R) ≡

(A∪C)∩(<span class="over">A</span>∪<span class="over">C</span>) →
<span class="grey">Using (3): </span>

<span class="grey">Using (2): </span>

__AΔC ⊆ (AΔB ∪ BΔC)__ <span class="comment">&#9;(5) </span>


---


Since it's given that:  
AΔB⊆D ∧ BΔC⊆D  
Using (1), it's eq∪ivalent to:  
(AΔB ∪ BΔC) ⊆ D  
And because of (5), we know that  
AΔC ⊆ (AΔB ∪ BΔC)  
Together with the transience of ⊆, <span class="comment">&#9;X⊆Y and Y⊆Z
⇒ X⊆Z </span>

__AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__


---

### ב


M: 𝓟({1,2,3})  
𝑹: ⟨A,B⟩∈𝑹 ⟺ AΔB⊆{1,2}  
𝑺: ⟨A,B⟩∈𝑺 ⟺ AΔ{1,2} ⊂ BΔ{1,2}


---

𝑹 is the equivalence relation because it's reflexive, symmetric and
transitive.

Reflexive: if 𝑹 is reflexive then AΔA⊆{1,2}.  
The symmetric difference between anything and itself is ∅ ⟹ AΔA = ∅.  
∅ is a subset of every possible set ⟹ ∅⊆{1,2} ⟹ __AΔA⊆{1,2}__

Symmetric: if 𝑹 is symmetric then AΔB⊆{1,2} → BΔA⊆{1,2}.  
Symmetric difference is commutative: XΔY ≡ YΔX for any X,Y.  
Therefore AΔB ≡ BΔA ⟹ __AΔB⊆{1,2} → BΔA⊆{1,2}__

Transitive: if 𝑹 is transitive then AΔB⊆{1,2} ∧ BΔC⊆{1,2} → AΔC⊆{1,2}.  
We proved in 2א that __AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__


---

The equivalence classes of 𝑹:

⟦∅⟧ is the set containing all elements in M such that {m∈M | ⟨m,0⟩∈𝑹}}.  
Remembering that for any X, the following is true: XΔ∅ = X;  
Therefore, using the definition of 𝑹:

__⟦∅⟧__ = {m∈M | mΔ∅ ⊆ {1,2}} = {m∈M | m ⊆ {1,2}} = __{∅, {1}, {2},
{1,2}}__


We are left with four subsets: {3}, {1,3}, {2,3}, {1,2,3}.  
Because {3} is a subset of each of them, let's figure out it's
equivalence class.  
⟦3⟧ = {m∈M | mΔ{3} ⊆ {1,2}}  
Because symmetric difference leaves out everything besides the
intersection, m has to contain {3} if we need mΔ{3}⊆{1,2} to be true,
so:  
{3} ⊆ m
