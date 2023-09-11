public class Main {
    public static void main(String[] args){
        int v1 = 3;
        Grafo g1 = new Grafo(v1);

        g1.adicionarAresta(0, 1, 3);
        g1.adicionarAresta(1, 2, 4);
        g1.adicionarAresta(2, 1, 5);
        g1.prim();

        int v2 = 2;
        Grafo g2 = new Grafo(v2);
        g2.prim();
    } 
}
