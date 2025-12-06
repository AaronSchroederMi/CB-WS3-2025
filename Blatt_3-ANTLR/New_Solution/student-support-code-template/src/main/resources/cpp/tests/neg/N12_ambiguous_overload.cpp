void f(int  r) { }
void f(int& r) { }

int main() {
    int a = 42;

    f(a); // error

    return 0;
}
