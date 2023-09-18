import java.util.*;

class Main {
    static final String ESTADO_INICIAL = "0000";

    static int encontrarW(String a, String b) {
        int w = 0;
        for (int i = 0; i < 4; ++i) {
            w += Math.min(Math.abs(a.charAt(i) - b.charAt(i)), 10 - Math.abs(a.charAt(i) - b.charAt(i)));
        }
        return w;
    }

    static int mst(List<List<Pair<Integer, Integer>>> G) {
        PriorityQueue<Pair<Integer, Integer>> Q = new PriorityQueue<>(Comparator.comparingInt(p -> -p.primeiro));
        List<Boolean> visto = new ArrayList<>(G.size());
        for (int i = 0; i < G.size(); ++i) {
            visto.add(false);
        }
        Q.add(new Pair<>(0, 0));
        int c = 0, ans = 0;
        while (c < G.size()) {
            Pair<Integer, Integer> par = Q.poll();
            assert par != null;
            int u = par.segundo;
            int w = -par.primeiro;
            if (!visto.get(u)) {
                visto.set(u, true);
                ++c;
                ans += w;
                for (int i = 0; i < G.get(u).size(); ++i) {
                    Pair<Integer, Integer> proxPar = G.get(u).get(i);
                    Q.add(new Pair<>(-proxPar.segundo, proxPar.primeiro));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int n = scanner.nextInt();
            List<String> chaves = new ArrayList<>(n);
            int inicial = Integer.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                chaves.add(scanner.next());
                inicial = Math.min(inicial, encontrarW(chaves.get(i), ESTADO_INICIAL));
            }

            List<List<Pair<Integer, Integer>>> G = new ArrayList<>(n);
            for (int i = 0; i < n; ++i) {
                G.add(new ArrayList<>());
            }

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    int w = encontrarW(chaves.get(i), chaves.get(j));
                    G.get(i).add(new Pair<>(j, w));
                    G.get(j).add(new Pair<>(i, w));
                }
            }

            System.out.println(mst(G) + inicial);
        }
    }

    static class Pair<T, U> {
        T primeiro;
        U segundo;

        Pair(T primeiro, U segundo) {
            this.primeiro = primeiro;
            this.segundo = segundo;
        }
    }
}
