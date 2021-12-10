public class l002problems {
    public static int equalset(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += equalset(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += equalset(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");

        return count;

    }

    public static void equalSet() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(equalset(arr, 1, arr[0], 0, arr[0] + " ", ""));
    }

    public static int permutation(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        char prev = '$';
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            if (prev != ch) {
                String ros = str.substring(0, i) + str.substring(i + 1);
                count += permutation(ros, ans + ch);
            }
            prev = ch;
        }
        return count;
    }

    public static void main(String[] args) {
        // equalSet();
        System.out.println(permutation("aaa", ""));
    }
}
