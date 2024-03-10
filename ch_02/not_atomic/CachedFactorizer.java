package ch_02.not_atomic;

import java.math.BigInteger;

public class CachedFactorizer {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHits() {
        return (double) cacheHits / (double) hits;
    }

    public void service(Request request, Response response) {
        BigInteger i = extract(request);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
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
