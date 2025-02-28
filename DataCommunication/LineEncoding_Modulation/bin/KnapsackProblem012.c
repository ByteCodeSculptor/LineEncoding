#include <stdio.h>

int max(int a, int b)
{
    return (a > b) ? a : b;
}

int knapsack(int capacity, int weight[], int profit[], int n)
{
    int dp[n + 1][capacity + 1];

    // Build the dp table in bottom-up manner
    for (int i = 0; i <= n; i++)
    {
        for (int w = 0; w <= capacity; w++)
        {
            if (i == 0 || w == 0)
                dp[i][w] = 0;
            else if (weight[i - 1] <= w)
                dp[i][w] = max(profit[i - 1] + dp[i - 1][w - weight[i - 1]], dp[i - 1][w]);
            else
                dp[i][w] = dp[i - 1][w];
        }
    }

    return dp[n][capacity];
}

int main()
{
    int n; // Number of items
    printf("Enter the number of items: ");
    scanf("%d", &n);

    int profit[n], weight[n];
    printf("Enter the profits of the items: ");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &profit[i]);
    }

    printf("Enter the weights of the items: ");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &weight[i]);
    }

    int capacity;
    printf("Enter the capacity of the knapsack: ");
    scanf("%d", &capacity);

    int maxProfit = knapsack(capacity, weight, profit, n);
    printf("Maximum profit is: %d\n", maxProfit);

    return 0;
}
