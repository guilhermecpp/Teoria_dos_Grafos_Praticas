#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 10010

vector< int > grafo[NMAX];

int valor[NMAX];

bool marc[NMAX];
 
int DFS(int u)
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

int main()
{

	int n, m, u, v, i;
	
	string resp = "POSSIBLE";
	
	cin >> n >> m;
	
	for(i = 0;i < n;i++) cin >> valor[i];
	
	for(i = 0;i < m;i++)
	{
	
		cin >> u >> v;
	
		grafo[u].push_back(v);
		grafo[v].push_back(u);
	
	}
	
	for(i = 0;i < n;i++)
	{
	
		if(marc[i] == true) continue;
		
		if(DFS(i) != 0) resp = "IMPOSSIBLE";
	
	}
	
	cout << resp << endl;
    
    return 0;
 
} 
