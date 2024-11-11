import java.util.*;

public class BFSGraph {
    private LinkedList<Integer> adjList[];

    @SuppressWarnings("unchecked")
    public BFSGraph(int nodes) {
        adjList = new LinkedList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source); 
    }

    public void bfs(int startNode) {
        boolean visited[] = new boolean[adjList.length];
        Queue<Integer> queue = new LinkedList<>();
        visited[startNode] = true;
        queue.add(startNode);

        System.out.println("BFS Traversal:");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int nodes = 12; 
        BFSGraph graph = new BFSGraph(nodes);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 3);
        graph.addEdge(5, 9);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        graph.addEdge(9, 7);
        graph.addEdge(9, 8);
        graph.addEdge(8, 11);
        graph.addEdge(11, 10);

        graph.bfs(1);
    }
}
