#ifndef HSBI_RUNTIME_H
#define HSBI_RUNTIME_H

#include <iostream>

inline void print_int(int v)   { std::cout << v << std::endl; }
inline void print_bool(bool v) { std::cout << (v ? 1 : 0) << std::endl; }
inline void print_char(char v) { std::cout << v << std::endl; }

#endif // HSBI_RUNTIME_H
