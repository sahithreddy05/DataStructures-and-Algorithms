import java.util.Arrays;

public class practice {
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

    // 516. Longest Palindromic Subsequence
    public static int lpss(String s, int i, int j, int[][] dp) {

        if (i >= j)
            return dp[i][j] = (i == j ? 1 : 0);

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = lpss(s, i + 1, j - 1, dp) + 2;

        else
            return dp[i][j] = Math.max(lpss(s, i + 1, j, dp), lpss(s, i, j - 1, dp));
    }

    public static int lpss_dp(String s, int I, int J, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++) {
                if (i >= j) {
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }

                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[I][J];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int ans = lpss(s, 0, n - 1, dp);

        return ans;
    }

    public int lcss(String str1, String str2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (str1.charAt(n - 1) == str2.charAt(m - 1))
            dp[n][m] = lcss(str1, str2, n - 1, m - 1, dp) + 1;
        else
            dp[n][m] = Math.max(lcss(str1, str2, n - 1, m, dp), lcss(str1, str2, n, m - 1, dp));

        return dp[n][m];
    }

    public static int lcss_DP(String str1, String str2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                if (str1.charAt(n - 1) == str2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = lcss(text1, text2, n, m, dp);
        return ans;
    }

}
