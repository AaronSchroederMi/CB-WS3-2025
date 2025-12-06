#include "hsbi_runtime.h"

class P {
public:
    P(int v) { (*this).v = v; } // "this" und "*this" sollen im Klassenkontext erkannt werden
    void set(int v) { (*this).v = v; } // "this" und "*this" sollen im Klassenkontext erkannt werden
    int v;
};

int main() {
    P p = P(3);
    print_int(p.v); // 3
    p.set(8);
    print_int(p.v); // 8

    return 0;
}
/* EXPECT:
3
8
*/
