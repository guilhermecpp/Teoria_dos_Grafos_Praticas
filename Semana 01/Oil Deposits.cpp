#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 110

bool mat[NMAX][NMAX];

int dlin[] = {-1, -1, -1,  0, 0,  1, 1, 1};
int dcol[] = {-1,  0,  1, -1, 1, -1, 0, 1};

void DFS(int lin, int col)
{
	
	int nlin, ncol, i;

	mat[lin][col] = false;
	
	for(i = 0;i < 8;i++)
	{
		
		nlin = lin + dlin[i];
		ncol = col + dcol[i];
		
		if(mat[nlin][ncol] == false) continue;
		
		DFS(nlin, ncol);
	
	}

}

int main()
{

	int n, m, resp, i, j;
	
	string s;
	
	while(cin >> n >> m)
	{
	
		if(n == 0 && m == 0) break;
		
		for(i = 0;i <= n + 1;i++)
		{
		
			for(j = 0;j <= m + 1;j++)
			{
			
				mat[i][j] = false;
			
			}
		
		}
		
		for(i = 1;i <= n;i++)
		{
		
			cin >> s;
		
			for(j = 1;j <= m;j++)
			{
			
				if(s[j - 1] == '@') mat[i][j] = true;
			
			}
		
		}
		
		resp = 0;
		
		for(i = 1;i <= n;i++)
		{
		
			for(j = 1;j <= m;j++)
			{
			
				if(mat[i][j] == false) continue;
				
				resp++;
				
				DFS(i, j);
			
			}
		
		}
		
		cout << resp << endl;
	
	}
	
    return 0;
 
} 
