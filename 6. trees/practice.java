import java.util.ArrayList;
import java.util.List;

public class practice {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);

        if (res)
            ans.add(root);
        return res;
    }

    public void kdown(TreeNode root, int k, TreeNode block, List<Integer> ans) {
        if (root == null || k < 0 || root == block)
            return;
        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kdown(root.left, k - 1, block, ans);
        kdown(root.right, k - 1, block, ans);

    }

    public int distanceK(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == null)
            return -1;

        if (root == target) {
            kdown(root, k, null, ans);
            return 1;
        }
        int ld = distanceK(root, k - ld, root.left, ans);
        if (ld != -1) {
            kdown(root, k - ld, root.left, ans);
            return ld + 1;
        }

        int rd = distanceK(root, target, k, ans);
        if (rd != -1) {
            kdown(root, k - ld, root.right, ans);
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath_(root, target.data, path);

        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kdown(path.get(i), k - i, block, ans);
            block = path.get(i);
        }

        return ans;
    }

}
