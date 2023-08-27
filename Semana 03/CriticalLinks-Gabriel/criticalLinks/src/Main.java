import com.sun.jdi.IntegerValue;
import java.util.Scanner;
import java.util.ArrayList;
/* Gabriel - 12121bcc020 - 2023 - Java - Critical Links*/

/* O problema apresenta uma rede de servidores de computadores, a intenção é identificar,
de acordo com o esquema da rede, quais das ligaçoes entre servidores são críticas.

Sabendo que: um link L, que conecta dois servidores, é considerado
crítico se existem, pelo menos, dois servidores A e B e as únicas conexões possíveis entre
estes passam por L. Sendo assim ao remover um link crítico cria-se pelo menos duas redes distintas
de servidores interconectados.

Este é expressamente um problema que usa o conceito de pontes.

Cada rede de servidores representa um grafo
Estrutura: Lista de adjacências.
Vértices: Cada servidor da rede
Arestas: Conexões entre dois servidores
*  */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> num = new ArrayList<Integer>(); //ArrayList que guarda numeros tirados da string de entrada
        String input = new String(); //Representa string de entrada
        Grafo c;
        int n; //Primeiro número lido na entrada, representa a quantidade de vértices do grafo

        n = sc.nextInt();
        c = new Grafo(n);

        int nv; //primeiro vértice lido

        for(int i = 0; i < n; i++){
            nv = sc.nextInt();
            input = sc.nextLine();
            pegaNumeros(input, num);
            criaArestas(nv, num, c);
            num.clear();
        }
        c.pontePonte();
    }
    public static void pegaNumeros(String input, ArrayList<Integer> numeros){
        String[] num = new String[input.length()];
        num = input.split(" ");

        for(int i = 2; i < num.length && !num[i].equals(null) && !num[i].equals('\n'); i++){
            numeros.add(Integer.parseInt(num[i]));
        }
    }
    public static void criaArestas(int a, ArrayList<Integer> num, Grafo c){
        for (Integer integer : num) {
            c.nAresta(a, integer);
        }
    }
}