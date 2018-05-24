#include <stdio.h>

/*
	얼른 disjoint-set 마스터가 되서
	이런 문제를 보고 union find 알고리즘을 응용해서 풀 수 있게 하자.
	알고리즘 코드를 알고 있다고 풀 수 있는건 아닌거 같다.
	그걸 이해하고 있고 응용할 수 있어야 돼... ㅠㅠ
*/

using namespace std;

bool arr[300001];
int A, B;
int parent[300001];
int N, L;

void init()
{
	
	for (int i = 1; i <= L; i++)
	{
		parent[i] = i;
	}
}

int find(int u)
{
	if (u == parent[u])
		return u;
	return parent[u] = find(parent[u]);
}


void merge(int u, int v)
{
	u = find(u);
	v = find(v);
	parent[u] = v;

}

int main()
{
	
	freopen("input.txt", "r", stdin);
	scanf("%d %d", &N, &L);
	init();
	
	for (int i = 1; i <= N; i++)
	{
		scanf("%d %d", &A, &B);
		
		if (!arr[A]) 
		{ 
			arr[A] = true;
			merge(A, B);
			printf("LADICA\n");
		}
		else if (!arr[B]) 
		{
			arr[B] = true;
			merge(B, A);
			printf("LADICA\n");
		}
		else if (!arr[find(A)]) 
		{
			arr[find(A)] = true;
			merge(A, B);
			printf("LADICA\n");
		}
		else if (!arr[find(B)]) 
		{
			arr[find(B)] = true;
			merge(B, A);
			printf("LADICA\n");
		}
		else 
		{
			printf("SMECE\n");
		}

	}

}
