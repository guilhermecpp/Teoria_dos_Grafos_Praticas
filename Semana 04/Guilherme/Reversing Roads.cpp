#include <bits/stdc++.h>
using namespace std;

#define NMAX 60

int n, m;

bool grafo[NMAX][NMAX];
int comp[NMAX];

vector< pair< int, int > > edges;

int tempo;
int pre[NMAX];
int low[NMAX];

int qtdSCC;
vector< int > SCC;

stack< int > pilha;

void Tarjan(int u)
{

	pilha.push(u);
	
	pre[u] = low[u] = tempo++;

	for(int v = 0;v < n;v++)
	{
	
		if(grafo[u][v] == false) continue;
	
		if(pre[v] == 0)
		{
		
			Tarjan(v);
			
			low[u] = min(low[u], low[v]);
		
		}else if(comp[v] == 0)
		{
			
			low[u] = min(low[u], pre[v]);
		
		}
	
	}
	
	if(pre[u] == low[u])
	{
	
		qtdSCC++;
	
		int v;
		
		do
		{
		
			v = pilha.top();
			pilha.pop();
			
			comp[v] = qtdSCC;
			if(qtdSCC == 1) SCC.push_back(v);
		
		}while(v != u);
	
	}
	
	return;

}

int main()
{

	int u, v, caso, i, j;
	
	pair< int, int > invert;
	
	caso = 1;
	
	while(cin >> n >> m)
	{
	
		for(i = 0;i < n;i++)
			for(j = 0;j < n;j++)
				grafo[i][j] = false;
	
		edges.clear();
	
		for(i = 0;i < m;i++)
		{
		
			cin >> u >> v;
			
			grafo[u][v] = true;
			
			edges.push_back({u, v});
		
		}
		
		tempo = 1;
		for(i = 0;i < n;i++) pre[i] = 0;
		for(i = 0;i < n;i++) comp[i] = 0;
		
		qtdSCC = 0;		
		SCC.clear();
		Tarjan(0);
		
		cout << "Case " << caso++ << ": ";
		
		if(SCC.size() == n)
		{
		
			cout << "valid" << endl;
		
		}else
		{
		
			invert = {-1, -1};
			
			for(j = 0;j < m;j++)
			{
			
				u = edges[j].first;
				v = edges[j].second;
				
				grafo[u][v] = false;
				grafo[v][u] = true;
		
				tempo = 1;
				for(i = 0;i < n;i++) pre[i] = 0;
				for(i = 0;i < n;i++) comp[i] = 0;
				
				qtdSCC = 0;
				SCC.clear();
				Tarjan(0);
				
				if(SCC.size() == n)
				{
				
					invert = {u, v};
			
					break;
			
				}
				
				grafo[u][v] = true;
				grafo[v][u] = false;
			
			}
			
			if(invert == make_pair(-1, -1))		
				cout << "invalid" << endl;
			else
				cout << invert.first << " " << invert.second << endl;
				
		}
	
	}
	
	return 0;

}
