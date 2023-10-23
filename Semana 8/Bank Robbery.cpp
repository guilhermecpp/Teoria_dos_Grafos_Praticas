#include <bits/stdc++.h>
using namespace std;

#define NMAX 1010
#define INF 1000000000

typedef pair< int, int > pii;
typedef vector< int > vi;
typedef vector< pii > vii;

int n;
vii grafo[NMAX];

int dist[NMAX];

void Dijkstra(vi sources)
{

	int u, v, w, d, i;

	priority_queue< pii, vii, greater< pii > > fila;

	for(i = 0;i < n;i++) dist[i] = INF;
	
	for(auto cur : sources) 
	{
	
		dist[cur] = 0;
		fila.push({dist[cur], cur});

	}
	
	while(fila.empty() == false)
	{
	
		d = fila.top().first;
		u = fila.top().second;
		fila.pop();
		
		if(d != dist[u]) continue;
		
		for(auto viz : grafo[u])
		{
		
			w = viz.first;
			v = viz.second;
			
			if(dist[v] > dist[u] + w)
			{
				
				dist[v] = dist[u] + w;
				fila.push({dist[v], v});
			
			}
		
		}
	
	}
	
	return;

}

int main()
{

	int m, b, p, u, v, w, i;
	
	bool fi;
	
	vi ba, po, resp;
	
	while(cin >> n >> m >> b >> p)
	{
	
		for(i = 0;i < n;i++) grafo[i].clear();
		ba.clear();
		po.clear();
		resp.clear();
		
		for(i = 0;i < m;i++)
		{
		
			cin >> u >> v >> w;
			
			grafo[u].push_back({w, v});
			grafo[v].push_back({w, u});
		
		}
		
		for(i = 0;i < b;i++)
		{
		
			cin >> u;
			
			ba.push_back(u);
		
		}
		
		for(i = 0;i < p;i++)
		{
		
			cin >> u;
			
			po.push_back(u);
		
		}
		
		Dijkstra(po);
		
		w = -1;
		
		for(auto cur : ba)
		{
		
			if(w < dist[cur])
			{
			
				w = dist[cur];
				resp.clear();
			
			}
			
			if(w == dist[cur]) resp.push_back(cur);
		
		}
		
		sort(resp.begin(), resp.end());
		
		cout << resp.size() << " ";
		
		if(w == INF)	cout << "*" << endl;
		else			cout << w << endl;
		
		fi = true;
		
		for(auto cur : resp)
		{
		
			if(fi == false) cout << " ";
			fi = false;
			
			cout << cur;
		
		}
		
		cout << endl;
	
	}

    return 0;
 
}
