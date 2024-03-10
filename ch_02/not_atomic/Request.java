package ch_02.not_atomic;

import java.math.BigInteger;

public class Request {

    private final BigInteger value;

    public Request(BigInteger value) {
        this.value = value;
    }

    public BigInteger get() {
        return value;
    }
}
