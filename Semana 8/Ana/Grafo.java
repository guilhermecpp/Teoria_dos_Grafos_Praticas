import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo {
    int V;
    List<List<Aresta>> arestas;
    int maxDistancia = 0;

    public Grafo(int V) {
        this.V = V;
        arestas = new ArrayList<>(V);

        for (int i = 0; i < V; i++){ 
            arestas.add(new ArrayList<>());

        }
    }

    void adicionarAresta(int u, int v, int peso) {
        arestas.get(u).add(new Aresta(v,peso));
        arestas.get(v).add(new Aresta(u,peso));
    }

    public List<Aresta> getEdges(int v) {
        return arestas.get(v);
    }

    /*
    N total de vértices
    M total de arestas
    B número de bancos
    P número de delegacias

    Localição dos bancos:
    U vértice de origem
    V vértice de destino
    T tempo de trânsito (peso) 
    */

    public int bfs(int v, int[] dist) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[v] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (Aresta a : getEdges(u)) {
                int n_dist = dist[u] + a.peso;

                if (n_dist < dist[a.destino]) {
                    dist[a.destino] = n_dist;
                    q.add(a.destino);
                }
            }
        }

        return 0; 
    }

}
