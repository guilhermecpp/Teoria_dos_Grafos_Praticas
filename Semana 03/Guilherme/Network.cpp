#include <bits/stdc++.h>
using namespace std; 

#define NMAX 110

vector< int > grafo[NMAX];      

int tempo;
int pre[NMAX];
int low[NMAX];

bool point[NMAX];
 
void Tarjan(int u, int pai)
{

	pre[u] = low[u] = tempo++;
	
	int ch = 0;
	
	for(auto v : grafo[u])
	{
	
		if(pre[v] == 0)
		{
		
			ch++;
		
			Tarjan(v, u);
			
			low[u] = min(low[u], low[v]);
		
			if(low[v] >= pre[u]) point[u] = true;
			
		}else if(v != pai)
		{
			
			low[u] = min(low[u], pre[v]);
		
		}
	
	}
	
	if(u == pai) 
		point[u] = (ch > 1);
		
	return;

}
 
int main()
{

	int n, u, v, x, resp, i;
	
	bool fi;
	
	string s;

	while(cin >> n)
	{
	
		if(n == 0) break;
			
		for(i = 1;i <= n;i++) grafo[i].clear();
	
		getchar();
	
		while(getline(cin, s))
		{
		
			if(s == "0") break;
		
			s.push_back(' ');
		
			fi = true;
			x = 0;
		
			for(auto letra : s)	
			{
			
				if(letra == ' ')
				{
				
					if(fi == true)
					{
						
						u = x;
						
						fi = false;
					
					}else
					{
					
						v = x;
					
						grafo[u].push_back(v);
						grafo[v].push_back(u);
					
					}
					
					x = 0;
				
				}else
				{
				
					x = (10 * x) + (int)(letra - '0');
				
				}
			
			}
		
		}
			
		tempo = 1;
		for(i = 1;i <= n;i++) pre[i] = 0;
		for(i = 1;i <= n;i++) point[i] = false;
		
		Tarjan(1, 1);
		
		resp = 0;
		
		for(i = 1;i <= n;i++) 
			resp += point[i];
			
		cout << resp << endl;
	
	}
	
    return 0;
 
}
