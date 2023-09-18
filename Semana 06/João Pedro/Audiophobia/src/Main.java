import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int oo = 0xffffff;
        int cases = 0;

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int q = scanner.nextInt();

            if (n == 0) {
                break;
            }

            int[][] f = new int[101][101];
            int i, j, k, x, y, b;

            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    f[i][j] = oo;
                }
            }

            while (m-- > 0) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                b = scanner.nextInt();
                f[x][y] = Math.min(f[x][y], b);
                f[y][x] = f[x][y];
            }

            for (k = 1; k <= n; k++) {
                for (i = 1; i <= n; i++) {
                    for (j = 1; j <= n; j++) {
                        f[i][j] = Math.min(f[i][j], Math.max(f[i][k], f[k][j]));
                    }
                }
            }

            if (cases > 0) {
                System.out.println();
            }
            System.out.printf("Case #%d%n", ++cases);

            while (q-- > 0) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (f[x][y] != oo) {
                    System.out.println(f[x][y]);
                } else {
                    System.out.println("no path");
                }
            }
        }
    }
}
