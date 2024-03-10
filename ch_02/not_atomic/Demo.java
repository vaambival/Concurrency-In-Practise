package ch_02.not_atomic;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        var lock = new CountDownLatch(5);
        var service = Executors.newFixedThreadPool(5);
//        var factorizer = new UnsafeCachingFactorizer();
//        var factorizer = new SynchronizedCachingFactorizer();
        var factorizer = new CachedFactorizer();
        var requests = new Request[10];
        var expected = new Response[10];
        var even = new BigInteger[] { BigInteger.ONE, BigInteger.TWO };
        var odd = new BigInteger[] { BigInteger.TWO, BigInteger.ONE };
        for (int i = 0; i < 10; i++) {
            if ((i + 1) % 2 == 0) {
                requests[i] = new Request(BigInteger.valueOf(2));
                expected[i] = new Response(even);
            } else {
                requests[i] = new Request(BigInteger.valueOf(1));
                expected[i] = new Response(odd);
            }
        }
        for (int i = 0; i < 10; i++) {
            final int index = i;
            service.submit(() -> {
                var response = new Response();
                factorizer.service(requests[index], response);
                if (!response.get().equals(expected[index].get())) {
                    System.out.println("FAIL!");
                }
                lock.countDown();
            });
        }
        lock.await();
        service.close();
        System.out.println(factorizer.getCacheHits());
        System.out.println(System.currentTimeMillis() - start);
    }
}
