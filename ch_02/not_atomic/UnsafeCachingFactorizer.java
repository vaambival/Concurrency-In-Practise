package ch_02.not_atomic;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UnsafeCachingFactorizer {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    public void service(Request request, Response response) {
        BigInteger i = extract(request);
        if (i.equals(lastNumber.get())) {
            System.out.println("CACHE");
            encode(response, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encode(response, factors);
        }
    }

    private BigInteger[] factor(BigInteger i) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (i == BigInteger.TWO) {
            return new BigInteger[]{ BigInteger.ONE, BigInteger.TWO };
        } else {
            return new BigInteger[] { BigInteger.TWO, BigInteger.ONE };
        }
    }

    private BigInteger extract(Request request) {
        return request.get();
    }

    private void encode(Response response, BigInteger[] answer) {
        response.set(answer);
    }
}
