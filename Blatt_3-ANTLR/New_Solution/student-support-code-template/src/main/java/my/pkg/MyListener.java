package my.pkg;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MyListener extends HelloPackageBaseListener {
    private int indentation = 0;
    private boolean newlineMatched = false;

    public void visitTerminal(TerminalNode node) {
        // getText() for traversal with depth search
        String text = node.getText().replace("<EOF>", "");

        if(text.contains("else")) {
            indentation = indentation - 3;
        } else if (text.contains("end")) {
            indentation = indentation - 3;
        }

        if(newlineMatched & !text.contains("\n")) {
            System.out.println();
            for (int i = 0; i < indentation; i++) {
                System.out.print(" ");
            }
            System.out.print(text + " ");
        } else if(!text.contains("\n")) {
            System.out.print(text + " ");
        }

        newlineMatched = text.contains("\n");

        if(text.contains("if")) {
            indentation = indentation + 3;
        } else if(text.contains("else")) {
            indentation = indentation + 3;
        } else if(text.contains("while")) {
            indentation = indentation + 3;
        }
    }
}
