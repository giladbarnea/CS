#### 𝑹: {⟨x,y⟩ for ⟨x,y⟩ ∈ A<sup>2</sup> if x𝑹y}


#### 𝑻·𝑹: {⟨a,c⟩ | ∃b ∈ B (⟨a,b⟩ ∈ 𝑻 ∧ ⟨b,c⟩ ∈ 𝑹)} 

![](./relations2.gif)


#### 𝑹<sup>2</sup>: aR2c ⟺ {⟨a,c⟩ | ∃b ∈ A (⟨a,b⟩ ∈ 𝑹 ∧ ⟨b,c⟩ ∈ 𝑹)}

an ordered pair ⟨a,c⟩∈R2 means there's a "middle" b∈B that satisfies ⟨a,b⟩∈𝑹 and ⟨b,c⟩∈𝑹

![](./relations1.gif)

properties
- whw



examples
- (a=−b)<sup>2</sup> = 𝗜<sub>ℝ</sub>
- ⟨a,b⟩ ∈ 𝑹<sup>2</sup> ⟺ ⟨a,c⟩,⟨c,b⟩ ∈ 𝑹


#### Reflexivity: 𝑹:=relation(A) is reflexive if ∀a∈A(⟨a,a⟩∈𝑹)

𝑹 is reflexive if every item ∈ A satisfies ⟨a,a⟩∈𝑹.

A = { −1, 0, 1 }. Is the eq oblique contained in 𝑹?

properties
- ⟺ 𝗜<sub>A</sub> ⊆ 𝑹
- ⟺ 𝑹<sup>−1</sup> is reflexive
- → 𝑹 ⊆ 𝑹<sup>2</sup> (and 𝑹<sup>2</sup> is reflexive)
- if 𝑺 ⊆ 𝑹 → 𝑺 is reflexive
- 𝑺 is reflexive → both 𝑹∪𝑺 and 𝑹∩𝑺 are reflexive

examples
- 𝗨<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ A×A = 𝗨<sub>A</sub>)
- 𝗜<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ {⟨−1, −1⟩, ⟨0, 0⟩, ⟨1, 1⟩})
- ≤, ≥ <comment>// both have eq oblique</comment>

counter examples
- ≠ (which is 𝗨<sub>A</sub> − 𝗜<sub>A</sub>)
- ⊂, >, ∅
- a=−b (forward oblique)
