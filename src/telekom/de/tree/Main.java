package telekom.de.tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {
    static class AVLTree {
        static class Node {
            int key, height;
            Node left, right;

            Node(int d) {
                key = d;
                height = 1;
            }
        }

        Node root;

        int height(Node N) {
            if (N == null)
                return 0;
            return N.height;
        }

        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;
            return x;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;
            return y;
        }

        Integer getBalanceFactor(Node N) {
            if (N == null)
                return 0;
            return height(N.left) - height(N.right);
        }

        Node insertNode(Node node, int item) {
            if (node == null)
                return new Node(item);
            if (item < node.key)
                node.left = insertNode(node.left, item);
            else if (item > node.key)
                node.right = insertNode(node.right, item);
            else
                return node;

            node.height = 1 + max(height(node.left), height(node.right));
            int balanceFactor = getBalanceFactor(node);
            if (balanceFactor > 1) {
                if (item < node.left.key) {
                    return rightRotate(node);
                } else if (item > node.left.key) {
                    node.left = leftRotate(node.left);
                    return rightRotate(node);
                }
            }
            if (balanceFactor < -1) {
                if (item > node.right.key) {
                    return leftRotate(node);
                } else if (item < node.right.key) {
                    node.right = rightRotate(node.right);
                    return leftRotate(node);
                }
            }
            return node;
        }

        Node nodeWithMimumValue(Node node) {
            Node current = node;
            while (current.left != null)
                current = current.left;
            return current;
        }

        Boolean contains(Node root, int key) {
            Node current = root;
            while (current != null) {
                if (current.key == key) {
                    return true;
                }
                current = current.key >= key ? current.left : current.right;
            }
            return false;
        }

        Node deleteNode(Node root, int item) {
            if (root == null)
                return null;
            if (item < root.key)
                root.left = deleteNode(root.left, item);
            else if (item > root.key)
                root.right = deleteNode(root.right, item);
            else {
                if ((root.left == null) || (root.right == null)) {
                    Node temp;
                    if (root.left == null)
                        temp = root.right;
                    else
                        temp = root.left;
                    root = temp;
                } else {
                    Node temp = nodeWithMimumValue(root.right);
                    root.key = temp.key;
                    root.right = deleteNode(root.right, temp.key);
                }
            }
            if (root == null)
                return null;

            root.height = max(height(root.left), height(root.right)) + 1;
            int balanceFactor = getBalanceFactor(root);
            if (balanceFactor > 1) {
                if (getBalanceFactor(root.left) < 0) {
                    root.left = leftRotate(root.left);
                }
                return rightRotate(root);
            }
            if (balanceFactor < -1) {
                if (getBalanceFactor(root.right) > 0) {
                    root.right = rightRotate(root.right);
                }
                return leftRotate(root);
            }
            return root;
        }

    }

    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(new File("input.txt"));
        var writer = new FileWriter("output.txt");
        var tree = new AVLTree();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            var operation = scanner.next();
            if (operation.equals("+")) {
                tree.root = tree.insertNode(tree.root, scanner.nextInt());
                writer.write(tree.getBalanceFactor(tree.root).toString());
            }
            if (operation.equals("-")) {
                tree.root = tree.deleteNode(tree.root, scanner.nextInt());
                writer.write(tree.getBalanceFactor(tree.root).toString());
            }
            if (operation.equals("?")) {
                writer.write(tree.contains(tree.root, scanner.nextInt()).toString());
            }
            writer.write("\n");
        }

        writer.close();
    }
}
