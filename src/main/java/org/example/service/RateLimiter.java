package org.example.service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Semaphore;

public class RateLimiter {
    private final int PERMITS_PER_SECOND;
    private int count = 1;

    private Instant startTime = Instant.now();

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
        while (permitsAreLocked()) {
        }

        setCount(getCount() + 1);
//        System.out.println("Acquire count " + getCount());
    }
    /**
     * Permits are locked for 1 second after 10 permits have
     * been give out.
     *
     * return TRUE if one second has not passed, ELSE false
     */
    private boolean permitsAreLocked() {
        return Duration.between(startTime, Instant.now()).toSeconds() < 1;
    }

    private synchronized int getCount() {
        return count;
    }

    private synchronized void setCount(int count) {
        this.count = count > 10 ? 1 : count +1;
        if (count == PERMITS_PER_SECOND) {
            startTime = Instant.now();
        }
    }
}
