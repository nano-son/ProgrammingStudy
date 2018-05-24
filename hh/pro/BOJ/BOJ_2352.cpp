// LIS 길이 구하는 문제
#include <stdio.h>
#include <algorithm>

using namespace std;

int N;
int arr[400001];
int LIS[400001];
int ret;

int my_search(int arr[400001], int value, int start, int end) {

	int mid = (start + end) / 2;

	if (start == end) {
		return mid;
	}
	if (value > arr[mid]) {
		my_search(arr, value, mid + 1, end);
	}
	else {
		my_search(arr, value, start, mid);
	}
}

int main() {
	 
	freopen("input.txt", "r", stdin);
	scanf("%d", &N);

	for (int i = 0; i < N; i++) {

		scanf("%d", &arr[i]);
	}

	for (int i = 0; i < N; i++) {

		if (ret == 0) {
			LIS[ret++] = arr[i];
		}
		
		if (LIS[ret - 1] < arr[i]) {
			LIS[ret++] = arr[i];
		}
		else if (LIS[ret - 1] > arr[i]) {

			LIS[my_search(LIS, arr[i], 0, ret - 1)] = arr[i];
		}
	}

	printf("%d", ret);
}