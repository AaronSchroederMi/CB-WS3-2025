import TokenType.TokenType;

import java.util.ArrayList;
import java.util.List;
import static TokenType.TokenType.*;

public class Parser {
    private final Node root = new Node(new Token(START, ""));
    private Node current = root;
    private List<Token> tokens = new ArrayList<>();
    private Token nextToken;
    private int index = 0;

    public Node createParseTree(List<Token> tokens) {
        this.tokens = tokens;
        this.nextToken = tokens.getFirst();
        program();
        return root;
    }

    private TokenType peek() {
        return nextToken.type();
    }
    private Token fullPeek() {
        return nextToken;
    }
    private boolean peek(TokenType type) {
        return nextToken.type() == type;
    }
    private Token consume() {
        if (nextToken.type() == EOF) return nextToken;
        Token t = nextToken;
        index++;
        nextToken = tokens.get(index);
        return t;
    }
    private Token match(TokenType type) {
        if (peek(type)) return consume();
        handleError(fullPeek());
        return null; //unreachable
    }

    private void handleError(Token token) {
        root.printMermaid();
        root.printLinear();
        throw new IllegalArgumentException("Unexpected token: " + token);
    }

    private void program() {
        while (!peek(EOF)) {
            element();
        }
        root.addChild(new Node(match(EOF)));
    }

    private void element() {
        switch (peek()) {
            case INTEGER -> {
                current.addChild(new Node(match(INTEGER)));
            }
            case STRING -> {
                current.addChild(new Node(match(STRING)));
            }
            case TRUE -> {
                current.addChild(new Node(match(TRUE)));
            }
            case FALSE -> {
                current.addChild(new Node(match(FALSE)));
            }
            case ID -> {
                current.addChild(new Node(match(ID)));
            }
            default -> {
                Token tmp = match(LBRACE);
                if (peek(LIST) || peek(NTH) || peek(HEAD) || peek(TAIL)
                        || peek(ADD) || peek(SUB) || peek(MUL) || peek(DIV)
                        || peek(PRINT) || peek(STR)
                        || peek(OR) || peek(EQUAL) || peek(GREATER) || peek(LESS)
                        || peek(ID)) {
                    current = current.addChild(new Node(new Token(STATEMENT, "")));
                    current.addChild(new Node(tmp));
                    statement();
                    return;
                }
                if (peek(IF)) {
                    current = current.addChild(new Node(new Token(CONDITIONAL, "")));
                    current.addChild(new Node(tmp));
                    conditional();
                    return;
                }
                if (peek(DEF) || peek(DEFN) || peek(LET)) {
                    current = current.addChild(new Node(new Token(ASSIGNMENT, "")));
                    current.addChild(new Node(tmp));
                    assignment();
                    return;
                }
                handleError(fullPeek());
            }

        }
    }

    private void statement() {
        switch (peek()) {
            case LIST -> {list(); current = current.getParent(); return;}
            case NTH -> {nth(); return;}
            case HEAD, TAIL -> {headTail(); return;}

            case ADD -> current.addChild(new Node(match(ADD)));
            case SUB -> current.addChild(new Node(match(SUB)));
            case MUL -> current.addChild(new Node(match(MUL)));
            case DIV -> current.addChild(new Node(match(DIV)));
            case PRINT -> current.addChild(new Node(match(PRINT)));
            case STR -> current.addChild(new Node(match(STR)));
            case OR -> current.addChild(new Node(match(OR)));
            case EQUAL -> current.addChild(new Node(match(EQUAL)));
            case GREATER -> current.addChild(new Node(match(GREATER)));
            case LESS -> current.addChild(new Node(match(LESS)));
            case ID -> current.addChild(new Node(match(ID)));
        }

        while (!peek(RBRACE)) {
            switch (peek()) {
                case INTEGER -> {
                    current.addChild(new Node(match(INTEGER)));
                }
                case STRING -> {
                    current.addChild(new Node(match(STRING)));
                }
                case ID -> {
                    current.addChild(new Node(match(ID)));
                }
                default -> {
                    Token tmp = match(LBRACE);
                    current = current.addChild(new Node(new Token(STATEMENT, "")));
                    current.addChild(new Node(tmp));
                    statement();
                }
            }
        }
        current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
    }

