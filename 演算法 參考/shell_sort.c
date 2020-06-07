#include <stdio.h> 

void ShellSort(int[], int n);

int main(void) 
{ 
    int data[10] = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81}; 
      
    ShellSort(data, 10);
    
    for(int i = 0; i < 10; i++) 
        printf("%d ", data[i]); 
    putchar('\n'); 
    
    return 0; 
} 

void ShellSort(int arr[], int n) {
	int gap, i, j;
	int temp;
	for (gap = n >> 1; gap > 0; gap >>= 1)
		for (i = gap; i < n; i++) {
			temp = arr[i];
			for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap)
				arr[j + gap] = arr[j];
			arr[j + gap] = temp;
		}
}