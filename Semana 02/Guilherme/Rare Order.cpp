// Link do problema: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=136
// Status - ACCEPTED
// Complexidade - O(N + M)
/* Solução:

Vértices: Letras maiúscula do alfabeto
Arestas : Se letra i vem antes de j na nova ordem alfabética, então  
          temos uma aresta de i para j
        
Para uma string x vir antes de outra string y no livro basta que a 
primeira posição que diferir entre x e y a letra de x vi antes da 
que de y na ordem à definir (no caso onde x = "ZX" e y = "ZXY", x 
sempre virá antes, como se o caracter '' viesse primeiro na ordem). 

Ao invés de verificar todas as string dois a dois (o que nos 
resultaria em O(n^2)) podemos apenas verificar a string i com a i+1.

Prova: Vamos analizar três string x, y, z tais que x < y e y < z. 
Temos que a primeira posição em que x e y difere (p1), o caracter de 
x é  menor, pois assumimos x < y. Analizando a primeira posição em 
que y e z diferem (p2) temos que podem ocorrer três casos:

Caso 1: p1 < p2: Temos que x[p1] < y[p1] = z[p1] ==> x[p1] < z[p1]
        AAA
        ABA
        ABB

Caso 2: p1 = p2: Temos que x[p1] < y[p1] < z[p1] ==> x[p1] < z[p1]
		AAA
		ABA
		ACA

Caso 3: p1 > p2: Temos que x[p2] = y[p2] < z[p2] ==> x[p2] < z[p2]
		AAA
		AAB
		ABA

Logo se x < y e y < z temos garantido que x < z. ■


Com a observação provada acima temos que se garatirmos que a string 
1 seja menor que 2, que a 2 seja menor que a 3, ... e que a n-1 seja 
menor que a n, então i será menor que j, para todo i < j.

Agora para garatir que a string i seja menor que a i+1, vamos denotar
que a posição que elas diferem seja p, logo basta criar um grafo 
direcionado e liga os vértices i[p] e i+1[p] por uma aresta (isso é 
claro se i[p] != ''), e aplicando o algoritmo de ordem topológica 
garatimos que i[p] venha primeiro que i+1[p] na ordem final das 
letras.

*/

#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 310
#define fi first
#define se second
#define pb push_back
#define mp make_pair

vector< int > grafo[NMAX]; // Grafo por lista de adjacência
bool has[NMAX];  		   // Letras que apareceram
bool marc[NMAX];           // Se já passou pelo i-esimo vértice

string resp;

void DFS(int u)
{

	marc[u] = true; // Marca que passamos pelo vértice

	for(auto v : grafo[u]) // Percorre os vizinhos de u
	{
	
		if(marc[v] == true) continue; // Ignora se já passou em v
	
		DFS(v);
	
	}

	// Parte do algoritmo de ordenação topológica
	resp.pb((char)(u));
	
	return;

}

int main()
{

	int i;

	string s, last;

	while(cin >> s)
	{
	
		if(s == "#") break;
		
		for(auto letra : s) has[letra] = true;
		
		for(i = 0;i < last.size();i++) // Encontra a primeira letra
		                               // que diferem
		{
		
			if(s[i] != last[i]) break;
		
		}
		
		if(i != last.size()) // Se i[p] != ''
			grafo[last[i]].pb(s[i]);
		
		last = s;
	
	}
	
	resp = "";
	
	for(i = 'A';i <= 'Z';i++)
	{
	
		// Pula a letra se ela não existe nas palavras ou já
		// passamos nela 
		if(has[i] == false || marc[i] == true) continue; 
	
		DFS(i);
	
	}
	
	// Parte do algoritmo de ordenação topológica
	reverse(resp.begin(), resp.end());  
	
	cout << resp << endl;
 
    return 0;
 
}
