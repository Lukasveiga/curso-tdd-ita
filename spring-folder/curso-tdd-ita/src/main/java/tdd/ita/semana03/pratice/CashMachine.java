package tdd.ita.semana03.pratice;

import org.springframework.stereotype.Repository;

public class CashMachine {

    private final UserRepository userRepository;

    public CashMachine(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String log(int accountId, String password) {
        var checkingAccount = userRepository.findAccountById(accountId)
                .orElseThrow(() -> new CheckingAccountNotFoundException("Checking Account with id: " + accountId + " was not found."));

        return "Authenticated User";
    }
}
