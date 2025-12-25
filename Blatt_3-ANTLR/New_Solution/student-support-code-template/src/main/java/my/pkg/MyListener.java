package my.pkg;

import org.antlr.v4.runtime.ParserRuleContext;

public class MyListener extends HelloPackageBaseListener{
    private final HelloPackageParser parser;
    //private int depth = 0;

    public MyListener(HelloPackageParser parser) {
        this.parser = parser;
    }

    public void exitStart(HelloPackageParser.StartContext ctx) {
        System.out.println("exitStart");
        System.out.println(ctx.getText());
    }

//    public void enterStmt(HelloPackageParser.StmtContext ctx) { }
//
//    public void enterExpr(HelloPackageParser.ExprContext ctx) { }
//
//    public void enterCondition(HelloPackageParser.ConditionContext ctx) { }
//
//    public void enterArOp(HelloPackageParser.ArOpContext ctx) { }
//
//    public void enterStringOp(HelloPackageParser.StringOpContext ctx) { }
//
//    public void enterCompOp(HelloPackageParser.CompOpContext ctx) { }
//
//    public void enterValue(HelloPackageParser.ValueContext ctx) { }
//
//    public void enterEveryRule(ParserRuleContext ctx) { }

//    public void enterEveryRule(ParserRuleContext ctx) {
//        try {
//            String ruleName = parser.getRuleNames()[ctx.getRuleIndex()];
//            System.out.println(ruleName + " " + depth * 2 + "\n Content: " + ctx.getText());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        depth++;
//    }

    public void exitEveryRule(ParserRuleContext ctx) {
        try {
            String ruleName = parser.getRuleNames()[ctx.getRuleIndex()];
            System.out.println(ruleName + " " + "\nContent: " + ctx.getText());
            System.out.println(ctx.getChildCount());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
