package ch_02;

public class StatefullFactorizer {

    private long count = 0;

    public long getCount() {
        return count;
    }

    public void factors(int n, Response answer) {
        count++;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                answer.setAnswer(i);
                return;
            }
        }
        answer.setAnswer(n);
    }
}
