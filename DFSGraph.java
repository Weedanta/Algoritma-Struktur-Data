import java.util.*;

public class DFSGraph {
    private LinkedList<Integer> adjList[];
    private boolean visited[];


    @SuppressWarnings("unchecked")
    public DFSGraph(int nodes) {
        adjList = new LinkedList[nodes];
        visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++) {
            adjList[i] = new LinkedList<>();
        }
    }


    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source); 
    }

    public void dfs(int startNode) {
        visited[startNode] = true;
        System.out.print(startNode + " ");
        for (int neighbor : adjList[startNode]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        int nodes = 12; 
        DFSGraph graph = new DFSGraph(nodes);

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

        System.out.println("DFS Traversal:");
        graph.dfs(1);
    }
}
