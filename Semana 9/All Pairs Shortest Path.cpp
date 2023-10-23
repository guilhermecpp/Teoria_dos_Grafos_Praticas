#include <bits/stdc++.h>
using namespace std;

#define NMAX 160
#define INF 1000000000

typedef struct Edge
{

	int u, v, w;

}Ed;

vector< Ed > edges;

int dist[NMAX][NMAX];
int dist2[NMAX][NMAX];

void Solv(int n)
{

	int u, v, w, init, i;
	
	for(init = 0;init < n;init++)
	{
	
		for(i = 0;i < n;i++) dist[init][i] = INF;
		dist[init][init] = 0;
		
		for(i = 0;i < n;i++)
		{
		
			for(auto cur : edges)
			{
			
				u = cur.u;
				v = cur.v;
				w = cur.w;
			
				if(dist[init][u] != INF && dist[init][v] > dist[init][u] + w)
					dist[init][v] = dist[init][u] + w;
			
			}
		
		}
		
		for(i = 0;i < n;i++) dist2[init][i] = dist[init][i];
		
		for(i = 0;i < n;i++)
		{
		
			for(auto cur : edges)
			{
			
				u = cur.u;
				v = cur.v;
				w = cur.w;
			
				if(dist2[init][u] != INF && dist2[init][v] > dist2[init][u] + w)
					dist2[init][v] = dist2[init][u] + w;
			
			}
		
		}
		
		for(i = 0;i < n;i++) 
			if(dist[init][i] != dist2[init][i])
				dist[init][i] = -INF;
	
	}
	
	return;
	
}

int main()
{

	int n, m, q, u, v, w, i, j, k, l;
	
	while(cin >> n >> m >> q)
	{
	
		if(n == 0 && m == 0 && q == 0) break;

		edges.clear();

		while(m--)
		{
		
			cin >> u >> v >> w;
			
			edges.push_back({u, v, w});
		
		}
		
		Solv(n);
		
		while(q--)
		{
		
			cin >> u >> v;
			
			if(dist[u][v] == INF)		cout << "Impossible" << endl;
			else if(dist[u][v] == -INF)	cout << "-Infinity" << endl;
			else						cout << dist[u][v] << endl;	
		
		}
		
		cout << endl;		
	
	}	

    return 0;
 
}
