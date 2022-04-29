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

    public int maxCoins(int[] nums,int si,int ei,int[][] dp){
        if(dp[si][ei] != 0) return dp[si][ei];
        int lele = si == 0 ? 1 : nums[si - 1];
        int rele = ei == nums.length - 1 ? 1:nums[ei + 1];
            
        int maxCoins = 0;
        for(int cut = si;cut <= ei;cut++)
        {
            int lcost = cut == si ? 0 :  maxCoins(nums,si,cut - 1,dp);
            int rcost = cut == ei ? 0 :  maxCoins(nums,cut + 1,ei,dp);
            
            maxCoins = Math.max(maxCoins,lcost + lele * nums[cut] * rele + rcost);
        }
        
        return dp[si][ei] = maxCoins;
        
    }
    public int maxCoins(int[] nums) {
      int n = nums.length;
        int[][] dp = new int[n][n];
        
        return maxCoins(nums,0,n-1,dp);
    }

    // boolean parentization
    public static class pairBoolean{
        long totalTrue = 0;
        long totalFalse = 0;
        
        pairBoolean(){
            
        }
        pairBoolean(long totalTrue,long totalFalse){
            this.totalTrue = totalTrue;
            this.totalFalse = totalFalse;
        }
    }
    
    public static void evaluateTrue(pairBoolean left,pairBoolean right,pairBoolean res,char operator){
        
        long mod = 1003;
        long totalTF = (left.totalTrue + left.totalFalse) * (right.totalTrue + right.totalFalse) % mod;
        long totalTrue = 0, totalFalse = 0;
        
        if(operator == '|'){
            
         totalFalse = (left.totalFalse * right.totalFalse) % mod;
         totalTrue = (totalTF - totalFalse + mod) % mod;    
         
        }else if(operator == '^'){
            
         totalTrue = (left.totalFalse * right.totalTrue) % mod + (left.totalTrue * right.totalFalse) % mod;
         totalFalse = (totalTF - totalTrue + mod) % mod;
         
        }else if(operator == '&'){

        totalTrue = (left.totalTrue * right.totalTrue) % mod;
        totalFalse = (totalTF - totalTrue + mod) % mod; 
        
    }
        
        res.totalFalse = (res.totalFalse + totalFalse) % mod;
        res.totalTrue = (res.totalTrue + totalTrue) % mod;
        
    }
    
    
    public static pairBoolean countWays(String S,int si,int ei,pairBoolean[][] dp){
        if(si == ei){
            char ch = S.charAt(si);
            int t = ch == 'T' ? 1 : 0;
            int f = ch == 'F' ? 1 : 0;
            return dp[si][ei] = new pairBoolean(t,f);
        }
        
        if(dp[si][ei] != null){
            return dp[si][ei];
        }
        pairBoolean res = new pairBoolean();
        for(int cut = si+1;cut<ei;cut+=2){
            
        pairBoolean lres = countWays(S,si, cut - 1,dp);
        
        pairBoolean rres = countWays(S,cut + 1,ei,dp);
        
        evaluateTrue(lres,rres,res,S.charAt(cut));
        
        }
        
        return dp[si][ei] = res;
    } 
    static int countWays(int N, String S){
        // code here
        pairBoolean[][] dp = new pairBoolean[N][N];
        return (int) countWays(S,0,N-1,dp).totalTrue;
    }
}
