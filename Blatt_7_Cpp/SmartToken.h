#ifndef SMARTTOKEN_H
#define SMARTTOKEN_H

#include "Token.h"
#include "RefCounter.h"

class SmartToken {
public:
    // Konstruktor: übernimmt rohen Token-Pointer, setzt Referenz auf 1
    SmartToken(Token* p = nullptr);

    // Copy-Konstruktor: inkrementiert Referenzzählung
    SmartToken(const SmartToken& sp);

    // Destruktor: dekrementiert Referenzzählung, löscht Token falls 0
    ~SmartToken();

    // Assignment: release aktuelles Token, addRef auf neues Token
    SmartToken& operator=(const SmartToken& sp);

    // Dereferenz-Operatoren
    Token& operator*();
    Token* operator->();

    // Vergleich: true, wenn beide denselben Token teilen
    bool operator==(const SmartToken& sp) const;

private:
    Token* pObj;       // Zeiger auf das gemeinsame Token
    RefCounter* rc;    // Referenzzähler für dieses Token
};

#endif // SMARTTOKEN_H
