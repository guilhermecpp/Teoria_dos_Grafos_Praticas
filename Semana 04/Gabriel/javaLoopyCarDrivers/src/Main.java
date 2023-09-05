import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo g = new Grafo();
        int n;
        String a, b;

        n = sc.nextInt();
        for(int i = 0; i < n; i++){
            a = sc.next();
            b = sc.next();
            g.newEdge(a, b);
        }
        g.dfsPrepare();
    }
}