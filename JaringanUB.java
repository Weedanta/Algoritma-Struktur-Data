import java.util.*;

public class JaringanUB {
    static class Edge {
        int destination;
        int weight;
        
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    static List<List<Edge>> graph;
    static int N, M;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        N = scanner.nextInt();
        M = scanner.nextInt();
        
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        
        int result = dijkstra(start, end);
        System.out.println(result);
    }
    
    static int dijkstra(int start, int end) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(start, 0));
        
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int node = current.destination;
            int dist = current.weight;
            
            if (node == end) {
                return dist;
            }
            
            if (dist > distance[node]) {
                continue;
            }
            
            for (Edge edge : graph.get(node)) {
                int newDist = dist + edge.weight;
                
                if (newDist < distance[edge.destination]) {
                    distance[edge.destination] = newDist;
                    pq.add(new Edge(edge.destination, newDist));
                }
            }
        }
        
        return -1;
    }
}