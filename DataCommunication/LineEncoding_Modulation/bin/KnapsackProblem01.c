#include <stdio.h>
int bubbleSort(int profit[], int n)
{
    int arr[] = profit;
    for (int i = 0; i < n - 1; i++)
    {
        for (int j = 0; j < n - i - 1; j++)
        {
            if (arr[j] > arr[j + 1])
            {
                // Swap arr[j] and arr[j + 1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    return arr;
}

int HighProfit(int n, int m, int profit[], int weight[])
{
    int bag[n];
    int maxProfit = 0;
    int index = 0;

    for (int j = 0; j < n; j++)
    {
        if (maxProfit < profit[j])
        {
            maxProfit = profit[j];
            index = j;
        }
    }
    return maxProfit;
}

int main()
{
    int n;         // no.of objects
    int m;         // Holdable weight of bag
    int profit[n]; // profit of each object
    int weight[n]; // weight of each object
    printf("enter the no.of objects : ");
    scanf("%d", &n);
    printf("enter the Holdable weight of bag : ");
    scanf("%d", &m);
    for (int i = 0; i < n; i++)
    {
        printf("enter the profit of each object : ");
        scanf("%d", &profit[i]);
        printf("enter the weight of each object : ");
        scanf("%d", &weight[i]);
    }
    int arr = bubbleSort(profit, n);
    int maxprofit = HighProfit(n, m, arr, weight);
    printf("%d", maxprofit);

    // call function;
}