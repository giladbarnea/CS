גלעד ברנע ת.ז. 203627310
# ממ"ן 13
## 1
**Find the power of each of the following sets**
---

### א
> The set of all real numbers A⊆(0,1) that when expanded as an infinite fraction, each digit appears adjacent to an equal digit.

First, A⊆(0,1) ⟹ |A|≤|(0,1)|.

On the other hand, function 𝒇:(0,1)→A, e.g: 𝒇(0.a<sub>1</sub>a<sub>2</sub>...) = 0.a<sub>1</sub>a<sub>1</sub>a<sub>2</sub>a<sub>2</sub>... is one-to-one, therefore:
|(0,1)|≤|A|
Considering Cantor-Bernstein: |(0,1)|≤|A| ⟹ **|(0,1)| = |A| = ℵ**

---
###ב
> ((ℕ)×(0,1)) ∩ (ℝ×ℚ)

The intersection means that:  
⟨x,y⟩∈((ℕ)×(0,1)) ∩ (ℝ×ℚ) ⟺ ⟨x,y⟩∈((ℕ)×(0,1)) ∧ ⟨x,y⟩∈(ℝ×ℚ)
Expanding the pairs:  
y∈(0,1) ∧ x∈ℕ ∧ y∈ℚ ∧ x∈ℝ ⟹ x∈ℕ∩ℝ = ℕ ∧ y∈(0,1)∩ℚ
Therefore:  
ℕ×((0,1)∩ℚ) = (ℕ×(0,1))∩(ℝ×ℚ)
Since (0,1)∩ℚ ⊆ Q ⟹ **|(0,1)∩ℚ|≤|ℚ|=ℵ0**

On the other hand, 
{1/1, 1/2, 1/3, ...} ⊆ (0,1)∩ℚ ⟹ ℵ0 = |{1/1, 1/2, 1/3, ...}| ≤ |(0,1)∩ℚ|
From Cantor-Bernstein: |(0,1)∩ℚ|=ℵ0
Since:  
- A×A is countable if A is countable  
- |A|=ℵ0 if A is countable and infinite  
- A×A is equivalent to A if A is countable and infinite
⟹
|ℕ×((0,1)∩ℚ)|=ℵ0 
Finally:
**|(ℕ×(0,1)∩(ℝ×ℚ))|=ℵ0**

