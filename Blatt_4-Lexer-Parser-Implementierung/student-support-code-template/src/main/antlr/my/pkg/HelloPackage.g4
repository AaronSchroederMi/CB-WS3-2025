grammar HelloPackage;

@header {
package my.pkg;
}

// Parser
// start : stmt* ;
start : stmt* EOF ;

stmt : expr ;

expr : atom | ('('('*'|'/'|'+'|'-') (integerExpr|stringExpr)')')+ ;

// Unnecessary precedence
stringExpr
          : stringExpr ('*'|'/') STRING
          | stringExpr ('+'|'-') STRING
          | stringExpr ('+'|'-'|'/') STRING
          // Endpoint of the left-recursion / no stringExpr anymore
          | STRING
          ;

// Unnecessary precedence
integerExpr
           : integerExpr ('*'|'/') INTEGER
           | integerExpr ('+'|'-') INTEGER
           | integerExpr ('='|'>'|'<') INTEGER
           // Endpoint of the left-recursion / no stringExpr anymore
           | INTEGER
           ;

atom
    : INTEGER
    | STRING
    | BOOLEAN
    | ID
    ;

// Lexer
// Integer
INTEGER : [0-9]+ ;
// Strings
STRING :  '"' (~[\n\r"])* '"' ;
// Booleans
BOOLEAN : 'true' | 'false' ;
// Identifiers (Variables)
ID : [a-zA-Z_][a-zA-Z0-9_]*;
// not precise enough defined
// different approach for ID
// ~['0-9`,@()#;.\r\n"]~[\r\n()"]*
// Comments
COMMENT : ';;' ~[\r\n]* -> skip ;
// New lines for windows and other operation systems
NL : '\r'?'\n' -> skip;
// Skip tabs and whitespaces
WS : [ \t\n]+ -> skip ;
