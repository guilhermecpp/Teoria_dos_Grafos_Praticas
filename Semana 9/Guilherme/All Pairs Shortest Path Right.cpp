#include <bits/stdc++.h>
using namespace std;

#define NMAX 160
#define INF 1000000000

int flody[NMAX][NMAX];

int main()
{

    int n, m, q, u, v, w, i, j, k;

    while(cin >> n >> m >> q)
    {

        if(n == 0 && m == 0 && q == 0) break;

        for(i = 0;i < n;i++)
            for(j = 0;j < n;j++)
                flody[i][j] = INF;

        for(i = 0;i < n;i++)
            flody[i][i] = 0;

        while(m--)
        {

            cin >> u >> v >> w;

            flody[u][v] = min(flody[u][v], w);

        }

        for(k = 0;k < n;k++)
            for(i = 0;i < n;i++)
                for(j = 0;j < n;j++)
                    if(flody[i][k] < INF && flody[k][j] < INF)
                        flody[i][j] = min(flody[i][j],
                                          flody[i][k] + flody[k][j]);

        for(k = 0;k < n;k++)
            for(i = 0;i < n;i++)
                for(j = 0;j < n;j++)
                    if(flody[k][k] < 0 && flody[i][k] != INF && flody[k][j] != INF)
                        flody[i][j] = -INF;

        while(q--)
        {

            cin >> u >> v;

            if(flody[u][v] == INF)          cout << "Impossible" << endl;
            else if(flody[u][v] == -INF)    cout << "-Infinity" << endl;
            else                            cout << flody[u][v] << endl;

        }

        cout << endl;

    }

    return 0;

}
