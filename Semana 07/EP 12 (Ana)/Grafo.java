import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo {
    int V;
    static boolean visited[];
    static int time[];
    ArrayList<Integer> adj[];

    public Grafo(int V) {
        this.V = V;
        adj = new ArrayList[V];
        visited = new boolean[V];
        time = new int[V];
        for (int i =0; i < V; i++){ 
            adj[i] = new ArrayList<>();
            time[i] = -1;
            visited[i] = false;
        }
        
    }

    void adicionarAresta(int u, int v) {
        adj[u].add(v);
    }

    List<Integer> adj(int u) { return adj[u];}
    
    //achando o tempo minimo entre dois vértices (a e b)
    public int bfs (int a, int b) {
        
        Queue<Integer> q = new LinkedList<Integer >() ;

        visited[a] = true;
        //peso[a] = 0;
        q.add(a);

        while (!q.isEmpty()) {
            int aux = q.remove() ;

            if (aux == b) {
                return time[aux];
            }

            for (int w : adj[aux]) {
                if (!visited[w]) {
                    visited[w] = true;
                    q.add(w);
                    time[w] = time[aux] + 1; 
                }
            }
        }
        // Se não houver caminho para o destino
        return -1;
    }
}
