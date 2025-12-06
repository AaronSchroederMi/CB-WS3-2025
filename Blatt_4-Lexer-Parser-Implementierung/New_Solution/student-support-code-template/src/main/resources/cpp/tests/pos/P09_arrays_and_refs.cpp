#include "hsbi_runtime.h"

void inc(int &v) { v = v + 1; }

int main() {
    int a[5];
    int i = 0;
    while (i < 5) { a[i] = i; i = i + 1; }

    print_int(a[0]);  // 0
    print_int(a[1]);  // 1
    print_int(a[2]);  // 2
    print_int(a[3]);  // 3
    print_int(a[4]);  // 4

    int &r = a[2];
    inc(r);
    print_int(a[2]);  // 3

    inc(a[4]);
    print_int(a[4]);  // 5

    return 0;
}
/* EXPECT:
0
1
2
3
4
3
5
*/
