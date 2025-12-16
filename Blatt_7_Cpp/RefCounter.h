#ifndef REFCOUNTER_H
#define REFCOUNTER_H

#include <iostream>

class RefCounter {
public:
    // Konstruktor: initialisiert Referenz auf 1
    RefCounter();

    // Zerstörer
    ~RefCounter();

    // Referenz erhöhen
    void addRef();

    // Referenz verringern; löscht Objekt, falls 0
    void release();

    // Anzahl der Referenzen abfragen
    int getCount() const;

private:
    int count; // interne Referenzzählung
};

#endif // REFCOUNTER_H
