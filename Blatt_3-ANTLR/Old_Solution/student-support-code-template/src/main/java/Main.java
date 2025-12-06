import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import my.pkg.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Main {
  static void main(String... args) throws IOException, URISyntaxException {
      // Einlesen über Konsole/Prompt
      // String input = IO.readln("expr?> ");

      A31Lexer lexer = new A31Lexer(CharStreams.fromString(
          "a     := 0\n" +
          "    if    10 < 1\n" +
          "       do\n" +
          "a    :=     42      # Zuweisung des Wertes 42 an die Variable a\n" +
          "else do\n" +
          "        a :=      7\n" +
          "  end"
      ));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      A31Parser parser = new A31Parser(tokens);

      // Start rule
      ParseTree tree = parser.start();
      ParseTreeWalker walker = new ParseTreeWalker();
      MyListener eval = new MyListener();
      walker.walk(eval, tree);
  }
}

class MyListener extends A31BaseListener {
    public void enterStart(A31Parser.StartContext ctx) {
        System.out.println(ctx.children.getFirst().getText() + ctx.children.getLast().getText());
    }

    public void exitStart(A31Parser.StartContext ctx) {
        //System.out.println(ctx.start.getText());
    }

    public void enterStmt(A31Parser.StmtContext ctx) {
        //System.out.println(ctx.ID().getText() + ctx);
    }

    public void exitStmt(A31Parser.StmtContext ctx) {
        //System.out.println(ctx.ID().getText());
    }

    public void enterExpr(A31Parser.ExprContext ctx) {
    }

    public void exitExpr(A31Parser.ExprContext ctx) {
    }

    public void enterCondition(A31Parser.ConditionContext ctx) {
    }

    public void exitCondition(A31Parser.ConditionContext ctx) {
    }

    public void enterPOINTCALC(A31Parser.POINTCALCContext ctx) {
    }

    public void exitPOINTCALC(A31Parser.POINTCALCContext ctx) {
    }
//
//    public void enterLINECALC(A31Parser$LINECALCContext ctx) {
//    }
//
//    public void exitLINECALC(A31Parser$LINECALCContext ctx) {
//    }
//
//    public void enterEQUAL(A31Parser$EQUALContext ctx) {
//    }
//
//    public void exitEQUAL(A31Parser$EQUALContext ctx) {
//    }
//
//    public void enterNOTEQUAL(A31Parser$NOTEQUALContext ctx) {
//    }

//    public void exitNOTEQUAL(A31Parser$NOTEQUALContext ctx) {
//    }
//
//    public void enterGREATEREQ(A31Parser$GREATEREQContext ctx) {
//    }
//
//    public void exitGREATEREQ(A31Parser$GREATEREQContext ctx) {
//    }
//
//    public void enterSMALLEREQ(A31Parser$SMALLEREQContext ctx) {
//    }
//
//    public void exitSMALLEREQ(A31Parser$SMALLEREQContext ctx) {
//    }
//
//    public void enterValue(A31Parser$ValueContext ctx) {
//    }
//
//    public void exitValue(A31Parser$ValueContext ctx) {
//    }
//
//    public void enterEveryRule(ParserRuleContext ctx) {
//    }
//
//    public void exitEveryRule(ParserRuleContext ctx) {
//    }
//
//    public void visitTerminal(TerminalNode node) {
//    }
//
//    public void visitErrorNode(ErrorNode node) {
//    }
}
