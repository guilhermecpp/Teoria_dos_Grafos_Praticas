import java.io.*;
import java.util.*;
 
public class Grafo {
 
    public int v;
    private LinkedList<Integer> adj[]; //Lista de adjacências
    static int tempo;
    public int contador;

    public Grafo(int v){
        this.v = v;
        this.contador = 0;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    void adicionarAresta(int u, int v) {
        adj[u].add(v); 
        adj[v].add(u);    
    }

    List<String> pontes(){
        boolean visitado[] = new boolean[v];
        int pre[] = new int[v];
        int low[] = new int[v];
        int pai[] = new int[v];
        List<String> listaPontes = new ArrayList<>();

        for (int i = 0; i < v; i++){
            // nenhum vértice é pai e nenhum vértice
            // foi visitado
            pai[i] = -1;
            visitado[i] = false;
        }

        //Aplica o dfs em todos os vértices
        for (int i = 0; i < v; i++)
            if (visitado[i] == false)
                dfs(i, visitado, pre, low, pai, listaPontes);

        Collections.sort(listaPontes); //ordena as pontes em ordem crescente
        return listaPontes; //Retorna uma lista com todas as pontes descobertas
    }

    void dfs(int u, boolean visitado[], int pre[], int low[], int pai[], List<String> listaPontes){
        visitado[u] = true; //u é o próximo vértice a ser visitado
        pre[u] = tempo++;
        low[u] = tempo++;
        Iterator<Integer> i = adj[u].iterator(); //Percorre os vértices adjacentes
        //Enquanto existir vértice adjacente, o dfs continua
        while (i.hasNext()){
            int v = i.next(); //v é o vértice adjacente de u
            if (visitado[v] == false){
                pai[v] = u; //Se v não foi visitado, u se torna pai de v
                dfs(v, visitado, pre, low, pai, listaPontes); //dfs em v
                //verifica se v possui conexão com seus ancestrais
                low[u]  = Math.min(low[u], low[v]); 

                // Se o vértice mais baixo acessível da subárvore em v estiver 
                // abaixo de u na árvore DFS, então u-v é uma ponte
                if (low[v] > low[u]) {
                    listaPontes.add(u + " " + v); //adiciona a aresta a lista de pontes
                    contador++;
                }
            }
            // Atualiza o valor baixo de u para chamadas de função pai.
            else if (v != pai[u])
                low[u]  = Math.min(low[u], pre[v]);
        }
    }
}