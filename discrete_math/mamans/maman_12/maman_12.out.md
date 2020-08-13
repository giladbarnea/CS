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

(X→Z) ∧ (Y→Z)<br>Z∨¬X ∧ Z∨¬Y<br>Z∨<over>(X∨Y)</over><br>(X∨Y) → Z<br><br>AΔB⊆D ∧ BΔC⊆D<br>(x∈(AΔB) → x∈D) ∧ (x∈(BΔC) → x∈D)<br>x∈(AΔB ∪ BΔC) → x∈D<br>(AΔB ∪ BΔC) ⊆ D<br>Therefore:<br>(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D <comment>(1)<br></div></comment>


---



I'll prove that AΔC ⊆ (AΔB ∪ BΔC), then it wo∪ld follow by transience that AΔC⊆C


---



Expanding (AΔB ∪ BΔC):  
  
(<over>B</over>∩A) ∪ (<over>B</over>∩C) ∪ (B∩<over>A</over>) ∪ (B∩<over>C</over>)  
[<over>B</over>∩(A∪C)] ∪ [B∩(<over>A</over>∪<over>C</over>)]  
__(AΔB ∪ BΔC) ≡ [<over>B</over>∩(A∪C)] ∪ [B∩(<over>A</over>∪<over>C</over>)]__ <comment>(2)  </comment>

<br>

Expanding AΔC:  
(A∩<over>C</over>) ∪ (<over>A</over>∩C)  
(A∪C) ∩ (<over>A</over>∪<over>C</over>)  
__AΔC ≡ (A∪C) ∩ (<over>A</over>∪<over>C</over>)__ <comment>(3)  </comment>


---



Define:   
P = B; Q = (A∪C); R = (<over>A</over>∪<over>C</over>)  
  
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
__Q∧R → (¬P∨Q ∨ P∧R) ≡ 𝚻__ <comment>(4)  </comment>


---



P, Q and R are placeholders (defined above), so I'll ∪se their "real" val∪es:  
Q∧R → (¬P∨Q ∨ P∧R) ≡  
  
(A∪C)∩(<over>A</over>∪<over>C</over>) → [<over>B</over>∩(A∪C)] ∪ [B∩(<over>A</over>∪<over>C</over>)]  
<grey>Using (3):  </grey>

AΔC ⊆ [<over>B</over>∩(A∪C)] ∪ [B∩(<over>A</over>∪<over>C</over>)]  
<grey>Using (2):  </grey>

__AΔC ⊆ (AΔB ∪ BΔC)__ <comment>(5)  </comment>


---



Since it's given that:  
AΔB⊆D ∧ BΔC⊆D  
Using (1), it's eq∪ivalent to:  
(AΔB ∪ BΔC) ⊆ D  
And because of (5), we know that   
AΔC ⊆ (AΔB ∪ BΔC)  
Together with the transience of ⊆, <comment>X⊆Y and Y⊆Z ⇒ X⊆Z  </comment>

__AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__




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
We proved in 2א that __AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__