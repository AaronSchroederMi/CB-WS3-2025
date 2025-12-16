#include <iostream>
#include <vector>

#include <windows.h>

#include "Token.h"
#include "RefCounter.h"
#include "SmartToken.h"
#include "RingBuffer.h"

// Declaration der tokenize-Funktion
void tokenize(const std::string& input, std::vector<Token>& tokens);

int main()
{
    SetConsoleOutputCP(CP_UTF8);
    SetConsoleCP(CP_UTF8);

    std::cout << "\n Aufgabe 01" << std::endl;
    {
        Token t1("if", 1, 1);
        {
            Token t2("variableName", 2, 5);
            Token t3("12345", 3, 10);

            std::cout << "[main] Token 2: " << t2.getLexem()
                      << " (row=" << t2.getRow()
                      << ", col=" << t2.getCol() << ")\n";

            std::cout << "[main] Token 3: " << t3.getLexem()
                      << " (row=" << t3.getRow()
                      << ", col=" << t3.getCol() << ")\n";
        }

        std::cout << "[main] Token 1: " << t1.getLexem()
                  << " (row=" << t1.getRow()
                  << ", col=" << t1.getCol() << ")\n";
    }

    std::cout << "\n[main]  Aufgabe 02" << std::endl;
    {
        std::vector<Token> tokens;
        tokens.reserve(10); //vektor wachsen vermeiden

        std::string input1 = "int main return 0";
        std::string input2 = "  hello   world   C++  ";

        tokenize(input1, tokens);
        tokenize(input2, tokens);

        // Tokens ausgeben
        for (const Token& t : tokens) {
            std::cout << "[main] Token: \"" << t.getLexem()
                      << "\"  row=" << t.getRow()
                      << "  col=" << t.getCol() << std::endl;
        }
    }

    std::cout << "\n[main]  Aufgabe 03" << std::endl;
    {
        RefCounter* rc = new RefCounter(); // count = 1

        rc->addRef();  // count = 2
        rc->addRef();  // count = 3

        std::cout << "[main] Aktuelle Referenzen: " << rc->getCount() << std::endl;

        rc->release(); // count = 2
        rc->release(); // count = 1

        std::cout << "[main] Vor letztem release, count = " << rc->getCount() << std::endl;

        rc->release();
    }

    std::cout << "\n[main]  Aufgabe 04" << std::endl;
    {
        SmartToken st1(new Token("outer", 1, 1));
        {
            SmartToken st2(st1);
            {
                SmartToken st3;
                st3 = st1;
            } // st3 zerstört
        } // st2 zerstört
    }

    std::cout << "\n[main]  Aufgabe 05" << std::endl;
    {
        RingBuffer buffer(3);

        SmartToken t1(new Token("eins", 1, 1));
        SmartToken t2(new Token("zwei", 1, 2));
        SmartToken t3(new Token("drei", 1, 3));
        SmartToken t4(new Token("vier", 1, 4));

        buffer.writeBuffer(t1);
        buffer.writeBuffer(t2);
        buffer.writeBuffer(t3);

        std::cout << "--- Lese erstes Element ---" << std::endl;
        SmartToken r1 = buffer.readBuffer();
        std::cout << "Gelesen: " << r1->getLexem() << std::endl;

        std::cout << "--- Schreibe viertes Element, Puffer voll ---" << std::endl;
        buffer.writeBuffer(t4); // Überschreibt ältestes Element

        std::cout << "--- Lese alle Elemente ---" << std::endl;
        for (int i = 0; i < 3; ++i) {
            SmartToken r = buffer.readBuffer();
            if (r.operator->()) {
                std::cout << "Gelesen: " << r->getLexem() << std::endl;
            } else {
                std::cout << "Gelesen: <leer>" << std::endl;
            }
        }
    }

    return 0;
}
