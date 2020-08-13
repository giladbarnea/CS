גלעד ברנע ת.ז. 203627310
# ממ"ן 11
## 1

Prove: 

AΔB⊆D ∧ BΔC⊆D → AΔC⊆D

<div class="line"></div>

Since (X→Z) ∧ (Y→Z) ≡ (X∨Y)→Z, also:

(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D. Proof:

<div class="box">

(X→Z) ∧ (Y→Z)<br>Z∨¬X ∧ Z∨¬Y<br>Z∨<over>(X∨Y)</over><br>(X∨Y) → Z<br><br>AΔB⊆D ∧ BΔC⊆D<br>(x∈(AΔB) → x∈D) ∧ (x∈(BΔC) → x∈D)<br>x∈(AΔB ∪ BΔC) → x∈D<br>(AΔB ∪ BΔC) ⊆ D<br>Therefore:<br>(AΔB⊆D ∧ BΔC⊆D) ≡ (AΔB ∪ BΔC) ⊆ D <comment>(1)<br></div></comment>

<div class="line"></div>

I'll prove that AΔC ⊆ (AΔB ∪ BΔC), then it wo∪ld follow by transience that AΔC⊆C

<div class="thin-line"></div>

Expanding (AΔB ∪ BΔC):



(<over>B</over>∩A) ∪ (<over>B</over>∩C) ∪ (B∩<over>A</over>) ∪ (B∩<over>C</over>)

[<over>B</over>∩(A∪C)] ∪ [B∩(<over>A</over>∪<over>C</over>)]

__(AΔB ∪ BΔC) ≡ [<over>B</over>∩(A∪C)] ∪ [B∩(<over>A</over>∪<over>C</over>)]__ <comment>(2)</comment>

<br><br>

Expanding AΔC:

(A∩<over>C</over>) ∪ (<over>A</over>∩C)

(A∪C) ∩ (<over>A</over>∪<over>C</over>)

__AΔC ≡ (A∪C) ∩ (<over>A</over>∪<over>C</over>)__ <comment>(3)</comment>





<div class="thin-line"></div>



Define: 

P = B; Q = (A∪C); R = (<over>A</over>∪<over>C</over>)



So proving Q∧R → (¬P∨Q ∨ P∧R),

will prove that AΔC ⊆ (AΔB ∪ BΔC)

<div class="thin-line"></div>

Proving Q∧R → (¬P∨Q ∨ P∧R) is always tr∪e:

Premise: (Q∧R); Concl∪sion: (¬P∨Q ∨ P∧R).

Premise → Concl∪sio∩ is always tr∪e if the following holds:

Whenever the Premise is tr∪e, also the Concl∪sio∩ is tr∪e.

Assuming that Premise is tr∪e:

Q∧R ≡ 𝚻 ⟹ 

Q≡𝚻 ∧ R≡𝚻

Using that \i∩ the Concl∪sion:

(¬P∨Q ∨ P∧R) ≡ (¬P∨𝚻 ∨ P∧𝚻) ≡ ¬P∨P ≡ 𝚻

Therefore the concl∪sio∩ is dependent ∪po∩ the premise, therefore 

__Q∧R → (¬P∨Q ∨ P∧R) ≡ 𝚻__ <comment>(4)</comment>

<div class="thin-line"></div>

P, Q and R are placeholders (defined above), so I'll ∪se their "real" val∪es:

Q∧R → (¬P∨Q ∨ P∧R) ≡

(A∪C)∩(¬A∪¬C) → [¬B∩(A∪C)] ∪ [B∩(¬A∪¬C)]

<grey>Using (3):</grey>

AΔC ⊆ [¬B∩(A∪C)] ∪ [B∩(¬A∪¬C)]

<grey>Using (2):</grey>

__AΔC ⊆ (AΔB ∪ BΔC)__ <comment>(5)</comment>

<div class="thin-line"></div>

Since it's given that:

AΔB⊆D ∧ BΔC⊆D

Using (1), it's eq∪ivalent to:

(AΔB ∪ BΔC) ⊆ D

And because of (5), we know that 

AΔC ⊆ (AΔB ∪ BΔC)

Together with the transience of ⊆, <comment>X⊆Y and Y⊆Z ⇒ X⊆Z</comment>

__AΔB⊆D ∧ BΔC⊆D → AΔC⊆D__