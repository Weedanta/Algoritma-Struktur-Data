package HeapDanBST;

import java.util.*;

public class TabletKuno {

    static class Node {
        int value;
        Node left, right;
        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    static class BST {
        Node root;

        public BST() {
            root = null;
        }

        public void insert(int value) {
            root = insertRec(root, value);
        }

        private Node insertRec(Node root, int value) {
            if (root == null) {
                root = new Node(value);
                return root;
            }
            if (value < root.value)
                root.left = insertRec(root.left, value);
            else if (value > root.value)
                root.right = insertRec(root.right, value);
            return root;
        }

        public void delete(int value) {
            root = deleteRec(root, value);
        }

        private Node deleteRec(Node root, int value) {
            if (root == null)
                return root;

            if (value < root.value)
                root.left = deleteRec(root.left, value);
            else if (value > root.value)
                root.right = deleteRec(root.right, value);
            else {
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;
                root.value = minValue(root.right);
                root.right = deleteRec(root.right, root.value);
            }
            return root;
        }

        private int minValue(Node root) {
            int minv = root.value;
            while (root.left != null) {
                root = root.left;
                minv = root.value;
            }
            return minv;
        }

        public boolean search(int value) {
            return searchRec(root, value);
        }

        private boolean searchRec(Node root, int value) {
            if (root == null)
                return false;
            if (value == root.value)
                return true;
            if (value < root.value)
                return searchRec(root.left, value);
            return searchRec(root.right, value);
        }

        public int findMin() {
            Node current = root;
            if (current == null)
                return -1;
            while (current.left != null)
                current = current.left;
            return current.value;
        }

        public int findMax() {
            Node current = root;
            if (current == null)
                return -1;
            while (current.right != null)
                current = current.right;
            return current.value;
        }

        public void inorderTraversal() {
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.print(root.value + " ");
                inorderRec(root.right);
            }
        }

        public void preorderTraversal() {
            preorderRec(root);
            System.out.println();
        }

        private void preorderRec(Node root) {
            if (root != null) {
                System.out.print(root.value + " ");
                preorderRec(root.left);
                preorderRec(root.right);
            }
        }

        public void postorderTraversal() {
            postorderRec(root);
            System.out.println();
        }

        private void postorderRec(Node root) {
            if (root != null) {
                postorderRec(root.left);
                postorderRec(root.right);
                System.out.print(root.value + " ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nOperations = sc.nextInt();
        sc.nextLine();
        BST bst = new BST();

        for (int i = 0; i < nOperations; i++) {
            String commandLine = sc.nextLine();
            String[] parts = commandLine.split(" ");

            if (parts[0].equals("INSERT")) {
                int value = Integer.parseInt(parts[1]);
                bst.insert(value);
            } else if (parts[0].equals("DELETE")) {
                int value = Integer.parseInt(parts[1]);
                bst.delete(value);
            } else if (parts[0].equals("SEARCH")) {
                int value = Integer.parseInt(parts[1]);
                boolean found = bst.search(value);
                if (found)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            } else if (parts[0].equals("FIND_MIN")) {
                int minValue = bst.findMin();
                System.out.println(minValue);
            } else if (parts[0].equals("FIND_MAX")) {
                int maxValue = bst.findMax();
                System.out.println(maxValue);
            } else if (parts[0].equals("INORDER")) {
                bst.inorderTraversal();
            } else if (parts[0].equals("PREORDER")) {
                bst.preorderTraversal();
            } else if (parts[0].equals("POSTORDER")) {
                bst.postorderTraversal();
            }
        }
    }
}
