package my.pkg;

public class MyListener extends HelloPackageBaseListener{
    public void enterStmt(HelloPackageParser.StmtContext ctx) {
        for(int i = 0; i < ctx.getChildCount(); i++){
            System.out.print(ctx.getChild(i).getText() + " ");
        }
    }
}
