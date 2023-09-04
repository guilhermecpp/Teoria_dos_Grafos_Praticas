#include <bits/stdc++.h>
using namespace std;

#define NMAX 200010

vector< int > grafo[NMAX];
int comp[NMAX];

int tempo = 1;
int pre[NMAX];
int low[NMAX];

int newId = 0;
map< string, int > id;
string iid[NMAX];

int qtdSCC = 0;
vector< string > SCC[NMAX];

stack< int > pilha;

void Tarjan(int u)
{

	pilha.push(u);
	
	pre[u] = low[u] = tempo++;

	for(auto v : grafo[u])
	{
	
		if(pre[v] == 0)
		{
		
			Tarjan(v);
			
			low[u] = min(low[u], low[v]);
		
		}else if(comp[v] == 0)
		{
			
			low[u] = min(low[u], pre[v]);
		
		}
	
	}
	
	if(pre[u] == low[u])
	{
	
		qtdSCC++;
	
		int v;
		
		do
		{
		
			v = pilha.top();
			pilha.pop();
			
			comp[v] = qtdSCC;
			SCC[qtdSCC].push_back(iid[v]);
		
		}while(v != u);
		
		sort(SCC[qtdSCC].begin(), SCC[qtdSCC].end());
	
	}
	
	return;

}

bool cmp(vector< string > a, vector< string > b)
{
	
	return a[0] < b[0];

}

int main()
{

	int n, u, v, i;
	
	string s;
	
	vector< string > avoid;
	
	cin >> n;
	
	for(i = 0;i < n;i++)
	{
	
		cin >> s;
	
		if(id[s] == 0)
		{
		
			id[s] = ++newId;
			iid[newId] = s;
		
		}

		u = id[s];	
		
		cin >> s;
	
		if(id[s] == 0)
		{
		
			id[s] = ++newId;
			iid[newId] = s;
		
		}

		v = id[s];
	
		grafo[u].push_back(v);
	
	}
	
	for(i = 1;i <= newId;i++)
	{
	
		if(comp[i] == 0) Tarjan(i);
	
	}
	
	sort(SCC + 1, SCC + qtdSCC + 1, cmp);
	
	for(i = 1;i <= qtdSCC;i++)
	{
	
		if(SCC[i].size() == 1) 
		{
		
			avoid.push_back(SCC[i][0]);
	
			continue;
	
		}
		
		cout << "okay";
		for(auto cur : SCC[i]) cout << " " << cur;
		cout << endl;
	
	}
	
	if(avoid.size() != 0)
	{
		
		cout << "avoid";
		for(auto cur : avoid) cout << " " << cur;
		cout << endl;

	}
	return 0;

}
