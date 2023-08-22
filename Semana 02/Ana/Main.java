/* Um colecionador de livros raros descobriu recentemente um livro escrito em um 
idioma desconhecido que usava o mesmo caracteres como o idioma Inglês. O livro continha 
um índice curto, mas a ordem dos itens no índice era diferente do que se esperaria 
se os caracteres fossem ordenados da mesma forma que no alfabeto inglês. O colecionador 
tentou usar o índice para determinar a ordem dos caracteres (ou seja, a sequência de 
agrupamento) do alfabeto estranho, então desistiu frustrado com o tédio de a tarefa.
Você deve escrever um programa para completar o trabalho do coletor. Em particular, 
seu programa levará um conjunto de strings que foi classificado de acordo com uma 
sequência de agrupamento específica e determina o que essa sequência é.

Entrada:
A entrada consiste em uma lista ordenada de strings de letras maiúsculas, uma string 
por linha. Cada string contém no máximo 20 caracteres. O fim da lista é sinalizado por 
uma linha com o único caractere ‘#’. Nem todas as letras são necessariamente usadas, 
mas a lista implicará uma ordenação completa entre as letras que são usados.

Saída:
Sua saída deve ser uma única linha contendo letras maiúsculas na ordem que especifica 
o agrupamento sequência usada para produzir o arquivo de dados de entrada.
 */

import java.util.LinkedList;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        int V = 4;

        Grafo G = new Grafo(V);
        
        G.adicionarAresta('X', 'Z', 0);
        G.adicionarAresta('Z', 'Y', 2);
        G.adicionarAresta('Y', 'W', 3);

        //G.adicionarAresta('X', 'W', 0);
        //G.adicionarAresta('X', 'Y', 0);
        //G.adicionarAresta('W', 'W', 1);
        //G.adicionarAresta('W', 'X', 1);
        //G.adicionarAresta('W', 'Y', 1);
        //G.adicionarAresta('Y', 'W', 3);
        //G.adicionarAresta('Z', 'X', 2);

        char c[] = {'X','W','Z','Y'}; //serve para localizar o índice de cada caracter
        G.mostrar(c);
        G.ordenacaoTopologica(c);

    }
}
