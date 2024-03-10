package ch_02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class LaziInitRaceDemo {

    public static void main(String[] args) throws InterruptedException {
        var service = Executors.newFixedThreadPool(5);
        var latch = new CountDownLatch(5);
        var init = new LazyInitRace();
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                try {
                    var obj = init.getInstance();
                    System.out.println(obj.getCount());
                    latch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        latch.await();
        service.shutdown();
        System.out.println(init.getInstance().getCount());
    }
}

class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() throws InterruptedException {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}
