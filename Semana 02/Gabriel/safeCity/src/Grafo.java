import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
    ArrayList <ArrayList<Vertice>> adj; //vetor dinamico de listas de adjacencia

    public Grafo(){
        adj = new ArrayList<ArrayList<Vertice>>();
    }
    public void novaAresta(String a, String b){
        int ao, af;
        ao = buscaV(a);
        af = buscaV(b);

        if(ao == -1){
            addVr(a);
            ao = adj.size()-1;
        }
        if(af == -1){
            addVr(b);
            af = adj.size()-1;
        }

        adj.get(ao).add(adj.get(af).get(0));

    }
    public void addVr(String a){
        adj.add(new ArrayList<Vertice>());
        adj.get(adj.size()-1).add(new Vertice(a, adj.size()-1));
    }
    public int buscaV(String a){
        for(int i = 0; i < adj.size(); i++){
            if(a.equals(adj.get(i).get(0).city)){
                return i;
            }
        }
        return -1;
    }
    public void mostraGrafo(){
        for(int i = 0; i< adj.size(); i++){
            System.out.println((i+1) + ": ");
            System.out.print("Raiz: ");
            for(int j = 0; j < adj.get(i).size(); j++){
                System.out.print(adj.get(i).get(j).city + " - ");
            }
            System.out.println();
        }
    }

    public void mostraV(String a){
        int n = buscaV(a);
        if(adj.get(n).get(0).sfty == 1){
            System.out.println(adj.get(n).get(0).city + " safe");
        }else{
            System.out.println(adj.get(n).get(0).city + " trapped");
        }
    }
    public void classificaGrafo(Vertice inic){
        adj.get(inic.indice).get(0).sfty = 0;
        int saida = 0;

        for(int i = 1; i < adj.get(inic.indice).size(); i++){
            if(adj.get(inic.indice).get(i).sfty == -1){
                classificaGrafo(adj.get(inic.indice).get(i));
            }
            if(adj.get(inic.indice).get(i).sfty == 0){
                adj.get(inic.indice).get(0).sfty = 1;
                saida++;
            }else if(adj.get(inic.indice).get(i).sfty == 1){
                adj.get(inic.indice).get(0).sfty = 1;
                saida++;
            }
        }
        if(saida == 0){
            adj.get(inic.indice).get(0).sfty = 2;
        }
    }


}
