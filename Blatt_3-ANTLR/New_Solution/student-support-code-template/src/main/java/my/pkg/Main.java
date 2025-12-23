package my.pkg;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
  static void main(String... args) {
    //IO.println("Hello World!");

    // Einlesen über Konsole/Prompt
    String input = IO.readln("expr?> ") + "\n";

    // Demonstriert den Einsatz von Packages und Grammatiken
    HelloPackageLexer lexer = new HelloPackageLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HelloPackageParser parser = new HelloPackageParser(tokens);

    // Start-Regel
    ParseTree tree = parser.start();

    ParseTreeWalker walker = new ParseTreeWalker();
    MyListener listener = new MyListener();
    System.out.println();
    System.out.println();
    walker.walk(listener, tree);

    // Output AST
    // IO.println(tree.toStringTree(parser));
  }
}
