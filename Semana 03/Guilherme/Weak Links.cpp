#include <bits/stdc++.h>
using namespace std; 

#define NMAX 100010

vector< int > grafo[NMAX];      

int tempo;
int pre[NMAX];
int low[NMAX];

int comp[NMAX];

vector< pair< int, int > > bridges;
 
void Tarjan(int u, int pai)
{

	pre[u] = low[u] = tempo++;
	
	for(auto v : grafo[u])
	{
	
		if(pre[v] == 0)
		{
		
			Tarjan(v, u);
			
			low[u] = min(low[u], low[v]);
		
			if(low[v] > pre[u]) 
				bridges.push_back({min(u, v), max(u, v)});
			
		}else if(v != pai)
		{
			
			low[u] = min(low[u], pre[v]);
		
		}
	
	}
		
	return;

}

bool cmp(pair< int, int > a, pair< int, int > b)
{

	if(a.first != b.first) return a.first < b.first;
	
	return a.second < b.second;

}
 
int main()
{

	int n, m, u, v, i;

	while(cin >> n >> m)
	{
	
		if(n == 0 && m == 0) break;
			
		for(i = 0;i < n;i++) grafo[i].clear();
	
		while(m--)
		{
			
			cin >> u >> v;
			
			grafo[u].push_back(v);
			grafo[v].push_back(u);
		
		}
			
		tempo = 1;
		for(i = 0;i < n;i++) pre[i] = 0;
		
		bridges.clear();
		
		Tarjan(0, 0);
		
		sort(bridges.begin(), bridges.end(), cmp);
		
		cout << bridges.size();
	
		for(auto cur : bridges)
			cout << " " << cur.first << " " << cur.second;
	
		cout << endl;
	
	}
	
    return 0;
 
}
