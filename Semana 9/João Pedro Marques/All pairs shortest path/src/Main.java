import java.util.*;

class Main {
    private static final int INF = 1000000000;

    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int q = scanner.nextInt();

            if (n == 0 && m == 0 && q == 0) {
                break;
            }

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                graph.get(u).add(new Edge(v, w));
                graph.get(v).add(new Edge(u, w));
            }

            while (q-- > 0) {
                int source = scanner.nextInt();
                int destination = scanner.nextInt();

                int[] dist = new int[n];
                Arrays.fill(dist, INF);
                dist[source] = 0;

                Queue<Integer> queue = new LinkedList<>();
                queue.add(source);

                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (Edge edge : graph.get(u)) {
                        int v = edge.v;
                        int w = edge.w;
                        if (dist[v] > dist[u] + w) {
                            dist[v] = dist[u] + w;
                            queue.add(v);
                        }
                    }
                }

                // Check for unreachable nodes
                if (dist[destination] == INF) {
                    System.out.println("Impossible");
                } else if (dist[destination] == -INF) {
                    System.out.println("-Infinity");
                } else {
                    System.out.println(dist[destination]);
                }
            }

            System.out.println();
        }
    }
}
