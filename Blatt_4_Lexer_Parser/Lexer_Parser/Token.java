import TokenType.*;

public record Token (TokenType type, String value) {
    @Override
    public String toString() {
        return "< " + type + ", " + value + " >";
    }

    public String toMermaidSafeString() {
        String tmp = value;
        if (value.startsWith("\"") && value.endsWith("\"")) {
            tmp = "&#34" + value.substring(1, value.length() - 1) + "&#34";
        }
        return "< " + type + ", " + tmp + " >";
    }
}
