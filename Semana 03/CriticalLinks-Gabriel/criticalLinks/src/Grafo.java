import java.util.ArrayList;
public class Grafo {
    ArrayList<Integer>[] g; //Listas de adjacências
    int dfsi[]; //Array de classificação inicial
    int dfsf[]; //Array de classificação final (low)
    boolean visited[]; //Array de vértices visitados
    int parent[]; //Array que guarda pais

    ArrayList<Ponte> pontes; //Lista de pontes encontradas
    int t; //Tempo de descoberta

    public Grafo(int v){
        dfsi = new int[v];
        dfsf = new int[v];
        parent = new int[v];
        visited = new boolean[v];

        pontes = new ArrayList<Ponte>();

        g = new ArrayList[v];
        for(int i = 0; i < v; i++){
            g[i] = new ArrayList<Integer>();
            visited[i] = false;
        }

        t = 0;
    }
    public void nAresta(int a, int b){
        g[a].add(b);
    }

    /*prPonte: Percorre a lista de pontes e avalia se uma determinada ponte já foi descrita
    Retorna false se a aresta da entrada já está presente e true se ainda não foi adicionada
    */

    public boolean prPonte(int a, int b){
        for(int i = 0; i < pontes.size(); i++){
            if((a == pontes.get(i).a && b == pontes.get(i).b) || (a == pontes.get(i).b && b == pontes.get(i).a)){
                return false;
            }
        }
        return true;
    }
    /*ponteDFS: algoritmo de DFS recursivo que percorre todos os adjacentes ao parâmetro 'int v' e classifica
    * os seus tempos de descoberta*/
    public void ponteDFS(int v){
        t += 1;
        visited[v] = true;
        dfsi[v] = t;
        dfsf[v] = t;

        for(int i = 0; i < g[v].size(); i++){
            /* Se o vértice que produz aresta com o VAtual não for visitado entra aqui*/
            if(!visited[g[v].get(i)]){
                parent[g[v].get(i)] = v; //marcamos o VAtual como pai deste vértice
                ponteDFS(g[v].get(i)); //aplica-se dfs no vértice

                /*Se, apos o dfs, o tempo de descoberta final do VAtual for maior que o tempo de descoberta
                final do vértice, então o tempo de descoberta final do VAtual é atualizado para o mesmo do vértice.

                Isso significa também que existe outro caminho até o vértice além do VAtual
                 */
                if(dfsf[v] > dfsf[g[v].get(i)]){
                    dfsf[v] = dfsf[g[v].get(i)];
                }
                /* Ainda, se o tempo de descoberta final do vértice visitado for maior que o tempo de descoberta inicial
                do VAtual, então declaramos a aresta como ponte.

                Isso significa que, não existe outro caminho até o vértice visitado a não ser o que passa pelo VAtual,
                logo esta aresta é uma ponte.
                 */
                if(dfsf[g[v].get(i)] > dfsi[v]){
                    if(prPonte(v,g[v].get(i))) pontes.add(new Ponte(v, g[v].get(i)));
                }
            /* Se o vértice que produz aresta com o VAtual não for o pai dele e se
            o tempo de descoberta final do VA for maior do que o tempo de descoberta final do vértice
            então o tempo de descoberta final do VAtual será igual ao do vértice.

            Isso significa que existe outro caminho até o vértice avaliado que não passa pelo VAtual
             */
            }else if(g[v].get(i) != parent[v]){
                if(dfsf[v] > dfsf[g[v].get(i)]){
                    dfsf[v] = dfsf[g[v].get(i)];
                }
            }
        }
    }

    public void pontePonte(){
        for(int i = 0; i < g.length; i++){
            if(!visited[i]){
                ponteDFS(i);
            }
        }
        ordenaLinks(pontes);
        System.out.println(pontes.size() + " critical links");
        for(int j = 0; j < pontes.size(); j++){
            System.out.println(pontes.get(j).a + " - " + pontes.get(j).b);
        }
    }
    public void ordenaLinks(ArrayList<Ponte> p){
        int tam = p.size();
        Ponte aux;
        while(tam > 1){
            for(int v = 0; v < tam-1; v++){
                if(p.get(v).maior(p.get(v+1)) == 1){
                    aux = p.get(v+1);
                    p.set(v+1, p.get(v));
                    p.set(v, aux);
                }
            }
            tam--;
        }
    }
}
