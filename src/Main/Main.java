package src.Main;

import src.Trees.Node;
import src.Trees.Tree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String filePath = "src/Graphviz/dotFiles/ArvoreBinGerado.dot";
    public static void main(String[] args) {
        Tree tree = new Tree(12);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(13);
        tree.insert(17);
        tree.insert(1);
        tree.insert(9);
        tree.insert(19);


        System.out.println("Caminhamento pré-ordem:");
        tree.preOrderTraversal();

        System.out.println("\nCaminhamento em ordem:");
        tree.inOrderTraversal();

        System.out.println("\nCaminhamento pós-ordem:");
        tree.postOrder();

        Node value = tree.remove(15);

        generateDotFile(filePath, tree);

        runDotFile();
    }

    private static void runDotFile() {
        try {
            String[] command = {"cmd.exe", "/c", "start", "\"\"", "src\\Graphviz\\run.bat"};
            Process process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateDotFile(String filePath, Tree tree) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("digraph ArvoreBinaria {\n" +
                    "node [shape=circle, style=filled, color=black, fillcolor=\"#Add8e6\"];\n" +
                    "edge [color=black];");

            writeNodes(tree.getRoot(), bw);

            bw.write("\n}");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeNodes(Node node, BufferedWriter bw) throws IOException {
        if (node != null) {
            if (node.getLeft() != null) {
                bw.write(node.getValue() + " -> " + node.getLeft().getValue() + ";");
            }
            if(node.getRight() != null) {
                bw.write(node.getValue() + " -> " + node.getRight().getValue() + ";");
            }
            writeNodes(node.getLeft(), bw);
            writeNodes(node.getRight(), bw);
        }
    }
}