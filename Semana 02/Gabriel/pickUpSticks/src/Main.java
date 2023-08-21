import java.util.Scanner;
//Gabriel Antonio

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo g;
        int aux1, aux2;
        int n, a;

        n = sc.nextInt();
        a = sc.nextInt();

        g = new Grafo(n);

        for(int i = 0; i < a; i++) {
            aux1 = sc.nextInt();
            aux2 = sc.nextInt();
            aux1--;
            aux2--;
            g.novaAresta(aux1, aux2);
        }
        aux1 = sc.nextInt();
        aux2 = sc.nextInt();

        boolean res = g.ordena();
        if(res){
            for(int i = g.p.size()-1; i >= 0; i--){
                System.out.println(g.p.get(i) + 1);
            }
        }else{
            System.out.println("IMPOSSIBLE");
        }
    }
}