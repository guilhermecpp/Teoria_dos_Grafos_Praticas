#include<bits/stdc++.h>
using namespace std;

#define NMAX 1000010

typedef struct Edge
{

	int u, v, w;
	
	bool operator < (Edge other) const
	{
	
		return w < other.w;
	
	}

}Ed;

int pai[NMAX];
int tam[NMAX];

vector< Ed > edges;

int findPai(int u)
{

	if(u == pai[u]) return u;
	
	return pai[u] = findPai(pai[u]);

}

bool Join(int u, int v)
{

	u = findPai(u);
	v = findPai(v);
	
	if(u == v) return false;
	
	if(tam[u] > tam[v]) swap(u, v);
	
	pai[u] = v;
	tam[v] += tam[v];
	
	return true;

}

int main()
{	

	int n, m, k, u, v, w, ma, qtd, i;
	
	ma = -1;
	qtd = 0;
	
	cin >> n >> m;
	
	for(i = 0;i < n;i++) pai[i] = i;
	for(i = 0;i < n;i++) tam[i] = 1;

	for(i = 0;i < m;i++)
	{
	
		cin >> u >> v >> w;
		
		edges.push_back({u, v, w});
		
	}
	
	sort(edges.begin(), edges.end());
	
	for(auto ed : edges)
	{
	
		u = ed.u;
		v = ed.v;
		w = ed.w;
		
		if(Join(u, v) == true) 
		{
		
			ma = w;
			qtd++;
			
		}
	
	}
	
	if(qtd == n - 1)
	{
	
		cout << ma << endl;
	
	}else
	{
	
		cout << "IMPOSSIBLE" << endl;
		
	}
	
	return 0;

}
