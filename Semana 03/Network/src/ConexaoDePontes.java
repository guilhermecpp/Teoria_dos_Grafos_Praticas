import java.util.ArrayList;
import java.util.Scanner;

public class ConexaoDePontes {

    // Função recursiva para encontrar as conexões de pontes no grafo
    static void conexaoDePontes(ArrayList<No> grafo, No u, int[] tempo) {
        tempo[0]++; // Incrementa o tempo
        u.d = tempo[0]; // Define o tempo de descoberta do nó
        u.low = tempo[0]; // Inicializa o valor de low do nó
        for (int v : u.arestas) { // Percorre as arestas do nó u
            if (grafo.get(v).d == 0) { // Se o nó v ainda não foi visitado
                u.filhos++; // Incrementa o número de filhos de u
                grafo.get(v).pai = u; // Define u como pai de v
                conexaoDePontes(grafo, grafo.get(v), tempo); // Chamada recursiva para o nó v
                u.low = Math.min(u.low, grafo.get(v).low); // Atualiza o valor de low de u
                if (u.pai != null && grafo.get(v).low >= u.d) {
                    u.pontoDeCorte = true; // Se v.low >= u.d, u é um ponto de corte
                }
            } else if (u.pai != grafo.get(v)) {
                u.low = Math.min(u.low, grafo.get(v).d); // Atualiza o valor de low de u
            }
        }
    }

    // Função principal para encontrar os pontos de corte no grafo
    static void conexaoDePontes(ArrayList<No> grafo) {
        int[] numeroDeCortes = {0}; // Array para armazenar o número de pontos de corte
        int[] tempo = {0}; // Array para controlar o tempo de descoberta
        for (No no : grafo) {
            no.filhos = 0; // Inicializa o número de filhos do nó
            no.d = 0; // Inicializa o tempo de descoberta do nó
            no.pai = null; // Define o pai do nó como null
            no.pontoDeCorte = false; // Inicializa o ponto de corte como falso
        }
        for (No no : grafo) {
            if (no.d == 0) { // Se o nó ainda não foi visitado
                conexaoDePontes(grafo, no, tempo); // Chama a função para explorar o nó e seus filhos
                if (no.filhos > 1) {
                    no.pontoDeCorte = true; // Se o nó tiver mais de 1 filho, é um ponto de corte
                }
            }
        }
        for (No no : grafo) {
            if (no.pontoDeCorte) {
                numeroDeCortes[0]++; // Conta o número de pontos de corte
            }
        }
        System.out.println(numeroDeCortes[0]); // Imprime o número de pontos de corte
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int N = scanner.nextInt(); // Lê o número de nós no grafo
            if (N == 0) {
                break; // Se N for 0, encerra o programa
            }
            scanner.nextLine(); // Consumir o caractere de nova linha
            ArrayList<No> grafo = new ArrayList<>(N + 1); // Cria uma lista de nós do grafo
            for (int i = 0; i <= N; i++) {
                grafo.add(new No()); // Inicializa os nós do grafo
            }
            while (true) {
                String linha = scanner.nextLine(); // Lê a linha de entrada
                String[] tokens = linha.split(" "); // Divide a linha em tokens
                int u = Integer.parseInt(tokens[0]); // Lê o nó u
                if (u == 0) {
                    break; // Se u for 0, encerra a entrada das arestas
                }
                for (int i = 1; i < tokens.length; i++) {
                    int v = Integer.parseInt(tokens[i]); // Lê o nó v
                    grafo.get(u).arestas.add(v); // Adiciona v às arestas do nó u
                    grafo.get(v).arestas.add(u); // Adiciona u às arestas do nó v
                }
            }
            conexaoDePontes(grafo); // Chama a função para encontrar pontos de corte no grafo
        }
    }
}
