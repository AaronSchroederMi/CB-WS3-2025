package my.pkg;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MyListener extends HelloPackageBaseListener {
    private int identation = 0;
    private boolean newlineMatched = false;

    public void visitTerminal(TerminalNode node) {
        // getText() for traversal with depth search
        String text = node.getText().replace("<EOF>", "");

        if(text.contains("else")) {
            identation = identation - 3;
        } else if (text.contains("end")) {
            identation = identation - 3;
        }

        if(newlineMatched & !text.contains("\n")) {
            System.out.println();
            for (int i = 0; i < identation; i++) {
                System.out.print(" ");
            }
            System.out.print(text + " ");
        } else if(!text.contains("\n")) {
            System.out.print(text + " ");
        }

        newlineMatched = text.contains("\n");

        if(text.contains("if")) {
            identation = identation + 3;
        } else if(text.contains("else")) {
            identation = identation + 3;
        } else if(text.contains("while")) {
            identation = identation + 3;
        }
    }
}
