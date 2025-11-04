import java.io.IOException;
import java.net.URISyntaxException;
import my.pkg.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
  static void main(String... args) throws IOException, URISyntaxException {
    while (true) {
      // Einlesen über Konsole/Prompt
      String input = IO.readln("expr?> ");

      A31Lexer lexer = new A31Lexer(CharStreams.fromString(input + "\n"));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      A31Parser parser = new A31Parser(tokens);

      ParseTree tree = parser.start(); // Start-Regel
      IO.println(tree.toStringTree(parser));
      A31VisitorPRP prettyPrinter = new A31VisitorPRP();
      IO.println(prettyPrinter.visit(tree));
    }
  }
}
