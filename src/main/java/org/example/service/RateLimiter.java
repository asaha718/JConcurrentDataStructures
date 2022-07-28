package org.example.service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Semaphore;

public class RateLimiter {
    private final int PERMITS_PER_SECOND;
    private int count = 0;

    private Instant startTime= Instant.now();

    public static RateLimiter create(int permitsPerSecond) {
        return new RateLimiter(permitsPerSecond);
    }

    private RateLimiter(int permitsPerSecond) {
        PERMITS_PER_SECOND = permitsPerSecond;
    }

    /**
     * If 'count' number of permits are available, claim them.
     * Else, wait.
     */
    public void acquire(int count) {
        // TODO
    }

    /**
     * If 1 permit is available, claim it.
     * Else, wait.
     */
    public void acquire() {
        //count is greater than permits given or
        while (getCount() > PERMITS_PER_SECOND || Duration.between(startTime, Instant.now()).toSeconds() < 1) {
        }

        setCount(getCount() + 1);
        System.out.println("Current count " + getCount());
    }

    public void release() {
        setCount(getCount() - 1);

    }

    public synchronized int getCount(){
        return count;
    }

    public synchronized void setCount(int count){
        this.count = count;
        if(count == PERMITS_PER_SECOND) {
            startTime = Instant.now();
        }
    }
}
