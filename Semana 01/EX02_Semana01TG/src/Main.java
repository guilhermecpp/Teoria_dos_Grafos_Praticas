import java.util.Scanner;
//Gabriel Antonio

public class Main {
    /*Motivacao: Um mapa deve ser colorido utilizando apenas duas cores,
     o problema pede que se mostre se é possivel realizar tal tarefa
     levando em conta que cada localidade deve ter cor diferente dos seus adjacentes*/

    /* Aqui, cada localidade do mapa é representada por um vértice do grafo, e, as fronteiras que
     as ligam são representadas por arestas*/

    /* Cada vértice do grafo guarda como informação seu indice e sua cor, para facilitar o trabalho
     definimos a cor como um inteiro, são 4 possiveis cores, 0, que significa que o vértice ainda não foi colorido(inicial),
     1 que representa a primeira cor, 2 que representa a segunda e 3 que representa um erro na coloração, assim que
     um vértice recebe a cor 3, o programa identifica e já responde à sua execução com a mensagem (NOT BICOLORABLE.)*/

    /*A função principal que possibilita a coloração é a função "decideCor", que recebe como parametro o indice do vértice a colorir
    e avalia as cores de todos os adjacentes, retornando um inteiro que é a cor que será atribuida ao vértice,
    tanto na função baseada na DFS quando na BSF a função é utilizada de maneira semelhante*/

    /*O grafo é representado por listas de adjacencia*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo mapa;
        int aux, a, b;

        System.out.println("Digite numero de vertices(max 200):");
        aux = sc.nextInt();
        mapa = new Grafo(aux);

        System.out.println("Digite numero de arestas:");
        aux = sc.nextInt();

        //Recebe cada dupla de Vertices e adiciona nas respectivas listas de adjacencia
        for(int i = 0; i < aux; i++){
            System.out.println("Digite dois numeros referentes aos vertices que compoem arestas:");
            a = sc.nextInt();
            b = sc.nextInt();
            mapa.novaAresta(a,b);
        }
        System.out.println("1 - Colorir por DFS");
        System.out.println("2 - Colorir por BFS");
        aux = sc.nextInt();

        if(aux == 1) mapa.coloreDFS(0);
        else mapa.coloreBFS(0);

        if(mapa.getRes() == 1){
            System.out.println("NOT BICOLORABLE.");
        }else System.out.println("BICOLORABLE.");
    }
}