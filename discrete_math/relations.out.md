%ignore and
#### 𝑹: {⟨x,y⟩ for ⟨x,y⟩ ∈ A<sup>2</sup> if x𝑹y}   

#### 𝑻·𝑹: {⟨a,c⟩ | ∃b ∈ B (⟨a,b⟩ ∈ 𝑻 ∧ ⟨b,c⟩ ∈ 𝑹)}   
![](./relations2.gif)  

#### 𝑹<sup>2</sup>: a𝑹<sup>2</sup>c ⟺ {⟨a,c⟩ | ∃b ∈ A (⟨a,b⟩ ∈ 𝑹 ∧ ⟨b,c⟩ ∈ 𝑹)}  
an ordered pair ⟨a,c⟩∈𝑹<sup>2</sup> means there's a "middle" b∈B that satisfies ⟨a,b⟩∈𝑹 and ⟨b,c⟩∈𝑹  
![](./relations1.gif)  
properties  
- whw

examples
- (a=−b)<sup>2</sup> = 𝗜<sub>ℝ</sub>
- ⟨a,b⟩ ∈ 𝑹<sup>2</sup> ⟺ ⟨a,c⟩,⟨c,b⟩ ∈ 𝑹


#### Empty ∅<sub>A</sub>

properties  
- 𝑺·∅<sub>A</sub> = ∅



examples
- {⟨x,y⟩ ∈ ℕ<sup>2</sup> | x+y﹤x}


#### Identity 𝗜<sub>A</sub>

properties  
- 𝑹·𝗜<sub>A</sub> = 𝑹


#### Reflexivity: 𝑹:=relation(A) is reflexive if ∀a ∈ A(⟨a,a⟩ ∈ 𝑹)  
𝑹 is reflexive if every a in A satisfies ⟨a,a⟩∈𝑹. In other words:   
𝗜<sub>A</sub> ⊆ 𝑹  
A = { −1, ∅, 1 }. Is ⋱ contained in 𝑹?

properties  
- ⟺ 𝑹<sup>−1</sup> is reflexive
- → 𝑹 ⊆ 𝑹<sup>2</sup> (and 𝑹<sup>2</sup> is reflexive)
- if 𝑺⊆𝑹 then 𝑺 is reflexive
- if 𝑺 is reflexive then both 𝑹∪𝑺 and 𝑹∩𝑺 are reflexive

examples
- 𝗨<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ A×A = 𝗨<sub>A</sub>)
- 𝗜<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ {⟨−1, −1⟩, ⟨∅, ∅⟩, ⟨1, 1⟩})
- ≤, ≥ <comment>// both contain ⋱</comment>

counter examples
- ≠ (which is 𝗨<sub>A</sub> − 𝗜<sub>A</sub>)
- <, >, ∅<sub>A</sub>
- a=−b ⋰


#### Anti-Reflexivity: 𝑹:=relation(A) is anti-reflexive iff ¬∃a ∈ A(⟨a,a⟩ ∈ 𝑹)  
𝑹 is reflexive if every a in A satisfies ⟨a,a⟩ ∉ 𝑹. In other words:

𝗜<sub>A</sub> ∩ 𝑹 = ∅ <comment>// just 𝗜<sub>A</sub> ⊈ 𝑹 isn't enough; 𝗜<sub>A</sub> = {⟨1,1⟩, ⟨2,2⟩} ⊈ 𝑹 = {⟨1,1⟩, ⟨1,2⟩} but ⟨1,1⟩ ∈ 𝑹 so isn't anti-reflexive</comment>

examples
- ≠, <, >, ∅<sub>A</sub>

counter examples
- 𝗨<sub>A</sub>, 𝗜<sub>A</sub>, a=−b ⋰, ≤, ≥


#### Symmetry: 𝑹:=relation(A) is symmetric iff 𝑹 = 𝑹<sup>−1</sup>  
𝑹 is symmetric if every ⟨x,y⟩ in 𝑹 satisfies ⟨y,x⟩ ∈ 𝑹 <comment>// assuming both x and y exist in A</comment>

