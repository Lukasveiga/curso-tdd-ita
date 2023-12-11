package tdd.ita.semana03.pratice;

import java.util.Optional;

public interface UserRepository {
    Optional<CheckingAccount> findAccountById(int accountId);
}
