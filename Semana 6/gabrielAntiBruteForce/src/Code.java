import com.sun.jdi.IntegerValue;
import java.lang.Math;
public class Code {
    int a, b, c, d;

    public Code(String number){
        a = Character.getNumericValue(number.charAt(0));
        b = Character.getNumericValue(number.charAt(1));
        c = Character.getNumericValue(number.charAt(2));
        d = Character.getNumericValue(number.charAt(3));
    }
    public Code(){
        a = 0;
        b = 0;
        c = 0;
        d = 0;
    }
    public int codeDist(Code ot){
        int r = 0;
        r += mendist(a, ot.a);
        r += mendist(b, ot.b);
        r += mendist(c, ot.c);
        r += mendist(d, ot.d);
        return r;
    }
    public int mendist(int a, int b){
        int menor = 0;
        int m1 = Math.abs(a-b);
        int m2 = 10-m1;
        return Math.min(m1, m2);
    }
}