---
###ג
> 𝓟((0,1)\\𝑰) where 𝑰 is the set of all real, irrational numbers (I think it's marked 𝕁?)

(0,1)\\𝑰 is the set of all rational numbers contained in the interval (0,1).
So (0,1)\\𝑰 = (0,1)∩ℚ ⟹ |(0,1)\\𝑰|=ℵ0 ⟹ |(0,1)\\𝑰|=ℵ0.
Since:  
- given |A|=ℵ0, then 𝓟(A) is not countable and 𝓟(A) ~ {0,1}<sup>A</sup>  
- if n is a positive natural number, then {0,1,...,n}<sup>ℕ</sup> ~ ℝ, so |{0,1,...,n}<sup>ℕ</sup>| = ℵ
⟹
𝓟((0,1)\\𝑰) ~ {0,1}<sup>(0,1)\\𝑰</sup> ⟹ **|𝓟((0,1)\\𝑰)|=ℵ**
---
###ד
> 𝓟((0,10**−10 )\\ℚ)

The power of (0,10**−10):
Since every non-degenerate interval is equivalent to ℝ, ⟹  
(0,10**−10) ~ ℝ ⟹ |(0,10**−10)|=ℵ.

The power of (0,10**−10)\\ℚ:
We know that for any two sets A and B:  
given B⊆A and |B|=ℵ0, the two sets satisfy |A\B|=|A| if A\B is infinite or A is non-countable (either is sufficient). 
Let A=(0,10**−10); B=(0,10**−10)∩ℚ. It follows that B⊆A and |B|=ℵ0, therefore |A\B|=|A|.
Since |A|=ℵ and A\B=(0,10**−10 )\\ℚ ⟹ |(0,10**−10 )\\ℚ|=ℵ.
Because |𝓟(A)|=2**|A| ⟹ |𝓟((0,10**−10 )\\ℚ)| = 2**|(0,10**-10)\\Q|=__2**ℵ__ 

--- 
%sets
## 2
> Let:  
> M = {A∈𝓟(ℕ) | |A|=ℵ0 ∧ |~A|=ℵ0}  
> K = {A∈𝓟(ℕ) | |~A|=ℵ0}
> Prove or disprove the following:

/%sets

---
### א
> |K|=ℵ0

The statement is false.
%sets
Let X be the set of all odd, natural numbers. It follows that for every set Y that satisfies Y⊆X, the following holds: ~X⊆~Y.
~Y⊆ℕ and is infinite ⟹ |~Y|=ℵ0 ⟹ every subset of X belongs to K.  
/%sets  
Therefore 𝓟(X)⊆K ⇒ |𝓟(X)|≤|K|.
Since |X|=ℵ0 ⟹ |𝓟(X)| = 2**|X| = 2<sup>ℵ0</sup>, therefore 2<sup>ℵ0</sup>≤|K| ⟹ __|K|≠ℵ0__ 

---
### ב
> |M|=ℵ0

The statement is false.
Let:  
X be the set of all odd, natural numbers  
Y = {4n+2 | n∈ℕ}  
Z is a set that satisfies Y≤Z≤X (and so is infinite and contained in ℕ)

%sets
It follows that |~Z|=ℵ0 and that |Z|=ℵ0 ⟹ Z∈M ⟹ {Z∈𝓟(ℕ) | Y⊆Z⊆X}⊆M.
/%sets
For convenience, let Q={Z∈𝓟(ℕ) | Y⊆Z⊆X}.
Let's define function 𝒇 from Q to 𝓟(X\\Y), defined by 𝒇(B)=B\\F. 𝒇 is onto and one-to-one ⟹ Q ~ 𝓟(X\\Y).
Since X\\Y={4n | n∈ℕ} ⟹ |X\\Y|=ℵ0, therefore |𝓟(X\\Y)|=2<sup>ℵ0</sup>=ℵ ⟹ |Q|=ℵ.
Q is contained in M therefore |M|≥ℵ therefore __|M|≠ℵ0__

---
### ג
> |𝓟(ℕ)\\K|=ℵ0

The statement is true.
K is the set of all sets partial to ℕ whose complement is infinite, therefore 𝓟(ℕ)\\K is the set of all set partial to ℕ whose complement is __finite__.
Let X(ℕ) be the set of all finite sets that are partial to ℕ. In other words, the union of all finite sets of natural numbers.
A finite set S of natural numbers is countable, because S is contained in a set of natural numbers ranging from ∅ to n, where n if the greatest element in S.
Therefore X(ℕ) consist of a countable number of countable sets ⟹ X(ℕ) itself is countable.
Moreover, X(ℕ) is infinite ⟹ |X(ℕ)|=ℵ0. // (0)
%sets
Let's define function 𝒇 from 𝓟(ℕ)\\K to X(ℕ), defined by 𝒇(A)=~A. 𝒇 is onto and one-to-one ⟹ |𝓟(ℕ)\\K| = |X(ℕ)|. // (1)
/%sets
Combining (0) and (1), we get __|𝓟(ℕ)\\K|=ℵ0__
 
---
### ד
> |𝓟(ℕ)\\M|=ℵ0

The statement is true.
Based on the definition of M:  
𝓟(ℕ)\\M is the set of all sets partial to ℕ which are finite or that their complement is finite.
Let X = {A∈𝓟(ℕ) | |A|=ℵ0} ⟹  
𝓟(ℕ)\\X is the set of all finite sets partial to ℕ ⟹  
𝓟(ℕ)\\M = (𝓟(ℕ)\\K)∪(𝓟(ℕ)\\X).
Because 𝓟(ℕ)\\X is the same as X(ℕ) from gimel, |𝓟(ℕ)\\X|=ℵ0.  
Considering the conclusion of gimel (|𝓟(ℕ)\\K|=ℵ0), and given that the union of sets with cardinality of ℵ0 has itself a cardinality of ℵ0 ⟹ __|𝓟(ℕ)\\M|=ℵ0__.

---

## 3
> Let:  
> A = {A𝑖 | 𝑖∈ℕ} where Ai⊆ℕ, A𝑖≠A𝑗, A𝑖∩A𝑗=∅, for all 𝑖,𝑗∈ℕ, 𝑖≠𝑗  
> B is a set of non-empty, open intervals in ℝ such that no two intervals overlap  
> C is an uncountable, infinite set of open intervals in ℝ.
> Prove: 

---
###א 
> |B|≤|A|

First let's prove that |A|=ℵ0.
Function 𝒇:ℕ→A defined by 𝒇(𝑖)=A𝑖 for all 𝑖∈ℕ is onto.  
Also, because 𝑖,𝑗∈ℕ and 𝑖≠𝑗, the following holds: A𝑖≠A𝑗 ⟹ 𝒇(𝑖)≠𝒇(𝑗) ⟹ A~ℕ ⟹ |A|=ℵ0.
Let B={B𝛼 | 𝛼∈I} where I is a matching set of indices.  
Let 𝒇 be a function from I→B defined by 𝒇(𝛼)=B𝛼 for all 𝛼∈I.  
𝒇 is one-to-one and onto ⟹ |B|=|I|.
Because every non-empty open interval contains all rational numbers, and because for all 𝛼∈I, set B𝛼 is a non-empty open interval ⟹ for all 𝛼∈I there exists a number q𝛼∈ℚ such that q𝛼∈B𝛼.
Let 𝒈 be a function from I→ℚ defined by 𝒇(𝛼)=q𝛼 for all 𝛼∈I.  
To prove (by negation) that 𝒈 is one-to-one, let's assume that 𝒇(𝛼)=𝒇(𝛽) for 𝛼,b∈I where 𝛼≠𝛽 ⟹ q𝛼 = q𝛽.  
q𝛼∈B𝛼 and q𝛽∈B𝛽 ⟹ q𝛼∈(B𝛼∩B𝛽) which negates the definition of B (no two intervals in B overlap).
Having concluded that 𝒈 is one-to-one ⟹ |I|≤|ℚ|=ℵ0 ⟹ |B|≤ℵ0 ⟹ __|B|≤|A|__.

---
###ב 
> Prove that two intervals I,J∈C exist such that |I∩J|=|ℝ|

We have concluded in alef that if two intervals are don't overlap, then their set is countable.
Combining the definition of C together with the aforementioned conclusion, it follows that there exist I,J∈C such that I∩J≠∅.
Let I=(a,b); J=(c,d). Assuming a≤c, since I∩J≠∅ ⟹ c﹤b ⟹ I∩J=(c,b).
We know that every non-degenerate is equivalent to ℝ, therefore __|I∩J|=|(b,c)|=|ℝ|__.