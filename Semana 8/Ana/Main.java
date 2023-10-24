import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

      for (int delegacia : delegacias) {
          g.bfs(delegacia, dist); 
      }

    int max = 0;
    List<Integer> x = new ArrayList<>();

    for (int banco : bancos) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, dist[i]);
        }

        if (min > max) {
            max = min;
            x.clear();
            x.add(banco);
        } else if (min == max) {
            x.add(banco);
        }
    }

    System.out.println(" ");
    System.out.println(x.size() + " " + max);

    for (int x1 : x) {
        System.out.print(x1 + " ");
    }

    System.out.println();
    }
}


