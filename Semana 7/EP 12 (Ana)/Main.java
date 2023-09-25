import java.io.*;
import java.util.*;

public class Main{
public static void main(String[] args){
        int M = 9;
        int N = 10; 

        Grafo g = new Grafo(M * N);

        //adicionando as arestas
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int u = i * N + j;
                int v;
                // Adicione as arestas nas direções permitidas (norte, sul, leste, oeste)
                if (i > 0) {
                    v = (i - 1) * N + j;
                    g.adicionarAresta(u, v);
                }
                if (i < M - 1) {
                    v = (i + 1) * N + j;
                    g.adicionarAresta(u, v);
                }
                if (j > 0) {
                    v = i * N + (j - 1);
                    g.adicionarAresta(u, v);
                }
                if (j < N - 1) {
                    v = i * N + (j + 1);
                    g.adicionarAresta(u, v);
                }
            }
        }

        // Defina o ponto inicial e o ponto de destino
        int pontoInicial = 7 * N + 2;
        int pontoDestino = 2 * N + 7;

        // Encontre o tempo mínimo para o robô se mover do ponto inicial para o ponto de destino
        int tempoMinimo = g.bfs(pontoInicial, pontoDestino);
        System.out.println("Tempo mínimo: " + tempoMinimo + " segundos");
    }
}


