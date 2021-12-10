import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class practice {

    public int binarySearch(int[] arr, int si, int ei, int data) {

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public int firstIndex(int[] arr, int si, int ei, int data) {

        if (arr[0] == data)
            return 0;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                if (arr[mid - 1] == data)
                    ei = mid - 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }

    public int lastIndex(int[] arr, int si, int ei, int data) {
        int n = arr.length - 1;
        if (arr[n] == data)
            return arr.length - 1;
        while (si <= ei) {

            int mid = (si + ei) / 2;

            if (arr[mid] == data) {

                if (arr[mid + 1] == data)
                    si = mid + 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }

    public int[] searchRange(int[] arr, int data) {
        return new int[] { firstIndex(arr, 0, arr.length - 1, data), lastIndex(arr, 0, arr.length - 1, data) };
    }

    public int closestElement(int[] arr, int si, int ei, int data) {

        if (data < arr[si])
            return si;
        else if (data > arr[ei])
            return ei;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return data - arr[ei] < arr[si] - data ? ei : si;
    }

    public int perfectLocation(int[] arr, int si, int ei, int data) {

        while (si < ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid;
        }

        return ei;
    }

    public bool searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int si = 0, ei = n * m - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2, r = mid / m, c = mid % m;
            if (matrix[r][c] == target)
                return true;
            else if (matrix[r][c] < target)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return false;
    }

    public boolean searchMatrix_2(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length, si = n - 1, ei = 0;
        while (si >= 0 && ei < m) {
            int ele = matrix[si][ei];
            if (ele == target)
                return true;
            else if (ele < target)
                ei++;
            else
                si--;
        }
        return false;
    }

    public static long totalInversionCount(long[] arr, long[] sortedArray, long si, long mid, long ei) {
        int i = (int) si, j = (int) mid + 1, k = (int) si;
        long count = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else
                sortedArray[k++] = arr[j++];
            count += mid - i + 1;
        }

        while (i <= mid || j <= ei) {
            sortedArray[k++] = arr[i < mid ? i++ : j++];
        }

        while (si <= ei)
            arr[(int) si] = sortedArray[(int) si++];
        return count;

    }

    public static long inversionCount(long[] arr, long[] sortedArray, long si, long ei) {

        if (si >= ei)
            return 0;

        long mid = (si + ei) / 2;
        long count = 0;

        count += inversionCount(arr, sortedArray, si, mid);
        count += inversionCount(arr, sortedArray, mid + 1, ei);

        count += totalInversionCount(arr, sortedArray, si, mid, ei);
        return count;
    }

    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long sortedArray[] = new long[(int) N];
        return inversionCount(arr, sortedArray, 0, N - 1);
    }

    // 658
    List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        if (x <= arr[0])
            return Arrays.stream(arr, 0, k).boxed().collect(Collectors.toList());
        else if (x >= arr[n - 1])
            return Arrays.stream(arr, n - k, n).boxed().collect(Collectors.toList());

        int idx = perfectLocation(arr, 0, n - 1, x);
        int si = Math.max(0, idx - k);
        int ei = Math.min(n - 1, idx + k);

        while ((ei - si + 1) > k) {
            if (x - arr[si] > arr[ei] - x) {
                si++;
            } else
                ei--;
        }
        return Arrays.stream(arr, si, ei + 1).boxed().collect(Collectors.toList());
    }

    // 33
    public int search(int[] nums, int target) {
        int n = nums.length, si = 0, ei = n - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[si] <= nums[mid]) {
                if (nums[si] <= target && target < nums[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[ei])
                    si = mid + 1;
                else
                    ei = mid + 1;
            }
        }
        return -1;
    }

    // 81
    public boolean Search(int[] nums, int target) {
        int n = nums.length, si = 0, ei = n - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == target || nums[si] == target)
                return true;
            else if (nums[si] < nums[mid]) {
                if (nums[si] <= target && target < nums[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            } else if (nums[mid] < nums[ei]) {
                if (nums[mid] < target && target <= nums[ei])
                    si = mid + 1;
                else
                    ei = mid - 1;
            } else
                si++;
        }
        return false;
    }

    // 153
    int findMin(int[] arr) {
        int n = arr.length, si = 0, ei = n - 1;

        if (arr[si] < arr[ei])
            return arr[si];

        while (si < ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] < arr[ei])
                ei = mid;
            // else if(arr[si] <= arr[mid]) si = mid + 1;
            else
                si = mid + 1;
        }
        return arr[si];
    }

    // 154
    int FindMin(int[] arr) {

        int n = arr.length, si = 0, ei = n - 1;

        if (arr[si] < arr[ei])
            return arr[si];

        while (si < ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] < arr[ei])
                ei = mid;
            else if (arr[mid] > arr[ei])
                si = mid + 1;
            else
                ei--;
        }
        return arr[si];
    }

    // 1
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { i, map.get(target - nums[i]) };
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[] {};
    }
    // 167

    public List<List<Integer>> allPairs(int[] nums, int tar) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        int si = 0;
        int ei = nums.length - 1;

        while (si < ei) {
            int csum = nums[si] + nums[ei];

            if (csum == tar) {
                List<Integer> smallAns = new ArrayList<>();

                smallAns.add(nums[si]);
                smallAns.add(nums[ei]);

                and.add(smallAns);

                si++;
                ei--;

                while (si < ei && nums[si] == nums[si + 1])
                    ;
                si++;
                while (si < ei && nums[ei] == nums[ei + 1])
                    ;
                ei++;
            } else if (csum < tar) {
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    public List<List<Integer>> TwoSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == target) {
                ArrayList<Integer> smallAns = new ArrayList<>();
                smallAns.add(arr[si]);
                smallAns.add(arr[ei]);
                ans.add(smallAns);
                si++;
                ei--;
                while (si < ei && arr[si] == arr[si - 1])
                    si++;
                while (si < ei && arr[ei] == arr[ei + 1])
                    ei--;
            } else if (sum < target)
                si++;
            else
                ei--;
        }
        return ans;
    }

    public void prepareAns(List<List<Integer>> ans, List<List<Integer>> smallAns, int fixEle) {
        for (List<Integer> arr : smallAns) {
            List<Integer> ar = new ArrayList<>();
            ar.add(fixEle);
            for (int ele : arr)
                ar.add(ele);
            ans.add(ar);
        }
    }

    public List<List<Integer>> threeSum(int[] nums, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = si; i < ei;) {
            List<List<Integer>> smallAns = TwoSum(nums, target - nums[i], i + 1, ei);
            prepareAns(ans, smallAns, nums[i]);
            i++;
            while (i < ei && nums[i] == nums[i - 1])
                i++;
        }
        return ans;
    }

    // 15
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length - 1;
        Arrays.sort(nums);
        return threeSum(nums, 0, 0, n);
    }

    public List<List<Integer>> kSum(int[] arr, int target, int k, int si, int ei) {
        if (k == 2)
            return twoSum(arr, target, si, ei);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = si; i < ei;) {
            List<List<Integer>> smallAns = kSum(arr, target - arr[i], k - 1, i + 1, ei);
            prepareAns(ans, smallAns, arr[i]);
            i++;
            while (i < ei && arr[i] == arr[i - 1])
                i++;
        }
        return ans;

    }

    public List<List<Integer>> fourSum(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        List<List<Integer>> ans = kSum(arr, target, 4, 0, n - 1);

        return ans;
    }

    public int twoSumCount(int[] nums1, int[] nums2, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele: nums1)
            map.put(ele,map.getOrDefault(ele,0)+1);
        
        int count = 0;
        for(int ele:nums2)
            if(map.containsKey(target - ele))
                count += map.get(target - ele);

        return count;
    }

    // O(n^2)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int e1:nums1) 
            for(int e2:nums2)
                map.put(e1+e2, map.getOrDefault(e1 + e2,0) + 1);
        
        int count = 0,target = 0;
            for(int e3:nums3)
                for(int e4:nums4)
                    if(map.containsKey(target - e1 - e2 ))
                            count += map.get(target - e1 -e2);
        return count;
    }

    public int fourSumCount_2(int[] nums1,int[] nums2,int[] nums3,int[] nums4){

        int n = nums1.length, idx = 0;
        int[] A = new int[n * n];
        int[] B = new int[n * n];

        for(int e1:nums1)
            for(int e2:nums2)
                A[idx++] = e1 + e2;

        idx = 0;
        for(int e3:nums3)
            for(int e4:nums4)   
                B[idx++] = e3 + e4;
        
        return twoSumCount(A, B, 0);
    }

    // 658
    

}
