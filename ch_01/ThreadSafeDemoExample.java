package ch_01;

public class ThreadSafeDemoExample {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int k = 0; k < 100_000; k++) {
            ThreadSafeCounter counter = new ThreadSafeCounter();
            Thread first = new Thread(() -> {
                for (int i = 0; i < 500; i++) {
                    counter.getAndIncrementX();
                }
            });
            Thread second = new Thread(() -> {
                for (int i = 0; i < 500; i++) {
                    counter.getAndIncrementX();
                }
            });
            first.start();
            second.start();
            first.join();
            second.join();
            if (counter.getAndIncrementX() != 1000) {
                System.out.println("NO");
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
