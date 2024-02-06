package tdd.ita.semana03.pratice.exceptions;

public class ExpiredCreditCardValidityException extends RuntimeException {
    public ExpiredCreditCardValidityException(String message) {
        super(message);
    }
}
