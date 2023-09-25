import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int n;

        while(true){
            n = sc.nextInt();
            if(n > 26) break;
            Grafo g;
            g = new Grafo(n);

            String a; //Guarda identificador String do planeta
            double b; //Guarda valor do produto do planeta
            String c; //Guarda adjacentes ao planeta numa unica string
            char[] cChars; //vetor que divide a string para acessarmos cada adjacente

            for(int i = 0; i < n; i++){
                a = sc.next();
                b = sc.nextDouble();
                c = sc.next();

                cChars = new char[c.length()];
                c.getChars(0, c.length(), cChars, 0);

                if(!g.dic.contains(a)){
                    g.dic.add(a);
                }
                g.value[g.dic.indexOf(a)] = b;

                for(int j = 0; j < c.length(); j++){
                    if(!g.dic.contains(""+cChars[j])){
                        g.dic.add(""+cChars[j]);
                    }
                    g.addEdge(g.dic.indexOf(a), g.dic.indexOf(""+cChars[j]));
                }
            }
            g.findImport();
            g.showValue();
        }
    }

}