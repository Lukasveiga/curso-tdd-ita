package tdd.ita.semana03.pratice.exceptions;

public class InsuficientFundsException extends RuntimeException {
    public InsuficientFundsException(String message) {
        super(message);
    }
}
