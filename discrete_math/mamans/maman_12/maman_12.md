גלעד ברנע ת.ז. 203627310
# ממ"ן 11
## 1
Prove: 
AΔB⊆D ∧ BΔC⊆D → AΔC⊆D
<line>
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
I'll prove that AΔC ⊆ (AΔB ∪ BΔC), then it wo∪ld follow by transience that AΔC⊆C
<thin>
Expanding (AΔB ∪ BΔC):
%sets
(¬B∩A) ∪ (¬B∩C) ∪ (B∩¬A) ∪ (B∩¬C)
[¬Bn(A∪C)] ∪ [Bn(¬A∪¬C)]
__(AΔB ∪ BΔC) ≡ [¬Bn(A∪C)] ∪ [Bn(¬Au¬C)]__ // (2)



Expanding AΔC:
(An¬C) ∪ (¬AnC)
(A∪C) n (¬Au¬C)
__AΔC ≡ (A∪C) n (¬Au¬C)__ // (3)

/%sets
<thin>
%sets
Define: 
P = B; Q = (A∪C); R = (¬Au¬C)
/%sets
So proving Q∧R → (¬P∨Q ∨ P∧R),
will prove that AΔC ⊆ (AΔB ∪ BΔC)
<thin>
Proving Q∧R → (¬P∨Q ∨ P∧R) is always tr∪e:
Premise: (Q∧R); Concl∪sion: (¬P∨Q ∨ P∧R).
Premise → Concl∪sion is always tr∪e if the following holds:
Whenever the Premise is tr∪e, also the Concl∪sion is tr∪e.
Assuming that Premise is tr∪e:
Q∧R ≡ 𝚻 ⟹ 
Q≡𝚻 ∧ R≡𝚻
Using that \in the Concl∪sion:
(¬P∨Q ∨ P∧R) ≡ (¬P∨𝚻 ∨ P∧𝚻) ≡ ¬P∨P ≡ 𝚻
Therefore the concl∪sion is dependent ∪pon the premise, therefore 
__Q∧R → (¬P∨Q ∨ P∧R) ≡ 𝚻__ // (4)
<thin>
P, Q \and R are placeholders (defined above), so I'll ∪se their "real" val∪es:
Q∧R → (¬P∨Q ∨ P∧R) ≡
(A∪C)∩(¬Au¬C) → [¬Bn(A∪C)] ∪ [Bn(¬Au¬C)]
// Using (3):
AΔC ⊆ [¬Bn(A∪C)] ∪ [Bn(¬Au¬C)]
// Using (2):
__AΔC ⊆ (AΔB ∪ BΔC)__ // (5)
<thin>
Since it's given that:
AΔB⊆D ∧ BΔC⊆D
Using (1), it's eq∪ivalent to:
(AΔB ∪ BΔC) ⊆ D
And because of (5), we know that 
AΔC ⊆ (AΔB ∪ BΔC)
Together with the transience of ⊆, // X⊆Y \and Y⊆Z ⇒ X⊆Z
__AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__