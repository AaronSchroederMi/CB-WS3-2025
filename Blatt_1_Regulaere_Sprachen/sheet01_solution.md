# Blatt 01: Reguläre Sprachen

## A1.1: Sprachen von regulären Ausdrücken (1P)

Welche Sprache wird von dem folgenden regulären Ausdruck beschrieben?

$`a\ +\ a\ (a\ +\ b)^*\ a`$

> Antwort: $`L = \{a\} \cup \{a(a+b)^*a\} = \{a\} + \{a(a+b)^*a\}`$
>
> Wörter dieser Sprache fangen immer mit $`a`$ an und enden auch immer mit $`a`$.  
> Zwischen diesem Anfang und Ende der Worte kann sich eine beliebige Kombination aus $`\{a, b\}`$ befinden.
>
> Worte: $`w = \{(a), (a, ,a), (a,b,a), (a,a,a), (a,ab,a), (a,ba,a), ...\}`$

## A1.2: Bezeichner in Programmiersprachen (3P)

Betrachten Sie eine Programmiersprache, in der die Bezeichner (= Namen
für Variablen, Funktionen, Klassen, Methoden, …) folgenden Aufbau haben:

- Alle Variablennamen beginnen mit **V** oder **v**
- Handelt es sich um globale Variablen, beginnen Sie mit **V**, lokale
  beginnen mit **v**
- Funktions- und Methodenparameter beginnen mit **p**, KLassenparameter
  (bei der Definition von Vererbung) beginnen mit **P**
- Weitere Bezeichner müssen mit einem Buchstaben (a-z, A-Z) beginnen
- Die folgenden Zeichen dürfen Buchstaben, Ziffern und ein Unterstrich
  sein
- Bezeichner dürfen nicht mit einem Unterstrich enden
- Alle Bezeichner müssen aus mindestens zwei Zeichen bestehen

Entwickeln Sie einen regulären Ausdruck, der den Aufbau der Bezeichner
beschreibt. Beachten Sie, dass Ihr regex alle zulässigen Bezeichner
beschreiben muss, aber keinen einzigen unzulässigen beschreiben darf.
Wählen Sie zwei Bezeichner aus der Sprache und zeigen Sie, wie sie vom
regex gemacht werden.

> Antwort:  $`(a-z + A-Z)(a-z + A-Z + 0-9 + \_)^*(a-z + A-Z + 0-9)`$
>
> Bsp. 1: "Va" (Erstes segment: "V", Zweites Segment: "", Drittes Segment: "a")  
> Bsp. 2: "pasz_9" (Erstes segment: "p", Zweites Segment: "pasz_", Drittes Segment: "9")

Entwickeln Sie einen DFA, der diese Bezeichner akzeptiert. Beachten Sie,
dass Ihr DFA alle zulässigen Bezeichner akzeptieren muss, aber keinen
einzigen unzulässigen akzeptieren darf. Wählen Sie zwei Bezeichner aus
der Sprache und zeigen Sie, wie sie vom Automaten zeichenweise gelesen
und akzeptiert werden.

