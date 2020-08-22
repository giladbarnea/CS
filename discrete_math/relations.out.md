
#### 𝑹: {⟨x,y⟩ for ⟨x,y⟩ ∈ A<sup>2</sup> if x𝑹y}


#### 𝑻·𝑹: {⟨a,c⟩ | ∃b ∈ B (⟨a,b⟩ ∈ 𝑻 ∧ ⟨b,c⟩ ∈ 𝑹)}   
![](../Documents/CompSci/discrete_math/relations2.gif)


#### 𝑹<sup>2</sup>: a𝑹<sup>2</sup>c ⟺ {⟨a,c⟩ | ∃b ∈ A (⟨a,b⟩ ∈ 𝑹 ∧ ⟨b,c⟩ ∈ 𝑹)}  
> an ordered pair ⟨a,c⟩∈𝑹<sup>2</sup> means there's a "middle" b∈B that satisfies ⟨a,b⟩∈𝑹 and ⟨b,c⟩∈𝑹  

![](./relations1.gif)  
<br>

_Examples_

- (a=−b)<sup>2</sup> = 𝑰<sub>ℝ</sub>

- ⟨a,b⟩ ∈ 𝑹<sup>2</sup> ⟺ ⟨a,c⟩,⟨c,b⟩ ∈ 𝑹  

#### Empty ∅<sub>A</sub>

> 𝑹:=rel(A×B) = ∅  
> No pair in A×B satisfies ⟨a,b⟩ ∈ 𝑹


_Properties_

- 𝑺·∅<sub>A</sub> = ∅

- anti−symmetric

- symmetric ?


_Examples_

- {⟨x,y⟩ ∈ ℕ<sup>2</sup> | x+y﹤x}

<br>
#### Identity 𝑰<sub>A</sub>

_Properties_

- 𝑹·𝑰<sub>A</sub> = 𝑹


#### Reflexivity

> 𝑹:=rel(A) is reflexive if ∀a ∈ A(⟨a,a⟩ ∈ 𝑹)  
> 𝑹 is reflexive if every a in A satisfies ⟨a,a⟩∈𝑹. In other words:  
> 𝑰<sub>A</sub> ⊆ 𝑹


A = { −1, 0, 1 }. Is ⋱ contained in 𝑹?

𝑹 = lambda a,b: a⊙b; `all(R(x,x) for x in A)`?


_Properties_

- ⟺ 𝑹<sup>−1</sup> is reflexive

- → 𝑹 ⊆ 𝑹<sup>2</sup> (and 𝑹<sup>2</sup> is reflexive)

- → 𝑹⊆𝑹<sup>2</sup>

- if 𝑺⊆𝑹 then 𝑺 is reflexive

- if 𝑺 is reflexive then both 𝑹∪𝑺 and 𝑹∩𝑺 are reflexive

_Examples_

- 𝑼<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ A×A = 𝑼A)

- 𝑰<sub>A</sub>: ∀a ∈ A(⟨a,a⟩ ∈ {⟨−1, −1⟩, ⟨0, 0⟩, ⟨1, 1⟩})

- ≤, ≥ <span class="comment">both contain ⋱</span>

_Counter Examples_

- ≠ (which is 𝑼<sub>A</sub> − 𝑰A)

- <, >, ∅<sub>A</sub>

- a=−b ⋰


#### Antireflexivity

> 𝑹:=rel(A) is antireflexive iff ¬∃a ∈ A(⟨a,a⟩ ∈ 𝑹)  
> 𝑹 is antireflexive if every a in A satisfies ⟨a,a⟩ ∉ 𝑹. In other words:  
> 𝑰<sub>A</sub> ∩ 𝑹 = ∅ <span class="comment">just 𝑰<sub>A</sub> ⊈ 𝑹 isn't enough; 𝑰<sub>A</sub> = {⟨1,1⟩, ⟨2,2⟩}⊈𝑹 = {⟨1,1⟩, ⟨1,2⟩} but ⟨1,1⟩∈𝑹 so isn't antireflexive</span>


_Examples_

- ≠, <, >, ∅<sub>A</sub>


_Counter Examples_

- 𝑼<sub>A</sub>, 𝑰<sub>A</sub>, a=−b ⋰, ≤, ≥


#### Symmetry

> 𝑹:=rel(A) is symmetric iff 𝑹 = 𝑹<sup>−1</sup>  
> 𝑹 is symmetric if every ⟨x,y⟩ in 𝑹 satisfies ⟨y,x⟩ ∈ 𝑹  
> ∀x∀y(⟨x,y⟩∈𝑹 → ⟨y,x⟩∈𝑹)  
> 𝑹 = lambda a,b: a⊙b; `all(rel(y,x) for x,y in R)`?


_Properties_

- if 𝑺 is symmetric then both 𝑹∪𝑺 and 𝑹∩𝑺 are reflexive

- if 𝑺 is symmetric then 𝑹\𝑺 is symmetric

_Examples_

