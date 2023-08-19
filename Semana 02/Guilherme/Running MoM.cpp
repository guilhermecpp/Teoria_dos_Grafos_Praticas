// Link do problema: https://open.kattis.com/problems/runningmom
// Status - ACCEPTED
// Complexidade - O(N + M)
/* Solução:

Vértices: Cidades
Arestas : Se há um voo da cidade i para a j, então temos uma aresta 
          de i para j
         
Esse problema é uma aplicação direta do algoritmo de ordenação 
topológica.

*/

#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 100010
#define fi first
#define se second
#define pb push_back
#define mp make_pair
#define WHITE 0 // Não vizitado
#define GRAY  1 // Vizitando (ou chega em um ciclo)
#define BLACK 2 // Vizitado

vector< int > grafo[NMAX]; // Grafo por lista de adjacência
int color[NMAX];		   // Cor do i-esimo vértice

int newId = 1;			// 
map< string, int > id;  // Mapeando as cidades

bool DFS(int u)
{

	// Estamos vizitando u
	color[u] = GRAY; 

	for(auto v : grafo[u])
	{
	
		// Ignora se v já acabou
		if(color[v] == BLACK) continue;
	
		// Se v está sendo vizitado (ou chega em um ciclo), u chega 
		// em um ciclo, assim retornamos true (e deixamos u GRAY)
		if(color[v] == GRAY) return true;
	
		if(DFS(v) == true) return true;
	
	}

	// Acabamos de visitar u, logo ele não pertence a um ciclo
	color[u] = BLACK;
	
	return false;

}

int main()
{

	int n, m, u, v, i;
	
	string s;

	cin >> n;
	
	while(n--)
	{
	
		cin >> s;
		if(id[s] == 0) id[s] = newId++;
		u = id[s];
	
		cin >> s;
		if(id[s] == 0) id[s] = newId++;
		v = id[s];
		
		grafo[u].pb(v);
	
	}
	
	n = newId - 1;
	
	for(i = 1;i <= n;i++)
	{
	
		if(color[i] == WHITE) DFS(i);
	
	}
	
	// Apartir desse momento:
	// color[u] = GRAY  ==> u chega num ciclo (safe)
	// color[u] = BLACK ==> u não chega num ciclo (trapped)
	
	while(cin >> s)
	{
	
		u = id[s];
		
		if(color[u] == GRAY)	cout << s << " safe" << endl;
		else					cout << s << " trapped" << endl;
	
	}
 
    return 0;
 
}
