#include "SmartToken.h"
#include <iostream>

// Konstruktor
SmartToken::SmartToken(Token* p)
    : pObj(p)
{
    if (pObj) {
        rc = new RefCounter(); // Referenz auf 1
        std::cout << "[SmartToken] Konstruktor: Token \""
                  << pObj->getLexem() << "\", RefCount = 1" << std::endl;
    } else {
        rc = nullptr;
    }
}

// Copy-Konstruktor
SmartToken::SmartToken(const SmartToken& sp)
{
    pObj = sp.pObj;
    rc = sp.rc;
    if (rc) {
        rc->addRef();
        std::cout << "[SmartToken] Copy-Konstruktor: Token \""
                  << pObj->getLexem() << "\", RefCount = " << rc->getCount() << std::endl;
    }
}

// Destruktor
SmartToken::~SmartToken()
{
    if (rc) {
        rc->release();
        std::cout << "[SmartToken] Destruktor: Token \""
                  << (pObj ? pObj->getLexem() : "null")
                  << "\", RefCount = " << rc->getCount() << std::endl;
        if (rc->getCount() == 0) {
            std::cout << "[SmartToken] Token \"" << pObj->getLexem() << "\" wird gelöscht" << std::endl;
            delete pObj;
            pObj = nullptr;
            delete rc;
            rc = nullptr;
        }
    }
}

// Assignment
SmartToken& SmartToken::operator=(const SmartToken& sp)
{
    if (this != &sp) {
        // Erst das aktuelle freigeben
        if (rc) {
            rc->release();
            std::cout << "[SmartToken] Assignment: altes Token RefCount = " << rc->getCount() << std::endl;
            if (rc->getCount() == 0) {
                std::cout << "[SmartToken] Altes Token \"" << pObj->getLexem() << "\" wird gelöscht" << std::endl;
                delete pObj;
                pObj = nullptr;
                delete rc;
                rc = nullptr;
            }
        }

        // Neues übernehmen
        pObj = sp.pObj;
        rc = sp.rc;
        if (rc) {
            rc->addRef();
            std::cout << "[SmartToken] Assignment: neues Token \"" << pObj->getLexem()
                      << "\", RefCount = " << rc->getCount() << std::endl;
        }
    }
    return *this;
}

// Dereferenz
Token& SmartToken::operator*()
{
    return *pObj;
}

Token* SmartToken::operator->()
{
    return pObj;
}

// Vergleich
bool SmartToken::operator==(const SmartToken& sp) const
{
    return pObj == sp.pObj;
}
