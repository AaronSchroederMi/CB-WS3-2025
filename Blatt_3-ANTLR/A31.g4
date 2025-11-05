grammar A31;

// Parser Rules
// EOF -> Stands for "End of line"
start : (stmt | NEWLINE)* EOF ;

stmt  : (ID ':=')? expr NEWLINE | condition;

expr : expr ('*'|'/')  expr | expr ('+'|'-')  expr | value ;

condition : 'while' expr 'do' NEWLINE stmt* 'end' NEWLINE | 'if' expr 'do' NEWLINE stmt* ('else' 'do' NEWLINE stmt*)? 'end' NEWLINE ;

// No priority changes for alternatives at the same level

arithmeticOp : ('*'|'/')        #MUL
             | ('+'|'-')        #ADD
             | '=='     # EQUAL
             | '!='     # NOTEQUAL
             | '>' '='?     # GREATEREQ
             | '<' '='?     # SMALLEREQ  // After this default-case recommended and then nesting with values the operators
             ;

value : NUM | ID | STRING;

// Lexer Rules
// Identifiers (Variables)
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
STRING :  '"' (~[\n\r"])* '"' ;
// Numbers
NUM : [0-9]+ ;
// Comments
COMMENT : '#' ~[\r\n]* -> skip ;
// New lines for windows and other operation systems
NEWLINE : '\r'?'\n';
// Skip tabs and whitespaces
WS : [ \t]+ -> skip ;
