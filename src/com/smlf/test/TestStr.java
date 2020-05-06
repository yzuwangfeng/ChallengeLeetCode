package com.smlf.test;

public class TestStr {
    public static void main(String[] args) {
        System.out.println(""+strStr("hello","ll"));
    }

    public static int strStr(String haystack, String needle) {

        if (needle.equals("")) {
            return 0;
        }
        if(haystack.equals("")) {
            return -1;
        }

        int result = 0;
        int j = 0;
        for(int i = result; i<haystack.length(); i++) {
            if(haystack.charAt(i)==needle.charAt(j)){
                if(j==needle.length()-1){
                    return result;
                }
                j++;
            }else{
                result ++;
                i=result-1;
                j=0;
            }
        }

        return -1;

    }
}
