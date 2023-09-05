import java.util.LinkedList;
import java.util.Stack;

public class Grafo {
    private int V;
    private LinkedList<Integer> adj[];
    private boolean visited[];

    public Grafo(int v) {
        V = v;
        visited = new boolean[v];
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
            visited[i] = false;
        }
    }

    public void adicionarAresta(int v, int w) {
        adj[v].add(w);
    }

    void dfs(int u, Stack<Integer> stack) {
        visited[u] = true;
        for (int v = 0; v < adj[u].size(); v++) {
            if (!visited[adj[u].get(v)]) {
                dfs(adj[u].get(v), stack);
            }

        }
        stack.add(0, u);
    }

    Grafo transpose() {
        Grafo gt = new Grafo(V);
        for (int i = 0; i < V; i++) {
            for (int adj : adj[i])
                gt.adicionarAresta(adj, i);
        }
        return gt;
    }

    public void mostra(Grafo gt) {
        System.out.println("Grafo: ");
        for (int i = 0; i < V; i++) {
            System.out.println("Vértice: " + i);
            for (int j = 0; j < gt.adj[i].size(); j++) {
                System.out.print(gt.adj[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    public boolean fortementeConectados(Grafo g) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackT = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (g.visited[i] == false)
                g.dfs(i, stack);
        }

        Grafo gt = g.transpose();

        // Partimos para dfs no transposto
        // Situação do stack
        // DFS a partir do stack percorrido na ordem errada
        // Inclusão de novo stack.
        // percorrendo valor do stack no índice u ao invés de u propriamente
        stackT.clear();
        for (int u = 0; u < stack.size(); u++) {
            if (!gt.visited[stack.get(u)]) {
                gt.dfs(stack.get(u), stackT);
            }
            if (stackT.size() == V) {
                return true;
            } else
                return false;
        }
        return false;
    }

    public void verificarValidadeGrafo(Grafo g) {
        boolean grafoValido = g.fortementeConectados(g);
        if (grafoValido == true) {
            System.out.println("Válido");
        } else {
            boolean encontrouArestaValida = false;

            // Copiar o grafo original
            // Se vamos desfazer as mudanças toda vez que testarmos uma aresta nova, pra que
            // salvar o original?
            /*
             * Grafo grafoOriginal = new Grafo(V);
             * for (int u = 0; u < V; u++) {
             * for (int v = 0; v < adj[u].size(); v++) {
             * // grafoOriginal.adicionarAresta(u, v);
             * grafoOriginal.adicionarAresta(u, adj[u].get(v));
             * }
             * }
             */
            for (int u = 0; u < V; u++) {
                for (int i = 0; i < adj[u].size() && !encontrouArestaValida; i++) {
                    // estabelecemos todos os vértices como não visitados
                    for (int k = 0; k < V; k++) {
                        visited[k] = false;
                    }
                    int v = adj[u].get(0);

                    // Inverta a aresta temporariamente
                    adj[u].remove(0);
                    adj[v].add(u);

                    // Verificar se o grafo invertido é fortemente conexo
                    boolean grafoModificadoValido = fortementeConectados(this);

                    // Se o grafo invertido for fortemente conexo, imprima a aresta
                    if (grafoModificadoValido) {
                        System.out.println(u + " - " + v);
                        encontrouArestaValida = true;
                    }
                    // Reverta a aresta de volta à sua forma original
                    // Aqui a remoção estava errada, removendo arestas de u quando na verdade
                    // deveria ser de v
                    // removendo do fim de v pois não há masi modificações no grafo durante o
                    // processo
                    // logo, o último item em v é o que havia sido adicionado.

                    adj[v].removeLast(); // Remover o valor, não o índice
                    adj[u].add(v);
                }
            }

            // Se não encontrou nenhuma aresta válida, imprima "Inválido"
            if (!encontrouArestaValida) {
                System.out.println("Inválido");
            }

            // Restaurar o grafo original
            // Precisa?
            /*
             * for (int u = 0; u < V; u++) {
             * for (int v : grafoOriginal.adj[u]) {
             * adj[u].add(v);
             * }
             * }
             */
        }
    }
}
