#include <bits/stdc++.h>
using namespace std; 

#define NMAX 110

typedef struct Edge
{

    int u, v, w;

}Ed; 

vector< pair< int, int > > grafo[NMAX];

int pai[NMAX];
int tam[NMAX];  

int findPai(int u)
{

    if(pai[u] == u) return u;
    
    return pai[u] = findPai(pai[u]);

}

bool Join(int u, int v)
{

    u = findPai(u);
    v = findPai(v);
    
    if(u == v) return false;
    
    if(tam[u] > tam[v]) 
        swap(u, v);
        
    pai[u] = v;
    tam[v] += tam[u];
    
    return true;

}

int getMax(int u, int dest, int pai, int ma)
{

    if(u == dest) return ma;
    
    int v, w, x;
    
    for(auto viz : grafo[u])
    {
    
        w = viz.first;
        v = viz.second;
        
        if(v == pai) continue;
        
        x = getMax(v, dest, u, max(ma, w));  
             
        if(x != -1) return x;    
 
    }
    
    return -1;

}

bool cmp(Edge a, Edge b)
{

    return a.w < b.w;

}

int main()
{

    int n, m, q, u, v, w, caso = 1, i, j;
    
    vector< Ed > edges;
    
    while(cin >> n >> m >> q)
    {
    
        if(n == 0 && m == 0 && q == 0) break;
    
        if(caso != 1) cout << endl;
    
        edges.clear();      
        for(i = 1;i <= n;i++) grafo[i].clear();
        
        for(i = 1;i <= n;i++) pai[i] = i;
        for(i = 1;i <= n;i++) tam[i] = 1;
                
        while(m--) 
        {
        
            cin >> u >> v >> w;
            
            edges.push_back({u, v, w});           
       
        }
    
        sort(edges.begin(), edges.end(), cmp);
    
        for(auto cur : edges)
        {
    
            u = cur.u;
            v = cur.v;
            w = cur.w;
    
            if(Join(u, v) == false)
                continue;
            
            grafo[u].push_back({w, v});
            grafo[v].push_back({w, u});
      
        }
        
        cout << "Case #" << caso++ << endl;
    
        while(q--)
        {
        
            cin >> u >> v;
            
            w = getMax(u, v, u, -1);
            
            if(w == -1)    cout << "no path" << endl;
            else           cout << w << endl;                   
        
        }
        
    }

    return 0;

}