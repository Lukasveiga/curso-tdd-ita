package tdd.ita.semana03.pratice.ports;

import tdd.ita.semana03.pratice.entity.CreditCard;
import tdd.ita.semana03.pratice.entity.Envelope;

public interface Hardware {

    String catchAccountIdFromCreditCard(CreditCard creditCard);
    void withdrawFromCreditCard(CreditCard creditCard);
    void readEnvelope(Envelope envelope);

}
