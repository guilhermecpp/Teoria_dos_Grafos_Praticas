#include <bits/stdc++.h>
using namespace std; 

#define NMAX 100010

vector< int > grafo[NMAX];      

int tempo;
int pre[NMAX];
int low[NMAX];

int comp[NMAX];
 
void Tarjan(int u, int pai)
{

	pre[u] = low[u] = tempo++;
	
	for(auto v : grafo[u])
	{
	
		if(pre[v] == 0)
		{
		
			Tarjan(v, u);
			
			low[u] = min(low[u], low[v]);
		
			if(low[v] >= pre[u]) comp[u]++;
			
		}else if(v != pai)
		{
			
			low[u] = min(low[u], pre[v]);
		
		}
	
	}
	
	if(u != pai) comp[u]++;
		
	return;

}

bool cmp(pair< int, int > a, pair< int, int > b)
{

	if(a.second != b.second) return a.second > b.second;
	
	return a.first < b.first;

}
 
int main()
{

	int n, m, u, v, i;
	
	vector< pair< int, int > > resp; 

	while(cin >> n >> m)
	{
	
		if(n == 0 && m == 0) break;
			
		for(i = 0;i < n;i++) grafo[i].clear();
	
		while(cin >> u >> v)
		{
		
			if(u == -1 && v == -1) break;
			
			grafo[u].push_back(v);
			grafo[v].push_back(u);
		
		}
			
		tempo = 1;
		for(i = 0;i < n;i++) pre[i] = 0;
		for(i = 0;i < n;i++) comp[i] = 0;
		
		Tarjan(0, 0);
		
		resp.clear();
		
		for(i = 0;i < n;i++) 
			resp.push_back({i, comp[i]});
			
		sort(resp.begin(), resp.end(), cmp);
			
		for(i = 0;i < m;i++)
			cout << resp[i].first << " " << resp[i].second << endl;
	
		cout << endl;
	
	}
	
    return 0;
 
}
