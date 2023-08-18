#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 100010
#define fi first
#define se second
#define pb push_back
#define mp make_pair
#define WHITE 0
#define GRAY  1
#define BLACK 2

vector< int > grafo[NMAX];
int color[NMAX];

vector< int > resp;

bool DFS(int u)
{

	color[u] = GRAY;

	for(auto v : grafo[u])
	{
	
		if(color[v] == BLACK) continue;
		if(color[v] == GRAY) return false;
	
		if(DFS(v) == false) return false;
	
	}

	color[u] = BLACK;

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
		
		while(m--)
		{
		
			cin >> u >> v;
			
			grafo[u].pb(v);
		
		}
		
		for(i = 1;i <= n;i++)
		{
			
			if(color[i] != WHITE) continue;
			
			if(DFS(i) == false)
			{
				
				cout << "IMPOSSIBLE" << endl;
			
				resp.clear();
				
				break;
			
			}
		
		}
		
		reverse(resp.begin(), resp.end());
	
		for(auto cur : resp) cout << cur << endl;
	
	}
 
    return 0;
 
}
