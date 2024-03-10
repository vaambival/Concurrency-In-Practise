package ch_01;

public class UnsafeCounter {

    private int x;

    public int getAndIncrementX() {
        return x++;
    }
}