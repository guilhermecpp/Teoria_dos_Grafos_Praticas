import java.util.ArrayList;

public class Vertice {
    String city;
    int indice;
    int sfty; //-1 - não visitado // 0 - visitando// 1 ou 2 - visitado (1 - segura, 2 - não segura)

    public Vertice(String c,int ind){
        city = c;
        indice = ind;
        sfty = -1;
    }
}
