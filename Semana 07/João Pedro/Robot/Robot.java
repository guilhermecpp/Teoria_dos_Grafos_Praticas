import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class No {
    int x, y, dir, tempo;

 public No(int x, int y, int dir, int tempo) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tempo = tempo;
    }
}

class Main {
    static int M, N;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static boolean[][] obstaculo = new boolean[50][50];
    static boolean[][][] visitado = new boolean[51][51][4];

    static boolean ehValido(int x, int y) {
        if (x <= 0 || y <= 0 || x >= M || y >= N) return false;
        if (obstaculo[x - 1][y - 1]) return false;
        if (obstaculo[x - 1][y]) return false;
        if (obstaculo[x][y - 1]) return false;
        if (obstaculo[x][y]) return false;
        return true;
    }

    static int menorTempo(int inicioX, int inicioY, int dir, int fimX, int fimY) {
        Queue<No> fila = new ArrayDeque<>();
        if (ehValido(inicioX, inicioY)) {
            fila.add(new No(inicioX, inicioY, dir, 0));
            visitado[inicioX][inicioY][dir] = true;
        }

        while (!fila.isEmpty()) {
            No aux = fila.poll();

            if (aux.x == fimX && aux.y == fimY) return aux.tempo;

            if (!visitado[aux.x][aux.y][(aux.dir + 1) % 4]) {
                visitado[aux.x][aux.y][(aux.dir + 1) % 4] = true;
                fila.add(new No(aux.x, aux.y, (aux.dir + 1) % 4, aux.tempo + 1));
            }

            if (!visitado[aux.x][aux.y][(aux.dir + 3) % 4]) {
                visitado[aux.x][aux.y][(aux.dir + 3) % 4] = true;
                fila.add(new No(aux.x, aux.y, (aux.dir + 3) % 4, aux.tempo + 1));
            }

            for (int i = 1; i <= 3; i++) {
                if (ehValido(aux.x + di[aux.dir] * i, aux.y + dj[aux.dir] * i)) {
                    if (visitado[aux.x + di[aux.dir] * i][aux.y + dj[aux.dir] * i][aux.dir]) continue;
                    visitado[aux.x + di[aux.dir] * i][aux.y + dj[aux.dir] * i][aux.dir] = true;
                    fila.add(new No(aux.x + di[aux.dir] * i, aux.y + dj[aux.dir] * i, aux.dir, aux.tempo + 1));
                } else break;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inicioX, inicioY, fimX, fimY, dir;
        char[] s = new char[6];

        while (true) {
            M = scanner.nextInt();
            N = scanner.nextInt();
            if (M == 0 || N == 0) break;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    int ax = scanner.nextInt();
                    obstaculo[i][j] = ax != 0;
                }
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < 4; k++) {
                        visitado[i][j][k] = false;
                    }
                }
            }

            inicioX = scanner.nextInt();
            inicioY = scanner.nextInt();
            fimX = scanner.nextInt();
            fimY = scanner.nextInt();
            s = scanner.next().toCharArray();

            if (s[0] == 'n') dir = 0;
            else if (s[0] == 'e') dir = 1;
            else if (s[0] == 's') dir = 2;
            else dir = 3;

            System.out.println(menorTempo(inicioX, inicioY, dir, fimX, fimY));
        }
    }
}
