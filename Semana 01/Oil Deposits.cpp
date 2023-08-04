// Link do problema: https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=513
// Status - ACCEPTED
// Comeplexidade - O(N + M)

#include <bits/stdc++.h>
using namespace std;       
 
#define NMAX 110
// No pior caso, o grid será desse tamanho, então a ideia é criar
// a estrutura que já suporta esse grid máximo

bool mat[NMAX][NMAX];
// True : Posição é '@' e não foi vizitada
// False: Posição é '*' ou já foi vizitada

int dlin[] = {-1, -1, -1,  0, 0,  1, 1, 1};
int dcol[] = {-1,  0,  1, -1, 1, -1, 0, 1};
// Esses dois vetores as combinações de todas as direções possíveis,
// isso é, a alteração de linha e coluna em cada direção.

void DFS(int lin, int col)
{
	
	int nlin, ncol, i;

	mat[lin][col] = false;
	// Nesse ponto sabemos que ali pertencia o caractere '@', e
	// marcando false, não o vizitaremos novamente
	
	for(i = 0;i < 8;i++) // Andar pelas 8 direções
	{
		
		nlin = lin + dlin[i]; // Cálculo da nova linha
		ncol = col + dcol[i]; // Cálculo da nova coluna
		
		if(mat[nlin][ncol] == false) continue;
		// se a posição é '*' ou já foi vizitada, não faz sentido ir
		
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
				// Suponha que não podemos andar em nenhuma posição,
				// como se todas fossem '*', assim serão setadas
				// como false
			
			}
		
		}
		
		// Ir do 0 até o n + 1 pode gerar um estranhamento, porém
		// aqui foi utilizada uma técnica para deixar a codificação
		// mais simples, pois para evitar a DFS sair da matriz, foi
		// adicionado uma espécie de 'padding' na matriz original,
		// afim de deixar ela com bordas preenchidas por '*', vamos
		// analizar um caso teste para melhor explicação:
		//
		//           *******
		// *@*@*     **@*@**   
		// **@** ==> ***@***
		// *@*@*     **@*@**
		//           *******
		// 
		
		for(i = 1;i <= n;i++)
		{
		
			cin >> s;
		
			for(j = 1;j <= m;j++)
			{
			
				if(s[j - 1] == '@') mat[i][j] = true;
				// Todas as posições que podemos andar '@' serão
				// setadas como true
			
			}
		
		}
		
		resp = 0;
		
		for(i = 1;i <= n;i++)
		{
		
			for(j = 1;j <= m;j++)
			{
			
				if(mat[i][j] == false) continue;
				// Se estamos em um '*' ou já passarmos por essa
				// posição já contabilizamos sua componente, assim
				// podemos ignorar-lá
				
				resp++; // Achamos uma nova componente
				
				DFS(i, j);
			
			}
		
		}
	
		// Apesar de aparentar que o código no pior dos casos irá 
		// rodar várias DFS, porém cada vértice passará pela função
		// somente uma vez, assim todo vértice (e por consequência 
		// toda aresta) será analizado somente uma vez, ficando o 
		// código assim com complexidade O(N + M) amortizado
		
		cout << resp << endl;
	
	}
	
    return 0;
 
} 
