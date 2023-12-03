package tdd.ita.semana01.handson.pratice;

import java.util.List;

public class CamelCase {
    public static List<String> convertCamelCase(String input) {
        String result = input.substring(0,1).toLowerCase() + input.substring(1);
        return List.of(result);
    }
}
