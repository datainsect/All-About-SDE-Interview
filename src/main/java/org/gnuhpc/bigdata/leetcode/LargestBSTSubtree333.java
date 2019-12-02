package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeUtils;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class LargestBSTSubtree333 {

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (TreeUtils.isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE))
            return TreeUtils.sizeofTree(root);

        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
}
