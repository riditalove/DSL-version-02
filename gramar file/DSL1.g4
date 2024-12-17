grammar DSL1;// The name of the grammar is DSL
// This WS rule (WhiteSpace) skip spac
// Lexer rules

COLON       : ':';                        // Colon separator
NAME        : [A-Z][a-z]*[A-Z][a-z]*[0-1]*;             // Matches object names like MyRect, MyCircle
ACTION      : 'create' ;                // Matches the 'create' action keyword
SHAPE       : 'Rectangle' | 'Circle';  // Matches shapes
LPAREN      : '(';
RPAREN      : ')';
COLOR       : [a-z]+;
WS          : [ \t\r\n]+ -> skip;  // Skip spaces, tabs, and newlines

// Parser rules

program     : statements  ;                // The program consists of zero or more statements
statements : statement*;
statement   : NAME COLON ACTION SHAPE COLOR? ( LPAREN statements RPAREN )? ;