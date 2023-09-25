#include <bits/stdc++.h>
using namespace std;

#define NMAX 30

vector< int > grafo[NMAX];

double vet[NMAX];

int dist[NMAX];

double pot095[NMAX];

int getId(char c)
{

	if(c == '*') return 0;
	
	return (int)(c - 'A') + 1;

}

char getChar(int u)
{

	if(u == 0) return '*';
	
	return (char)(u - 1 + 'A');

}

void BFS(int init)
{
	
	int u;

	queue< int > fila;

	memset(dist, -1, sizeof dist);
	
	dist[init] = 0;
	fila.push(init);
	
	while(fila.empty() == false)
	{
	
		u = fila.front();
		fila.pop();
		
		for(auto v : grafo[u])
		{
		
			if(dist[v] == -1)
			{
			
				dist[v] = dist[u] + 1;
				fila.push(v);
			
			}
		
		}
	
	}
	
	return;

}

int main()
{

	int n, u, v, idBest, i;
	
	double best;
	
	char c;
	
	string s;
	
	pot095[0] = 1;
	for(i = 1;i < NMAX;i++) 
		pot095[i] = pot095[i - 1] * 0.95;
	
	while(cin >> n)
	{
	
		for(i = 0;i < NMAX;i++) grafo[i].clear();
		
		for(i = 1;i <= n;i++)
		{
			
			cin >> c;
			u = getId(c);
			
			cin >> vet[u];
			
			cin >> s;
			
			for(auto cur : s)
			{
			
				v = getId(cur);
				
				grafo[u].push_back(v);
				if(v != '*') grafo[v].push_back(u);
			
			}
		
		}
			
		BFS(0);
		
		for(i = 0;i < NMAX;i++) 
			vet[i] = vet[i] * pot095[dist[i]];
			
		best = 0;
		idBest = 0;
			
		for(i = 0;i < NMAX;i++)
		{
		
			if(best < vet[i])
			{
			
				best = vet[i];
				idBest = i;
				
			}
		
		}
		
		cout << "Import from " << getChar(idBest) << endl;
		
	}
	
    return 0;
 
}	
