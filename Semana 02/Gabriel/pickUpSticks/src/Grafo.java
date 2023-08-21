import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Grafo {
    int n_vert; //numero de vertices
    Integer[] estado; // -1 nao visitado, 0 visitando, 1 visitado
    Stack<Integer> p;
    Lista[] adj; //vetor de listas de adjacencia

    public Grafo(int v){
        n_vert = v;
        adj = new Lista[v];
        estado = new Integer[v];
        p = new Stack<Integer>();

        for(int i = 0; i < v; i++){
            estado[i] = -1;
            adj[i] = new Lista();
            adj[i].lista.add(new Vertice(i));
        }
    }
    public void mostraG(){
        for(int i = 0; i < n_vert; i++){
            System.out.println("\nVertice " + i + " : ");
            for(int j = 0; j < adj[i].lista.size(); j++){
                System.out.print(adj[i].lista.get(j).indice + " - ");
            }
        }
    }
    public boolean ordena(){
        boolean res = true;

        for(int i = 0; i < n_vert; i++){
            if(estado[i] == -1){
                res = dfs(i);
            }
            if(!res){
                break;
            }
        }
        return res;
    }
    public boolean dfs(int a){
        estado[a] = 0;
        boolean r = true;
        for(int i = 1; i < adj[a].lista.size(); i++){
            if(estado[adj[a].lista.get(i).indice] == -1){
                r = dfs(adj[a].lista.get(i).indice);
            }else if(estado[adj[a].lista.get(i).indice] == 0){
                r = false;
            }
        }
        estado[a] = 1;
        p.push(a);
        return r;
    }
    public void novaAresta(int a, int b){
        adj[a].lista.add(adj[b].lista.get(0));
    }
}
