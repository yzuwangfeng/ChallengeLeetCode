package com.smlf.test;

import java.util.*;

public class Solution {

//    public List<List<Integer>> permute(int[] nums) {
//        // 首先是特判
//        int len = nums.length;
//        // 使用一个动态数组保存所有可能的全排列
//        List<List<Integer>> res = new ArrayList<>();
//
//        if (len == 0) {
//            return res;
//        }
//
//        boolean[] used = new boolean[len];
//        List<Integer> path = new ArrayList<>();
//
//        dfs(nums, len, 0, path, used, res);
//        return res;
//    }
//
//    private void dfs(int[] nums, int len, int depth,
//                     List<Integer> path, boolean[] used,
//                     List<List<Integer>> res) {
//        if (depth == len) {
//            List<Integer> subResult = new ArrayList<>();
//            subResult.addAll(path) ;
//            res.add(subResult);
//            return;
//        }
//
//        for (int i = 0; i < len; i++) {
//            if (!used[i]) {
//                path.add(nums[i]);
//                used[i] = true;
//
//                dfs(nums, len, depth + 1, path, used, res);
//                // 注意：这里是状态重置，是从深层结点回到浅层结点的过程，代码在形式上和递归之前是对称的
//                used[i] = false;
//                path.remove(path.size() - 1);
//            }
//        }
//    }

//    public int[] plusOne(int[] digits) {
//        StringBuilder sb = new StringBuilder("");
//        for(int i:digits) {
//            sb.append(i);
//        }
//        int num = Integer.valueOf(sb.toString());
//        num++;
//        String resultStr = num+"";
//        int length = sb.length()-resultStr.length();
//        StringBuilder sb1 = new StringBuilder("");
//        for(int i = 0;i<length;i++) {
//            sb1.append('0');
//        }
//        sb1.append(resultStr);
//        int[] nums = new int[digits.length];
//        for(int i = 0;i<digits.length;i++) {
//            nums[i] = (sb1.toString().charAt(i)-'0');
//        }
//        return nums;
//    }

//    public int lengthOfLongestSubstring(String s) {
//        int i = 0;
//        int currentStart = 0;
//        int maxLength = 0;
//        Set<Character> set = new HashSet<>();
//        while(i < s.length()) {
//            if(!set.contains(s.charAt(i))){
//                set.add(s.charAt(i));
//                i++;
//            }else{
//                if(maxLength<(i-currentStart)) {
//                    maxLength = i-currentStart;
//                }
//                currentStart++;
//                set.clear();
//                i=currentStart;
//            }
//        }
//        return maxLength;
//    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);

        combine(result, path, candidates, 0, target);

        return result;
    }

    private void combine(List<List<Integer>> result, List<Integer> path, int[] candidates, int begin, int target) {


        for(int i = begin; i<candidates.length; i++) {
            if(target-candidates[i]==0) {
                List<Integer> temp = new ArrayList<>(path);
                temp.add(candidates[i]);
                result.add(temp);
            }
            if(target-candidates[i]>0) {
                path.add(candidates[i]);
                combine(result, path, candidates, i,target-candidates[i]);
            }
        }
        if(path.size()>0) {
            path.remove(path.size()-1);
        }

    }

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        for(int i = 0; i<intervals.length; i++) {
            int[] current = intervals[i];

            if(res.isEmpty()) {
                res.add(current);
            } else{
              int[] last = res.get(res.size()-1);
              if (current[0] <= last[1]) {
                  // 有交集
                  last[1] = Math.max(last[1], current[1]);
                  res.remove(res.size()-1);
                  res.add(last);
              }else{
                  res.add(current);
              }
            }
        }
        return res.toArray(new int[0][]);
    }


    /**
     * 198 题  打家劫舍
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }


    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     *
     * 1， 1， 1
     * 0， 1， 0
     * 1， 1， 1
     *
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }


    public int countPrimes(int n) {
        // 1. 给0 - n之间的数加上标记
        byte[] nums = new byte[n];
        for (int i = 0; i < n; i++) {
            nums[i] = 1;
        }

        // 2. 对于非质数，进行标记清除
        for (int i = 2; i < n; i++) {
            // 如果当前数为质数
            if (nums[i] == 1) {
                // 将当前数作为基数，筛掉其倍数的数字
                for (int j = 2; i * j < n; j++) {
                    // 标记清除
                    nums[i * j] = 0;
                }
            }
        }

        //3. 遍历数组，统计质数(元素值==1)
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] == 1)
                count++;
        }
        return count;
    }

    /**
     * 236题 二叉树的最近公共祖先
      */
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    private TreeNode ans;

    public Solution() {
        this.ans = null;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    /**
     * 236题 二叉树的最近公共祖先结束
     */


    /**
     * 53 题 最大自序和  动态规划算法
     * @param nums
     * @return
     */
    int maxResult = 0;
    public int maxSubArray(int[] nums) {
        if(nums.length==0) {
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        findMax(nums, nums.length-1);

        return maxResult;
    }

    private int findMax(int[] nums, int i) {
        if(i==0) {
            maxResult = nums[i];
            return nums[i];
        }
        int subNum = findMax(nums,i-1);
        if((nums[i]+subNum)>nums[i]) {
            if(maxResult<(nums[i]+subNum)){
                maxResult = nums[i]+subNum;
            }
            return nums[i]+subNum;
        }else{
            if(maxResult<nums[i]) {
                maxResult = nums[i];
            }
            return nums[i];
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();

//        int a = 11;int b=12; int c = 11;
//
//        System.out.println(a^=(b^c));

//
//     236题 二叉树的最近公共祖先
//
//        TreeNode root = stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]");
//        int pValue = Integer.parseInt("5");
//        TreeNode p = new TreeNode(pValue);
//        int qValue = Integer.parseInt("1");
//        TreeNode q = new TreeNode(qValue);
//
//        TreeNode ret = solution.lowestCommonAncestor(root, p, q);
//
//        String out = treeNodeToString(ret);
//
//        System.out.print(out);
//      236题 二叉树的最近公共祖先结束


//        System.out.println(""+solution.rob(new int[]{ 1, 2, 3, 1 }));

//        char[][] grid = new char[][] {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
//        System.out.println(solution.numIslands(grid));
//        List<List<Integer>> lists = solution.combinationSum(new int[]{2,3, 5}, 8);
//        System.out.println(lists.size());
        // System.out.println(solution.lengthOfLongestSubstring("au"));
    }
}