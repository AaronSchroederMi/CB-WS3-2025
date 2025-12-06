#include "hsbi_runtime.h"

int main() {
    // Variablen mit Basisdatentypen (ohne Initialisierung)
    bool a;
    int b;
    char c;

    a = false;
    b = 7;
    c = 'a';

    print_bool(a);  // false
    print_int(b);   // 7
    print_char(c);  // 'a'


    // Variablen mit Basisdatentypen (mit Initialisierung)
    bool aa = true;
    int bb = 42;
    char cc = 'c';

    print_bool(aa);  // true
    print_int(bb);   // 42
    print_char(cc);  // 'c'

    return 0;
}
/* EXPECT:
0
7
a
1
42
c
*/
