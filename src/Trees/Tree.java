package src.Trees;

public class Tree {
    Node root;

    public Tree(int value) {
        root = new Node();
        root.value = value;
        root.left = root.right = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }   

    private Node insert(int value, Node root) {
        if (root == null) {
            root = new Node(value);
        }

        if (value > root.value) {
            insert(value, root.right);
        }

        insert(value, root.left);

        return root;
    }
}
