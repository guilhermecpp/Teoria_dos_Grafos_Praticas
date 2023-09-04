import java.util.*;

class Main {
    static class Aresta {
        String n1, n2;

        Aresta(String n1, String n2) {
            this.n1 = n1;
            this.n2 = n2;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        List<Aresta> arestas = new ArrayList<>();
        Map<String, Integer> mapeamento = new HashMap<>();
        List<String> inverso = new ArrayList<>();

        // Ler entrada
        for (int i = 0; i < n; i++) {
            String s1 = scanner.next();
            String s2 = scanner.next();

            // Se não visto, adicionar
            mapeamento.put(s1, 1);
            mapeamento.put(s2, 1);

            arestas.add(new Aresta(s1, s2));
        }

        // Construir mapeamento
        int unico = 0;
        for (String s : mapeamento.keySet()) {
            mapeamento.put(s, unico);
            inverso.add(s);
            unico++;
        }

        // Construir Adjacência
        List<Integer>[] adj1 = new ArrayList[unico];
        List<Integer>[] adj2 = new ArrayList[unico];
        for (int i = 0; i < unico; i++) {
            adj1[i] = new ArrayList<>();
            adj2[i] = new ArrayList<>();
        }
        for (Aresta aresta : arestas) {
            int n1 = mapeamento.get(aresta.n1);
            int n2 = mapeamento.get(aresta.n2);
            adj1[n1].add(n2);
            adj2[n2].add(n1);
        }

        // Executar primeiro DFS para obter os tempos de 'leave'
        Stack<Integer> s = new Stack<>();
        boolean[] visitado = new boolean[unico];
        for (int i = 0; i < unico; i++) {
            if (!visitado[i]) {
                dfs1(adj1, visitado, s, i);
            }
        }

        // Executar segundo DFS para obter componentes
        List<Integer> unicoComponente = new ArrayList<>();
        List<List<Integer>> outrosComponentes = new ArrayList<>();
        Arrays.fill(visitado, false);
        while (!s.isEmpty()) {
            int i = s.pop();

            if (!visitado[i]) {
                List<Integer> componente = new ArrayList<>();
                dfs2(adj2, visitado, componente, i);
                if (componente.size() == 1) {
                    unicoComponente.add(componente.get(0));
                } else {
                    outrosComponentes.add(componente);
                }
            }
        }

        // Ordenar tudo
        for (List<Integer> componente : outrosComponentes) {
            Collections.sort(componente);
        }
        outrosComponentes.sort(Comparator.comparing(list -> list.get(0)));
        Collections.sort(unicoComponente);

        // Imprimir componentes conectados
        for (List<Integer> componente : outrosComponentes) {
            System.out.print("ok ");
            for (int j : componente) {
                System.out.print(inverso.get(j) + " ");
            }
            System.out.println();
        }

        // Imprimir componentes solitários
        if (!unicoComponente.isEmpty()) {
            System.out.print("avoid ");
            for (int i : unicoComponente) {
                System.out.print(inverso.get(i) + " ");
            }
            System.out.println();
        }
    }

    static void dfs1(List<Integer>[] adj, boolean[] visitado, Stack<Integer> s, int atual) {
        visitado[atual] = true;
        for (int i : adj[atual]) {
            if (!visitado[i]) {
                dfs1(adj, visitado, s, i);
            }
        }
        s.push(atual);
    }

    static void dfs2(List<Integer>[] adj, boolean[] visitado, List<Integer> comp, int atual) {
        visitado[atual] = true;
        comp.add(atual);

        for (int i : adj[atual]) {
            if (!visitado[i]) {
                dfs2(adj, visitado, comp, i);
            }
        }
    }
}
