// Link do problema: https://www.beecrowd.com.br/judge/pt/problems/view/3215
// Status - ACCEPTED
// Comeplexidade - O(N + M)

#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 10010
// No pior caso, terá esse número de vértices então a ideia é criar
// a estrutura que já suporta esse número máximo de vértices

vector< int > grafo[NMAX];
// Lista de adjacência

int valor[NMAX];
// Valor de cada pessoa

bool marc[NMAX];
// Se já passou por essa pessoa na DFS
 
int DFS(int u) // Busca em profundidade - O(N + M)
{

	marc[u] = true;

	int x = valor[u];

	for(auto v : grafo[u])
	{
	
		if(marc[v] == true) continue;
	
		x += DFS(v);
	
	}
	
	return x;

}
// A ideia é que todos os vértices que o vértice inicial alcançar
// será somada à resposta

int main()
{

	int n, m, u, v, i;
	
	string resp = "POSSIBLE"; // Suponha que a resposta seja POSSIBLE
	
	cin >> n >> m;
	
	for(i = 0;i < n;i++) cin >> valor[i];
	
	for(i = 0;i < m;i++)
	{
	
		cin >> u >> v;
	
		grafo[u].push_back(v); // Adiciona aresta
		grafo[v].push_back(u); // Adiciona aresta
	
	}
	
	for(i = 0;i < n;i++) // Passa por todos os vértices
	{
	
		if(marc[i] == true) continue; 
		// Se o vértice já foi vizitado, sua componente já foi
		// verificada, assim não a nescessidade de rodar uma busca
		// nesse vértice
		
		if(DFS(i) != 0) resp = "IMPOSSIBLE";
		// Caso alguma componente tenha a soma diferente de 0, a
		// resposta é IMPOSSIBLE
	
	}
	
	// Apesar de aparentar que o código no pior dos casos irá rodar
	// várias DFS, porém cada vértice passará pela função somente uma 
	// vez, assim todo vértice (e por consequência toda aresta) será
	// analizado somente uma vez, ficando o código assim com
	// complexidade O(N + M) amortizado
	
	cout << resp << endl;
    
    return 0;
 
} 
