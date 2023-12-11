package tdd.ita.semana03.pratice;

import java.util.Optional;

public interface RemoteService {
    Optional<CheckingAccount> recoveryAccountInfo(int accountId);
}
