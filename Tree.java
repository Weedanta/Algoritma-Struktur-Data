import java.util.*;

public class Tree<T> {
    Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }

    public Tree(T data) {
        root = new Node<T>(data);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(T dataParent, T data) {
        Node<T> node = getNode(root, dataParent);
        if (node != null) {
            node.childs.add(new Node<>(data));
        } else {
            System.out.println("Parent node not found: " + dataParent);
        }
    }

    public Node<T> getNode(Node<T> node, T data) {
        if (node.data.equals(data)) {
            return node;
        }

        for (Node<T> currNode : node.childs) {
            Node<T> returnNode = getNode(currNode, data);
            if (returnNode != null) {
                return returnNode;
            }
        }

        return null;
    }

    public void access() {
        System.out.print(root.data);
        access(root, " ");
    }

    private void access(Node<T> node, String space) {
        System.out.println();
        for (Node<T> currNode : node.childs) {
            System.out.print(space + "->" + currNode.data);
            access(currNode, space + " ");
        }
    }

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("DEKAN");

        // WADEK
        tree.add("DEKAN", "Laboratorium");
        tree.add("DEKAN", "BPPM");
        tree.add("DEKAN", "PSIK");
        tree.add("DEKAN", "BPJ");
        tree.add("DEKAN", "GJM");
        tree.add("DEKAN", "IRO");
        tree.add("DEKAN", "UPKP");
        tree.add("DEKAN", "UPMD");
        tree.add("DEKAN", "UKLTKSP");
        tree.add("DEKAN", "Bagian Tata Usaha");
        tree.add("DEKAN", "Dapartemen Teknik Informatika");
        tree.add("DEKAN", "Dapartemen Sistem Informasi");

        // Bawahan Bagian Tata Usaha
        tree.add("Bagian Tata Usaha", "Subbagian Akademik, Kemahasiswaan, Alumni, Kerja Sama, dan Kewirausahaan Mahasiswa");
        tree.add("Bagian Tata Usaha", "Subbagian Umum dan Aset");
        tree.add("Bagian Tata Usaha", "Subbagian Keuangan dan Kepegawaian");

        // Bawahan Teknik Informatika
        tree.add("Dapartemen Teknik Informatika", "Sekretaris Departemen");
        tree.add("Dapartemen Teknik Informatika", "UJM (Unit Jaminan Mutu)");
        tree.add("Dapartemen Teknik Informatika", "Prodi Sarjana Teknik Informatika");
        tree.add("Dapartemen Teknik Informatika", "Prodi Sarjana Teknik Komputer");
        tree.add("Dapartemen Teknik Informatika", "Prodi Magister Ilmu Komputer");

        // Bawahan Sistem Informasi
        tree.add("Dapartemen Sistem Informasi", "Sekretaris Departemen");
        tree.add("Dapartemen Sistem Informasi", "UJM (Unit Jaminan Mutu)");
        tree.add("Dapartemen Sistem Informasi", "Prodi Sarjana Sistem Informasi");
        tree.add("Dapartemen Sistem Informasi", "Prodi Sarjana Pendidikan Teknologi Informasi");
        tree.add("Dapartemen Sistem Informasi", "Prodi Sarjana Teknologi Informasi");

        // Display the tree structure
        System.out.println("Struktur Dekanat:");
        tree.access();
    }
}

class Node<T> {
    T data;
    List<Node<T>> childs;

    public Node(T data) {
        this.data = data;
        childs = new LinkedList<>();
    }
}
