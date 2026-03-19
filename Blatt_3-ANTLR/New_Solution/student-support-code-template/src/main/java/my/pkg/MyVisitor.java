package my.pkg;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor extends HelloPackageBaseVisitor<Object> {

    @Override
    public Object visitAssignStmt(HelloPackageParser.AssignStmtContext ctx) {
        visit(ctx.ID());
        System.out.print(" := ");
        visit(ctx.expr());
        visit(ctx.NEWLINE());
        return null;
    }

    @Override
    public Object visitIfStmt(HelloPackageParser.IfStmtContext ctx) {
        System.out.print("if ");
        visit(ctx.expr());
        System.out.println(" do");
        for(int i = 0; i < ctx.stmt().size(); i++) {
            visit(ctx.stmt(i));
        }
        System.out.println("end");
        return null;
    }

    @Override
    public Object visitElsedo(HelloPackageParser.ElsedoContext ctx) {
        System.out.println("else do" + ctx.NEWLINE() + ctx.stmt());
        return null;
    }

    @Override
    public Object visitWhileStmt(HelloPackageParser.WhileStmtContext ctx) {
        System.out.println("while " + ctx.expr() + " do");
        return null;
    }

    @Override
    public Object visitCompOp(HelloPackageParser.CompOpContext ctx) {
        for(int i = 0; i < ctx.children.size(); i++) {
            visit(ctx.children.get(i));
        }
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        System.out.print(node.getText());
        return null;
    }
}
