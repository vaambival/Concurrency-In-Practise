package ch_01;

public class SeqDemoExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int k = 0; k < 100_000; k++) {
            ThreadSafeCounter counter = new ThreadSafeCounter();
            for (int i = 0; i < 1000; i++) {
                counter.getAndIncrementX();
            }
            if (counter.getAndIncrementX() != 1000) {
                System.out.println("NO");
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
