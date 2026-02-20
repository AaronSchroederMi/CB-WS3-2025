package my.pkg;


/*
Explanation Visitor Pattern:
- Outsourcing the logic of the classes in a new class (Class which implements Visitor interface)
- Double Dispatch determines which function to use
- Classes who want to use the functions need to implement the accept method
*/

public class MyVisitor extends HelloPackageBaseVisitor<Object> {

    @Override public Object visitStart(HelloPackageParser.StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Object visitStmt(HelloPackageParser.StmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Object visitExpr(HelloPackageParser.ExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Object visitCondition(HelloPackageParser.ConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Object visitArOp(HelloPackageParser.ArOpContext ctx) {
        return visitChildren(ctx);
    }

    @Override public Object visitStringOp(HelloPackageParser.StringOpContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Object visitCompOp(HelloPackageParser.CompOpContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Object visitValue(HelloPackageParser.ValueContext ctx) {
        System.out.println("Test");
        return visitChildren(ctx);
    }
}

