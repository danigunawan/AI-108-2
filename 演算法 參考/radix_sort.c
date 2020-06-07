#include <stdio.h> 

void radixSort(int[]);
void print(int data[], int n);

int main(void) 
{ 
    int data[10] = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81}; 
      
    printf("\n排序前: "); 
    print(data, 10);

    radixSort(data);
    
    printf("\n排序後: "); 
    print(data, 10);
    
    return 0; 
} 

void print(int data[], int n)
{
    for(int i = 0; i < n; i++) 
        printf("%d ", data[i]); 
    putchar('\n'); 
}

void radixSort(int data[]) {
    int temp[10][10] = {0}; 
    int order[10] = {0}; 
    
    int n = 1; 
    while(n <= 10) { 
        for(int i = 0; i < 10; i++) { 
            int lsd = ((data[i] / n) % 10); 
            temp[lsd][order[lsd]] = data[i]; 
            order[lsd]++; 
        } 
        
        // 重新排列
        int k = 0;
        for(int i = 0; i < 10; i++) { 
            if(order[i] != 0)  {
                for(int j = 0; j < order[i]; j++, k++) { 
                    data[k] = temp[i][j]; 
                } 
            }
            order[i] = 0; 
        } 

        n *= 10; 
    }     
}