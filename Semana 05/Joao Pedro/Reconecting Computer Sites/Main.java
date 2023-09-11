import java.util.Arrays;
import java.util.Scanner;

class Main {
    static class Link implements Comparable<Link> {
        int x, y, v;

        public Link(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        @Override
        public int compareTo(Link outraLink) {
            return this.v - outraLink.v;
        }
    }

    static int[] Parent, Rank;

    static void makeInit(int n) {
        Parent = new int[n + 1];
        Rank = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            Parent[i] = i;
            Rank[i] = 1;
        }
    }

    static int findParent(int x) {
        if (Parent[x] != x)
            Parent[x] = findParent(Parent[x]);
        return Parent[x];
    }

    static void pLink(int x, int y) {
        if (Rank[x] > Rank[y]) {
            Rank[x] += Rank[y];
            Parent[y] = x;
        } else {
            Rank[y] += Rank[x];
            Parent[x] = y;
        }
    }

    static int union(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if (x != y) {
            pLink(x, y);
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N, K, M, a, b, c, flag = 0;

        while (scanner.hasNextInt()) {
            N = scanner.nextInt();
            int sum = 0;

            for (int i = 1; i < N; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                c = scanner.nextInt();
                sum += c;
            }

            if (flag != 0) {
                System.out.println();
            }
            flag = 1;
            System.out.println(sum);

            K = scanner.nextInt();
            Link[] D = new Link[K];

            for (int i = 0; i < K; i++) {
                D[i] = new Link(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }

            M = scanner.nextInt();
            D = Arrays.copyOf(D, K + M);

            for (int i = K, j = 0; j < M; i++, j++) {
                D[i] = new Link(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }

            M = K + M;
            sum = 0;
            Arrays.sort(D);

            makeInit(N);

            for (int i = 0; i < M; i++) {
                sum += union(D[i].x, D[i].y) * D[i].v;
            }

            System.out.println(sum);
        }
    }
}
