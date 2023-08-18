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

int newId = 1;
map< string, int > id;

bool DFS(int u)
{

	color[u] = GRAY;

	for(auto v : grafo[u])
	{
	
		if(color[v] == BLACK) continue;
		if(color[v] == GRAY) return true;
	
		if(DFS(v) == true) return true;
	
	}

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
	
	while(cin >> s)
	{
	
		u = id[s];
		
		if(color[u] == GRAY)	cout << s << " safe" << endl;
		else					cout << s << " trapped" << endl;
	
	}
 
    return 0;
 
}
