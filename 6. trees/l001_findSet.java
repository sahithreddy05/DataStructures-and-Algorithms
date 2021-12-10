import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class l001_findSet {
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

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root) {
        return root == null ? (int) 1e9 : Math.min(root.val, Math.min(minimum(root.left), minimum(root.right)));
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

    public ArrayList<TreeNode> nodeToRootPath_(TreeNode root, int data) {
        if (root == null)

        {
            return new ArrayList<>();
        }

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = nodeToRootPath_(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = nodeToRootPath_(root.left, data);
        if (right.size() != 0) {
            right.add(root);
            return left;
        }

        return new ArrayList<>();

    }

    ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
        return nodeToRootPath_(root, data);
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> smallAns) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);
        rootToAllLeafPath(root.left, ans, smallAns);
        rootToAllLeafPath(root.right, ans, smallAns);
        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();

        rootToAllLeafPath(root, ans, smallAns);
        return ans;
    }

    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || root.left == null || root.right == null)
            return;

        if (root.left == null || root.right == null)
            ans.add(root.val);

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root, ans);
        return ans;
    }

    public static int countExactlyOneChild(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int leftSinglechildCount = countExactlyOneChild(node.left);
        int RightSinglechildCount = countExactlyOneChild(node.right);

        int ans = leftSinglechildCount + RightSinglechildCount;

        if (node.left == null || node.right == null)
            ans++;

        return ans;
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

        int ld = distanceK(root.left, target, k, ans);
        if (ld != -1) {
            kdown(root, k - ld, root.left, ans);
            return ld + 1;
        }

        int rd = distanceK(root.right, target, k, ans);
        if (rd != -1) {
            kdown(root, k - rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath_(root, target.val, path);

        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kdown(path.get(i), k - i, block, ans);
            block = path.get(i);
        }

        return ans;
    }

    public static void burningTreeNode(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());

        ans.get(time).add(root.val);

        burningTreeNode(root.left, time + 1, blockNode, ans);
        burningTreeNode(root.right, time + 1, blockNode, ans);
    }

    public static int burningTree(TreeNode root, int firenode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == firenode) {
            burningTreeNode(root, 0, null, ans);
            return 1;
        }

        int lt = burningTree(root.left, firenode, ans);
        if (lt != -1) {
            burningTreeNode(root, lt, root.left, ans);
            return lt + 1;
        }

        int rt = burningTree(root.right, firenode, ans);
        if (rt != -1) {
            burningTreeNode(root, rt, root.right, ans);
            return rt + 1;
        }

        return -1;
    }

    public static void burningTree(TreeNode root, int data) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, data, ans);
    }

    // Node with water and fire.
    public static void burningTreeNodeWithWater(TreeNode root, int time, TreeNode blockNode, HashSet<Integer> waterSet,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode || waterSet.contains(root.val))
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());

        ans.get(time).add(root.val);

        burningTreeNodeWithWater(root.left, time + 1, blockNode, waterSet, ans);
        burningTreeNodeWithWater(root.right, time + 1, blockNode, waterSet, ans);
    }

    public static int burningTreeWithWater(TreeNode root, int firenode, HashSet<Integer> waterSet,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == firenode) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, 0, null, waterSet, ans);
                return 1;
            }
            return -2; // firenode is present it have water.
        }

        int lt = burningTreeWithWater(root.left, firenode, waterSet, ans);
        if (lt > 0) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, lt, root.left, waterSet, ans);
                return lt + 1;
            }
            return -2;
        }

        if (lt == -2)
            return -2;

        int rt = burningTreeWithWater(root.right, firenode, waterSet, ans);
        if (rt > 0) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, rt, root.right, waterSet, ans);
                return rt + 1;
            }

            return -2;
        }

        if (rt == -2)
            return -2;

        return -1;
    }

    public static void burningTreeWithWater(TreeNode root, int data) {
        HashSet<Integer> waterSet = new HashSet<>(); // unordered_set<int> map;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        burningTreeWithWater(root, data, waterSet, ans);
        System.out.println(ans);
    }

    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        ArrayList<TreeNode> list2 = new ArrayList<>();
        nodeToRootPath_(root, p.val, list1);
        nodeToRootPath_(root, q.val, list2);

        int i = list1.size() - 1, j = list2.size() - 1;
        TreeNode LCA = null;

        while (i >= 0 && j >= 0) {
            if (list1.get(i).val != list2.get(j).val)
                break;

            LCA = list1.get(i);

            i--;
            j--;
        }

        return LCA;
    }
}