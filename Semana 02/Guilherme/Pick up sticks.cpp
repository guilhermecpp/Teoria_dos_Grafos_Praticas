// Link do problema: https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=2733
// Status - ACCEPTED
// Complexidade - O(N)
/* Solução:

Vértices: Palitos
Arestas : Se o palito i está acima do j, então temos uma aresta de i 
          para j
         
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
#define WHITE 0 // Não visitado
#define GRAY  1 // Vizitando
#define BLACK 2 // Vizitado

vector< int > grafo[NMAX]; // Grafo por lista de adjacência
int color[NMAX];		   // Cor do i-esimo vértice

vector< int > resp;

bool DFS(int u)
{

	// Estamos vizitando u
	color[u] = GRAY;

	for(auto v : grafo[u])
	{
	
		// Ignora se v já acabou
		if(color[v] == BLACK) continue; 
		
		// Se v está sendo vizitado, encontramos um ciclo, assim
		// retornamos false
		if(color[v] == GRAY) return false;
	
		if(DFS(v) == false) return false;
	
	}

	// Acabamos de visitar u
	color[u] = BLACK;

	// Parte do algoritmo de ordenação topológica
	resp.pb(u);
	
	return true;

}

int main()
{

	int n, m, u, v, i;

	while(cin >> n >> m)
	{
	
		if(n == 0 && m == 0) break;
		
		resp.clear();	
		for(i = 1;i <= n;i++) grafo[i].clear();
		for(i = 1;i <= n;i++) color[i] = WHITE; 
		// Inicialmente todos os vértices estão não vizitados
		
		while(m--)
		{
		
			cin >> u >> v;
			
			grafo[u].pb(v);
		
		}
		
		for(i = 1;i <= n;i++)
		{
			
			// Se já passamos por i, ignoramos ele
			if(color[i] != WHITE) continue;
			
			if(DFS(i) == false)
			{
				
				// Achamos um ciclo :(
				
				cout << "IMPOSSIBLE" << endl;
			
				resp.clear();
				
				break;
			
			}
		
		}
		
		// Parte do algoritmo de ordenação topológica
		reverse(resp.begin(), resp.end());
	
		for(auto cur : resp) cout << cur << endl;
	
	}
 
    return 0;
 
}
