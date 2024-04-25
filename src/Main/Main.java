package src.Main;

import src.Trees.Tree;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(27);
        tree.insert(30);
        tree.insert(2);
        tree.insert(23);
        tree.insert(15);

        System.out.println("Caminhamento pré-ordem:");
        tree.preOrderTraversal();

        System.out.println("\nCaminhamento em ordem:");
        tree.inOrderTraversal();

        System.out.println("\nCaminhamento pós-ordem:");
    }
}
