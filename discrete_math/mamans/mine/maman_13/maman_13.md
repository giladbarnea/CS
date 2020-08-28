גלעד ברנע ת.ז. 203627310
# ממ"ן 13
## 1
**Find the power of each of the following sets**
---

### א
> The set of all real numbers A⊆(0,1) that when expanded as an infinite fraction, each digit appears adjacent to an equal digit.

First, A⊆(0,1) ⟹ |A|≤|(0,1)|.

On the other hand, function 𝒇:(0,1)→A, e.g: 𝒇(0.a<sub>1</sub>a<sub>2</sub>...) = 0.a<sub>1</sub>a<sub>1</sub>a<sub>2</sub>a<sub>2</sub>... is one-to-one (injective), therefore:
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