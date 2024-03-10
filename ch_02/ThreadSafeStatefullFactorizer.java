package ch_02;

import java.util.concurrent.atomic.AtomicLong;

public class ThreadSafeStatefullFactorizer {

    private AtomicLong count = new AtomicLong();

    public long getCount() {
        return count.get();
    }

    public void factors(int n, Response answer) {
        count.getAndIncrement();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                answer.setAnswer(i);
                return;
            }
        }
        answer.setAnswer(n);
    }
}
