import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args){
        
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int b = scanner.nextInt();
    int p = scanner.nextInt();

    Grafo g = new Grafo(n);

    for (int i = 0; i < m; i++) {
        int u = scanner.nextInt();
        int v = scanner.nextInt();
        int t = scanner.nextInt();

        g.adicionarAresta(u, v, t);
    }

    List<Integer> delegacias = new ArrayList<>();
    List<Integer> bancos = new ArrayList<>();

    for (int i = 0; i < p; i++) {
        delegacias.add(scanner.nextInt());
    }

    for (int i = 0; i < b; i++) {
        bancos.add(scanner.nextInt());
    }

    int[] dist = new int[n];

    for (int delegacias : delegacias) {
        bfs(g, delegacias, dist);
    }

    int max = 0;
    List<Integer> x = new ArrayList<>();

    for (int bancos : bancos) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, dist[i]);
        }

        if (min > max) {
            max = min;
            x.clear();
            x.add(bancos);
        } else if (min == max) {
            x.add(bancos);
        }
    }

    System.out.println(x.size() + " " + max);

    for (int x : x) {
        System.out.print(x + " ");
    }

    System.out.println();
    }
}


