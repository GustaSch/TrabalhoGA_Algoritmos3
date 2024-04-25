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

    public Node search(int value){
        return search(root,value);
    }

    private Node search(Node root, int value){
        if(root == null || root.value == value){
            return root;
        }

        if(value < root.value){
            return search(root.left,value);
        }

        return search(root.right,value);
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node root){
        if (root != null){
            System.out.println(root.value + "");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }


}
