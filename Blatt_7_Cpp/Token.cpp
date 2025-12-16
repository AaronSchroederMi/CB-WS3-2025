#include "Token.h"
#include <cstring>
#include <iostream>

// Konstruktor
Token::Token(const char* l, int r, int c)
    : row(r), col(c)
{
    if (l) {
        size_t len = std::strlen(l) + 1;
        lexem = new char[len];
        std::strcpy(lexem, l);
    } else {
        lexem = nullptr;
    }
}

// Copy-Konstruktor
Token::Token(const Token& other)
    : row(other.row), col(other.col)
{
    if (other.lexem) {
        size_t len = std::strlen(other.lexem) + 1;
        lexem = new char[len];
        std::strcpy(lexem, other.lexem);
    } else {
        lexem = nullptr;
    }
}

// Copy-Zuweisungsoperator
Token& Token::operator=(const Token& other)
{
    if (this != &other) {
        delete[] lexem;

        row = other.row;
        col = other.col;

        if (other.lexem) {
            size_t len = std::strlen(other.lexem) + 1;
            lexem = new char[len];
            std::strcpy(lexem, other.lexem);
        } else {
            lexem = nullptr;
        }
    }
    return *this;
}

// Destruktor
Token::~Token()
{
    std::cout << "[Token] Destruktor aufgerufen für Token: "
              << (lexem ? lexem : "null") << std::endl;
    delete[] lexem;
}

// Getter
const char* Token::getLexem() const { return lexem; }
int Token::getRow() const { return row; }
int Token::getCol() const { return col; }

#include <string>
#include <cctype>   // std::isspace
#include <vector>

void tokenize(const std::string& input, std::vector<Token>& tokens)
{
    int row = 1;     // aktuelle Zeile (für jetzt immer 1)
    int col = 1;     // aktuelle Spalte
    int i = 0;
    int n = input.length();

    while (i < n) {
        // Leerzeichen überspringen
        while (i < n && std::isspace(input[i])) {
            ++i;
            ++col;
        }

        if (i >= n) break;

        // Start eines Wortes
        int startCol = col;
        int start = i;

        while (i < n && !std::isspace(input[i])) {
            ++i;
            ++col;
        }

        // Wort extrahieren
        std::string word = input.substr(start, i - start);

        // Token erzeugen und hinzufügen
        tokens.emplace_back(word.c_str(), row, startCol);
    }
}