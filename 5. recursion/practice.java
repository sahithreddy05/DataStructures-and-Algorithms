import java.util.ArrayList;
public class practice {

    // top to bottom
    public static  ArrayList<String> mazepath_HVD(int sr,int sc,int er,int ec){
        
        if(sr == er && sc == ec){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myans = new ArrayList<>();
        if(sr+1 <= er){
         ArrayList<String> Vertical = mazepath_HVD(sr+1,sc,er,ec);
         for(String s: Vertical){
             myans.add("V"+s);
         }
        }

        if(sc+1 <= ec && sr+1 <=er){
            ArrayList<String> Diagonal = mazepath_HVD(sr+1, sc+1, er, ec);
            for(String s: Diagonal){
                myans.add("D"+ s);
            }
        }

        if(sc+1 <= ec){
            ArrayList<String> Horizantal = mazepath_HVD(sr, sc+1, er, ec);
            for(String s: Horizantal)
            {
                myans.add("H"+s);
            }
        }

        return myans;
    }

    public static ArrayList<String> mazepath_HVD_multi(int sr,int sc,int er, int ec)
    {
        if(sr == er && sc == ec){
            ArrayList<String> base =  new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myans = new ArrayList<>();
        // for(int jump = 1;sr + jump <= er;jump++)
        // {
        //     ArrayList<String> Vertical = mazepath_HVD_multi(sr+jump, sc, er, ec);
        //     for(String s: Vertical)
        //     myans.add("V" + jump + s);
        // }
        for (int jump = 1; sr + jump <= er; jump++) {
            ArrayList<String> Vertical = mazepath_HVD_multi(sr + jump, sc, er, ec);
            for (String s : Vertical) {
                myans.add("V" + jump + s);
            }
        }


        for(int jump = 1;sr + jump <= er && sc + jump <=ec;jump++)
        {
            ArrayList<String> Diagonal = mazepath_HVD_multi(sr+jump, sc+jump, er, ec);
            for(String s: Diagonal)
            myans.add("D" + jump + s);
        }

        for(int jump=1;sc+jump <= ec;jump++)
        {
            ArrayList<String> Horizantal = mazepath_HVD_multi(sr, sc+jump, er, ec);
            for(String s:Horizantal)
            myans.add("H"+ jump + s);
        }
        return myans;
    }

    public static int mazePath_multi(int sr,int sc,int er, int ec,ArrayList<String> ans, String psf){
        if(sr == er && sc == ec)
        {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for(int jump = 1; sr + jump <= er;jump++)
        count += mazePath_multi(sr+1, sc, er, ec, ans, psf + "V" + jump);
        
        for(int jump=1;sr + jump <= er && sc + jump <= ec;jump++)
        count += mazePath_multi(sr+1, sc + 1, er, ec, ans, psf + "D" + jump);
        
        for(int jump = 1; sc+ jump <= ec;jump++)
        count += mazePath_multi(sr, sc+1, er, ec, ans, psf + "H" + jump);

        return count;
    }

    public static int mazePath_HVD_2(int sr,int sc,int er,int ec,int[][] dir,String[] dirS,ArrayList<String> ans,
    String psf){
        if(sr == er && sc == ec){
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for(int d=0;d<dir.length;d++)
        {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];


            if(x >= 0 && y>= 0 && x <= er && y<= ec)
            count += mazePath_HVD_2(x, y, er, ec, dir, dirS, ans, psf + dirS[d]);
        }

        return count;
    }

    public static int floodfill(int sr,int sc,boolean[][] vis,int[][] dir,String[] dirS,ArrayList<String> ans,String psf)
    {
        int n = vis.length, m =  vis[0].length;

        if(sr == n -1 && sc == m - 1)
        {ans.add(psf);
        return 1;
        }

        int count = 0;
        vis[sr][sc] = true;


        for(int d = 0;d< dir.length;d++)
        {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r >= 0 && c >=0 && r < n && c < m && !vis[r][c])
            count += floodfill(r, c, vis, dir, dirS, ans, psf+dirS[d]);
        }
        vis[sr][sc] = false;
        return count;
    }


    public static int floodfill_multi(int sr,int sc,boolean[][] vis,
    int[][] dir,String[] dirS,ArrayList<String> ans, String psf)
    {
        int n = vis.length, m = vis[0].length;
        
        if(sr == n - 1 && sc == m - 1)
        {
            ans.add(psf);
            return 1;
        }
        
        int count  = 0;
        vis[sr][sc] = true;
        
        for(int d=0;d< dir.length;d++)
        for(int rad = 1; rad <= Math.max(n,m); rad++)
        {
            int r = sr + rad * dir[d][0];
            int c = sc + rad * dir[d][1];
            
            if(r>= 0 && c>= 0 && r < n && c < m)
            {
                if(!vis[r][c])
                count += floodfill(r, c, vis, dir, dirS, ans, psf+ dirS[d] + rad); 
            }
            else 
            break;
        }
        
        vis[sr][sc] = false;
        
        return count;
    }
    public static void mazepath()
    {
        int sr=0,sc=0,er=2,ec=2;
        ArrayList<String> ans =  new ArrayList<>();
        
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 }, };
        String[] dirS = { "V", "H", "D", "E" };
        
        //    System.out.println(mazePath_multi(sr,sc,er,ec,ans,""));
        
        System.out.println(mazePath_HVD_2(sr, sc, er, ec, dir, dirS, ans, ""));
        System.out.println(ans);
    }
    public static void floodfill(){
        int sr = 0, sc = 0, n = 3, m =3;
        boolean[][] vis =  new boolean[n][m];
        
        int[][] dir = {{1,0},{0,1},{1,1}};
        String[] dirS = {"V","H","D"};
        
        ArrayList<String> ans =  new ArrayList<>();
        
        System.out.println(floodfill_multi(sr, sc, vis, dir, dirS, ans, ""));
        System.out.println(ans);
        
    }
    // public static ArrayList<String> findPath(int[][] m,int n)
    // {
    //     int[][] dir = {{1,0},{0,-1},{0,1},{-1,0}};
    //     String[] dirS = {"D","L","R","U"};
        
