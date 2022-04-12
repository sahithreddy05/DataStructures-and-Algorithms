import java.util.Arrays;

public class l004Target {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    public static int permutation(int[] arr, int tar, int[] dp) {
        if (tar == 0)
            return dp[tar] = 1;

        if (dp[tar] != -1)
            return dp[tar];

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += permutation(arr, tar - ele, dp);
        }

        return dp[tar] = count;
    }

    public static int permutation_dp(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int tar = 1; tar <= Tar; tar++) {
            int count = 0;
            for (int ele : arr) {
                if (tar - ele >= 0)
                    count += dp[tar - ele];
            }
            dp[tar] = count;
        }

        return dp[Tar];
    }

    public static int combination(int[] arr, int n, int tar, int[][] dp) {

        if (tar == 0)
            return dp[n][tar] = 1;

        if (dp[n][tar] != -1)
            return dp[n][tar];

        int count = 0;
        for (int i = n; i > 0; i--) {
            if (tar - arr[i - 1] >= 0)
                count += combination(arr, i, tar - arr[i - 1], dp);
        }

        return dp[n][tar] = count;
    }

    public static int combination_DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele];
            }
        }
        return dp[Tar];
    }

    public static void fill2D(int[][] dp) {
        for (int[] d : dp)
            Arrays.fill(d, -1);
    }

    public static void target() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int[][] dp = new int[arr.length + 1][tar + 1];
        // Arrays.fill(dp, -1);
        fill2D(dp);

        // System.out.println(permutation(arr, tar, dp));
        System.out.println(combination(arr, arr.length, tar, dp));
        display2D(dp);

        // display(dp);
    }

    // 377
    // 322
    public int coinChange(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        for (int tar = 1; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0)
                    dp[tar] = Math.min(dp[tar - ele] + 1, dp[tar]);
            }
        }
        return dp[Tar] != (int) 1e9 ? dp[Tar] : -1;
    }

    // -1 : not explored, 0 : false, 1 : true
    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {

        if (n == 0 || tar == 0) {
            return dp[n][tar] = tar == 0 ? 1 : 0;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - arr[n - 1] >= 0)
            res = res || targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1;

        res = res || targetSum(arr, n - 1, tar, dp) == 1;

        return res ? 1 : 0;
    }

    public static Boolean isSubsetSum(int N, int arr[], int sum) {

        int[][] dp = new int[N + 1][sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = targetSum(arr, N, sum, dp);
        return ans == 1;
    }

    // =======================================================================================

    public static int knapSack(int W, int wt[], int val[], int n, int[][] dp) {

        if (n == 0 || W == 0) {
            return dp[n][W];
        }

        if (dp[n][W] != -1)
            return dp[n][W];

        int maxAns = 0;
        if (W - wt[n - 1] >= 0)
            maxAns = Math.max(maxAns, knapSack(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1]);
        maxAns = Math.max(maxAns, knapSack(W, wt, val, n - 1, dp));

        return dp[n][W] = maxAns;
    }

    public static int knapSack(int W, int[] wt, int val[], int N) {
        int[][] dp = new int[N + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return knapSack(W, wt, val, N, dp);
    }
    
    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    // https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1

    public static void main(String[] args) {
        target();
    }
}
