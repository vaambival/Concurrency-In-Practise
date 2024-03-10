package ch_01;

public class ThreadSafeCounter {

    private int x;

    public synchronized int getAndIncrementX() {
        return x++;
    }
}