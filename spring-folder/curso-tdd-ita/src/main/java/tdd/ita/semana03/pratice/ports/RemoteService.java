package tdd.ita.semana03.pratice.ports;

import tdd.ita.semana03.pratice.entity.CheckingAccount;

import java.util.Optional;

public interface RemoteService {
    Optional<CheckingAccount> recoveryAccountInfo(int accountId);
}
