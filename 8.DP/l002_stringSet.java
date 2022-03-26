import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class l002_stringSet {

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
            return dp[i][j] = (i == j) ? 1 : 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = lpss(s, i + 1, j - 1, dp) + 2;

        else
            return dp[i][j] = Math.max(lpss(s, i + 1, j - 1, dp), lpss(s, i, j, dp));
    }

    public static int lpss_DP(String s, int I, int J, int[][] dp) {

        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
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

    public static int lcss(String str1, String str2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

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

    // longest common substring
    public int lcsubstring_DP(String str1, String str2, int N, int M) {
        int[][] dp = new int[N + 1][M + 1];
        int maxLen = 0, ei = 0;
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                    if (dp[n][m] > maxLen) {
                        maxLen = dp[n][m];
                        ei = n - 1;
                    }
                }
            }
        }

        return maxLen;
    }

    // 583
    public int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = lcss(text1, text2, n, m, dp);
        return ans;
    }

    public int minDistance_1(String word1, String word2) {
        return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
    }

    // 115
    public int numDistinct(String s, String t, int n, int m, int[][] dp) {
        if (m == 0) {
            return dp[n][m] = 1;
        }

        if (n < m) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct(s, t, n - 1, m - 1, dp);
        int b = numDistinct(s, t, n - 1, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = a + b;
        else
            return dp[n][m] = b;
    }

    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }
                int a = dp[n - 1][m - 1]; // numDistinct(s, t, n - 1, m - 1, dp);
                int b = dp[n - 1][m]; // numDistinct(s, t, n - 1, m, dp);
                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = a + b;
                else
                    dp[n][m] = b;
            }
        }
        return dp[N][M];

    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = numDistinct(s, t, n, m, dp);
        return ans;
    }

    // 72
    public int minDistance(String s, String t, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m : n);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance(s, t, n, m - 1, dp);
        int delete = minDistance(s, t, n - 1, m, dp);
        int replace = minDistance(s, t, n - 1, m - 1, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    public int minDistance_02(String word1, String word2, int n, int m, int[] cost, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance_02(word1, word2, n, m - 1, cost, dp);
        int delete = minDistance_02(word1, word2, n - 1, m, cost, dp);
        int replace = minDistance_02(word1, word2, n - 1, m - 1, cost, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[2]), replace + cost[2]);
    }

    public int minDistance(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = minDistance(s, t, n, m, dp);

        return ans;
    }

    // 44
    public String removeStars(String p) {
        if (p.length() == 0)
            return p;

        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));

        int i = 1;
        while (i < p.length()) {
            while (i < p.length() && sb.charAt(sb.length() - 1) == '*' && p.charAt(i) == '*')
                i++;

            if (i < p.length())
                sb.append(p.charAt(i));
            i++;
        }
        return sb.toString();
    }

    public int isMatch(String s, String p, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?') {
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            res = res || isMatch(s, p, n - 1, m, dp) == 1;
            res = res || isMatch(s, p, n, m - 1, dp) == 1;
            return dp[n][m] = res ? 1 : 0;
        } else {
            return dp[n][m] = 0;
        }
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = isMatch(s, p, n, m, dp);

        return ans == 1;
    }

    // 1035
    public int maxUncrossedLines(int[] arr1, int[] arr2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (arr1[n - 1] == arr2[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1; // maxUncrossedLines(arr1,arr2,n-1,m-1,dp) + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }
        return dp[N][M];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = maxUncrossedLines(nums1, nums2, n, m, dp);
        return ans;
    }

    // 1458
    public int maximum(int... arr) {
        int max = arr[0];
        for (int ele : arr)
            max = Math.max(ele, max);

        return max;
    }

    public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e8;
        }

        if (dp[n][m] != -(int) 1e9)
            return dp[n][m];

        int val = nums1[n - 1] * nums2[m - 1];
        int acceptBothNumbers = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct(nums1, nums2, n, m - 1, dp);

        return dp[n][m] = maximum(val, acceptBothNumbers, a, b);
    }

    // public int maxDotProduct(int[] nums1, int[] nums2) {
    // int n = nums1.length, m = nums2.length;
    // int[][] dp = new int[n + 1][m + 1];
    // for (int i = 0; i <= n; i++)
    // dp[i][0] = Integer.MIN_VALUE;
    // for (int i = 0; i <= m; i++)
    // dp[0][i] = Integer.MIN_VALUE;

    // for (int i = 1; i <= n; i++) {
    // for (int j = 1; j <= m; j++) {
    // int curr = nums1[i - 1] * nums2[j - 1];
    // if (dp[i - 1][j - 1] == Integer.MIN_VALUE)
    // dp[i][j] = maximum(curr, dp[i][j - 1], dp[i - 1][j]);
    // else
    // dp[i][j] = maximum(curr, dp[i - 1][j - 1] + curr, dp[i][j - 1], dp[i -
    // 1][j]);
    // }
    // }
    // return dp[n][m];
    // }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e9);
        int ans = maxDotProduct(nums1, nums2, n, m, dp);
        return ans;
    }

    // 005
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int MaxLen = 0, si = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j] == true) {
                    if (j - i + 1 > MaxLen) {
                        MaxLen = j - i + 1;
                        si = i;
                    }
                }
            }
        }
        return s.substring(si, si + MaxLen);
    }

    // 132
    public int minCut(String s, int si, int ei, int[] dp, boolean[][] pdp) {
        if (pdp[si][ei])
            return 0;

        if (dp[si] != -1)
            return dp[si];

        int minAns = (int) 1e8;
        for (int cut = si; cut <= ei; cut++) {
            if (pdp[si][cut]) {
                minAns = Math.min(minAns, minCut(s, cut + 1, ei, dp, pdp) + 1);
            }
        }

        return dp[si] = minAns;
    }

    // faafaaaaabaageeg
    public int minCut(String s) {
        int n = s.length();
        boolean[][] pdp = new boolean[n][n];
        int count = 0, MaxLen = 0, si = 0;
        for (int gap = 0; gap < n; gap++)
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    pdp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    pdp[i][j] = true;
                else
                    pdp[i][j] = s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1];
            }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return minCut(s, 0, n - 1, dp, pdp);
    }

    // followUp Question : ai-bj-ck-dl-em-fn
    // HM : 1278. Palindrome Partitioning III

    // 139
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int len = 0, n = s.length();
        for (String ss : wordDict) {
            set.add(ss);
            len = Math.max(len, ss.length());
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (dp[i] == false)
                continue;

            for (int l = 1; l <= len && i + l <= n; l++) {
                String substr = s.substring(i, i + l);
                if (set.contains(substr)) {
                    dp[i + l] = true;
                }
            }
        }
        return dp[n];
    }

    
    
}
