import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Dupla>[] adj;
        boolean[] visited;
        int[] results;
        Code[] dic;
        int n, m, s;
        String aux;


        PriorityQueue<Dupla> lPrior = new PriorityQueue<>();

        n = sc.nextInt();
        results = new int[n];
        //for geral para passar por todos os n casos
        for(int i = 0; i < n; i++){
            //em todos os casos, lemos a quantidade de codigos a serem descritos

            s = 0;
            m = sc.nextInt();
            dic = new Code[m];//dicionario de codigos é iniciado com o tamanho igual a quantidade de codigos

            //iniciando grafo e vetores necessários
            adj = new ArrayList[m];
            visited = new boolean[m];

            for(int x = 0; x < m; x++){
                adj[x] = new ArrayList<Dupla>();
                visited[x] = false;
            }

            //guardando todos os m códigos escritos no dicionario
            for(int j = 0; j < m; j++){
                aux = sc.next();
                dic[j] = new Code(aux);
            }
            //adicionando cada código como aresta no grafo
            //todos os vértices são interligados e cada um deles
            //tem como peso de aresta a distancia em giros para se obter o número
            //ex: 0000 - 0011 tem como peso de aresta 2 pois a quantidade de giros totais
            //para chegar em 0011 a partir de 0000 é 2

            for(int k = 0; k < m; k++){
                for(int l = 0; l < m; l++){
                    if(k != l){
                        adj[k].add(new Dupla(dic[k].codeDist(dic[l]), l));
                    }
                }
            }

            visited[0] = true;

            lPrior.addAll(adj[0]);

            while(!lPrior.isEmpty()) {
                Dupla a;
                a = new Dupla(lPrior.peek().first, lPrior.poll().second);
                if(!visited[a.second]){
                    visited[a.second] = true;
                    s += a.first;
                   //System.out.println("Em " + dic[a.second].a + dic[a.second].b + dic[a.second].c + + dic[a.second].d + " com peso " + a.first);
                    lPrior.addAll(adj[a.second]);
                }
            }
            //adicionamos agora uma aresta 0000 ao caminho
            Code zero = new Code();
            for(int g = 0; g < m; g++){
                lPrior.add(new Dupla(zero.codeDist(dic[g]), 0));
            }
            //a menor aresta de 0000 ate um dos codigos digitados é somada ao resultado
            results[i] = s + lPrior.poll().first;
            lPrior.clear();
        }


        for(int i = 0; i < n; i++){
            System.out.println(results[i]);
        }
    }
}