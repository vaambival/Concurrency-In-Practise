package ch_02.not_atomic;

import java.math.BigInteger;

public class Response {

    private BigInteger[] answer;

    public Response(BigInteger[] bigIntegers) {
        this.answer = bigIntegers;
    }

    public Response() {

    }

    public void set(BigInteger[] answer) {
        this.answer = answer;
    }

    public BigInteger get() {
        return answer[0];
    }
}
