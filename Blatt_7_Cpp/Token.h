#ifndef TOKEN_H
#define TOKEN_H

class Token {
public:
    /**
     * Constructs a new token object.
     *
     * @param l is a pointer to the text of the token (to be copied)
     * @param r is the row in input where this token was found
     * @param c is the column in input where this token starts
     */
    Token(const char* l, int r, int c);

    /**
     * Copy constructor (Rule of Three)
     */
    Token(const Token& other);

    /**
     * Copy assignment operator (Rule of Three)
     */
    Token& operator=(const Token& other);

    /**
     * Destructs the token object and frees the stored lexem.
     */
    ~Token();

    // Getter for testing / output
    const char* getLexem() const;
    int getRow() const;
    int getCol() const;

private:
    char* lexem;    ///< Pointer to the text of the token
    int row;        ///< Row in input where this token was found
    int col;        ///< Column in input where this token starts
};

#endif // TOKEN_H
