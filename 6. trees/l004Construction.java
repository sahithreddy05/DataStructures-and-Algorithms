import java.util.ArrayList;
import java.util.LinkedList;

public class l004Construction {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode constructFromInOrder(int[] inOrder, int si, int ei) {
        if (si > ei)
            return null;
        int mid = (si / ei) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);

        root.left = constructFromInOrder(inOrder, si, mid - 1);
        root.right = constructFromInOrder(inOrder, mid + 1, ei);

        return root;
    }

    public static TreeNode constructFromInOrder(int[] inOrder) {
        return constructFromInOrder(inOrder, 0, inOrder.length - 1);
    }

    public TreeNode bstFromPreorder(int[] preorder, int lr, int rr, int[] idx) {
        int i = idx[0];

        if (i >= preorder.length || preorder[i] < lr || preorder[i] > rr)
            return null;

        TreeNode root = new TreeNode(preorder[i]);
        idx[0]++;

        root.left = bstFromPreorder(preorder, lr, root.val, idx);
        root.right = bstFromPreorder(preorder, root.val, rr, idx);

        return root;
    }

    public class CodecBinaryTree {
        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("# ");
                return;
            }
            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);

            return sb.toString();

        }

        int idx = 0;

        public TreeNode deserialize(String[] arr) {
            if (idx >= arr.length || arr[idx].equals("#")) {
                idx++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(arr[idx++]));
            root.left = deserialize(arr);
            root.right = deserialize(arr);
            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0)
                return null;
            String[] arr = data.split(" ");
            return deserialize(arr);
        }
    }

    public class CodecBinaryTree_02 {

        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            LinkedList<TreeNode> que = new LinkedList<>();
            que.addLast(root);

            while (que.size() != 0) {
                TreeNode rnode = que.removeFirst();
                sb.append((rnode != null ? rnode.val : "#") + " ");

                if (rnode == null)
                    continue;

                que.addLast(rnode.left);
                que.addLast(rnode.right);
            }

            return sb.toString();
        }

        int idx = 0;

        public TreeNode deserialize(String data) {
            if (data.length() == 0)
                return null;

            String[] arr = data.split(" ");
            LinkedList<TreeNode> que = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
            que.addLast(root);

            int idx = 1;
            while (que.size() != 0) {
                TreeNode rnode = que.removeFirst();

                if (!arr[idx].equals("#")) {
                    TreeNode leftChild = new TreeNode(Integer.parseInt(arr[idx]));
                    rnode.left = leftChild;
                    que.addLast(leftChild);
                }
                idx++;

                if (!arr[idx].equals("#")) {
                    TreeNode rightChild = new TreeNode(Integer.parseInt(arr[idx]));
                    rnode.right = rightChild;
                    que.addLast(rightChild);
                }
                idx++;
            }
            return root;
        }
    }

    // 110
    // public class BSTPair {
    // boolean isBal = true;
    // int h = -1;
    // }

    // public BSTPair isBalanced_(TreeNode root) {
    // if (root == null)
    // return new BSTPair();

    // BSTPair lp = isBalanced_(root.left);
    // BSTPair rp = isBalanced_(root.right);

    // BSTPair mypair = new BSTPair();
    // mypair.isBal = lp.isBal && rp.isBal;
    // if (mypair.isBal && Math.abs(lp.h - rp.h) < 2) {
    // mypair.h = Math.max(lp.h, rp.h) + 1;
    // } else {
    // mypair.isBal = false;
    // }

    // return mypair;
    // }

    // public boolean isBalanced(TreeNode root) {
    // return isBalanced_(root).isBal;
    // }

    public class BSTPair {
        boolean isBal = true;
        int h = -1;

        boolean isBST = true;
        int min = (int) 1e9;
        int max = -(int) 1e9;

        int size = 0;
        TreeNode largestRoot = null;
    }

    BSTPair largestBST_(TreeNode root) {
        if (root == null)
            return new BSTPair();

        BSTPair lp = largestBST_(root.left);
        BSTPair rp = largestBST_(root.right);

        BSTPair myPair = new BSTPair();
        myPair.isBST = false;
        if (lp.isBST && rp.isBST && lp.max < root.val && root.val < rp.min) {
            myPair.isBST = true;
            myPair.min = Math.min(lp.min, root.val);
            myPair.max = Math.max(lp.max, root.val);
            myPair.size = lp.size + rp.size + 1;
            myPair.largestRoot = root;
        } else {
            if (lp.size > rp.size) {
                myPair.size = lp.size;
                myPair.largestRoot = lp.largestRoot;
            } else {
                myPair.size = rp.size;
                myPair.largestRoot = rp.largestRoot;
            }
        }

        return myPair;

    }

    boolean largestBST(TreeNode root) {
        return largestBST_(root).isBal;
    }

}
