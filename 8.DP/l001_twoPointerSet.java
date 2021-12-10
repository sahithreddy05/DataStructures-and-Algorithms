import java.util.Arrays;

public class l001_twoPointerSet {
    // 1_Faith
    // 2_Recursive_Tree
    // 3_RecursiveCode->Memoization
    // 4_Observation
    // 5_Tabulation
    // 6_Optimization

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

    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        return dp[n] = ans;

    }

    public static int fibo_tabu(int n, int[] dp) {

        for (int i = 0; i < n; i++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;

        }
        return dp[n];
    }

    public static int fibo_opti(int n) {
        int a = 0, b = 1;
        for (int i = 2; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void fibo() {
        int n = 10;
        int[] dp = new int[n + 1];
        // System.out.println(fibo_memo(n, dp));
        // System.out.println(fibo_tabu(n, dp));
        System.out.println(fibo_opti(n));

        display(dp);
    }

    public static int mazepath_memo(int sr, int sc, int er, int ec, int[][] dp, int[][] dir) {
        if (er == sr && ec == sc) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;

        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += mazepath_memo(r, c, er, ec, dp, dir);
            }
        }

        return dp[sr][sc] = count;

    }

    public static int mazepath_tabu(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {

        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];
                    }
                }

                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    public static int mazepathjump_tabu(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {

        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    while (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];
                        r += d[0];
                        c += d[1];
                    }
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    // 62
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        return mazepath_tabu(0, 0, n - 1, m - 1, dp, dir);
    }

    // 63
    public static int mazepath_tabu(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir, int[][] obstacleGrid) {
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length && obstacleGrid[r][c] == 0) {
                        count += dp[r][c];
                    }
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        ;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        return mazepath_tabu(0, 0, n - 1, m - 1, dp, dir, obstacleGrid);
    }

    // 70
    // similar to fibonaci
    public int climbStairs(int n) {
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // 746
    // similar to fibonaci
    public int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        int[] dp = new int[N];
        for (int n = 0; n < N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int ans = Math.min(dp[n - 2], dp[n - 1]) + cost[n];
            dp[n] = ans;
        }

        return Math.min(dp[N - 2], dp[N - 1]);
    }

    // Board Path.========================================================
    public static int boardPath_memo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }

        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath_memo(sp + dice, ep, dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_tabu(int SP, int ep, int[] dp) {
        for (int sp = ep; sp >= 0; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += dp[sp + dice];// boardPath_memo(sp + dice, ep, dp);
            }

            dp[sp] = count;
        }

        return dp[SP];
    }

    public static int numDecodings(String s, int idx, int[] dp) {

        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        char ch = s.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;

        int count = numDecodings(s, idx + 1, dp);

        if (idx < s.length() - 1) {
            char ch1 = s.charAt(idx + 1);
            int num = (ch - '0') * 10 + (ch1 - '0');
            if (num <= 26)
                count += numDecodings(s, idx + 2, dp);
        }
        return dp[idx] = count;

    }

    public static int numDecodings_Dp(String s, int IDX, int[] dp) {

        for (int idx = s.length(); idx >= 0; idx--) {

            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch = s.charAt(idx);
            if (ch == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = dp[idx + 1];

            if (idx < s.length() - 1) {
                char ch1 = s.charAt(idx + 1);
                int num = (ch - '0') * 10 + (ch1 - '0');
                if (num <= 26)
                    count += dp[idx + 2];
            }

            dp[idx] = count;

        }
        return dp[IDX];
    }

    public static int numDecodings_opti(String s, int IDX, int[] dp) {
        int a = 1, b = 0;

        for (int idx = s.length(); idx >= 0; idx--) {

            char ch = s.charAt(idx);

            int sum = 0;

            if (ch != '0') {
                sum += a;

                if (idx < s.length() - 1) {
                    char ch1 = s.charAt(idx + 1);
                    int num = (ch - '0') * 10 + (ch1 - '0');
                    if (num <= 26)
                        sum += b;
                }
            }

            b = a;
            a = sum;

        }
        return a;
    }

    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        int ans = numDecodings(s, 0, dp);
        display(dp);
        return ans;
    }

    // 639
    int mod = (int) 1e9 + 7;

    public long numDecodings02_memo(String str, int idx, long[] dp) {
        int n = str.length();
        if (idx == n) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        char ch = str.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;

        long count = 0;
        if (ch == '*') {
            count = (count + 9 * numDecodings02_memo(str, idx + 1, dp)) % mod;
            if (idx < n - 1) {
                char ch1 = str.charAt(idx + 1);

                if (ch1 >= '0' && ch1 <= '6')

                    count = (count + 2 * numDecodings02_memo(str, idx + 2, dp)) % mod;

                else if (ch1 >= '7' && ch1 <= '9')

                    count = (count + 1 * numDecodings02_memo(str, idx + 2, dp)) % mod;

                else

                    count = (count + 15 * numDecodings02_memo(str, idx + 2, dp)) % mod;
            }
        } else {
            count = (count + 1 * numDecodings02_memo(str, idx + 1, dp)) % mod;
            if (idx < n - 1) {
                char ch1 = str.charAt(idx + 1);
                if (ch1 == '*' && ch == '1')
                    count = (count + 9 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                else if (ch1 == '*' && ch == '2')
                    count = (count + 6 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                else if (ch1 != '*') {
                    int num = (ch - '0') * 10 + (ch1 - '0');
                    if (num <= 26)
                        count = (count + 1 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                }
            }
        }

        return dp[idx] = count;

    }

    public static int goldMine(int[][] arr, int sr, int sc, int[][] dir, int[][] dp) {
        int n = arr.length, m = arr[0].length;
        if (sc == m - 1)
            return dp[sr][sc] = arr[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int maxGold = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r > 0 && c > 0 && r < n && c < m) {
                maxGold = Math.max(maxGold, goldMine(arr, r, c, dir, dp) + arr[sr][sc]);
            }
        }
        return dp[sr][sc] = maxGold;
    }

    // public static void goldMine_backEngg(int[][] dp,int[][] dir,int sr,int
    // sc,String asf)
    // {

    // }
    public static void goldMine() {
        int[][] arr = { { 10, 33, 13, 15 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
        int n = arr.length, m = arr[0].length;

        int[][] dp = new int[n][m];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int maxGold = 0;
        // int rIdx = 0;
        for (int r = 0; r < n; r++) {
            int ans = goldMine(arr, r, 0, dir, dp);
            if (ans > maxGold) {
                maxGold = ans;
                // rIdx = r;
            }
        }

        // goldMine_backEngg(dp, dir, rIdx, 0, "");

        System.out.println(maxGold);
    }

    public long countFriendsPairings(int n, long[] dp) {
        if (n == 0)
            return dp[n] = 1;

        if (dp[n] != -1)
            return dp[n];

        long single = countFriendsPairings(n - 1, dp);
        long pairUp = n - 2 >= 0 ? countFriendsPairings(n - 2, dp) * (n - 1) : 0;

        return dp[n] = (single + pairUp % mod) % mod;

    }

    public long countFriendsPairings(int n) {
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);

        return countFriendsPairings(n, dp);
    }

    public long countFriendsPairings_02(int n) {
        long a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            long sum = b + (a * (i - 1)) % mod;
            a = b;
            b = sum % mod;
        }
        return b;
    }

    public static int divideInKGroup(int n, int k, int[][] dp) {

        if (n == k || k == 1)
            return dp[n][k] = 1;

        if (dp[n][k] != 0)
            return dp[n][k];

        int selfGroup = divideInKGroup(n - 1, k - 1, dp);
        int partOfGroup = divideInKGroup(n - 1, k, dp) * k;

        return dp[n][k] = selfGroup + partOfGroup;
    }

    public static void divideInKGroup() {
        int n = 8;
        int k = 4;
        int[][] dp = new int[n + 1][k + 1];
        System.out.println(divideInKGroup(n, k, dp));

    }

    public static void mazePath() {
        int sr = 0, sc = 0, er = 3, ec = 3;
        int[][] dp = new int[er + 1][ec + 1];

        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        // System.out.println(mazepath_memo(sr, sc, er, ec, dp, dir));
        // System.out.println(mazepath_tabu(sr, sc, er, ec, dp, dir));
        System.out.println(mazepathjump_tabu(sr, sc, er, ec, dp, dir));
        display2D(dp);
    }

    public static void main(String[] args) {
        // fibo();
        // mazePath();
        divideInKGroup();
    }
}
