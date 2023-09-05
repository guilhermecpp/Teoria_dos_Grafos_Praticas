import java.util.Collections;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Grafo {
    ArrayList<ArrayList<Integer>> adj; //listas de adjacência
    ArrayList<Integer> queue; //lista p dfs
    ArrayList<String> dic; //referencia
    ArrayList<String> cfc; //componente fortemente conectado
    ArrayList<String> todosCFC; //todos os componentes fortemente conectados
    ArrayList<String> n; //cfc's unitarios
    int []visited; //-1 = nao vis, 0 = visitado, 1 = classificado
    int tdi[]; //tempo de descoberta inicial
    int tdl[]; //tempo de descoberta final
    int t; //tempo de descoberta

    public Grafo(){
        adj = new ArrayList<>();
        dic = new ArrayList<>();
        queue = new ArrayList<>();
        cfc = new ArrayList<>();
        todosCFC = new ArrayList<>();
        n = new ArrayList<>();
        t = -1;
    }

    public void newEdge(String a, String b){
        if(!dic.contains(a)){
            dic.add(a);
            adj.add(new ArrayList<>());
        }
        if(!dic.contains(b)){
            dic.add(b);
            adj.add(new ArrayList<>());
        }
        adj.get(dic.indexOf(a)).add(dic.indexOf(b));
    }

    public void dfsPrepare(){
        //iniciando todos os arrays necessários com o tamanho máximo correto
        tdi = new int[dic.size()];
        tdl = new int[dic.size()];
        visited = new int[dic.size()];
        for(int i = 0; i < dic.size(); i++){
            visited[i] = -1;
        }
        n.clear();

        //aplicando dfs em todos os vértices não visitados
        for(int i = 0; i < dic.size(); i++){
            if(visited[i] == -1){
                dfs(i);
            }
        }
        /*ordena cfc's possiveis e apresenta cada grupo em uma linha*/
        Collections.sort(todosCFC);
        for(int i = 0; i < todosCFC.size(); i++){
            System.out.print("okay ");
            System.out.println(todosCFC.get(i));
        }

        /*ordena cfc's unitarios e apresenta eles na última linha*/
        Collections.sort(n);
        System.out.print("avoid ");
        for(int i = 0; i<n.size(); i++){
            System.out.print(n.get(i) + " ");
        }
    }
    public void dfs(int ind){
        t++; //t = 0
        tdi[ind] = t;
        tdl[ind] = t;
        visited[ind] = 0; //vértice ind = visitado
        queue.add(ind); // ind adicionado a fila

        //percorre todas as arestas de ind
        for(int i = 0; i < adj.get(ind).size(); i++){
            //se aresta não visitada ainda, aplica dfs nela
            if(visited[adj.get(ind).get(i)] == -1){
                dfs(adj.get(ind).get(i));
            }
            //quando aresta adjacente ja estiver visitada,
            //se seu tempo de descoberta low for menor que o do seu "pai",
            //o tdl do seu pai é igual ao seu.
            if(visited[adj.get(ind).get(i)] == 0){
                if(tdl[ind] > tdl[adj.get(ind).get(i)]) tdl[ind] = tdl[adj.get(ind).get(i)];
            }
        }
        //se o vértice atual for raiz de um cfc, seus tempos de descoberta são os mesmos
        if(tdi[ind] == tdl[ind]){
            //limpamos lista de cfc's
            cfc.clear();
            //adicionamos o vértice a lista de cfc's
            cfc.add(dic.get(ind));
            //dizemos que ele já foi classificado em um cfc
            visited[ind] = 1;
            //percorremos a fila de visitas, removendo todos os vértices da fila até o vértice raiz
            //adicionamos cada um deles a lista de cfc's atual
            for(int j = queue.size()-1; queue.get(j) != ind; j--){
                cfc.add(dic.get(queue.get(j)));
                visited[queue.get(j)] = 1;
                queue.remove(j);
            }
            queue.remove(queue.size()-1);
            //depois de transferir para a lista de cfc's todos os vértices até a raiz
            //avaliamos se o cfc encontrado é unitario(apenas um vértice)
            //se for, ele não pode ser considerado um cfc neste problema, e é adicionado a outra lista
            //se não, ele é ordenado e passa a fazer parte de uma lista de cfc's(todosCFC)
            if(cfc.size() > 1){
                String a = "";
                Collections.sort(cfc);
                for(int i = 0; i < cfc.size(); i++){
                    a = a.concat(cfc.get(i));
                    a = a.concat(" ");
                }
                todosCFC.add(a);
            }
            else{
                n.add(dic.get(ind));
                cfc.clear();
            }
        }
    }
}
