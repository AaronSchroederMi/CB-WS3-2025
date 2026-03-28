grammar HelloPackage;

@header {
package my.pkg;
}

// Parser Rules
// EOF -> Stands for "End of line"
start
    : (stmt | NEWLINE)* EOF
    ;

// Assign(String(ID),expression(expr))

stmt
    : ID ':=' expr NEWLINE # VDECLARATION
    | condition # WHILEORIFSTATEMENT
    ;

expr
    : (arOp|compOp)*
    | stringOp
    ;

condition
        : 'while' expr 'do' NEWLINE stmt* 'end' NEWLINE # WHILESTMT
        | 'if' expr 'do' NEWLINE stmt* (elsedo)? 'end' NEWLINE # IFSTMT
        ;

elsedo
        : 'else do' NEWLINE stmt*
        ;

arOp
    : arOp ('*'|'/') arOp
    | arOp ('+'|'-') arOp
    | (NUM|ID)
    ;

stringOp
    : (STRING|ID) ('+' (STRING|ID))*
    ;

compOp
    : compOp ('>''='?|'<''='?) compOp
    | compOp ('=='|'!=') compOp
    | value
    ;

value
    : NUM
    | ID
    | STRING
    ;

// Lexer Rules
// Identifiers (Variables)
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
// String expressions
STRING :  '"' (~[\n\r"])* '"' ;
// Numbers
NUM : [0-9]+ ;
// Comments
COMMENT : '#' ~[\r\n]* -> skip ;
// New lines for windows and other operation systems
NEWLINE : '\r'?'\n';
// Skip tabs and whitespaces
WS : [ \t]+ -> skip ;
