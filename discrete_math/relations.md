#### 𝑹: {⟨x,y⟩ for ⟨x,y⟩ ∈ A2 if x𝑹y}

#### 𝑻*𝑹: {⟨a,c⟩ | ∃b ∈ B (⟨a,b⟩ ∈ 𝑻 ∧ ⟨b,c⟩ ∈ 𝑹)} 
![](./relations2.gif)

#### 𝑹2: aR2c ⟺ {⟨a,c⟩ | ∃b ∈ A (⟨a,b⟩ ∈ 𝑹 ∧ ⟨b,c⟩ ∈ 𝑹)}
an ordered pair ⟨a,c⟩∈R2 means there's a "middle" b∈B that satisfies ⟨a,b⟩∈𝑹 \and ⟨b,c⟩∈𝑹
![](./relations1.gif)
properties
- whw

examples
- (a=−b)2 = 𝗜ℝ
- ⟨a,b⟩ ∈ 𝑹2 ⟺ ⟨a,c⟩,⟨c,b⟩ ∈ 𝑹

#### Reflexivity: 𝑹:=relation(A) is reflexive if ∀a ∈ A(⟨a,a⟩ ∈ 𝑹)
𝑹 is reflexive if every item in A satisfies ⟨a,a⟩∈𝑹.
A = { -1, 0, 1 }. Is the eq oblique contained \in 𝑹?
properties
- ⟺ 𝗜A ⊆ 𝑹
- ⟺ 𝑹−1 is reflexive
- → 𝑹 ⊆ 𝑹2 (and 𝑹2 is reflexive)
- if 𝑺 ⊆ 𝑹 → 𝑺 is reflexive
- 𝑺 is reflexive → both 𝑹∪𝑺 \and 𝑹∩𝑺 are reflexive
examples
- 𝗨A: ∀a ∈ A(⟨a,a⟩ ∈ A×A = 𝗨A)
- 𝗜A: ∀a ∈ A(⟨a,a⟩ ∈ {⟨-1, -1⟩, ⟨0, 0⟩, ⟨1, 1⟩})
- ≤, ≥ // both have eq oblique
counter examples
- ≠ (which is 𝗨A − 𝗜A)
- <, >, ∅
- a=−b (forward oblique)

#### Anti-Reflexivity: 𝑹:=relation(A) is anti-reflexive if Exists a ∈ A(⟨a,a⟩ ∈ 𝑹)
