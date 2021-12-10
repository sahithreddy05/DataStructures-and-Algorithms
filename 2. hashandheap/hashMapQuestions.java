import java.util.Scanner;
import java.lang.*;
import java.io.*;
public class hashMapQuestions {
      // 128
      public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int ele : nums)
            map.add(ele);

        int len = 0;
        for (int ele : nums) {
            if (!map.contains(ele))
                continue;

            int prev = ele - 1, next = ele + 1;
            map.remove(ele);
            while (map.contains(prev))
                map.remove(prev--);
            while (map.contains(next))
                map.remove(next++);

            len = Math.max(len, next - prev - 1);
        }

        return len;

    }

    // 781

    // 1218


    // https://codeforces.com/contest/1520/problem/D  same difference
	public void samedifference ()
	{
		Scanner sc = new Scanner(System.in);
		long t = sc.nextInt();
		while(t-- > 0)
		{
			long n = sc.nextInt();
			HashMap<Long,Long> map =  new HashMap<>();
			long maxi = 0;
			for(long i=0;i<n;i++)
			{
				long x = sc.nextInt();
				if(map.containsKey(x - i))
				{
					maxi += map.get(x - i);
					// map.put(x - i, map.get(x - i) + (long)1);
				} 
				map.put(x - i, map.getOrDefault(x - i, (long)0) + (long)1);

			}
			System.out.println(maxi);
			
		}
		
	}

}
