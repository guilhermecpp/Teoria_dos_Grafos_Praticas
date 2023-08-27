import java.util.ArrayList;

public class Ponte {
    int a;
    int b;

    public Ponte(int v1, int v2){
        a = v1;
        b = v2;
    }

    public int maior(Ponte comp){
        if(a > comp.a){
            return 1;
        }else if(a < comp.a){
            return -1;
        }else return 0;
    }
}
