#include <stdio.h>

void bubble_sort(int arr[], int n);

int main() 
{
	int arr[] = {5,4,3,6,7,8,14,11,12,1,2,9,10,13};
	
    bubble_sort(arr, 14);
	
	for (int i = 0; i < 14; ++i)
		printf("%d ", arr[i]);
		
	return 0;
}

/* 氣泡排序法 */
void bubble_sort(int arr[], int n) {
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < i; ++j) {
      if (arr[j] > arr[i]) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }
  }
}
