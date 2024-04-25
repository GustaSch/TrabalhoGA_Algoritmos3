package src.Trees;

public class Tree {
    Node root;

    public Tree(int value) {
        root = new Node(value);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(int value){
        insert(value,root);
    }
    private Node insert(int value, Node root) {
        //base case
        if (root == null) {
            return new Node(value);
        }
        //recursive case
        if (value > root.getValue()) {
            root.setRight(insert(value, root.getRight())); // inserting in the right subtree
        } else {
            root.setLeft(insert(value, root.getLeft())); // inserting in the left subtree
        }

        return root;
    }

    public Node remove(int value) {
        return remove(root, value);
    }

    private Node remove(Node root, int value) {
        if(root == null) return null;

        else if (root.getValue() > value) root.setLeft(remove(root.getLeft(), value));

        else if (root.getValue() < value) root.setRight(remove(root.getRight(), value));

        else{ // Found the node to be removed
            //Case 1: No child
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            }
            //Case 2: One child
            else if (root.getLeft() == null) {
                root = root.getRight();
            } else if (root.getRight() == null) {
                root = root.getLeft();

            } else { //Case 3: Two children
                // Find the minimum node in the right subtree of the node to be removed
                Node minimum = findMin(root.getRight());
                root.setValue(minimum.getValue());
                root.setRight(remove(root.getRight(), minimum.getValue()));
            }
        }
        return root;
    }

    private Node findMin(Node right) {
        while (right.getLeft() != null) {
            right = right.getLeft();
        }
        return right;
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void  postOrder(Node root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getValue() + " ");
        }
    }

    public Node search(int value){
        return search(root,value);
    }

    private Node search(Node root, int value){
        if(root == null || root.getValue() == value){
            return root;
        }

        if(value < root.getValue()){
            return search(root.getLeft(),value);
        }

        return search(root.getRight(),value);
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node root){
        if (root != null){
            System.out.print(root.getValue() + " ");
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());
        }
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root){
        if(root != null){
            inOrderTraversal(root.getLeft());
            System.out.print(root.getValue() + " ");
            inOrderTraversal(root.getRight());
        }
    }
}