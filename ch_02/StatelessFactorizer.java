package ch_02;

public class StatelessFactorizer {

    public void factors(int n, Response answer) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                answer.setAnswer(i);
                return;
            }
        }
        answer.setAnswer(n);
    }
}
