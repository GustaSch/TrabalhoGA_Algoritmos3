package src.Main;

import src.Trees.Node;
import src.Trees.Tree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String filePath = "src/Graphviz/dotFiles/ArvoreBinGerado.dot";
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Tree tree = new Tree(54);
        tree.insert(6);
        tree.insert(70);
        tree.insert(56);
        tree.insert(63);
        tree.insert(80);
        tree.insert(92);


        //Tree tree = generateRandomTree();

        System.out.println("Caminhamento pré-ordem:");
        tree.preOrderTraversal();

        System.out.println("\nCaminhamento em ordem:");
        tree.inOrderTraversal();

        System.out.println("\nCaminhamento pós-ordem:");
        tree.postOrder();

        System.out.print("Digite o valor do nó a ser buscado: ");
        Integer nodeToSearch = sc.nextInt();
        sc.nextLine();
        Node value = tree.search(nodeToSearch);
        if (value != null) {
            System.out.println("Nó encontrado: " + value.getValue());
            System.out.println("Nó da esquerda: " + (value.getLeft() != null ? value.getLeft().getValue() : "Nó nulo"));
            System.out.println("Nó da direita: " + (value.getRight() != null ? value.getRight().getValue() : "Nó nulo"));
        } else {
            System.out.println("Nó não encontrado");
        }

        System.out.print("Digite o valor do nó a ser removido: ");
        Integer nodeToRemove = sc.nextInt();
        sc.nextLine();
        tree.remove(nodeToRemove);

        generateDotFile(filePath, tree);
        runDotFile();
    }

    public static Tree generateRandomTree() {
        Random random = new Random();
        int size = random.nextInt(20) + 1; // Tamanho aleatório entre 1 e 20
        int rootValue = random.nextInt(100); // Valor aleatório para a raiz
        Tree tree = new Tree(rootValue);
        for (int i = 0; i < size - 1; i++) {
            int value = random.nextInt(100); // Valor aleatório para cada nó
            tree.insert(value);
        }
        return tree;
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