    private void list() {
        current.addChild(new Node(match(LIST)));

        while (!peek(RBRACE)) {
            switch (peek()) {
                case INTEGER -> {
                    current.addChild(new Node(match(INTEGER)));
                }
                case STRING -> {
                    current.addChild(new Node(match(STRING)));
                }
                case TRUE -> {
                    current.addChild(new Node(match(TRUE)));
                }
                case FALSE -> {
                    current.addChild(new Node(match(FALSE)));
                }
                case ID -> {
                    current.addChild(new Node(match(ID)));
                }
                default -> {
                    Token tmp = match(LBRACE);
                    current = current.addChild(new Node(new Token(STATEMENT, "")));
                    current.addChild(new Node(tmp));
                    statement();
                }
            }
        }
        current.addChild(new Node(match(RBRACE)));
    }

    private void nth() {
        current.addChild(new Node(match(NTH)));

        if (peek(LBRACE)) {
            current.addChild(new Node(match(LBRACE)));
            list();
        } else {
            current.addChild(new Node(match(ID)));
        }
        current.addChild(new Node(match(INTEGER)));
        current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
    }

    private void headTail() {
        switch (peek()) {
            case HEAD -> {
                current.addChild(new Node(match(HEAD)));
            }
            case TAIL -> {
                current.addChild(new Node(match(TAIL)));
            }
            default ->  {
                handleError(fullPeek());
            }
        }
        if (peek(LBRACE)) {
            current.addChild(new Node(match(LBRACE)));
            list();
        } else  {
            current.addChild(new Node(match(ID)));
        }
        current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
    }

    private void conditional() {
        current.addChild(new Node(match(IF)));
        condition();
        switch (peek()) {
            case INTEGER -> {
                current.addChild(new Node(match(INTEGER)));
            }
            case STRING -> {
                current.addChild(new Node(match(STRING)));
            }
            case TRUE -> {
                current.addChild(new Node(match(TRUE)));
            }
            case FALSE -> {
                current.addChild(new Node(match(FALSE)));
            }
            case ID -> {
                current.addChild(new Node(match(ID)));
            }
            default ->  {
                Token tmp = match(LBRACE);
                if (peek(DO)) {
                    doBody(tmp);
                    break;
                }
                current = current.addChild(new Node(new Token(STATEMENT, "")));
                current.addChild(new Node(tmp));
                statement();
            }
        }
        if (peek(LBRACE) || peek(INTEGER) || peek(STRING) || peek(TRUE) || peek(FALSE) || peek(ID)) {
            switch (peek()) {
                case INTEGER -> {
                    current.addChild(new Node(match(INTEGER)));
                }
                case STRING -> {
                    current.addChild(new Node(match(STRING)));
                }
                case TRUE -> {
                    current.addChild(new Node(match(TRUE)));
                }
                case FALSE -> {
                    current.addChild(new Node(match(FALSE)));
                }
                case ID -> {
                    current.addChild(new Node(match(ID)));
                }
                default -> {
                    Token tmp = match(LBRACE);
                    if (peek(DO)) {
                        doBody(tmp);
                        break;
                    }
                    current = current.addChild(new Node(new Token(STATEMENT, "")));
                    current.addChild(new Node(tmp));
                    statement();
                }
            }
        }
        current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
    }

