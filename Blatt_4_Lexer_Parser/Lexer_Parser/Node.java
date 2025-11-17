import java.util.ArrayList;
import java.util.List;
import static TokenType.TokenType.*;

public class Node {
    private Token token;
    private List<Node> children = new ArrayList<Node>();
    private Node parent;

    private static int idCounter = 0;
    private int id;

    public Node(Token token) {
        this.token = token;
        idCounter++;
        id = idCounter;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node addChild(Node child) {
        children.add(child);
        child.parent = this;
        return child;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public void printLinear() {
        if (token.type() == START) {
            IO.println("Linear Parse Tree");
            IO.println("-".repeat(children.toString().length()));
        }
        IO.print(token);
        if (!isLeaf()) {
            IO.print("(");
            for (int i = 0; i < children.size(); i++) {
                children.get(i).printLinear();
                if (i != children.size() - 1) {
                    IO.print(", ");
                }
            }
            IO.print(")");
        }
        if (token.type() == START) {
            IO.println();
            IO.println("-".repeat(children.toString().length()));
            IO.println();
        }
    }

    public void printMermaid() {
        if (isLeaf()) return;
        if (token.type() == START) {
            IO.println("Mermaid Parse Tree");
            IO.println("-".repeat(token.toString().length() * 7));
        }
        for (Node child : children) {
            IO.print(id + "[\""+ token.toMermaidSafeString() + "\"]");
            IO.print(" --> ");
            IO.print(child.id + "[\"" + child.token.toMermaidSafeString() + "\"]");
            IO.print("\n");
            child.printMermaid();
        }
        if (token.type() == START) {
            IO.println("-".repeat(token.toString().length() * 7));
            IO.println();
        }
    }

    @Override
    public String toString() {
        return token.toString() + ", " + children.toString().replace("[", "").replace("]", "");
    }

    public Node getParent() {
        return parent;
    }
}
