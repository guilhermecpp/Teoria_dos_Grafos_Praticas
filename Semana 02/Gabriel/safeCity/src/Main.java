import java.util.Scanner;
//Gabriel Antonio -- Running MoM
/* Estrutura: Listas de Adjacencia
*  Vértice: Cidades
*  Arestas: Voos diarios fixos entre cidades
*  Grafo Direcionado
*
*  Vértices tem como atributos:
*  int indice - usado para facilitar algumas operações
*  String city - guarda String que marca o nome da cidade respectiva
*  int sfty - marca o estado da cidade (-1 - não visitada, 0 - visitando) e também o estado (1 - safe, 2 - trapped)
*
*  O problema apresenta um personagem denominado MoM (Man of Mysteries) que precisa regularmente estar em movimento
*  para isso ele precisa garantir que as cidade para as quais ele se direciona tenham voos diários fixos disponíveis
*  para outras cidades com as mesmas características.
*
*  Primeiro apresentamos uma lista de voos diários fixos entre diversas cidades, logo após é preciso avaliar a situação
*  de cada cidade listada.
*
*  Exemplo: Existindo 4 cidades (A,B,C,D), se é possível realizar os seguintes voos:
*  De A até B
*  De B até C
*  De B até D
*  De D até A
*  Temos um grafo onde as cidades A, B e D fazem parte de um ciclo, o que torna elas cidades seguras para o MoM, já que,
*  estando em qualquer uma delas ele consegue se deslocar, sem restriçoes, para outra cidade adiante, sem ficar preso.
*  Neste mesmo exemplo a cidade C já não é segura, pois não há nenhum outro voo partindo desta cidade, o que significa que
*  caso o MoM viaje até ela, não há rota de escape.
*
*  Método principal: Classifica Grafo
*  - Percorre o grafo com um mecanismo DFS e classfica cada cidade de acordo com suas arestas, considerando a presença de ciclos.
*/

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo g = new Grafo();
        String city1, city2;
        String teste = " ";
        int n;
        n = sc.nextInt();

        for(int i = 0; i < n; i++){
            city1 = sc.next();
            city2 = sc.next();
            g.novaAresta(city1, city2);
        }
        g.classificaGrafo(g.adj.get(0).get(0));

        while(!teste.equals(null)){
            teste = sc.next();
            g.mostraV(teste);
        }
    }
}