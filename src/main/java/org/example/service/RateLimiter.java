package org.example.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class RateLimiter {
    private final int PERMITS_PER_SECOND;
    private Semaphore semaphore;
    Timer t;


    public static RateLimiter create(int permitsPerSecond) {
        return new RateLimiter(permitsPerSecond);
    }

    private RateLimiter(int permitsPerSecond) {
        PERMITS_PER_SECOND = permitsPerSecond;
        semaphore = new Semaphore(PERMITS_PER_SECOND);
        t = new Timer();
    }


    /**
     * If 'count' number of permits are available, claim them.
     * Else, wait.
     */
    public void acquire(int count) throws InterruptedException {
        semaphore.acquire(count);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                semaphore.release(count);
            }
        }, 1000);
    }

    /**
     * If 1 permit is available, claim it.
     * Else, wait.
     */
    public void acquire() throws InterruptedException {
        this.acquire(1);
    }

    /**
     * Permits are locked for 1 second after 10 permits have
     * been give out.
     * <p>
     * return TRUE if one second has not passed, ELSE false
     */
}
