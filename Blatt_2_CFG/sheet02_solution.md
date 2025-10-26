# Blatt 02: CFG

## A2.1: PDA (3P)

Erstellen Sie einen deterministischen PDA, der die Sprache

``` math
L = \lbrace w \in \lbrace a, b, c \rbrace^* \; | \; w \; \text{hat doppelt so viele a's wie c's} \rbrace
```

akzeptiert.

> DPDA: $`P = (Q, \Sigma, \Gamma, \delta, q_0, \perp, F)`$  
> $`Q = \{q_0, Stop\}`$  
> $`\Sigma = \{ a, b, c\}`$  
> $`\Gamma = \{A, B, C\}`$  
> $`\delta = ...`$ <-- Siehe Abb.  
> $`q_0 = q_0`$  
> $`F = \{Stop\}`$  

[![](https://mermaid.ink/img/pako:eNpdUstu2zAQ_JXFGulJdmRZCmMWKCo7QC_JpbkFBALapCyhlKhSFJLU9qU_0n_rl4SkpKQND-QMd2aXjz3iXguJFA-GtyXcfmcNwMUF3FtuLHyCb5or6OyLqpoD-JjHEn4-xlBUStFZUQg3os4a_UPSmVitRjx_qoQtadI-e5szfD1CV_JWUthXZh-B4jupKDB0MYZnr7q3un2XiZ2af5B6AUMI4veTWtmNNWA-_wKn2Tpdf47g75_fcOnnU0jMmv9EnuwnUZ67ddzJA89HtvFsxFuHQ27Pd_8UGHk-aXeDbzNi79t6zCfPZjTxwfSWlA_GSeyNm0BO_uDDDViDkfuuSiC1ppcR1tLU3FM8hjjaUtaSoX8wIQveK8uQNWdna3nzoHU9OY3uDyXSgqvOsb4V7ilvKu56oX7bNbIR0mx131ikqzQlIQvSIz4jTQhZxGR5tSJJtlyTJM4ifEG6jhfLzN0pJml6TeLr7Bzhr1A3XpAsS13ETSROlkmEUlRWm7uhDUM3nl8BGZHG4Q?type=png)](https://mermaid.live/edit#pako:eNpdUstu2zAQ_JXFGulJdmRZCmMWKCo7QC_JpbkFBALapCyhlKhSFJLU9qU_0n_rl4SkpKQND-QMd2aXjz3iXguJFA-GtyXcfmcNwMUF3FtuLHyCb5or6OyLqpoD-JjHEn4-xlBUStFZUQg3os4a_UPSmVitRjx_qoQtadI-e5szfD1CV_JWUthXZh-B4jupKDB0MYZnr7q3un2XiZ2af5B6AUMI4veTWtmNNWA-_wKn2Tpdf47g75_fcOnnU0jMmv9EnuwnUZ67ddzJA89HtvFsxFuHQ27Pd_8UGHk-aXeDbzNi79t6zCfPZjTxwfSWlA_GSeyNm0BO_uDDDViDkfuuSiC1ppcR1tLU3FM8hjjaUtaSoX8wIQveK8uQNWdna3nzoHU9OY3uDyXSgqvOsb4V7ilvKu56oX7bNbIR0mx131ikqzQlIQvSIz4jTQhZxGR5tSJJtlyTJM4ifEG6jhfLzN0pJml6TeLr7Bzhr1A3XpAsS13ETSROlkmEUlRWm7uhDUM3nl8BGZHG4Q)

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

> Anmerkung: Ich Habe die vermutung das ein DPDA hier nicht möglich ist

## A2.2: Akzeptierte Sprache (2P)

Ist der folgenden PDA deterministisch? Warum (nicht)?

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

## A2.3: Kontextfreie Sprache (2P)

Welche Sprache generiert die folgende kontextfreie (Teil-) Grammatik?

``` math
G = (\lbrace \text{Statement}, \text{Condition}, \ldots \rbrace, \lbrace \text{"if"}, \text{"else"}, \ldots \rbrace, P, \text{Statement})
```

mit

``` math
\begin{eqnarray}
P = \lbrace &&                                                                                                           \nonumber \\
&\text{Statement}& \rightarrow \text{"if" Condition Statement} \; | \; \text{"if" Condition Statement "else" Statement}  \nonumber \\
&\text{Condition}& \rightarrow \ldots                                                                                    \nonumber \\
\rbrace                                                                                                                  \nonumber
\end{eqnarray}
```

Ist die Grammatik mehrdeutig? Warum (nicht)?

## A2.4: Kontextfreie Grammatik (3P)

Entwickeln Sie eine kontextfreie Grammatik für die Sprache

``` math
L = \lbrace a^ib^jc^k \; | \; i = j \lor j = k \rbrace
```

Zeigen Sie, dass die Grammatik mehrdeutig ist. Entwickeln Sie einen PDA
für diese Sprache.

------------------------------------------------------------------------

<img src="https://licensebuttons.net/l/by-sa/4.0/88x31.png" width="10%">

Unless otherwise noted, this work is licensed under CC BY-SA 4.0.

<blockquote><p><sup><sub><strong>Last modified:</strong> 102c219 (homework: finalize B02 (#374), 2025-10-03)<br></sub></sup></p></blockquote>
