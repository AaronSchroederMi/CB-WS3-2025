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
        input = Files.readString(Path.of("C:\\Users\\Muhammed Korkmaz\\Documents\\CB-WS3-2025\\Blatt_3-ANTLR\\New_Solution\\student-support-code-template\\src\\main\\resources\\input.txt"));
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
    System.out.println("Pretty printing with listeners");
    walker.walk(listener, tree);

    System.out.println();
    System.out.println("Pretty printing with visitor pattern");

    // Traversal with visitor Pattern
    MyVisitor visitor = new MyVisitor();
    visitor.visit(tree);
  }
}