- ∅<sub>A</sub> <span class="comment">can't point at ⟨x,y⟩ and say ⟨y,x⟩ is not in ∅<sup>−1</sup></span>

- 𝑼<sub>A</sub>, 𝑰<sub>A</sub>, a=−b ⋰, ≠

_Counter Examples_

- ≤, ≥, <, >


#### Antisymmetry

> 𝑹:=rel(A) is antisymmetric iff 𝑹 ∩ 𝑹<sup>−1</sup> = ∅  <span class="comment">𝑹 ∩ 𝑹<sup>−1</sup> = ∅ means there can't be a ⟨x,x⟩</span>  
> 𝑹 is antisymmetric if every ⟨x,y⟩ in 𝑹 satisfies ⟨y,x⟩ ∉ 𝑹  
> ∀x∀y((x,y) ∈ 𝑹 → (y,x) ∉ 𝑹)


_Properties_

- → 𝑹 is antireflexive

- → 𝑹<sup>−1</sup> is antisymmetric

- if 𝑺⊆𝑹 then 𝑺 is antisymmetric

- if 𝑺∪𝑻 is antisymmetric then both 𝑺 and 𝑻 are antisymmetric

- → 𝑹∩𝑺 is antisymmetric

- [if 𝑹 is antireflexive and transitive then it's asymmetric and antisymmetric](https://proofwiki.org/wiki/Antireflexive_and_Transitive_Relation_is_Asymmetric)

- No set is a ⊂ of itself, so ⊂ is antisymmetric <span class="comment">lesson 7 00:27:40</span>


_Examples_

- <, >, ∅<sub>A</sub>

- b﹥a<sup>2</sup>


_Counter Examples_

- ≠, ≤, ≥, 𝑼<sub>A</sub>, 𝑰<sub>A</sub>, a=−b ⋰, ≠

- b﹤a<sup>2</sup> <span class="comment">⟨3,4⟩ and ⟨4,3⟩ are symmetric</span>


#### Weak Antisymmetry

> 𝑹 ∩ 𝑹<sup>−1</sup> ⊆ 𝑰<sub>A</sub>  
> ∀x∀y(⟨x,y⟩ ∈ 𝑹 ∧ ⟨y,x⟩ ∈ 𝑹 → x=y)  
> if both ⟨x,y⟩ ∈ 𝑹 and ⟨y,x⟩ ∈ 𝑹 it's only because they're equal  
> for x,y ∈ A: if x≠y and ⟨x,y⟩ ∈ 𝑹 then must ⟨y,x⟩ ∉ 𝑹


A<sub>S</sub> vs WA<sub>S</sub>: A<sub>S</sub> requires every pair's opposite to not be in 𝑹, whereas WA<sub>S</sub> requires the same only for pairs that x=y


_Examples_

- 𝑰<sub>A</sub>


#### Transitivity

> 𝑹<sup>2</sup> ⊆ 𝑹  
> ∀x∀y∀z((𝑹(x,y) ∧ 𝑹(y,z)) → 𝑹(x,z))  
> Every (x,y,z)∈A that satisfy ⟨x,y⟩∈𝑹 and ⟨y,z⟩∈𝑹 also satisfy ⟨x,z⟩∈𝑹  
> If you see an x that leads to y that leads to z, then expect x to lead to z <span class="comment">this is why 𝑹<sup>2</sup> ⊆ 𝑹</span>


_Properties_

- if 𝑻 is symmetric and antisymmetric then it's also transitive

_Examples_

- A={1,2,3}; 𝑹 = {⟨__1__,2⟩, ⟨2,__3__⟩, __⟨1,3⟩__} ⇒ 𝑹<sup>2</sup> = {⟨1,3⟩} ⊆ 𝑹

- A={1,2,3}; 𝑻 = {⟨1,2⟩} ⇒ 𝑻<sup>2</sup> = ∅ ⊆ 𝑻

- 𝑾 = {⟨1,1⟩} ⇒ 𝑾<sup>2</sup> = {⟨1,1⟩} ⊆ 𝑾

- 𝑰<sub>A</sub>

- ∅<sub>A</sub>

- 𝑼<sub>A</sub> <span class="comment">if ⟨a,b⟩ ∈ A<sup>2</sup> and ⟨b,a⟩ ∈ A<sup>2</sup> then ⟨a,c⟩ ∈ A<sup>2</sup></span>

- if |A|﹥1 then ≠ is trans

- <, ≤


_Counter Examples_

- 𝑷={⟨1,2⟩, ⟨2,1⟩} ⇒ 𝑷<sup>2</sup> = {⟨1,1⟩, ⟨2,2⟩} ⊈ 𝑷 <span class="comment">iow: 1 leads to 2 leads to 1, but ⟨1,1⟩ ⊈ 𝑷</span>

- ∃x∃y∃z(𝑹(x,y) ∧ 𝑹(y,z) ∧ ¬𝑹(x,z))


------


#### Equivalance

> 𝑹 over A is equivalence iff 𝑹 is reflexive, symmetric and transitive


_Examples_

- 𝑼<sub>A</sub>, 𝑰<sub>A</sub>, equality

- "Has the same absolute value" on the set of real numbers

- if A=∅ then ∅<sub>A</sub> is symmetric, transitive and reflexive


_Counter Examples_

- ≥ <span class="comment">reflexive and transitive but not symmetric</span>

- if A≠∅ then ∅<sub>A</sub> is symmetric and transitive, but not reflexive


#### Connexivity

<span class="grey">lesson 7 00:06:00</span>

> 𝑹 over A is connexive iff ∀(x,y)∈A (x≠y → ⟨x,y⟩∈𝑹 ⋁ ⟨y,x⟩∈𝑹)


_Examples_

- Any two numbers ℕ


---
### Order

---

<span class="grey">lesson 7 00:00:00</span>

<br>
#### Partial Order

> 𝑹 over A (≤) is a partial order iff it's [antireflexive](#antireflexivity) and [transitive](#transitivity)


_Properties_

- [Antisymmetric](#antisymmetry) <span class="comment">because antireflexive and transitive</span>


_Examples_

- ⊂ over 𝓟(ℕ) <span class="comment">A∈𝓟(ℕ) is antisym because A⊄A, and trans because A⊂B⊂C ⇒ A⊂C</span>


???  
for all a, b, and c:  
- a ≤ a <span class="comment">reflex</span>  
- if a ≤ b and b ≤ a, then a = b <span class="comment">antisymm</span>  
- if a ≤ b and b ≤ c, then a ≤ c <span class="comment">trans</span>  

_Examples_

- equality  
???


#### Total Order

> Partial order and [connexive](#connexivity) (aka "linearly ordered")  
> ∀(x,y)∈A (x≠y → ⟨x,y⟩∈𝑹 ⊻ ⟨y,x⟩∈𝑹) <span class="comment">note the xor. verify</span>


_Examples_

- < over ℕ <span class="comment">also over ℝ?</span>


_Counter Examples_

- if A≠∅ then 𝑰<sub>A</sub> isn't total order because for all a∈A: a=a


---
### Partitions

---


#### Partition of A is a set of non-empty, non-overlapping subsets of A whose union = A

_Properties_

- every a∈A is in exactly one block

- no block contains ∅

- union of blocks = A

- intersection of any two blocks = ∅

- → A is finite ⇒ rank of P is |X| − |P| ?


_Examples_

- {A} is partition of A <span class="comment">trivial</span>

- ∅'s only partition is ∅

- {1,2,3} has five partitions: {{1},{2},{3}}, {{1, 2}, {3}}, {{1, 3},{2}}, {{1},{2, 3}}, {{1, 2, 3}}


_Counter Examples_

- not partitions of {1,2,3}: 
  - {{}, {1,3}, {2}} <span class="comment">contains ∅</span>
  - {{1, 2}, {2, 3}} <span class="comment">2 exists in more than one block</span>
  - {{1}, {2}} <span class="comment">no block contains 3</span>


---


#### Equivalence Class: {x∈𝑺 | x≡a} where a∈𝑺

> Given 𝑹 is an equivalence relation on 𝑺, the equivalence class of an element a in 𝑺 is the set {x∈𝑺 | ⟨x,a⟩∈𝑹}  
> ⟦a⟧ = {b|a𝑹b} = {b|⟨a,b⟩ ∈ 𝑹} <span class="comment">all elements in 𝑺 that when paired with a, exist in 𝑹</span>  
> In other words: going over 𝑹, the elements in ⟦a⟧ are all the elements that a is paired with


_Properties_

- ⋃ of all equivalence classes = 𝑺 ?

- a ∈ ⟦a⟧ <span class="comment">every element exists in its equivalence class</span>

- the items in each equivalence class of 𝑺 exist only in their equivalence class ?

- every possible pair of eq. classes is zar ? 


_Examples_

- 𝑿 = all cars; relation ≡<sub>𝑿</sub> = "has the same color as"; one particular equivlance class consists of all green cars

- Relation ≡<sub>ℤ</sub> is ⟨a,b⟩∈≡<sub>ℤ</sub> ⟺ (a − b)%2==0 ⟹ two equivalence classes: even numbers and odd numbers

- 𝑺 = {1,2,3,4,5}
  - ≡<sub>𝑺</sub> = {⟨1,__1__⟩, ⟨1,__2__⟩, ⟨1,__3__⟩, ⟨2,2⟩, ⟨3,3⟩, ⟨4,4⟩, ⟨5,5⟩, ⟨2,1⟩, ⟨2,3⟩, ⟨3,2⟩, ⟨3,1⟩}  
  - ⟦1⟧ = __{1, 2, 3}__ <span class="comment">everything that 1 is related to</span>
  - ⟦2⟧ = {2, 1, 3}
  - ⟦3⟧ = {3, 2, 1} <span class="comment">note that ⟦1⟧ ≡ ⟦2⟧ ≡ ⟦3⟧</span>
  - ⟦4⟧ = {4}
  - ⟦5⟧ = {5}