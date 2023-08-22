import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Grafo {
    private int V;   
    private LinkedList<Character> adj[]; 
  
    //Constructor
    public Grafo(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
    public void adicionarAresta(char v,char w, int p) {
        adj[p].add(w); //p é o index do vértice v
    }

    public void mostrar(char [] c) {
        for(int i = 0; i < c.length; i++){
            System.out.print(c[i] + "->");
            for (char v : adj[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    void dfs(int v, boolean marcado[], Stack<Character> stack, char [] c) {
        marcado[v] = true; //marca que o elemento foi visitado

        System.out.print("+ " + v + "\n");
        Iterator<Character> i = adj[v].listIterator(); // i será usado para percorrer os vizinhos do vértice v
        while (i.hasNext()) { //enquanto exister vizinhos de i, a busca continua
            int n = adj[v].indexOf(i.next()); //n é a posição do vizinho
            
            char viz = adj[v].get(n); // pega a letra do vizinho na posição n
            for(int p = 0; p < c.length; p++)
            {
            
            	if(c[p] == viz) 
            	{
            	
            		n = p; // n é a posição do vizinho em c
            
            		break;
            		
            	}
            
            }
            
            if (!marcado[n]) //caso o vizinho não tenha sido visitado, ocorrerá o dfs nesse vértice
                dfs(n, marcado, stack, c);
        }
        System.out.print("- " + v + "\n");
        stack.push(c[v]); //após analisar o vértice, ele é empilhado
    }

    void ordenacaoTopologica(char [] c) {
        Stack<Character> stack = new Stack<>(); 
        boolean marcado[] = new boolean[V];
        
        //aplica a dfs em cada vértice
        for (int i = 0; i < V; i++)
            if (marcado[i] == false)
                dfs(i, marcado, stack, c);

        //Imprime o resultado da ordenação topológica
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
            
        System.out.print("\n");
    }
}