∀x∀y((x,y) ∈ 𝑹 → (y,x) ∈ 𝑹)



properties
- if 𝑺 is symmetric then both 𝑹∪𝑺 and 𝑹∩𝑺 are reflexive

examples
- ∅<sub>A</sub> <comment>// can't point at ⟨x,y⟩ and say ⟨y,x⟩ is not in ∅<sup>−1</sup></comment>
- 𝗨<sub>A</sub>, 𝗜<sub>A</sub>, a=−b ⋰, ≠

counter examples
- ≤, ≥, <, >


#### Anti-Symmetry: 𝑹:=relation(A) is anti-symmetric iff 𝑹 ∩ 𝑹<sup>−1</sup> = ∅

𝑹 is anti-symmetric if every ⟨x,y⟩ in 𝑹 satisfies ⟨y,x⟩ ∉ 𝑹

∀x∀y((x,y) ∈ 𝑹 → (y,x) ∉ 𝑹)

𝑹 ∩ 𝑹<sup>−1</sup> = ∅ means there can't be a ⟨x,x⟩



properties
- → 𝑹 is anti-reflexive
- → 𝑹<sup>−1</sup> is anti-symmetric
- if 𝑺⊆𝑹 then 𝑺 is anti-symmetric
- → 𝑹∩𝑺 is anti-symmetric



examples
- <, >, ∅<sub>A</sub>
- b﹥a<sup>2</sup>



counter examples  
- ≠, ≤, ≥, 𝗨<sub>A</sub>, 𝗜<sub>A</sub>, a=−b ⋰, ≠
- b﹤a<sup>2</sup> <comment>// ⟨3,4⟩ and ⟨4,3⟩ are symmetric</comment>


#### Weak Anti-Symmetry: 𝑹 ∩ 𝑹<sup>−1</sup> ⊆ 𝗜<sub>A</sub>  
∀x∀y(⟨x,y⟩ ∈ 𝑹 ∧ ⟨y,x⟩ ∈ 𝑹 → x=y)

if both ⟨x,y⟩ ∈ 𝑹 and ⟨y,x⟩ ∈ 𝑹 it's only because they're equal

for x,y ∈ A: if x≠y and ⟨x,y⟩ ∈ 𝑹 then must ⟨y,x⟩ ∉ 𝑹



AS vs WAS: AS requires every pair's opposite to not be in 𝑹, whereas WAS requires the same only for pairs that x=y



examples
- 𝗜<sub>A</sub>


#### Transitivity: 𝑹<sup>2</sup> ⊆ 𝑹

Every (x,y,z) ∈ A that satisfy ⟨x,y⟩ ∈ 𝑹 and ⟨y,z⟩ ∈ 𝑹 also satisfy ⟨x,z⟩ ∈ 𝑹

If you see an x that leads to y that leads to z, then expect x to lead to z <comment>// this is why 𝑹<sup>2</sup> ⊆ 𝑹</comment>



examples
- A={1,2,3}; 𝑹 = {⟨__1__,2⟩, ⟨2,__3__⟩, __⟨1,3⟩__} ⇒ 𝑹<sup>2</sup> = {⟨1,3⟩} ⊆ 𝑹
- A={1,2,3}; 𝑻 = {⟨1,2⟩} ⇒ 𝑻<sup>2</sup> = ∅ ⊆ 𝑻
- 𝗜<sub>A</sub>
- ∅<sub>A</sub>
- 𝗨<sub>A</sub> <comment>// if ⟨a,b⟩ ∈ A<sup>2</sup> and ⟨b,a⟩ ∈ A<sup>2</sup> then ⟨a,c⟩ ∈ A<sup>2</sup></comment>
- if |A|﹥1 then ≠ is trans
- <, ≤



counter examples
- 𝑷={⟨1,2⟩, ⟨2,1⟩} ⇒ 𝑷<sup>2</sup> = {⟨1,1⟩, ⟨2,2⟩} ⊈ 𝑷 <comment>// iow: 1 leads to 2 leads to 1, but ⟨1,1⟩ ∉ 𝑷</comment>