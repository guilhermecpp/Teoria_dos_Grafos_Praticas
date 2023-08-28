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
        int tempoDescoberta[] = new int[v];
        int menorCaminho[] = new int[v];
        int verticePai[] = new int[v];
        List<String> listaPontes = new ArrayList<>();

        for (int i = 0; i < v; i++){
            // nenhum vértice é pai e nenhum vértice
            // foi visitado
            verticePai[i] = -1;
            visitado[i] = false;
        }

        //Aplica o dfs em todos os vértices
        for (int i = 0; i < v; i++)
            if (visitado[i] == false)
                dfs(i, visitado, tempoDescoberta, menorCaminho, verticePai, listaPontes);

        Collections.sort(listaPontes); //ordena as pontes em ordem crescente
        return listaPontes; //Retorna uma lista com todas as pontes descobertas
    }

    void dfs(int u, boolean visitado[], int tempoDescoberta[],
                    int menorCaminho[], int verticePai[], List<String> listaPontes){
        visitado[u] = true; //u é o próximo vértice a ser visitado
        tempoDescoberta[u] = tempo++;
        menorCaminho[u] = tempo++;
        Iterator<Integer> i = adj[u].iterator(); //Percorre os vértices adjacentes
        //Enquanto existir vértice adjacente, o dfs continua
        while (i.hasNext()){
            int v = i.next(); //v é o vértice adjacente de u
            if (visitado[v] == false){
                verticePai[v] = u; //Se v não foi visitado, u se torna pai de v
                dfs(v, visitado, tempoDescoberta, menorCaminho, verticePai, listaPontes); //dfs em v
                //verifica se v possui conexão com seus ancestrais
                menorCaminho[u]  = Math.min(menorCaminho[u], menorCaminho[v]); 

                // Se o vértice mais baixo acessível da subárvore em v estiver 
                // abaixo de u na árvore DFS, então u-v é uma ponte
                if (menorCaminho[v] > tempoDescoberta[u]) {
                    listaPontes.add(u + " " + v); //adiciona a aresta a lista de pontes
                    contador++;
                }
            }
            // Atualiza o valor baixo de u para chamadas de função pai.
            else if (v != verticePai[u])
                menorCaminho[u]  = Math.min(menorCaminho[u], tempoDescoberta[v]);
        }
    }
}