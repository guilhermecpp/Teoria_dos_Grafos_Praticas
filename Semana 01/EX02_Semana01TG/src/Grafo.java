import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
    int n_vert; //numero de vertices
    Lista[] adj; //vetor de listas de adjacencia
    private int res; //resposta final à coloração

    public Grafo(int v){
        n_vert = v;
        adj = new Lista[v];
        res = 0;
        for(int i = 0; i < v; i++){
            adj[i] = new Lista();
            adj[i].lista.add(new Vertice(i));
        }
    }
    public void novaAresta(int a, int b){
        adj[a].lista.add(adj[b].lista.get(0));
        adj[b].lista.add(adj[a].lista.get(0));
    }

    public void mostraG(){
        System.out.println("Grafo: ");
        for(int i = 0; i < adj.length; i++){
            for(int j = 0; j < adj[i].lista.size(); j++){
                System.out.println(adj[i].lista.get(j).indice + " de cor " + adj[i].lista.get(j).cor);
            }
            System.out.println();
        }
    }
    public int decideCor(int a){
        int c1 = 0, c2 = 0, nl = 0;
        for(int i = 1; i < adj[a].lista.size(); i++){
            if(adj[a].lista.get(i).cor == 1){
                c1++;
            }else if(adj[a].lista.get(i).cor == 2){
                c2++;
            }else nl++;
        }
        if(c1 != 0 && c2 != 0){
            return 3;
        }
        if(c2 == 0){
            return 2;
        }else return 1;
    }
    /*Função: ColoreDFS
    * Recebe como parametro o indice do vértice inicial, atribui a ele uma cor,
    * avalia a validez da cor, então entra em um laço for que recursivamente chama a função
    * para todos os vértices adjacentes ao inicial, promovendo uma busca em profundidade colorindo cada um
    * que ainda não tenha sido colorido*/
    public void coloreDFS(int a){
        adj[a].lista.get(0).cor = decideCor(a);
        if(adj[a].lista.get(0).cor == 3){
            setRes(1);
            return;
        }
        for(int i = 1; i < adj[a].lista.size(); i++) {
            if (adj[a].lista.get(i).cor == 0) {
                coloreDFS(adj[a].lista.get(i).indice);
            }
        }
    }

    /*Função: ColoreBFS
    * Recebe o primeiro vértice como parametro, cria uma fila e coloca este vértice nela,
    * então, num laço while, que se repetirá enquanto a fila não está vazia, percorre todos os
    * vértices adjacentes ao véertice inicial da fila, adicionando-os a ela se eles ainda não foram
    * coloridos e se eles ainda não estão presentes na fila.
    * Por fim, colore o primeiro vértice da fila com sua respectiva cor, testa se sua cor é valida,
    * então remove-o do inicio da fila, fazendo com que o primeiro de seus vértices adjacentes ocupe
    * esta posição, com o qual o proximo ciclo de while vai trabalhar, assim, colorindo o vetor de acordo
    * com a estratégia BFS de busca*/
    public void coloreBFS(int a){
        ArrayList<Integer> fila = new ArrayList<>();
        fila.add(a);

        while(!fila.isEmpty()){
            for(int i = 1; i < adj[fila.get(0)].lista.size(); i++) {
                if(!(fila.contains(adj[fila.get(0)].lista.get(i).indice)) && adj[fila.get(0)].lista.get(i).cor == 0){
                    fila.add(adj[fila.get(0)].lista.get(i).indice);
                    System.out.println("Adicionando " + adj[fila.get(0)].lista.get(i).indice + " a fila");
                }
            }
            adj[fila.get(0)].lista.get(0).cor = decideCor(fila.get(0));
            if(adj[fila.get(0)].lista.get(0).cor == 3){
                setRes(1);
                return;
            }
            fila.remove(0);
        }
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

}
