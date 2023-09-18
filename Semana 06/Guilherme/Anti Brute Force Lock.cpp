#include <bits/stdc++.h>
using namespace std; 

#define NMAX 510

typedef struct Edge
{

    int u, v, w;

}Ed; 

int vet[NMAX];

int pai[NMAX];
int tam[NMAX];  
 
int getDist(int s1, int s2)
{

    int a, b, x, r, i;
    
    r = 0;

    for(i = 0;i < 4;i++)
    {
    
        a = s1 % 10;
        b = s2 % 10;
        
        x = abs(a - b);
        
        r += min(x, 10 - x);
        
        s1 /= 10;
        s2 /= 10;
    
    }
    
    return r;

}

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

bool cmp(Edge a, Edge b)
{

    return a.w < b.w;

}

int main()
{

    int tt, n, u, v, w, resp, i, j;
    
    vector< Ed > edges;
    
    cin >> tt;
    
    while(tt--)
    {
    
        edges.clear();
        resp = 0;
    
        cin >> n;
        
        for(i = 1;i <= n;i++) pai[i] = i;
        for(i = 1;i <= n;i++) tam[i] = 1;
                
        for(i = 1;i <= n;i++) cin >> vet[i];
            
        for(i = 1;i <= n;i++)
        {
        
            for(j = i + 1;j <= n;j++)
            {
            
                edges.push_back({i, j, getDist(vet[i], vet[j])});
                     
            }
        
        }
    
        sort(edges.begin(), edges.end(), cmp);
    
        for(auto cur : edges)
        {
    
            u = cur.u;
            v = cur.v;
            w = cur.w;
    
            if(Join(u, v) == false)
                continue;
            
            resp += w;
    
        }
    
        w = getDist(vet[1], 0);
    
        for(i = 2;i <= n;i++)
            w = min(w, getDist(vet[i], 0));
        
        resp += w;
    
        cout << resp << endl;
        
    }

    return 0;

}