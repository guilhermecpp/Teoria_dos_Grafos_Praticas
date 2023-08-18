#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 310
#define fi first
#define se second
#define pb push_back
#define mp make_pair

vector< int > grafo[NMAX];
bool has[NMAX];
bool marc[NMAX];

string resp;

void DFS(int u)
{

	marc[u] = true;

	for(auto v : grafo[u])
	{
	
		if(marc[v] == true) continue;
	
		DFS(v);
	
	}

	resp.pb((char)(u));
	
	return;

}

int main()
{

	int i;

	string s, last;

	while(cin >> s)
	{
	
		if(s == "#") break;
		
		for(auto letra : s) has[letra] = true;
		
		for(i = 0;i < last.size();i++)
		{
		
			if(s[i] != last[i]) break;
		
		}
		
		if(i != last.size()) grafo[last[i]].pb(s[i]);
		
		last = s;
	
	}
	
	resp = "";
	
	for(i = 'A';i <= 'Z';i++)
	{
	
		if(has[i] == false || marc[i] == true) continue;
	
		DFS(i);
	
	}
	
	reverse(resp.begin(), resp.end());
	
	cout << resp << endl;
 
    return 0;
 
}
