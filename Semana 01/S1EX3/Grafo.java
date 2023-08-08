import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
    int width, height; //dimensões do mapa (grafo)
    char[][] matriz; //matriz de adjacência

    //Criação do mapa
    public Grafo(int width, int height){ 
        this.width = width;
        this.height = height;
        matriz = new char[height][width];
    }

    //Preenchendo o mapa com as informações dada pelo usuário
    public void preencherMapa(){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < height; i++) {
            matriz[i] = scanner.next().toCharArray(); //Preenche cada linha da matriz com a linha digitada 
        }
    }

    //Validação do mapa fornecido
    public void validarMapa(){
        boolean[][] visitado = new boolean[height][width];

        //Movimentação dentro do mapa
        int x = 0, y = 0, novo_X = 0, novo_Y = 0; //posições atuais e novas posições
        //O laço continua enquanto a posição atual (x, y) 
        //é válida (dentro das dimensões do mapa)
        while (validarPosicao(x, y, height, width)) {
            // Se a posição atual já foi visitada (vis[x][y] == true), 
            // isso significa que o labirinto não tem solução e imprime "!" 
            if (visitado[x][y] == true) {
                System.out.println("!");
            }
            // Se a posição atual contém o baú ("*")
            // Então o mapa é validado e imprime "*" 
            if (matriz[x][y] == '*') {
                System.out.println("*");
            }

            //Movimentação para direita(>), esquerda(<),
            //para baixo(v) ou para cima(^)
            if (matriz[x][y] == '>') {
                novo_X = 0;
                novo_Y = 1;
            } else if (matriz[x][y] == '<') {
                novo_X = 0;
                novo_Y = -1;
            } else if (matriz[x][y] == 'v') {
                novo_X = 1;
                novo_Y = 0;
            } else if (matriz[x][y] == '^') {
                novo_X = -1;
                novo_Y = 0;
            }

            // A posição atual é marcada como visitada (vistado[x][y] = true).
            visitado[x][y] = true;
            //Atualiza a posição de x e y
            x += novo_X;
            y += novo_Y;
        }
    }

    //Verifica se a posição é válida dentro dos limites do mapa
    public static boolean validarPosicao(int x, int y, int height, int width) {
        return x > -1 && y > -1 && x < height && y < width;
    }
}