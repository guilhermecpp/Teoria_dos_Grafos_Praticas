#include<bits/stdc++.h>
using namespace std;

#define NMAX 1000010

typedef long long ll;

typedef struct Edge
{

	ll u, v, w;
	
	bool operator < (Edge other) const
	{
	
		return w < other.w;
	
	}

}Ed;

ll pai[NMAX];
ll tam[NMAX];

vector< Ed > edges;

ll findPai(ll u)
{

	if(u == pai[u]) return u;
	
	return pai[u] = findPai(pai[u]);

}

bool Join(ll u, ll v)
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

	ll n, m, k, u, v, w, r1, r2, i;
	
	bool first = true;
	
	while(cin >> n)
	{
	
		if(first == false) cout << endl;
		first = false;
	
		edges.clear();
		
		r1 = 0LL;
		r2 = 0LL;
		
		for(i = 1;i < n;i++)
		{
		
			cin >> u >> v >> w;
			
			r1 += w;
		
		}
		
		cin >> k;
		
		for(i = 0;i < k;i++)
		{
		
			cin >> u >> v >> w;
			
			edges.push_back({u, v, w});
		
		}
		
		cin >> m;
		
		for(i = 0;i < m;i++)
		{
		
			cin >> u >> v >> w;
			
			edges.push_back({u, v, w});
			
		}
		
		sort(edges.begin(), edges.end());
		
		for(i = 1;i <= n;i++) pai[i] = i;
		for(i = 1;i <= n;i++) tam[i] = 1;
		
		for(auto ed : edges)
		{
		
			u = ed.u;
			v = ed.v;
			w = ed.w;
			
			if(Join(u, v) == true) r2 += w;
		
		}
		
		cout << r1 << endl;
		cout << r2 << endl;
		
	}
	
	return 0;

}
