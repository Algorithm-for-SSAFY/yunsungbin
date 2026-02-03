#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<stdlib.h>
// n <= 1000

int queue[2001];
int front = -1;
int rear = -1;
int cnt = 1;

int size;


void push(int i) {
	queue[++rear] = i;
}

int pop() {
	size--;
	return queue[++front];
}

void shots(int ix) {
	int tmp;
	for (int i = ix; i <= rear; ++i) {
		queue[i] = queue[i + 1];
	}
	rear--;
	front--;
}

void makeQueue(int n) {
	for (int i = 0; i < n; ++i) {
		push(i+1);
	}
}

int main()
{
	int n, k;

	scanf("%d", &n);
	scanf("%d", &k);
	size = n;

	makeQueue(n);
	printf("<");
	while (size > 0) { // cnt 1 front -1 - cnt 2 front 0 - cnt 3 front 1 ! pop -> size = 6, front 2 ! shots front(2) rear 5
				// cnt 4 front 3 - cnt 5 front 4 - cnt 6 
		if (cnt % k == 0)
		{
			if (size > 1)
				printf("%d, ", pop());
			else
				printf("%d", pop());
			shots(front);
		}
		else
			front++;
		cnt++;
		if (front >= rear)
			front = -1;
	}
	printf(">");


	return 0;
}