    //     ArrayList<String> res = new ArrayList<String>();
    //     if(m[0][0] == 0 || m[n-1][n-1] == 0)
    //     return res;
        
    //     int count  =  floodfill(0,0,m,"",res,dir,dirS);
    //     return res;
    // }

    void display(int[] arr){
        for(int ele: arr)
            System.out.print(ele + " ");
            System.out.println();
    }
    void display2D(int[][] arr)
    {
        for(int[] a: arr)
        display(a);

        System.out.println();
    }

    public int floodfil(int sr,int sc,int[][] jumpMAt,int[][] dir,int[][] ans)
    {
        int n = jumpMAt.length, m = jumpMAt[0].length;
        if(sr == n - 1 && sc == m - 1)
        {
            ans[sr][sc] = 1;
            display2D(ans);
            ans[sr][sc] = 0;
            return 1;
        }
        
        int jump = jumpMAt[sr][sc];
        jumpMAt[sr][sc] = 0; //block
        ans[sr][sc] = 1; // psf
        
        int count = 0;
        for(int d = 0;d < dir.length;d++)
        {
            for(int rad = 1;rad <= jump;rad++)
            {
                int r = sr + rad * dir[d][0];
                int c =  sc + rad * dir[d][1];

                if(r >= 0 && c >= 0 && r < n && c < m){
                    if(jumpMAt[r][c] != 0)
                    count += floodfil(r, c, jumpMAt, dir, ans);
                }
                else break;
            }
        }

        jumpMAt[sr][sc] = jump; //unblock
        ans[sr][sc] = 0;    //psf
        return count;
    }

    public static class pair {
        String psf = "";
        int  len = 0;

        pair(String psf, int len){
            this.len = len;
            this.psf = psf;
        }
    }

    public static pair longestPath(int sr,int sc,boolean[][] vis,
    int[][] dir,String[] dirS)
    {
        int n = vis.length, m = vis[0].length;
        if(sr == n - 1 && sc == m - 1)
        {
            return new pair("",0);
        }

        vis[sr][sc] = true; // blocked
        pair myans =  new pair("",-1);
        for(int d =0;d< dir.length;d++)
        {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && c>= 0 && r < n && c < m)
            {
                if(!vis[r][c])
                {
                    pair recAns = longestPath(r, c, vis, dir, dirS);
                    if(recAns.len != -1 && recAns.len + 1> myans.len)
                    {
                        myans.len =  recAns.len + 1;
                        myans.psf = dirS[d] + recAns.psf;
                    }

                }
            }
        }

        vis[sr][sc] = false;
        return myans;
    }
    public static void main(String[] args)
    {
        // int sr = 0, sc = 0, er = 2, ec = 2;
        // System.out.println(mazepath_HVD(sr,sc,er,ec));
        
        // int sr = 0, sc = 0, er = 2, ec = 2;
        // System.out.println(mazepath_HVD_multi(sr,sc,er,ec));
        
        // mazepath();
        // floodfill();
        // System.out.println(mazePath_HVD_2(sr, sc, er, ec, dir, dirS, ans, ""));
    }
}
