import java.util.*;

public class Main {
    static class Aresta {
        int destino, custo;

        public Aresta(int destino, int custo) {
            this.destino = destino;
            this.custo = custo;
        }
    }

    static List<Aresta>[] arestas;
    static boolean[] visitado;
    static PriorityQueue<Aresta> filaPrioridade;

    public static void processar(int no) {
        visitado[no] = true;
        for (Aresta aresta : arestas[no]) {
            if (!visitado[aresta.destino]) {
                filaPrioridade.add(new Aresta(aresta.destino, aresta.custo));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 0) {
                break;
            }

            arestas = new List[n];
            for (int i = 0; i < n; ++i) {
                arestas[i] = new ArrayList<>();
            }
            visitado = new boolean[n];
            filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(a -> a.custo));

            while (m-- > 0) {
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                int custo = scanner.nextInt();
                arestas[i].add(new Aresta(j, custo));
                arestas[j].add(new Aresta(i, custo));
            }

            processar(0);
            int piorCusto = 0;

            while (!filaPrioridade.isEmpty()) {
                Aresta atual = filaPrioridade.poll();
                if (!visitado[atual.destino]) {
                    processar(atual.destino);
                    piorCusto = Math.max(piorCusto, atual.custo);
                }
            }

            boolean todosConectados = true;
            for (int i = 0; i < n; ++i) {
                todosConectados &= visitado[i];
            }

            if (todosConectados) {
                System.out.println(piorCusto);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
