import java.io.*;
import java.util.*;

public class Main{
public static void main(String[] args){

        int v1 = 4;
        Grafo g1 = new Grafo(v1);

        g1.adicionarAresta(0, 3);
        g1.adicionarAresta(0, 1);
        g1.adicionarAresta(0, 2);

        List<String> listaPontes = g1.pontes();
        System.out.print(g1.contador + " ");
        for (String p : listaPontes) {
            System.out.print(p + " ");
        }

        System.out.println(" ");

        int v2 = 7;
        Grafo g2 = new Grafo(v2);

        g2.adicionarAresta(0, 1);
        g2.adicionarAresta(0, 2);
        g2.adicionarAresta(1, 3);
        g2.adicionarAresta(2, 3);
        g2.adicionarAresta(3, 4);
        g2.adicionarAresta(4, 5);
        g2.adicionarAresta(4, 6);
        g2.adicionarAresta(5, 6);

        List<String> listaPontes2 = g2.pontes();
        System.out.print(g2.contador + " ");
        for (String p : listaPontes2) {
            System.out.print(p + " ");
        }
    }
}