> Antwort: DFA $`A = (Q, \Sigma, \delta, q_0, F)`$  
> $`Q = \{q_0,q_1,q_2,q_3,q_e\}`$
> - $`q_0`$: noch kein Zeichen  
> - $`q_1`$: ein gültiges Zeichen 
> - $`q_2`$: letzte Eingabe war gültig, kein Unterstrich und nicht die erste Eingabe 
> - $`q_3`$: letzte Eingabe war gültig, ein Unterstich und nicht die erste Eingabe 
> - $`q_e`$: Alles andere (Fehler) 
> $`\Sigma = \{a-z, A-Z,0-9,\_\}`$  
> $`\delta = `$
> 
> [![](https://mermaid.ink/img/pako:eNp9kstOwzAQRX_FmqqsnCp2mpcXSAgkNrCBXVUJWfWkiUji4Diiz38nTktRVBWvZub6XD9m9rDSCkHA2sgmJy9vy5qQ6ZS8W2ksuSPPWpaktduyqNfEaS5G8vXhk6woSzHJMtUv2lqjP1FMVBCcY--7UDYXvNmMOH7mlHLkheOSX3EO-7uOxdYl7mTPuycH6e3og7c49BU2FmqFBskCi1WOtdPxpLMRSH0vdSIfiR-uFIz33_Lj__nxa7_gZunawslA-64UCoQ1HVKo0FTSpbAffgZsjhUuQfShwkx2pV3Csj72WCPrhdbVL2l0t85BZLJs-6xrVP-ZT4XsW15dqgbdMx91V1sQcRwOJiD2sAHBkmA2j6IwYgFLEz6POIUtCM6j2ZzFfsJ4mCaBnx4p7IZj_VkY-YmfxAkL05inPKKAqrDavJ6mbRi64w-t78IJ?type=png)](https://mermaid.live/edit#pako:eNp9kstOwzAQRX_FmqqsnCp2mpcXSAgkNrCBXVUJWfWkiUji4Diiz38nTktRVBWvZub6XD9m9rDSCkHA2sgmJy9vy5qQ6ZS8W2ksuSPPWpaktduyqNfEaS5G8vXhk6woSzHJMtUv2lqjP1FMVBCcY--7UDYXvNmMOH7mlHLkheOSX3EO-7uOxdYl7mTPuycH6e3og7c49BU2FmqFBskCi1WOtdPxpLMRSH0vdSIfiR-uFIz33_Lj__nxa7_gZunawslA-64UCoQ1HVKo0FTSpbAffgZsjhUuQfShwkx2pV3Csj72WCPrhdbVL2l0t85BZLJs-6xrVP-ZT4XsW15dqgbdMx91V1sQcRwOJiD2sAHBkmA2j6IwYgFLEz6POIUtCM6j2ZzFfsJ4mCaBnx4p7IZj_VkY-YmfxAkL05inPKKAqrDavJ6mbRi64w-t78IJ)  
> $`q_0 = q_0`$  
> $`F = q_2`$
> 
> Bsp. 1: `q_0 -\V\-> q_1 -\a\-> q_2 (Ende)`  
> Bsp. 2: `q_0 -\p\-> q_1 -\a\-> q_2 -\s\-> q_2 -\z\-> q_2 -\_\-> q_3 -\9\-> q_2 (Ende)`

Entwickeln Sie eine reguläre Grammatik, die diese Bezeichner generiert.
Beachten Sie, dass Ihre Grammatik alle zulässigen Bezeichner generieren
können muss, aber keinen einzigen unzulässigen generieren darf. Wählen
Sie zwei Bezeichner aus der Sprache und zeigen Sie die Ableitungsbäume
dazu.

> Antwort: formale Grammatik $`G = (N, T, P, S)`$  
> $`N = \{S, B, C\}`$  
> $`T = \{a, b, c\}`$  
> $`P`$  
> $`S`$ ist der Startzustand  
> 
> $`a = \{a-z, A-Z\}`$   
> $`b = \{a-z, A-Z, 0-9, \_\}`$  
> $`c = \{a-z, A-Z, 0-9\}`$  
> 
> P =  
> $`S --> aB`$  
> $`B --> bB | cC`$  
> $`C --> \epsilon`$
>
> Bsp. 1: `S -\V\-> B -\a\-> C -\\-> epsilon (Ende), S -> aB -> cC -> epsilon`  
> Bsp. 2: `S -\p\-> B -\a\-> B -\s\-> B -\z\-> B -\_\-> B -\9\-> C -\\-> epsilon (Ende), S -> aB -> bB -> bB -Bb -> -> bB cC -> epsilon`

## A1.3: Gleitkommazahlen in Programmiersprachen (2P)

Recherchieren Sie zunächst den Aufbau von Gleitkommazahlen in Python und
Java.

> Java Bsp: 3F, .4d, +.7, .6, 7.f, .3e-5

> Python: Siehe PDF

Erstellen Sie für jede der beiden Programmiersprachen reguläre
Ausdrücke, DFAs und reguläre Grammatiken wie in Aufgabe A1.2.
Verifizieren Sie Ihre Lösungen wie in Aufgabe A1.2. Vorgaben, die sich
auf Längen oder Werte von Teilen der Zahlen beziehen, ignorieren Sie
bitte.

> Java:
> 
> **regulärer Ausdruck** =
> $`(+ | - | \epsilon) \; \Big( ((0-9)^+ (.(0-9)^*)) + (.(0-9)^+) \Big) ((e+E)(+ | - | \epsilon)(0-9)^+)? (f+F+d+D+\epsilon)`$
> 
> **DFA:** $`A = (Q, \Sigma, \delta, q_0, F)`$  
> $`Q = \{q_0, q_1, q_2, q_3, q_4, q_5, q_6, q_7, q_8, q_9\}`$  
> $`\Sigma = \{e, E, f, F, d, D, 0-9, ., +, -\}`$  
> $`\delta = `$  
> [![](https://mermaid.ink/img/pako:eNqdk0tv4yAQgP8Kmqp7WRxhDLbh0Etfl-1le6ssrVDBiVU_sgSrjzT_fSGJu6FKK7U-zXzMN4MRrOF-0AYkzK1aLtCv31WP0OkpunXKOvQDXQ-qRSv33Db9HIW1EBv09w9BddO28qSutf_wytnhwcgTnWX7OHlstFtIunw61Mq9pnUQ3zSq6Kea-J7Gv6exr2n_j8yZVUjC6STJGXoliXj1WRrBWUA0Qj8xSgLNqn7H02N-euCzqTI7VpnFk3aQRpV8wizCLIIGo8tA84jWGF1hpDG6CGtiapTH_1JEdN--nIr5-80cwHdT-QdTdwfvBx2fUH6oAfa3vdEgnR0Nhs7YToUU1tum4BamMxVIH2pTq7F1FVT9xmtL1d8NQzeZdhjnC5C1alc-G5faX4CLRvmn1L1Ra3pt7Pkw9g5kUQi-7QJyDU8gczETBSU8o0VelowwDM8gaZrOCOVciELkLM0Y32B42c4ls5JRmvKc-RUiCMkxGN24wd7snvH2NW_-AXhOJ3E?type=png)](https://mermaid.live/edit#pako:eNqdk0tv4yAQgP8Kmqp7WRxhDLbh0Etfl-1le6ssrVDBiVU_sgSrjzT_fSGJu6FKK7U-zXzMN4MRrOF-0AYkzK1aLtCv31WP0OkpunXKOvQDXQ-qRSv33Db9HIW1EBv09w9BddO28qSutf_wytnhwcgTnWX7OHlstFtIunw61Mq9pnUQ3zSq6Kea-J7Gv6exr2n_j8yZVUjC6STJGXoliXj1WRrBWUA0Qj8xSgLNqn7H02N-euCzqTI7VpnFk3aQRpV8wizCLIIGo8tA84jWGF1hpDG6CGtiapTH_1JEdN--nIr5-80cwHdT-QdTdwfvBx2fUH6oAfa3vdEgnR0Nhs7YToUU1tum4BamMxVIH2pTq7F1FVT9xmtL1d8NQzeZdhjnC5C1alc-G5faX4CLRvmn1L1Ra3pt7Pkw9g5kUQi-7QJyDU8gczETBSU8o0VelowwDM8gaZrOCOVciELkLM0Y32B42c4ls5JRmvKc-RUiCMkxGN24wd7snvH2NW_-AXhOJ3E)
>
> $`q_0 = q_0`$  
> $`F = \{q_4, q_5, q_8, q_9\}`$
> 
> **formale Grammatik:** $`G = (N, T, P, S)`$  
> $`N = \{S, A, B, C, D, E, F, G, H, I\}`$  
> $`T = \{a, b, c, d, e\}`$  
> $`P`$  
> $`S`$ ist der Startzustand
> 
> $`a = \{0-9\}`$  
> $`b = \{+, -\}`$  
> $`c = \{.\}`$  
> $`d = \{E, e\}`$  
> $`e = \{f, F, d, D\}`$  
> 
> P =  
> $`S --> aA | bB | cC`$  
> $`A --> aA | cD`$  
> $`B --> aA | cC`$  
> $`C --> aE`$  
> $`D --> aD | dF | eI | \epsilon`$  
> $`E --> aE | dF | eI | \epsilon`$  
> $`F --> aH | bG`$  
> $`G --> aH`$  
> $`H --> eI | \epsilon`$  
> $`I --> \epsilon`$  
> 
> fehler "5f" (alles ohne Punkt wird nicht abgedeckt)

## A1.4: Mailadressen? (1P)

Warum ist der folgende regex ungeeignet für die Verarbeitung von
Mailadressen?

$`(a-z)^+@(a-z).(a-z)`$

> Antwort: Allgemein passt der Regex nicht zu der Form die E-Mails annehmen
> - z.b. kann eine E-Mail durchaus Zahlen vor dem "@" haben  
> - z.b. können Sonderzeichen sowohl vor wie, auch nach dem Komma auftreten  
> - z.b. wird nur ein Zeichen nach dem '@' und dem '.' jeweils zugelassen   
> - etc

Bitte beachten Sie, dass die Schreibweise a-z nicht unserer Definition
genügt. Eigentlich müsste jedes Zeichen aufgeführt werden:

$`a + b + c + c + \ldots + z`$ ist besser, aber immer noch nicht
richtig. Warum?

> Antwort: Zum einen sind es zwei 'c' statt einem 'd', wobei das wahrscheinlich nicht der Punkt war.  
> Die $`a + b + c + ... + z`$ variante ist allerdings um fair zu sein auch in der Literatur weitverbreitet.
> 
> Das Problem bei beiden Ansetzen ist, das sie potenziell nicht eindeutig sind.

Anmerkung: Diese Darstellung wird ab jetzt akzeptiert.

Verbessern Sie den gegebenen regulären Ausdruck.

> Verbesserung:  
> 
> $` A = \{\,A,B,\dots,Z,\;a,b,\dots,z,\;0,1,\dots,9,\;.,!,\#,\$,\%,\&,' ,*,+,-,/,=,?,\text{^},_,\;\text{``},\{,|,\},\sim\,\} `$ 
> 
> $`B = \{\text{"},(,),,,:,;,<,>,@,[,\text{\\},]\}`$
> 
> Vereinfacht und nicht vollständig
> $`(A)^+@(A)^+.(A)^+`$
> 
> Vereinfacht und zu vollständig
> $`(A + B)^+@(A + B)^+.(A + B)^+`$


## A1.5: Der zweitletzte Buchstabe (1P)

Entwickeln Sie einen DFA, der nur Wörter über
$`\Sigma = \lbrace 1,2,3 \rbrace`$ akzeptiert, deren zweitletztes
Zeichen dasselbe ist wie das zweite.

> Siehe PDF

## A1.6: Sprache einer regulären Grammatik (2P)

Welche Sprache generiert die folgende Grammatik?

> Antwort: $`\Sigma = {a, b, c, d} \;\;\;\;\; L=a(b+c)^*(dc(b+c)^*)^*d(a+b)`$

$$
\begin{eqnarray}
S &\rightarrow& a A                      \nonumber \\
A &\rightarrow& d B \ | \ b A \ | \ c A  \nonumber \\
B &\rightarrow& a C \ | \ b C \ | \ c A  \nonumber \\
C &\rightarrow& \epsilon                 \nonumber
\end{eqnarray}
$$

Können Sie einen regulären Ausdruck oder einen DFA dafür angeben?

[![](https://mermaid.ink/img/pako:eNptkk1TwyAQhv8Ks516SjMpBAwcvOiMF73ozcmMQwtpMiYhEjK2tv3vQvphO8rp3Zd92GVhC0ujNAhYWdmV6OklbxGaTtGrk9ahG_RoZI16t6mrdoXCXtAafb4nqKjqWkyKQvkV9c6aDy0mipCjnn1VypUCd-tLjBwxpQJ4xrDE_2AB_O3H6T4EofRsdod2cuf1_JDlxcFUwcRX1iJCy6tUfMGTK2vx1zqhEPkRVQqEs4OOoNG2kSGE7dgluFI3OgfhpdKFHGqXQ97uPdbJ9s2Y5kRaM6xKEIWsex8NnfIXe6ikn39zdq1ulbb3ZmgdCE4YHU8BsYU1CEx4nGJMM4w5SXlCWQQbEPOMxxlLk9uEYMwyRtk-gu-xcBJTmvnUlFJOKU0Zj0Cryhn7fHj88Q_sfwD3waFV?type=png)](https://mermaid.live/edit#pako:eNptkk1TwyAQhv8Ks516SjMpBAwcvOiMF73ozcmMQwtpMiYhEjK2tv3vQvphO8rp3Zd92GVhC0ujNAhYWdmV6OklbxGaTtGrk9ahG_RoZI16t6mrdoXCXtAafb4nqKjqWkyKQvkV9c6aDy0mipCjnn1VypUCd-tLjBwxpQJ4xrDE_2AB_O3H6T4EofRsdod2cuf1_JDlxcFUwcRX1iJCy6tUfMGTK2vx1zqhEPkRVQqEs4OOoNG2kSGE7dgluFI3OgfhpdKFHGqXQ97uPdbJ9s2Y5kRaM6xKEIWsex8NnfIXe6ikn39zdq1ulbb3ZmgdCE4YHU8BsYU1CEx4nGJMM4w5SXlCWQQbEPOMxxlLk9uEYMwyRtk-gu-xcBJTmvnUlFJOKU0Zj0Cryhn7fHj88Q_sfwD3waFV)
