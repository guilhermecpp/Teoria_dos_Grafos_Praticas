#include <bits/stdc++.h>
using namespace std;

#define NMAX 60
#define INF 1000000000

typedef struct Info
{

	int lin, col, dir;

}In;

int n, m;

int matInit[NMAX][NMAX];

bool mat[NMAX][NMAX];

int dist[NMAX][NMAX][5];

int dlin[] = {-1, 0, 1,  0};
int dcol[] = { 0, 1, 0, -1};

bool Valid(int lin, int col)
{

	return (1 <= lin && lin <= n && 1 <= col && col <= m);

}

void BFS(int ilin, int icol, int idir)
{
	
	int lin, col, dir, nlin, ncol, ndir;
	
	queue< In > fila;

	for(int i = 1;i <= n;i++)
		for(int j = 1;j <= m;j++)
			for(int k = 0;k < 4;k++)
				dist[i][j][k] = INF;

	dist[ilin][icol][idir] = 0;
	fila.push({ilin, icol, idir});
		
	while(fila.empty() == false)
	{
	
		lin = fila.front().lin;
		col = fila.front().col;
		dir = fila.front().dir;
		fila.pop();
	
		nlin = lin;
		ncol = col;
		ndir = (dir + 1) % 4;
		
		if(dist[nlin][ncol][ndir] == INF)
		{
		
			dist[nlin][ncol][ndir] = dist[lin][col][dir] + 1;
			fila.push({nlin, ncol, ndir});
		
		}
	
		nlin = lin;
		ncol = col;
		ndir = (dir + 3) % 4;
		
		if(dist[nlin][ncol][ndir] == INF)
		{
		
			dist[nlin][ncol][ndir] = dist[lin][col][dir] + 1;
			fila.push({nlin, ncol, ndir});
		
		}
		
		for(int i = 1;i <= 3;i++)
		{
		
			nlin = lin + i * dlin[dir];
			ncol = col + i * dcol[dir];
			ndir = dir;
			
			if(Valid(nlin, ncol) == false) break;
			if(mat[nlin][ncol] == false) break;
			
			if(dist[nlin][ncol][ndir] == INF)
			{
			
				dist[nlin][ncol][ndir] = dist[lin][col][dir] + 1;
				fila.push({nlin, ncol, ndir});
			
			}
		
		}
	
	}
	
	return;

}

int main()
{	

	int x, a, b, c, d, dir, i, j;
	
	string s;
	
	while(cin >> n >> m)
	{
	
		if(n == 0 && m == 0) break;
		
		for(i = 1;i <= n;i++)
		{
		
			for(j = 1;j <= m;j++)
			{
			
				cin >> matInit[i][j];
			
			}
		
		}
		
		for(i = 1;i <= n;i++)
		{
		
			for(j = 1;j <= m;j++)
			{
			
				x = 0;
				x += matInit[i][j];
				x += matInit[i][j + 1];
				x += matInit[i + 1][j];
				x += matInit[i + 1][j + 1];
				
				mat[i][j] = (x == 0);
			
			}
		
		}
		
		n--;
		m--;
		
		cin >> a >> b >> c >> d >> s;
		
		if(s == "north") 	dir = 0;
		if(s == "east") 	dir = 1;
		if(s == "south")	dir = 2;
		if(s == "west") 	dir = 3;
		
		BFS(a, b, dir);
		
		x = INF;
		x = min(x, dist[c][d][0]);
		x = min(x, dist[c][d][1]);
		x = min(x, dist[c][d][2]);
		x = min(x, dist[c][d][3]);
		
		if(x == INF) x = -1;
		
		cout << x << endl;
		
	}	
	
    return 0;
 
}	
