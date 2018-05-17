
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