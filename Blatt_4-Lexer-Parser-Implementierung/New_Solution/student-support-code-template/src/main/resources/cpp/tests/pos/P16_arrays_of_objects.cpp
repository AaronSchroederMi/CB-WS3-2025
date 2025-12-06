#include "hsbi_runtime.h"

class D {
public:
    D() { value = 0; }
    D(int x) { value = x; }
    void inc() { value = value + 1; }
    int value;
};

int main() {
    D arr[3];
    arr[0].value = 10;
    arr[1] = D(20);
    arr[2] = arr[1];

    arr[0].inc();
    arr[1].inc();
    arr[2].inc();

    print_int(arr[0].value); // 11
    print_int(arr[1].value); // 21
    print_int(arr[2].value); // 21

    return 0;
}
/* EXPECT:
11
21
21
*/