    private void condition() {
        current = current.addChild(new Node(new Token(CONDITION, "")));
        current.addChild(new Node(match(LBRACE)));
        switch (peek()) {
            case OR -> current.addChild(new Node(match(OR)));
            case EQUAL -> current.addChild(new Node(match(EQUAL)));
            case GREATER -> current.addChild(new Node(match(GREATER)));
            case LESS -> current.addChild(new Node(match(LESS)));
            default -> handleError(fullPeek());
        }
        while (!peek(RBRACE)) {
            switch (peek()) {
                case INTEGER -> {
                    current.addChild(new Node(match(INTEGER)));
                }
                case STRING -> {
                    current.addChild(new Node(match(STRING)));
                }
                case ID -> {
                    current.addChild(new Node(match(ID)));
                }
                default -> {
                    Token tmp = match(LBRACE);
                    current = current.addChild(new Node(new Token(STATEMENT, "")));
                    current.addChild(new Node(tmp));
                    statement();
                }
            }
        }
        current = current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
        current = current.getParent();
    }

    private void doBody(Token tmp) {
        current = current.addChild(new Node(new Token(DOBODY, "")));
        current.addChild(new Node(tmp));
        current.addChild(new Node(match(DO)));
        while (!peek(RBRACE)) {
            switch (peek()) {
                case INTEGER -> {
                    current.addChild(new Node(match(INTEGER)));
                }
                case STRING -> {
                    current.addChild(new Node(match(STRING)));
                }
                case TRUE -> {
                    current.addChild(new Node(match(TRUE)));
                }
                case FALSE -> {
                    current.addChild(new Node(match(FALSE)));
                }
                case ID -> {
                    current.addChild(new Node(match(ID)));
                }
                default -> {
                    Token tmp1 = match(LBRACE);
                    current = current.addChild(new Node(new Token(STATEMENT, "")));
                    current.addChild(new Node(tmp1));
                    statement();
                }
            }
        }
        current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
    }

    private void assignment() {
        switch (peek()) {
            case DEF -> {
                current.addChild(new Node(match(DEF)));
                current.addChild(new Node(match(ID)));
                switch (peek()) {
                    case INTEGER -> {
                        current.addChild(new Node(match(INTEGER)));
                    }
                    case STRING -> {
                        current.addChild(new Node(match(STRING)));
                    }
                    case TRUE -> {
                        current.addChild(new Node(match(TRUE)));
                    }
                    case FALSE -> {
                        current.addChild(new Node(match(FALSE)));
                    }
                    case ID -> {
                        current.addChild(new Node(match(ID)));
                    }
                    default -> {
                        Token tmp = match(LBRACE);
                        current = current.addChild(new Node(new Token(STATEMENT, "")));
                        current.addChild(new Node(tmp));
                        statement();
                    }
                }
            }
            case DEFN -> {
                current.addChild(new Node(match(DEFN)));
                current.addChild(new Node(match(ID)));
                current.addChild(new Node(match(LBRACE)));
                current.addChild(new Node(match(ID)));
                current.addChild(new Node(match(RBRACE)));
                while (!peek(RBRACE)) {
                    element();
                }
            }
            case LET -> {
                current.addChild(new Node(match(LET)));
                current.addChild(new Node(match(LBRACE)));
                if (!peek(ID)) handleError(fullPeek());
                while (!peek(RBRACE)) {
                    switch (peek()) {
                        case INTEGER -> {
                            current.addChild(new Node(match(INTEGER)));
                        }
                        case STRING -> {
                            current.addChild(new Node(match(STRING)));
                        }
                        case TRUE -> {
                            current.addChild(new Node(match(TRUE)));
                        }
                        case FALSE -> {
                            current.addChild(new Node(match(FALSE)));
                        }
                        case ID -> {
                            current.addChild(new Node(match(ID)));
                        }
                        default -> {
                        Token tmp1 = match(LBRACE);
                        current = current.addChild(new Node(new Token(STATEMENT, "")));
                        current.addChild(new Node(tmp1));
                        statement();
                        }
                    }
                }
                current.addChild(new Node(match(RBRACE)));

                while (!peek(RBRACE)) {
                    element();
                }
                current.addChild(new Node(match(RBRACE)));
            }
            default -> handleError(fullPeek());
        }
        current.addChild(new Node(match(RBRACE)));
        current = current.getParent();
    }
}