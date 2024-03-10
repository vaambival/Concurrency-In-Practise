package ch_02;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class StatefullDemo {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        var lock = new CountDownLatch(10);
        var service = Executors.newFixedThreadPool(10);
        var generator = new Random();
//        var factorizer = new StatefullFactorizer();
        var factorizer = new ThreadSafeStatefullFactorizer();
        var answers = new Response[1000];
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            service.submit(() -> {
                answers[index] = new Response();
                factorizer.factors(generator.nextInt(100), answers[index]);
                lock.countDown();
            });
        }
        lock.await();
        service.close();
        for (var response : answers) {
            System.out.println(response.getAnswer());
        }
        System.out.println(factorizer.getCount());
        System.out.println(System.currentTimeMillis() - start);
    }
}
