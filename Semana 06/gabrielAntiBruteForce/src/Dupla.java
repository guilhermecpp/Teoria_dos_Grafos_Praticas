public class Dupla implements Comparable<Dupla> {
    int first;
    int second;

    public Dupla(int a, int b) {
        this.first = a;
        this.second = b;
    }

    public int compareTo(Dupla duplaAlt) {
        return Integer.compare(this.first, duplaAlt.first);
    }
}