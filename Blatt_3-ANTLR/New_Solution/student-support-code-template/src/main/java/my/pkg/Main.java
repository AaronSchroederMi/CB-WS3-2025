package my.pkg;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  static void main(String... args) {

    String input = "";

    try {
        input = Files.readString(Path.of("src\\main\\resources\\input.txt"));
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }

    // Demonstriert den Einsatz von Packages und Grammatiken
    HelloPackageLexer lexer = new HelloPackageLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HelloPackageParser parser = new HelloPackageParser(tokens);

    // Start-Regel
    ParseTree tree = parser.start();

    ParseTreeWalker walker = new ParseTreeWalker();

    // Traversal with listener
    MyListener listener = new MyListener();
    walker.walk(listener, tree);

    System.out.println();

    // Traversal with visitor Pattern
    MyVisitor visitor = new MyVisitor();
    Object Ast = visitor.visit(tree);
    // System.out.println(Ast.toString());

    // Output AST
    IO.println(tree.toStringTree(parser));
  }
}
