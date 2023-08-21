import java.util.Stack;
public class OrdTop {
    Integer[] marc; // 0 - nao visitado, 1 - visitando, 2 - visitado
    Stack<Integer> p;

    public OrdTop(int n){
        marc = new Integer[n];
        p = new Stack<Integer>();
        for(int i = 0; i<n; i++){
            marc[i] = 0;
        }
    }
    public boolean avaliaP(){
        for(int i = 0; i < p.size(); i++){
            if(p.get(i) == -1){
                return false;
            }
        }
        return true;
    }
    public void ordena(Grafo a, Vertice b){
        System.out.println("Iniciando avaliação do vertice " + b.indice);
        if(marc[b.indice] == 0){
            for(int i = b.indice; i < a.n_vert; i++){
                marc[i] = 1;
                for(int j = 1; j < a.adj[i].lista.size(); j++){
                    if(marc[a.adj[i].lista.get(j).indice] == 0){
                        ordena(a, a.adj[i].lista.get(j));
                    }else
                    if(marc[a.adj[i].lista.get(j).indice] == 1){
                        System.out.println("Encontramos " + a.adj[i].lista.get(j).indice + " em " + b.indice);
                        p.push(-1);
                        break;
                    }
                }
                System.out.println("Fim de " + i);
                marc[i] = 2;
                //System.out.println("Adicionando " + b.indice + " na pilha");
                p.push(i);
            }
        }
    }

}
