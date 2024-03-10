package ch_02;

import java.util.concurrent.atomic.AtomicInteger;

public class ExpensiveObject {
    private static AtomicInteger counter = new AtomicInteger();

    public ExpensiveObject() throws InterruptedException {
        counter.getAndIncrement();
        Thread.sleep(5_000);
    }

    public int getCount() {
        return counter.get();
    }
}
