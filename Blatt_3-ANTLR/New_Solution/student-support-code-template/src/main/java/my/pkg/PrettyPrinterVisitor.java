package my.pkg;


// Terminalnodes (Terminale/ Lexer regeln (Tokens))
// Parser rules (Nichtterminale / Rule contexts)
// every node gets visited one time
// visit(ctx.expr())
// ctx.ID().getText()
//  ctx.INT().getText()
// Lexer = Vokabeln
// Parser = Grammatik
// Always use visit to evaluate the part tree (for example result of 2 + 3)

import org.antlr.v4.runtime.tree.TerminalNode;

public class PrettyPrinterVisitor extends HelloPackageBaseVisitor<String> {

    @Override
    public String visitStart(HelloPackageParser.StartContext ctx) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ctx.getChildCount(); i++) {
            String currentContent = visit(ctx.getChild(i));
            if(currentContent != null) {
                sb.append(currentContent);
            }
        }
        return sb.toString();
    }

    @Override
    public String visitVDECLARATION(HelloPackageParser.VDECLARATIONContext ctx) {
        return ctx.ID().getText() + " := " + visit(ctx.expr()) + ctx.NEWLINE().getText();
    }

    @Override
    public String visitExpr(HelloPackageParser.ExprContext ctx) {
        StringBuilder result  = new StringBuilder();

        for(int i = 0; i < ctx.children.size(); i++) {
            String currentContent = visit(ctx.getChild(i));
            if(currentContent != null) {
                result.append(currentContent);
            }
        }

        return result.toString();
    }

    @Override
    public String visitWHILESTMT(HelloPackageParser.WHILESTMTContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("while ").append(visit(ctx.expr())).append(" do\n");

        for(int i = 0; i < ctx.stmt().size(); i++) {
            String currentContent = visit(ctx.stmt(i));
            if(currentContent != null) {
                sb.append(currentContent);
            }
        }

        sb.append("end\n");

        return sb.toString();
    }

    @Override
    public String visitIFSTMT(HelloPackageParser.IFSTMTContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("if ").append(visit(ctx.expr())).append(" do\n");

        for(int i = 0; i < ctx.stmt().size(); i++) {
            String currentContent = visit(ctx.stmt(i));
            if(currentContent != null) {
                sb.append(currentContent);
            }
        }

        if(ctx.elsedo()!= null) {
            sb.append(visit(ctx.elsedo()));
        }

        sb.append("end\n");

        return sb.toString();
    }

    @Override
    public String visitArOp(HelloPackageParser.ArOpContext ctx) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ctx.children.size(); i++) {
            String currentContent = visit(ctx.getChild(i));
            if (currentContent != null) {
                result.append(currentContent);
                if(i < ctx.children.size() - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    @Override
    public String visitStringOp(HelloPackageParser.StringOpContext ctx) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ctx.children.size(); i++) {
            String currentContent = visit(ctx.getChild(i));
            if (currentContent != null) {
                result.append(currentContent);
                if(i < ctx.children.size() - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    @Override
    public String visitCompOp(HelloPackageParser.CompOpContext ctx) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ctx.children.size(); i++) {
            String currentContent = visit(ctx.getChild(i));
            if (currentContent != null) {
                result.append(currentContent);
                if(i < ctx.children.size() - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        if(node.getText().equals("<EOF>")) {
            return "";
        }
        return node.getText();
    }

}
