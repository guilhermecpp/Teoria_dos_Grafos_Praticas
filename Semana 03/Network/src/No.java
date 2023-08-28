import java.util.ArrayList;

class No {
    boolean pontoDeCorte;
    int filhos;
    int d;
    int low;
    No pai;
    ArrayList<Integer> arestas;

    No() {
        pontoDeCorte = false;
        filhos = 0;
        d = 0;
        low = 0;
        pai = null;
        arestas = new ArrayList<>();
    }
}