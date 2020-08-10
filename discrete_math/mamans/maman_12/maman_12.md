גלעד ברנע ת.ז. 203627310
# ממ"ן 11
## 1
Prove: 
AΔB⊆D ∧ BΔC⊆D → AΔC⊆D
<div class="line"></div>
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
<line>
I'll prove that AΔC ⊆ (AΔB ∪ BΔC).
<thin>
Expanding (AΔB ∪ BΔC):
(<oline>B</oline>∩A) ∪ (<oline>B</oline>∩C) ∪ (B∩<oline>A</oline>) ∪ (B∩<oline>C</oline>)
[<oline>B</oline>n(AuC)] ∪ [Bn(<oline>A</oline>u<oline>C</oline>)]
__(AΔB ∪ BΔC) ≡ [<oline>B</oline>n(AuC)] ∪ [Bn(<oline>A</oline>u<oline>C</oline>)]__ // (2)


Expanding AΔC:
(An<oline>C</oline>) u (<oline>A</oline>nC)
(A∪C) n (<oline>A</oline>u<oline>C</oline>)
__AΔC ≡ (A∪C) n (<oline>A</oline>u<oline>C</oline>)__ // (3)

<thin>
Define: 
P = B; Q = (AuC); R = (<oline>A</oline>u<oline>C</oline>)
So proving Q∧R → (¬P∨Q ∨ P∧R),
will prove that AΔC ⊆ (AΔB ∪ BΔC)
<thin>
Proving Q∧R → (¬P∨Q ∨ P∧R) is always true:
Premise: (Q∧R); Conclusion: (¬P∨Q ∨ P∧R).
Premise → Conclusion is always true if the following holds:
Whenever the Premise is true, also the Conclusion is true.
Assuming that Premise is true:
Q∧R ≡ 𝚻 ⟹ 
Q≡𝚻 ∧ R≡𝚻
Using that \in the Conclusion:
(¬P∨Q ∨ P∧R) ≡ (¬P∨𝚻 ∨ P∧𝚻) ≡ ¬P∨P ≡ 𝚻
Therefore the conclusion is dependent upon the premise, therefore 
__Q∧R → (¬P∨Q ∨ P∧R) ≡ 𝚻__ // (4)
<thin>
P, Q \and R are placeholders (defined above), so I'll use their "real" values:
Q∧R → (¬P∨Q ∨ P∧R) ≡
(A∪C)∩(<oline>A</oline>u<oline>C</oline>) → [<oline>B</oline>n(AuC)] ∪ [Bn(<oline>A</oline>u<oline>C</oline>)]
// Using (3):
AΔC ⊆ [<oline>B</oline>n(AuC)] ∪ [Bn(<oline>A</oline>u<oline>C</oline>)]
// Using (2):
__AΔC ⊆ (AΔB ∪ BΔC)__ // (5)
<thin>
Since it's given that:
AΔB⊆D ∧ BΔC⊆D
Using (1), it's equivalent to:
(AΔB ∪ BΔC) ⊆ D
And because of (5), we know that 
AΔC ⊆ (AΔB ∪ BΔC)
Together with the transience of ⊆, // X⊆Y \and Y⊆Z => X⊆Z
__AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__