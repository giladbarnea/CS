%ignore and <
גלעד ברנע ת.ז. 203627310
# ממ"ן 11
## 1
### א 

Prove:   
AΔB⊆D ∧ BΔC⊆D → AΔC⊆D

---

Since (X→Z) ∧ (Y→Z) ≡ (X∨Y)→Z, also:  
(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D. Proof:
<box>
(X→Z) ∧ (Y→Z)
Z∨¬X ∧ Z∨¬Y
Z∨¬(X∨Y)
(X∨Y) → Z

AΔB⊆D ∧ BΔC⊆D
(x∈(AΔB) → x∈D) ∧ (x∈(BΔC) → x∈D)
x∈(AΔB ∪ BΔC) → x∈D
(AΔB ∪ BΔC) ⊆ D
Therefore:
(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D // (1)
</box>

---

I'll prove that AΔC ⊆ (AΔB ∪ BΔC), then it wo∪ld follow by transience that AΔC⊆C

---

Expanding (AΔB ∪ BΔC):  
%sets  
(¬B∩A) ∪ (¬B∩C) ∪ (B∩¬A) ∪ (B∩¬C)  
[¬B∩(A∪C)] ∪ [B∩(¬A∪¬C)]  
__(AΔB ∪ BΔC) ≡ [¬B∩(A∪C)] ∪ [B∩(¬A∪¬C)]__ // (2)  


Expanding AΔC:  
(A∩¬C) ∪ (¬A∩C)  
(A∪C) ∩ (¬A∪¬C)  
__AΔC ≡ (A∪C) ∩ (¬A∪¬C)__ // (3)  

---

Define:   
P = B; Q = (A∪C); R = (¬A∪¬C)  
/%sets  
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
Using that \in the Conclusion:  
(¬P∨Q ∨ P∧R) ≡ (¬P∨𝚻 ∨ P∧𝚻) ≡ ¬P∨P ≡ 𝚻  
Therefore the conclusion is dependent ∪pon the premise, therefore   
__Q∧R → (¬P∨Q ∨ P∧R) ≡ 𝚻__ // (4)  

---

P, Q and R are placeholders (defined above), so I'll ∪se their "real" val∪es:  
Q∧R → (¬P∨Q ∨ P∧R) ≡  
%sets  
(A∪C)∩(¬A∪¬C) → [¬B∩(A∪C)] ∪ [B∩(¬A∪¬C)]  
// Using (3):  
AΔC ⊆ [¬B∩(A∪C)] ∪ [B∩(¬A∪¬C)]  
// Using (2):  
__AΔC ⊆ (AΔB ∪ BΔC)__ // (5)  

---

Since it's given that:  
AΔB⊆D ∧ BΔC⊆D  
Using (1), it's eq∪ivalent to:  
(AΔB ∪ BΔC) ⊆ D  
And because of (5), we know that   
AΔC ⊆ (AΔB ∪ BΔC)  
Together with the transience of ⊆, // X⊆Y and Y⊆Z ⇒ X⊆Z  
__AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__
/%sets

---
### ב
 
𝑹: ⟨A,B⟩∈𝑹 ⟺ AΔB⊆{1,2}  
𝑺: ⟨A,B⟩∈𝑺 ⟺ AΔ{1,2} ⊂ BΔ{1,2}

---
𝑹 is the equivalence relation because it's reflexive, symmetric and transitive.
Reflexive: if 𝑹 is reflexive then AΔA⊆{1,2}.  
The symmetric difference between anything and itself is ∅ ⟹ AΔA = ∅.      
∅ is a subset of every possible set ⟹ ∅⊆{1,2} ⟹ __AΔA⊆{1,2}__
Symmetric: if 𝑹 is symmetric then AΔB⊆{1,2} → BΔA⊆{1,2}.    
Symmetric difference is commutative: XΔY ≡ YΔX for any X,Y.  
Therefore AΔB ≡ BΔA ⟹ __AΔB⊆{1,2} → BΔA⊆{1,2}__
Transitive: if 𝑹 is transitive then AΔB⊆{1,2} ∧ BΔC⊆{1,2} → AΔC⊆{1,2}.  
We proved \in 2א that __AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__