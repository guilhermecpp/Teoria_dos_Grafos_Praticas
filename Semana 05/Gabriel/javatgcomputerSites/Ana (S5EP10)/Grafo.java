import java.util.ArrayList;
import java.util.PriorityQueue;

class Grafo {
    ArrayList<Aresta>[] adj;
    boolean[] visited;
    PriorityQueue<Aresta> Prioridade = new PriorityQueue<>();
    int custoMin = 0;

    public Grafo(int v) {
        adj = new ArrayList[v];
        visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
            visited[i] = false;
        }
    }

    public void adicionarAresta(int u, int v, int peso) {
        adj[u].add(new Aresta(u, v, peso));
        adj[v].add(new Aresta(v, u, peso));
    }

    public void prim() {
        visited[0] = true;
        for (int i = 0; i < adj[0].size(); i++) {
            Prioridade.add(adj[0].get(i));
        }
        while (!Prioridade.isEmpty()) {
            Aresta a = Prioridade.poll();
            if (!visited[a.destino]) {
                visited[a.destino] = true;
                custoMin = a.peso;
                for (int i = 0; i < adj[a.destino].size(); i++) {
                    Prioridade.add(adj[a.destino].get(i));
                }
            }
        }
        if(custoMin == 0){
            System.out.println("IMPOSSÍVEL");
        }
    }
}
