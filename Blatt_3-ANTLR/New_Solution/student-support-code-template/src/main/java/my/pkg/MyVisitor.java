package my.pkg;

// Labeling: ANTLR rule context is replaced with individual contexts for each alt.
// For precise identification what ANTLR does

// Parse Tree (Concrete Syntax Tree)
// Ein Parse Tree ist im Prinzip die Grammatik als Baum:
// Jede Regel, die du in der Grammar hast (z. B. stmt, expr, term, factor), taucht typischerweise als Knoten auf
// Plus alle Tokens (:=, do, end, NEWLINE, Klammern, …)
// Er enthält also sehr viel „Parsing-Kram“, der nur dazu da ist, dass die Grammatik funktioniert

// AST (Abstract Syntax Tree)
// Enthält nur semantisch relevante Konstrukte
// Statements: Assign, If, While, …
// Expressions: BinaryOp, Var, Literal, …
// lässt “reine Syntax” weg:
// do, end, NEWLINE, Klammern
// Hilfsregeln (expr/term/factor) werden oft komplett weggedacht

import org.antlr.v4.runtime.tree.TerminalNode;

// MyVisitor ist die Klasse des Besuchers.
public class MyVisitor extends HelloPackageBaseVisitor<Object> {

    public Object visitAssignStmt(HelloPackageParser.AssignStmtContext ctx) {
        visit(ctx.ID());
        System.out.print(" := ");
        // returned expression of generic object  Expression x = (Expression)visit(ctx.expr());
        Expression x = (Expression)visit(ctx.expr());
        visit(ctx.NEWLINE());
        return new Assign(ctx.ID().getText(), x);
    }

    public Object visitTerminal(TerminalNode node) {
        System.out.print(node.getText());
        return null;
    }
}

/*
     visitor.visit(root)
           ↓
     root.accept(visitor)
           ↓
     visitor.visitAssignStmt(ctx)
           ↓
     visit(ctx.expr())
           ↓
     expr.accept(visitor)
           ↓
     visitor.visitExpr(ctx)
*/
