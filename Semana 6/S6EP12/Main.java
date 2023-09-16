public class Main {
    public static void main(String[] args){
        int v = 8;
        Grafo g1 = new Grafo(v);

        g1.adicionarAresta(1, 2, 50);
        g1.adicionarAresta(1, 3, 60);
        g1.adicionarAresta(2, 4, 120);
        g1.adicionarAresta(2, 5, 90);
        g1.adicionarAresta(3, 6, 50);
        g1.adicionarAresta(4, 6, 80);
        g1.adicionarAresta(4, 7, 70);
        g1.adicionarAresta(5, 7, 40);
        g1.adicionarAresta(6, 7, 140);

        System.out.println("Case #1: ");
        g1.prim(1, 7);
        g1.prim(2, 6);
        g1.prim(6, 2);

        Grafo g2 = new Grafo(v);
        g2.adicionarAresta(1, 2, 50);
        g2.adicionarAresta(1, 3, 60);
        g2.adicionarAresta(2, 4, 120);
        g2.adicionarAresta(3, 6, 50);
        g2.adicionarAresta(4, 6, 80);
        g2.adicionarAresta(5, 7, 40);

        System.out.println("\nCase #2: ");
        g2.prim(7, 5);
        g2.prim(1, 7);
        g2.prim(2, 4);

    } 
}
