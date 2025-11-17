import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(readFile());
        Parser parser = new Parser();
        Node tree = parser.createParseTree(tokens);

        tree.printMermaid();
        tree.printLinear();

    }

    private static String readFile() throws IOException {
        Path path;
        while (true) {
            String tmp = IO.readln("enter?> ");
            path = Path.of("Blatt_4_Lexer_Parser/Bsp_Programme/"  + tmp);
            if (Files.exists(path)) break;
            IO.println("...no such file");
        }
        IO.println("Reading expr file: " + path);
        String expr = Files.readString(path, StandardCharsets.UTF_8);
        return expr += "    ";
    }
}