package tdd.ita.semana03.pratice;

import org.springframework.stereotype.Repository;

public class CashMachine {

    private final UserRepository userRepository;

    public CashMachine(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String log(int accountId, String password) {
        return "Authenticated User";
    }
}
