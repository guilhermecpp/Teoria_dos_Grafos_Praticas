import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {
    ArrayList<Integer>[] adj;
    ArrayList<String> dic;
    double[] value;
    boolean[] visited;

    public Grafo(int v){
        //Teremos v planetas na galáxia, mas reservaremos o espaço 0 para a terra *
        //Logo, devemos armazenar v+1 vértices, todos os planetas da galáxia e a terra
        adj = new ArrayList[v+1];
        value = new double[v+1];
        for(int i = 0; i<v+1; i++){
            adj[i] = new ArrayList<>();
            value[i] = -1;
        }
        visited = new boolean[v+1];
        dic = new ArrayList<>();
        dic.add("*");

    }
    public void findImport(){
        visited[0] = true;
        double[] tax = new double[visited.length];
        Arrays.fill(tax, 0.0);

        ArrayList<Integer> q = new ArrayList<>();
        int aux;
        q.add(0);

        while(!q.isEmpty()){
            aux = q.remove(0);
            //System.out.println("Visitando adjacentes de " + dic.get(aux));
            for(int w : adj[aux]){
                if(!visited[w]){
                    visited[w] = true;
                    //System.out.println("Subtraindo " + (tax[aux]*100) + "% de " + dic.get(w));
                    //System.out.println("Antes - " + value[w] + " vs Agora: " + (value[w] - value[w]*tax[aux]));
                    value[w] = value[w] - value[w]*tax[aux];
                    tax[w] += tax[aux] + 0.05;
                    q.add(w);
                }
            }
        }
    }
    public void addEdge(int a, int b){
        adj[a].add(b);
        adj[b].add(a);
    }
    public void showValue(){
        int maior = 1;
        for(int w = 2; w < value.length; w++){
            if(visited[w]){
                //System.out.println("Comparando " + dic.get(w) + " - "+ value[w] + " com " + dic.get(maior) +" - "+ value[maior]);
                if(value[w] > value[maior]){
                    maior = w;
                }else if(value[w] == value[maior]){
                    if(dic.get(w).compareTo(dic.get(maior)) < 0)
                        maior = w;
                }
            }
        }
        System.out.println("Import from " + dic.get(maior));
    }
}
