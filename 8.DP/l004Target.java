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

    public static void main(String[] args) {
        target();
    }
}
