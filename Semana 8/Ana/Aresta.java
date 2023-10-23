import java.util.ArrayList;

public class Aresta implements Comparable<Aresta> {
    int destino, peso;

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}
