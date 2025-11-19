grammar lisp_artige_sprache;

//Parser
start       : elements* EOF
            ;

elements    : expression
            | statement
            | conditional
            | assignments
            ;

expression  : INTEGER
            | STRING
            | TRUE
            | FALSE
            | ID
            ;

statement   : '(' (arithOp | logicOp | funcOP | ID) (operanden | statement)+ ')'
            | '(' 'list' (expression | statement)* ')'
            | '(' 'nth' ('(' 'list' (expression | statement)* ')' | ID) INTEGER ')'
            | '(' ( 'head' | 'tail' ) ('(' 'list' (expression | statement)* ')' | ID) ')'
            ;

conditional : '(' 'if' condition (statement | expression | doBody) ')'
            | '(' 'if' condition (statement | expression | doBody) (statement | expression | doBody) ')'
            ;

assignments : '(' 'def' ID (ID | expression | statement) ')'
            | '(' 'defn' ID '(' ID ')' elements* ')'
            | '(' 'let' '(' (ID (ID | expression | statement))+ ')' elements ')'
            ;

doBody      : '(' 'do' (statement | expression)+ ')'
            ;

condition   : '(' logicOp (operanden | statement)+ ')'
            ;

arithOp     : '+'
            | '-'
            | '*'
            | '/'
            ;

funcOP      : 'print'
            | 'str'
            ;

logicOp     : '.'
            | '='
            | '>'
            | '<'
            ;

operanden   : STRING
            | INTEGER
            | ID
            ;

//Lexer
INTEGER     : [0-9]+;
STRING      : '"' (~[\n\r"])* '"';
TRUE        : 'true';
FALSE       : 'false';
ID          : [a-z][a-zA-Z]*;

WS          : [ \n\t]+ -> skip;
COMMENT     : ';;' (~[\n\r])* -> skip;