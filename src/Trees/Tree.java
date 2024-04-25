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

    public Node insert(int value) {
        return insert(value, root);
    }

    private Node insert(int value, Node root) {
        //base case
        if (root == null) {
            return new Node(value);
        }

        if (value > root.getValue()) {
            root.setRight(insert(value, root.getRight()));
        } else {
            root.setLeft(insert(value, root.getLeft()));
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
}
