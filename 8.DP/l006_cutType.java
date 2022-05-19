public class l006_cutType {
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
    // 312 burst ballons

    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];
        int lele = si == 0 ? 1 : nums[si - 1];
        int rele = ei == nums.length - 1 ? 1 : nums[ei + 1];

        int maxCoins = 0;
        for (int cut = si; cut <= ei; cut++) {
            int lcost = cut == si ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rcost = cut == ei ? 0 : maxCoins(nums, cut + 1, ei, dp);

            maxCoins = Math.max(maxCoins, lcost + lele * nums[cut] * rele + rcost);
        }

        return dp[si][ei] = maxCoins;

    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        return maxCoins(nums, 0, n - 1, dp);
    }

    // min and max
    public static class pairmm {
        int min = (int) 1e9;
        int max = 0;

        pairmm() {

        }

        pairmm(int val) {
            this.min = this.max = val;
        }
    }

    public static pairmm evaluateMinMax(pairmm leftRes, pairmm rightRes, char operator) {

        pairmm pair = new pairmm();
        if (operator == '+') {
            pair.min = leftRes.min + rightRes.min;
            pair.max = leftRes.max + rightRes.max;
        } else if (operator == '*') {
            pair.min = leftRes.min * rightRes.min;
            pair.max = leftRes.max * rightRes.max;
        }
        return pair;
    }

    public static pairmm minMax(String str, int si, int ei, pairmm[][] dp) {

        if (si == ei) {
            return dp[si][ei] = new pairmm((str.charAt(si) - '0'));
        }

        if (dp[si][ei] != null) {
            return dp[si][ei];
        }

        pairmm myRes = new pairmm();
        for (int cut = si + 1; cut < ei; cut += 2) {

            pairmm leftRes = minMax(str, si, cut - 1, dp);
            pairmm rightRes = minMax(str, cut + 1, ei, dp);
            pairmm pair = evaluateMinMax(leftRes, rightRes, str.charAt(cut));

            myRes.min = Math.min(myRes.min, pair.min);
            myRes.max = Math.max(myRes.max, pair.max);
        }

        return dp[si][ei] = myRes;
    }

    public static void minMax() {
        String str = "1+2*3+4*5";
        int n = str.length();
        pairmm[][] dp = new pairmm[n][n];

        pairmm res = minMax(str, 0, n - 1, dp);

        System.out.println("Min value: " + res.min);
        System.out.println("Max value: " + res.max);
    }

    // boolean parentization
    public static class pairBoolean {
        long totalTrue = 0;
        long totalFalse = 0;

        pairBoolean() {

        }

        pairBoolean(long totalTrue, long totalFalse) {
            this.totalTrue = totalTrue;
            this.totalFalse = totalFalse;
        }
    }

    public static void evaluateTrue(pairBoolean left, pairBoolean right, pairBoolean res, char operator) {

        long mod = 1003;
        long totalTF = (left.totalTrue + left.totalFalse) * (right.totalTrue + right.totalFalse) % mod;
        long totalTrue = 0, totalFalse = 0;

        if (operator == '|') {

            totalFalse = (left.totalFalse * right.totalFalse) % mod;
            totalTrue = (totalTF - totalFalse + mod) % mod;

        } else if (operator == '^') {

            totalTrue = (left.totalFalse * right.totalTrue) % mod + (left.totalTrue * right.totalFalse) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;

        } else if (operator == '&') {

            totalTrue = (left.totalTrue * right.totalTrue) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;

        }

        res.totalFalse = (res.totalFalse + totalFalse) % mod;
        res.totalTrue = (res.totalTrue + totalTrue) % mod;

    }

    public static pairBoolean countWays(String S, int si, int ei, pairBoolean[][] dp) {
        if (si == ei) {
            char ch = S.charAt(si);
            int t = ch == 'T' ? 1 : 0;
            int f = ch == 'F' ? 1 : 0;
            return dp[si][ei] = new pairBoolean(t, f);
        }

        if (dp[si][ei] != null) {
            return dp[si][ei];
        }
        pairBoolean res = new pairBoolean();
        for (int cut = si + 1; cut < ei; cut += 2) {

            pairBoolean lres = countWays(S, si, cut - 1, dp);

            pairBoolean rres = countWays(S, cut + 1, ei, dp);

            evaluateTrue(lres, rres, res, S.charAt(cut));

        }

        return dp[si][ei] = res;
    }

    static int countWays(int N, String S) {
        // code here
        pairBoolean[][] dp = new pairBoolean[N][N];
        return (int) countWays(S, 0, N - 1, dp).totalTrue;
    }


    public static void main(String[] args) {
        minMax();
    }
}
