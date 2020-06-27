package com.smlf.test;

import java.util.concurrent.DelayQueue;

public class Delay {

    public static void main(String[] args) throws Exception {
        DelayQueue<Boy> boyDelayQueue = new DelayQueue<>();
        for (int i = 0; i < 100; i += 10) {
            Boy boy = new Boy();
            boy.setBirth(i);
            boyDelayQueue.add(boy);
        }
        Boy b = boyDelayQueue.take();
        System.out.println(b.getBirth());
    }
}
