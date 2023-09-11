public class Dupla implements Comparable<Dupla>{
    int first;
    int second;

    public Dupla(int a, int b){
        first = a;
        second = b;
    }

    @Override
    public int compareTo(Dupla duplaAlt) {
        return Integer.compare(first, duplaAlt.first);
    }
}
