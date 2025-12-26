package my.pkg;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MyListener extends HelloPackageBaseListener {
    private int identation = 0;
    private boolean newlineMatched = false;

    public void visitTerminal(TerminalNode node) {
        if(node.getText().contains("else")) {
            if(identation != 0) {
                identation = identation - 3;
            }
        } else if (node.getText().contains("end")) {
            if(identation != 0) {
                identation = identation - 3;
            }
        }

        // getText() for traversal with depth search
        String text = node.getText().replace("<EOF>", "");
        text = text.replaceAll(" ", "");

        if(newlineMatched) {
            System.out.println();
            for (int i = 0; i < identation; i++) {
                System.out.print(" ");
            }
            System.out.print(text + " ");
            newlineMatched = false;
        } else {
            System.out.print(text + " ");
        }

        newlineMatched = text.equals("\n");

        if(node.getText().contains("if")) {
            identation = identation + 3;
        } else if(node.getText().contains("else")) {
            identation = identation + 3;
        }
    }
}
