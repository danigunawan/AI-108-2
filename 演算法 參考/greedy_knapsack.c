#include <stdio.h>
#include <stdlib.h>

void package(int n,float c,float w[],float x[]);

int main(void)
{
    int n = 3;
    float c = 20;
    float w[] = {15,10,18};//已經按照單位價值降序排列
    float *x;
    x = (float*)malloc(sizeof(float)*n);
    printf("******背包*******\n");
    package(n,c,w,x);
}

void package(int n,float c, float w[],float x[])
{
    int i;

    for(i=0;i<n;i++)
        x[i] = 0;
        
    for(i=0;i<n;i++)
    {
        if(w[i] > c)
            break;
            
        x[i] = 1;
        c = c - w[i];
        printf("放入第%d件物品，背包剩餘容量%f.\n",(i+1),c);
    }

    if(i<=n)//還可以放入一個物品的一部分
    {
        x[i] = c/w[i];
        printf("放入第%d件物品的%f部分.背包剩餘容量為0.\n",(i+1),w[i]*x[i]);
    }

}
