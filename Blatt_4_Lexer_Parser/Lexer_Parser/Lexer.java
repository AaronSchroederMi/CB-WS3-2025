import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final List<Token> result = new ArrayList<>();
    private CharSequence chars = new StringBuilder();

    public List<Token> tokenize(String input) {
        chars = input;
        IO.println(chars);
        createTokens();
        return result;
    }

    private void createTokens() {
        while (true) {
            IO.println(result);
            if (chars.isEmpty()) {
                addToken(TokenType.EOF, "");
                return;
            }
            switch (chars.charAt(0)) {
                //skipped Tokens
                case ' ', '\n', '\r', '\t':
                    chars = chars.subSequence(1, chars.length()); continue;
                case ';':
                    if (chars.charAt(1) == ';') {
                        handleComment();
                        continue;
                    }

                //braces
                case '(': addToken(TokenType.LBRACE,"("); continue;
                case ')': addToken(TokenType.RBRACE,")"); continue;

                //keywords (do, def, defn)
                case 'd':
                    if (chars.charAt(1) == 'o' && (Character.isWhitespace(chars.charAt(2)) || chars.charAt(2) == ')')) {
                        addToken(TokenType.DO, "do");
                        continue;
                    } else if (chars.charAt(1) == 'e') {
                        if (chars.charAt(2) == 'f' && chars.charAt(3) == 'n' && (Character.isWhitespace(chars.charAt(4)) || chars.charAt(4) == ')')) {
                            addToken(TokenType.DEFN, "defn");
                            continue;
                        } else if (chars.charAt(2) == 'f' && (Character.isWhitespace(chars.charAt(3)) || chars.charAt(3) == ')')) {
                            addToken(TokenType.DEF, "def");
                            continue;
                        }
                    }
                case 'i':
                    if (chars.charAt(1) == 'f' && (Character.isWhitespace(chars.charAt(2)) || chars.charAt(2) == ')')) {
                        addToken(TokenType.IF, "if");
                        continue;
                    } else if (chars.subSequence(1, 3).equals("et") && (Character.isWhitespace(chars.charAt(3)) || chars.charAt(3) == ')')) {
                        addToken(TokenType.LET, "let");
                        continue;
                    }

                //simple functions (Operations)
                case '=': addToken(TokenType.EQUAL, "="); continue;
                case '>': addToken(TokenType.GREATER, ">"); continue;
                case '<': addToken(TokenType.LESS, "<"); continue;
                case '.': addToken(TokenType.OR, "."); continue;
                case '+': addToken(TokenType.ADD, "+"); continue;
                case '-': addToken(TokenType.SUB, "-"); continue;
                case '*': addToken(TokenType.MUL, "*"); continue;
                case '/': addToken(TokenType.DIV, "/"); continue;

                //strings
                case '"': handleString(); continue;

                //false
                case 'f':
                    if (chars.subSequence(1, 5).equals("alse") && (Character.isWhitespace(chars.charAt(5)) || chars.charAt(5) == ')')) {
                        addToken(TokenType.FALSE, "false");
                        continue;
                    }

                    //functions (list, nth, head, tail, print, str) also true
                case 'l':
                    if (chars.subSequence(1, 4).equals("ist") && (Character.isWhitespace(chars.charAt(4)) || chars.charAt(4) == ')')) {
                        addToken(TokenType.LIST, "list");
                        continue;
                    }
                case 'n':
                    if (chars.subSequence(1, 3).equals("th") && (Character.isWhitespace(chars.charAt(3)) || chars.charAt(3) == ')')) {
                        addToken(TokenType.NTH, "nth");
                        continue;
                    }
                case 'h':
                    if (chars.subSequence(1, 4).equals("ead") && (Character.isWhitespace(chars.charAt(4)) || chars.charAt(4) == ')')) {
                        addToken(TokenType.HEAD, "head");
                        continue;
                    }
                case 't':
                    if (chars.subSequence(1, 4).equals("ail") && (Character.isWhitespace(chars.charAt(4)) || chars.charAt(4) == ')')) {
                        addToken(TokenType.TAIL, "tail");
                        continue;
                    } else if (chars.subSequence(1, 4).equals("rue") && (Character.isWhitespace(chars.charAt(4)) || chars.charAt(4) == ')')) {
                        addToken(TokenType.TRUE, "true");
                        continue;
                    }
                case 'p':
                    if (chars.subSequence(1, 5).equals("rint") && (Character.isWhitespace(chars.charAt(5)) || chars.charAt(5) == ')')) {
                        addToken(TokenType.PRINT, "print");
                        continue;
                    }
                case 's':
                    if (chars.subSequence(1, 4).equals("str") && (Character.isWhitespace(chars.charAt(4)) || chars.charAt(4) == ')')) {
                        addToken(TokenType.STR, "str");
                        continue;
                    }

                //ID's and Numbers
                default:
                    if (isASCIILetter(chars.charAt(0))) {
                    handleID();
                    } else if  (Character.isDigit(chars.charAt(0))) {
                        handleInteger();
                    } else {
                        throw new IllegalArgumentException("Illegal character: " + chars.charAt(0));
                    }
            }
        }
    }

    private void addToken(TokenType type, String value) {
        result.add(new Token(type, value));
        chars = chars.subSequence(value.length(), chars.length());
    }

    private boolean isASCIILetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private void handleComment() {
        while (chars.charAt(0) != '\n') {
            chars = chars.subSequence(1, chars.length());
        }
    }
    private void handleString() {
        String string = "\"";
        int i = 1;
        while (chars.charAt(i) != '"') {
            string += chars.charAt(i);
            i++;
        }
        addToken(TokenType.STRING, string + '"');
    }
    private void handleInteger() {
        String Integer = "";
        int i = 0;
        while (true) {
            if (Character.isDigit(chars.charAt(i))) {
                Integer += chars.charAt(i);
                i++;
            } else if (Character.isWhitespace(chars.charAt(i)) || chars.charAt(i) == ')') {
                break;
            } else {
                throw new IllegalArgumentException("Illegal character: " + chars.charAt(0));
            }
        }
        addToken(TokenType.INTEGER, Integer);
    }
    private void handleID() {
        String id = "";
        int i = 0;
        while (true) {
            if (isASCIILetter(chars.charAt(i))) {
                id += chars.charAt(i);
                i++;
            } else if (Character.isWhitespace(chars.charAt(i)) || chars.charAt(i) == ')') {
                break;
            } else {
                throw new IllegalArgumentException("Illegal character: " + chars.charAt(0));
            }
        }
        addToken(TokenType.ID, id);
    }
}
