package com.smlf.test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Boy implements Delayed {

    private Integer birth;

    @Override
    public long getDelay(TimeUnit unit) {
        return this.birth;
    }

    @Override
    public int compareTo(Delayed o) {
        if(o instanceof  Boy) {
            return this.birth-((Boy) o).getBirth();
        }
        return 1;
    }

    public Integer getBirth() {
        return birth;
    }

    public void setBirth(Integer birth) {
        this.birth = birth;
    }
}
