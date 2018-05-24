#include <stdio.h>
#include <queue>

using namespace std;

/*
	어떻게 이런 생각을 해낼 수 있을까....
	자릿수 숫자마다 감소하는 형태의 숫자 찾기
	1~9까지 큐에 넣고
	큐에서 하나씩 빼가면서 맨 끝에 자리보다 작은 수를 큐에 다시 넣어가면서
	찾으셨다.
	갓
*/



const int MAX = 1000000;

int N;
int idx = 9;
queue<long long> que;
long long down[MAX + 1];

void preCal() {

	while (idx <= N) {

		if (que.empty())
			return;
		long long downNum = que.front();
		que.pop();
		
		int lastNum = downNum % 10;

		for (int i = 0; i < lastNum; i++) {
			que.push(downNum * 10 + i);
			down[++idx] = downNum * 10 + i;
		}
	}

}

int main() {
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 1; i <= 9; i++) {
		que.push(i);
		down[i] = i;
	}
	preCal();

	if (!down[N] && N)
		printf("%d", -1);
	else
		printf("%lld", down[N]);


}