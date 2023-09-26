import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int INF = 2000000000;
    static int n;
    static int[] path = new int[27];
    static double[] val = new double[27];
    static boolean[][] adj = new boolean[27][27];

    static char bfs() {
        Queue<Edge> q = new LinkedList<>();
        Arrays.fill(path, INF);
        path[26] = 0;
        q.add(new Edge(26, 0));

        while (!q.isEmpty()) {
            Edge e = q.poll();
            for (int i = 0; i < 26; ++i) {
                if (adj[e.first][i] && e.second + 1 < path[i]) {
                    path[i] = e.second + 1;
                    q.add(new Edge(i, e.second + 1));
                }
            }
        }

        double mx = 0.0;
        int mxi = 0;
        for (int i = 0; i < 26; ++i) {
            double d = val[i] - (val[i] * 0.05 * (path[i] - 1));
            if (d > mx) {
                mx = d;
                mxi = i;
            }
        }
        return (char) (mxi + 'A');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char c;
        String s;

        while (scanner.hasNextInt()) {
            n = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (int i = 0; i < 27; ++i) {
                Arrays.fill(adj[i], false);
                path[i] = INF;
                val[i] = 0.0;
            }

            for (int i = 0; i < n; ++i) {
                String[] input = scanner.nextLine().split(" ");
                c = input[0].charAt(0);
                c -= 'A';
                val[c] = Double.parseDouble(input[1]) * 100.0;
                s = input[2];

                for (int j = 0; j < s.length(); ++j) {
                    if (s.charAt(j) == '*')
                        adj[c][26] = adj[26][c] = true;
                    else
                        adj[c][s.charAt(j) - 'A'] = adj[s.charAt(j) - 'A'][c] = true;
                }
            }

            System.out.println("Import from " + bfs());
        }
        scanner.close();
    }

    static class Edge {
        int first;
        int second;

        Edge(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
