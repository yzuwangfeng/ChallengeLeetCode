package com.smlf.test;

public class MaxsubSequence {

    public static int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public static void main(String[] args) {

        int[] input = new int[]{4,8,5,1,9};
        int[] result= maxSubsequence(input, 3);

        for(int i:result) {
            System.out.println(i);
        }

    }
}
