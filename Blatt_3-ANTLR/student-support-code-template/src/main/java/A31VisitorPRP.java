import org.antlr.v4.runtime.tree.*;

public class A31VisitorPRP extends A31BaseVisitor<Object>{

    @Override
    public Object visitStart(A31Parser.StartContext ctx) {
        return "\n" + ctx.getText();
    }

    @Override
    public Object visitStmt(A31Parser.StmtContext ctx) {
        return "\n" + ctx.getText();
    }

    @Override
    public Object visitExpr(A31Parser.ExprContext ctx) {
        return "\n" + ctx.getText();
    }

    @Override
    public Object visitCondition(A31Parser.ConditionContext ctx) {
        return "\n" + ctx.getText();
    }

    @Override
    public Object visitArithmeticOp(A31Parser.ArithmeticOpContext ctx) {
        return "\n" + ctx.getText();
    }

    @Override
    public Object visitComparisonOp(A31Parser.ComparisonOpContext ctx) {
        return "\n" + ctx.getText();
    }

    @Override
    public Object visitValue(A31Parser.ValueContext ctx) {
        return "\n" + ctx.getText();
    }
}
