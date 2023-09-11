//Código produzido com base no Algoritmo de Prim para encontrar arvore geradora minima
/* Usei como referência vídeo explicativo do youtube, adaptei o algoritmo para java fazendo
* as devidas alterações.
* Link: https://www.youtube.com/watch?v=3iWBD63OQDE&t=566s.
* */
//Gabriel Antonio - 2023
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        int s1 = 0, s2 = 0;//somas 1 e 2
        int p, s, val;
        ArrayList<Dupla>[] adj;//Grafo em listas de adj
        boolean[] visited;
        PriorityQueue<Dupla> lPrior = new PriorityQueue<>();

        n = sc.nextInt();

        for(int i = 0; i < n-1; i++){
            p = sc.nextInt();
            s = sc.nextInt();
            s1 += sc.nextInt();
        }
        //iniciando grafo
        adj = new ArrayList[n];
        visited = new boolean[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
            visited[i] = false;
        }

        //arestas extras
        m = sc.nextInt();
        for(int i = 0; i < m; i++){
            p = sc.nextInt()-1;
            s = sc.nextInt()-1;
            val = sc.nextInt();
            adj[p].add(new Dupla(val, s));//adicionando aresta s em p com valor val
            adj[s].add(new Dupla(val, p));//adicionando aresta p em s com valor val
        }

        //arestas originais
        m = sc.nextInt();
        for(int i = 0; i < m; i++){
            p = sc.nextInt()-1;
            s = sc.nextInt()-1;
            val = sc.nextInt();
            adj[p].add(new Dupla(val, s));//adicionando aresta s em p com valor val
            adj[s].add(new Dupla(val, p));//adicionando aresta p em s com valor val
        }

        //Algoritmo de Prim a partir da raiz = 0
        visited[0] = true; //marco como visitado
        //adicionando todos os vértices a fila de prioridade
        for(int i = 0; i < adj[0].size(); i++){
            lPrior.add(new Dupla(adj[0].get(i).first, adj[0].get(i).second));
        }
        //enquanto a fila de prioridade não for vazia:
        /*Colocar em dupla auxiliar "a" o menor elemento(topo da fila de prioridade)
        * se vértice ligado a aresta "a"(segundo elemento da dupla) não tiver sido visitado
        * somamos a carga da aresta "a" aresta ao total, marcamos como visitado e aplicamos Prim a ele,
        * adicionando a fila de prioridade todos os seus adjacentes, a partir daí laço while garante a repetição
        * em todos os elementos do grafo*/

        while(!lPrior.isEmpty()){
            //cria dupla auxiliar que guarda primeiro elemento da fila e retira ele da fila(poll)
            Dupla a = new Dupla(lPrior.peek().first, lPrior.poll().second);
            //se não visitado
            if(!visited[a.second]){
                s2 = s2 + a.first;//soma ao total o valor da aresta
                visited[a.second] = true; //marca vértice como visitado
                //adiciona todos os adjacentes ao vértice na fila
                for(int i = 0; i < adj[a.second].size(); i++){
                    lPrior.add(new Dupla((adj[a.second].get(i).first), adj[a.second].get(i).second));
                }
                //a fila é reordenada ao adicionar um novo item de acordo com a ordem de prioridades crescente
                //(primeiro elemento sempre o menor)
            }
        }
        //printa primeira soma e segunda soma
        System.out.println(s1+"\n"+s2);
    }
}