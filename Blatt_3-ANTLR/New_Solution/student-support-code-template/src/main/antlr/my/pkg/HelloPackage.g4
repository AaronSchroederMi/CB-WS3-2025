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
    : ID ':=' expr NEWLINE  #AssignStmt
    | condition #conditionStmt
    ;

expr
    : (arOp|compOp)*
    | stringOp
    ;

condition
        : 'while' expr 'do' NEWLINE stmt* 'end' NEWLINE #whileStmt
        | 'if' expr 'do' NEWLINE stmt* (elsedo)? 'end' NEWLINE #ifStmt
        ;

elsedo
        : 'else' 'do' NEWLINE stmt*
        ;

arOp
    : left=arOp ('*'|'/') right=arOp #MulDiv
    | left=arOp ('+'|'-') right=arOp #PlusMin
    | (NUM|ID) #NUM
    ;

stringOp
    : (STRING|ID) ('+' (STRING|ID))*
    ;

compOp
    : leftCompOp=compOp ('>''='?|'<''='?) compOp
    | leftCompOp=compOp ('=='|'!=') compOp
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
