/*
	흔한 문제이지만 항상 헷갈렸던 문제
	회의 시간에 시작시간과 종료시간이 주어졌을 때
	주어진 시간 안에 가장 많은 회의를 소화할 수 있도록 하는 문제

	1-3 1-7 같은 시간에 시작하지만 끝나는 시간이 다르다
	즉 끝나는 시간을 기준으로 생각하면 편하다

	끝나는 시간기준으로 소팅을 한 다음
	가능한 회의를 하나씩 카운트 해주면 풀 수 있다.


*/
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
vector<pair<int, int>> arr;

bool compare(pair<int, int> a, pair<int, int> b)
{
	if (a.second == b.second)
	{
		return a.first < b.first;
	}
	return a.second < b.second;
}

int main()
{
	int x, y;
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 0; i < N; i++)
	{
		scanf("%d %d", &x, &y);
		arr.push_back(make_pair(x, y));
	}
	// sort!!
	sort(arr.begin(), arr.end(), compare);

	
	int last = 0;
	int ans = 0;
	for (pair<int, int> temp : arr)
	{
		if (temp.first >= last)
		{
			last = temp.second;
			ans++;
		}
	}

	printf("%d", ans);
}