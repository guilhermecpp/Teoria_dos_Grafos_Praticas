import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int width, height; //dimensões do mapa (grafo)

        //Recebendo as dimensões do mapa pelo usuário
        Scanner scanner = new Scanner(System.in);
        width = scanner.nextInt();
        height = scanner.nextInt();
        
        Grafo mapa = new Grafo(width, height); //Criar mapa
        mapa.preencherMapa(); //Preencher o para com as informações dadas pelo usuário
        mapa.validarMapa(); //Verificar se o mapa é válido ou não
        scanner.close(); 
    }
}