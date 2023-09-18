import java.util.ArrayList;
import java.util.PriorityQueue;

class Grafo {
    ArrayList<Aresta>[] adj; //lista de adjacências
    boolean[] visited;
    //Arestas ordenadas por peso
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

    //Encontrar a árvore mínima entre dois vértices
public void prim(int u, int v) {
    visited[u] = true;
    //adiciona as arestas conectadas a 
    //a fila de prioridade
    for (int i = 0; i < adj[u].size(); i++) {
        Prioridade.add(adj[u].get(i));
    }
    //Construindo a árvore geradora miníma
    //(processa todas as arestas)
    while (!Prioridade.isEmpty()) {
        Aresta a = Prioridade.poll();
        //verifica se o vértice adjacente
        //já foi visitado
        if (!visited[a.destino]) {
            visited[a.destino] = true;
            custoMin = Math.max(custoMin, a.peso);

            //adiciona todas as arestas conectadas
            //ao vértice destino na fila de
            //prioridade
            for (int i = 0; i < adj[a.destino].size(); i++) {
                Prioridade.add(adj[a.destino].get(i));
            }
            
            // Se o vértice 'v' foi visitado, saia do loop
            if (a.destino == v) {
                break;
            }
        }
        // Se o grafo não é conectável, saia do loop
        if (visited[v]) {
            break;
        }
    }
    if(custoMin <= 0){
        System.out.println("NO PATH");
    }else{
         System.out.println(custoMin);
    }

    }
}