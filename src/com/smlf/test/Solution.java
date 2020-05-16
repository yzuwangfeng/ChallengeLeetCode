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


    public static void main(String[] args) {

        Solution solution = new Solution();

        System.out.println(""+solution.rob(new int[]{ 1, 2, 3, 1 }));
//        List<List<Integer>> lists = solution.combinationSum(new int[]{2,3, 5}, 8);
//        System.out.println(lists.size());
        // System.out.println(solution.lengthOfLongestSubstring("au"));
    }
}
