package com.smlf.test;

import java.util.HashSet;
import java.util.Set;

public class UseSet {
    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
        s.add("123");
        s.add("123");
        System.out.println(s.size());
    }
}
