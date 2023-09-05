import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int v1 = 3;
        Grafo g1 = new Grafo(v1);

        g1.adicionarAresta(0, 1);
        g1.adicionarAresta(1, 2);
        g1.adicionarAresta(0, 2);
        g1.verificarValidadeGrafo(g1);

    }
}
