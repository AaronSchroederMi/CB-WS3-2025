# Blatt 02: CFG

## A2.1: PDA (3P)

Erstellen Sie einen deterministischen PDA, der die Sprache

$$
L = \lbrace w \in \lbrace a, b, c \rbrace^* \; | \; w \; \text{hat doppelt so viele a's wie c's} \rbrace
$$

akzeptiert.

> DPDA: $`P = (Q, \Sigma, \Gamma, \delta, q_0, \perp, F)`$  
> $`Q = \{q_0, q_1\}`$  
> $`\Sigma = \{ a, b, c\}`$  
> $`\Gamma = \{A, B, C\}`$  
> $`\delta = ...`$ <-- Siehe Abb.  
> $`q_0 = q_0`$  
> $`F = \{q_1\}`$  

[![](https://mermaid.ink/img/pako:eNptUl1PgzAU_SvNXeYTm5QNGDUxwkx80Rd9MySmW8sg8mUpcRP34h_xv_lLbFnZFiMJ5Jx7zzm3wO1gXTEOBDaC1im6f4xLhMZj9CSpkOgC3VU0R43c5Vm5QbqnMUdvLzZKsjwnoyRh6rIaKapXTkZsNjN48p4xmRKn3mqbMtx0qElpzQliq3yyzsR6f-jgU-dYPR1D8iYujRBNJtfoU5NRMA-uLPTz_YUu9VPXPvWUQWuftOtBF4ZGufrjpAOPhig17J-pKinsc0LDIs1MYniGdT0yeKnw0kyJzrCuR0sTpEn_TqYZnvPTaeISLPWrMgZEipZbUHBRUE2h6_sgU17wGIiCjCe0zWUMcblXtpqWz1VVDE5RtZsUSELzRrG2ZupL32ZU7UFxrApeMi6WVVtKIN7c7UOAdLAF4vveFGMncD08WwS27VmwA-I4ztRfzH0Xuz72fHXvLfjox9pT15kFeOHP_cDGAfZUHGeZrMTDYQX7Tdz_AmrFxQk?type=png)](https://mermaid.live/edit#pako:eNptUl1PgzAU_SvNXeYTm5QNGDUxwkx80Rd9MySmW8sg8mUpcRP34h_xv_lLbFnZFiMJ5Jx7zzm3wO1gXTEOBDaC1im6f4xLhMZj9CSpkOgC3VU0R43c5Vm5QbqnMUdvLzZKsjwnoyRh6rIaKapXTkZsNjN48p4xmRKn3mqbMtx0qElpzQliq3yyzsR6f-jgU-dYPR1D8iYujRBNJtfoU5NRMA-uLPTz_YUu9VPXPvWUQWuftOtBF4ZGufrjpAOPhig17J-pKinsc0LDIs1MYniGdT0yeKnw0kyJzrCuR0sTpEn_TqYZnvPTaeISLPWrMgZEipZbUHBRUE2h6_sgU17wGIiCjCe0zWUMcblXtpqWz1VVDE5RtZsUSELzRrG2ZupL32ZU7UFxrApeMi6WVVtKIN7c7UOAdLAF4vveFGMncD08WwS27VmwA-I4ztRfzH0Xuz72fHXvLfjox9pT15kFeOHP_cDGAfZUHGeZrMTDYQX7Tdz_AmrFxQk)

Beschreiben Sie Schritt für Schritt, wie der PDA die Eingaben *bcaba*
und *bccac* abarbeitet.

> ### bcaba <-- erfüllt anforderungen
> ``` 
> b;        Stack: []               Node: q_0       Regel: b, ⟂ / ⟂
> bc;       Stack: [A, A]           Node: q_0       Regel: c, ⟂ / AA⟂
> bca;      Stack: [A]              Node: q_0       Regel: a, A / ε
> bcab;     Stack: [A]              Node: q_0       Regel: b, A / A
> bcaba;    Stack: []               Node: q_0       Regel: a, A / ε
> 
> Kann zu endknoten wechseln        Node: Stop      Regel: ε, ⟂ / ⟂
> ```
> ### bccac <-- erfüllt anforderungen nicht
> ```
> b;        Stack: []               Node: q_0       Regel: b, ⟂ / ⟂
> bc;       Stack: [A, A]           Node: q_0       Regel: c, ⟂ / AA⟂
> bcc;      Stack: [A, A, A, A]     Node: q_0       Regel: c, A / AAA
> bcca;     Stack: [A, A, A]        Node: q_0       Regel: a, A / ε
> bccac;    Stack: [A, A, A, A, A]  Node: q_0       Regel: c, A / AAA
> 
> Kann nicht zu endknoten wechseln (Stack nicht leer)
> ```

> Anmerkung: Ich Habe die vermutung das ein DPDA hier nicht möglich ist (proved wrong)

## A2.2: Akzeptierte Sprache (2P)

Ist der folgenden PDA deterministisch? Warum (nicht)?

> Antwort: Nein, in q_3 gibt es auf den input "d" zwei Stack operationen

$`q_4`$ sei der akzeptierende Zustand.

$$
\begin{eqnarray}
\delta(q_0,a, \perp) &=& (q_0, A\perp)           \nonumber \\
\delta(q_0,a, A) &=& (q_0, AA)                   \nonumber \\
\delta(q_0,b, A) &=& (q_1, BA)                   \nonumber \\
\delta(q_1,b, B) &=& (q_1, BB)                   \nonumber \\
\delta(q_1,c, B) &=& (q_2, \epsilon)             \nonumber \\
\delta(q_2,c, B) &=& (q_2, \epsilon)             \nonumber \\
\delta(q_2,d, A) &=& (q_3, \epsilon)             \nonumber \\
\delta(q_3,d, A) &=& (q_3, \epsilon)             \nonumber \\
\delta(q_3,d, A) &=& (q_3, AA)                   \nonumber \\
\delta(q_3,\epsilon, \perp) &=& (q_4, \epsilon)  \nonumber
\end{eqnarray}
$$

Zeichnen Sie den Automaten. Geben Sie das 7-Tupel des PDa an. Welche
Sprache akzeptiert er?

[![](https://mermaid.ink/img/pako:eNqN00tu2zAQANCrDCZIV5IjkrIlcFHEboFu0k27KwgUtElZQvQrRaFJHF-g9-gRco7cISepqJ_RBjGqjTTkmxG_B9xVSiPHvZF1CjdfRAlweQlfrTQW3sGnSubQ2Ps8K_fg-ty3hh_fA0iyPOcXSaK6x2usqW41v1CMjd_-z0zZlNP6zqV1CdcHaFJZaw67zOw8yOVW5xwEdn0Cj4MiZxSZFT2j6KzYGcVGNcjwJNU291_pUCDMfFgdqxtRjjMD338Pjy6QHrz8-g1XsO5eY8PahWsXPLqZvk7aDmYzGTIZ4sxENo5s3ia7gTw_jYROhP4nGQajhsHMhk2GzWWen4ZZCnFi4WkxT_CvWuqflegLT1miRK87gplCbk2rPSy0KaQL8dD3o011oQW6_VA6kW1uBYry2KXVsvxWVcWUaap2nyJPZN50UVurbqs-ZrI738XcanSptPlQtaVFzkIW9lWQH_AOOWHRYhmGNKZsSYMViT28R-7TBQlYEEU0JFEU0_jo4UP_W7IIVjRihK5WASURjZceapXZynwerlZ_w45_AEUPC1g?type=png)](https://mermaid.live/edit#pako:eNqN00tu2zAQANCrDCZIV5IjkrIlcFHEboFu0k27KwgUtElZQvQrRaFJHF-g9-gRco7cISepqJ_RBjGqjTTkmxG_B9xVSiPHvZF1CjdfRAlweQlfrTQW3sGnSubQ2Ps8K_fg-ty3hh_fA0iyPOcXSaK6x2usqW41v1CMjd_-z0zZlNP6zqV1CdcHaFJZaw67zOw8yOVW5xwEdn0Cj4MiZxSZFT2j6KzYGcVGNcjwJNU291_pUCDMfFgdqxtRjjMD338Pjy6QHrz8-g1XsO5eY8PahWsXPLqZvk7aDmYzGTIZ4sxENo5s3ia7gTw_jYROhP4nGQajhsHMhk2GzWWen4ZZCnFi4WkxT_CvWuqflegLT1miRK87gplCbk2rPSy0KaQL8dD3o011oQW6_VA6kW1uBYry2KXVsvxWVcWUaap2nyJPZN50UVurbqs-ZrI738XcanSptPlQtaVFzkIW9lWQH_AOOWHRYhmGNKZsSYMViT28R-7TBQlYEEU0JFEU0_jo4UP_W7IIVjRihK5WASURjZceapXZynwerlZ_w45_AEUPC1g)

> $`L = \{w \in \{a, b, c, d\} | \; w = (a)^n(b)^m(c)^m(d)^*, n > 0, m ≥ 0\}`$

## A2.3: Kontextfreie Sprache (2P)

Welche Sprache generiert die folgende kontextfreie (Teil-) Grammatik?

$$
G = (\lbrace \text{Statement}, \text{Condition}, \ldots \rbrace, \lbrace \text{"if"}, \text{"else"}, \ldots \rbrace, P, \text{Statement})
$$

mit

$$
\begin{eqnarray}
P = \lbrace &&                                                                                                           \nonumber \\
&\text{Statement}& \rightarrow \text{"if" Condition Statement} \; | \; \text{"if" Condition Statement "else" Statement}  \nonumber \\
&\text{Condition}& \rightarrow \ldots                                                                                    \nonumber \\
\rbrace                                                                                                                  \nonumber
\end{eqnarray}
$$

> Die Sprache beschreibt if Statements die in einander ge-nested werden können. Auch kann den if Statements jeweils else Statements angehängt werden  
> - Bsp.: "if" [Condition] Statement
> - Bsp.: "if" [Condition] Statement "else" Statement
> - Bsp.: "if" [Condition] "if" [Condition] Statement "else" Statement

Ist die Grammatik mehrdeutig? Warum (nicht)?

> Ja diese Sprache ist mehrdeutig, siehe Beispiel:  
> `"if" [Condition] "if" [Condition] Statement "else" Statement`
>
[![](https://mermaid.ink/img/pako:eNp1k01rhDAQhv-KzNld8m02h1LYXnvqrQgl1uyuoMnWRmgr_vdmt1ohmFt8xnkmvjgjvLvagIJzr6-X0mbZxxvS2W73EA5YR4DEgM6ALoDFgMdAxKCIgQxgmfM4troyrcpevPamM9ZPfyW8lprTzMjKjs7WjW-cnRZ50sQ2TDxtEmlTsZZM-2lmKrcb5i-slqyrCJAY0BnQBbAY8BiI2FHEQFbrTdJZVxtZV-ms0ya2YeJpk0ibimoz6-0GyMP_3dSgfD-YHDrTd_r2COOtrQR_CW-WoMKxNic9tL6E0k6h7artq3Pd0tm74XwBddJhZA7DtQ4znhodlqf7p72xtemPbrAeFMGFvFtAjfAFCnO-55QhTqTE6CAKmsM3KIbRPrADRYxixBFhUw4_98FoL6Q4CIaFZIgIQXIwISTXP_8t7n1_p18s1TTu?type=png)](https://mermaid.live/edit#pako:eNp1k01rhDAQhv-KzNld8m02h1LYXnvqrQgl1uyuoMnWRmgr_vdmt1ohmFt8xnkmvjgjvLvagIJzr6-X0mbZxxvS2W73EA5YR4DEgM6ALoDFgMdAxKCIgQxgmfM4troyrcpevPamM9ZPfyW8lprTzMjKjs7WjW-cnRZ50sQ2TDxtEmlTsZZM-2lmKrcb5i-slqyrCJAY0BnQBbAY8BiI2FHEQFbrTdJZVxtZV-ms0ya2YeJpk0ibimoz6-0GyMP_3dSgfD-YHDrTd_r2COOtrQR_CW-WoMKxNic9tL6E0k6h7artq3Pd0tm74XwBddJhZA7DtQ4znhodlqf7p72xtemPbrAeFMGFvFtAjfAFCnO-55QhTqTE6CAKmsM3KIbRPrADRYxixBFhUw4_98FoL6Q4CIaFZIgIQXIwISTXP_8t7n1_p18s1TTu)

## A2.4: Kontextfreie Grammatik (3P)

Entwickeln Sie eine kontextfreie Grammatik für die Sprache

$$
L = \lbrace a^ib^jc^k \; | \; i = j \lor j = k \rbrace
$$

> $`G = \{\{A, B, C, D, E\}, \{a, b, c\}, P, S\}`$  
> 
> $`P = \{`$  
> S $`\rightarrow`$ AC | B  
> A $`\rightarrow`$ aAb | E  
> B $`\rightarrow`$  aB | D  
> C $`\rightarrow`$  cC | E  
> D $`\rightarrow`$ bDc | E  
> E $`\rightarrow \; \epsilon`$  
> $`\}`$

Zeigen Sie, dass die Grammatik mehrdeutig ist. Entwickeln Sie einen PDA
für diese Sprache.

> Mehrdeutigkeit... Ja siehe "" und "" bzw. wenn $`i = j = k`$

[![](https://mermaid.ink/img/pako:eNqVlMuOmzAUhl_FOtF0RVICBIIrVQ2XdNNuyq5i48QmoHITGHWmSTZ9kb5bn6TGOCVBYqqyQMfH339uBp_hWFEGGE4NqVP06UtcIvT0hCJOGo7eoI8VyVHLX_KsPKF-r7cZilCS5TleJAkVj9bypvrG8IKaprKX3zPKU2zUz70o-nBGbUpqhtExa44aysmB5RjFEMVw7YndLLFThDdLeIrwZwlfEcEsESginCVCRexnib0iIl7VI0QP-XLatABiQBIe581ZK9VouXyPLr25cC33nYZ-__qJ3sp377zs4lJOTHCz5J3PEx5PCv1RqGTkJvGUhijeu2UaCnylJgl6Q2TvXyVdghGcljCJFQzIK435wuNLyX6Q7G_pp6KLPJIB8tEMEg774X8E6c3DMDFJ3085HJHjtMXwoUUVZUB8xRxUf_792PYPMf2HtGIGoIm_OKOAedMxDQrWFKRfwlmeI_CUFSyG_gukLCFdzmOIy6uQ1aT8WlXFTdlU3SkFnJC8FauupuLbDDIirojir7dhJWWNX3UlB2xZlimjAD7DM2BDX690c-vYruFatuPYGw1eAK9da2VvNoZh2ZZjrg3duGrwQybWV1vDcFzH0u2tsXUtXcRjNONV83m4n-Q1df0DRkpsJw?type=png)](https://mermaid.live/edit#pako:eNqVlMuOmzAUhl_FOtF0RVICBIIrVQ2XdNNuyq5i48QmoHITGHWmSTZ9kb5bn6TGOCVBYqqyQMfH339uBp_hWFEGGE4NqVP06UtcIvT0hCJOGo7eoI8VyVHLX_KsPKF-r7cZilCS5TleJAkVj9bypvrG8IKaprKX3zPKU2zUz70o-nBGbUpqhtExa44aysmB5RjFEMVw7YndLLFThDdLeIrwZwlfEcEsESginCVCRexnib0iIl7VI0QP-XLatABiQBIe581ZK9VouXyPLr25cC33nYZ-__qJ3sp377zs4lJOTHCz5J3PEx5PCv1RqGTkJvGUhijeu2UaCnylJgl6Q2TvXyVdghGcljCJFQzIK435wuNLyX6Q7G_pp6KLPJIB8tEMEg774X8E6c3DMDFJ3085HJHjtMXwoUUVZUB8xRxUf_792PYPMf2HtGIGoIm_OKOAedMxDQrWFKRfwlmeI_CUFSyG_gukLCFdzmOIy6uQ1aT8WlXFTdlU3SkFnJC8FauupuLbDDIirojir7dhJWWNX3UlB2xZlimjAD7DM2BDX690c-vYruFatuPYGw1eAK9da2VvNoZh2ZZjrg3duGrwQybWV1vDcFzH0u2tsXUtXcRjNONV83m4n-Q1df0DRkpsJw)
