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


#### Reflexivity: 𝑹:=relation(A) is reflexive if ∀a ∈ A(⟨a,a⟩ ∈ 𝑹)  
𝑹 is reflexive if every a in A satisfies ⟨a,a⟩∈𝑹. In other words:   
𝗜<sub>A</sub> ⊆ 𝑹  
A = { −1, 0, 1 }. Is the eq oblique contained in 𝑹?

properties  
- ⟺ 𝑹<sup>−1</sup> is reflexive
- → 𝑹 ⊆ 𝑹<sup>2</sup> (and 𝑹<sup>2</sup> is reflexive)
- if 𝑺 ⊆ 𝑹 → 𝑺 is reflexive
- 𝑺 is reflexive → both 𝑹∪𝑺 and 𝑹∩𝑺 are reflexive

examples
- 𝗨<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ A×A = 𝗨<sub>A</sub>)
- 𝗜<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ {⟨-1, −1⟩, ⟨0, 0⟩, ⟨1, 1⟩})
- ≤, ≥ <comment>// both have eq oblique</comment>

counter examples
- ≠ (which is 𝗨<sub>A</sub> − 𝗜<sub>A</sub>)
- ⊂, >, ∅
- a=−b ⋰


#### Anti-Reflexivity: 𝑹:=relation(A) is anti-reflexive iff ¬∃a ∈ A(⟨a,a⟩ ∈ 𝑹)  
𝑹 is reflexive if every a in A satisfies ⟨a,a⟩ ∉ 𝑹. In other words:  
𝗜<sub>A</sub> ∩ 𝑹 = ∅ (just 𝗜<sub>A</sub> ⊈ 𝑹 isn't enough; 𝗜<sub>A</sub> = {⟨1,1⟩, ⟨2,2⟩} ⊈ 𝑹 = {⟨1,1⟩, ⟨1,2⟩} but ⟨1,1⟩ ∈ 𝑹 so isn't anti-reflexive)

examples
- ≠, ⊂, >, ∅

counter examples
- 𝗨<sub>A</sub>, 𝗜<sub>A</sub>, a=−b, ≤, ≥


#### Symmetry: 𝑹:=relation(A) is symmetric iff 𝑹 = 𝑹<sup>−1</sup>  
𝑹 is reflexive if every ⟨x,y⟩ in 𝑹 satisfies ⟨y,x⟩ ∈ 𝑹.

examples
- ∅ (can't point at ⟨x,y⟩ and say ⟨y,x⟩ is not in ∅<sup>−1</sup>)
- 𝗨<sub>A</sub>, 𝗜<sub>A</sub>, a=−b, ≠

counter examples
- ≤, ≥, ⊂, >