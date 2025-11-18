grammar HelloPackage;

@header {
package my.pkg;
}


// Parser
// start : stmt* ;
start : stmt* EOF ;

stmt  : expr ;

expr  : atom ;

atom  : STRING | BOOLEAN | INTEGER;

// Lexer

// Integer
INTEGER   : [0-9]+ ;
// Strings
STRING :  '"' (~[\n\r"])+ '"' ;
// Booleans
BOOLEAN : 'true' | 'false' ;
// Comments
COMMENT : ';;' ~[\r\n]* -> skip ;
// New lines for windows and other operation systems
NL : '\r'?'\n' -> skip;
// Skip tabs and whitespaces
WS    : [ \t\n]+ -> skip ;
