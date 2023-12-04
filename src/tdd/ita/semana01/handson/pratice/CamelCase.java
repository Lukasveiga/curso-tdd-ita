package tdd.ita.semana01.handson.pratice;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {
    public static List<String> convertCamelCase(String input) {
        var resultList = new ArrayList<String>();

        String[] splitedInput;

        if(hasNumbers(input))
            splitedInput = input.trim().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        else
            splitedInput = input.trim().split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");

        for(String word : splitedInput) {
            var result = word.trim();

            if(!isAllLettersUpperCase(word)) {
                result = word.substring(0,1).toLowerCase() + word.substring(1);
            }

            resultList.add(result);
        }

        return resultList;
    }

    private static boolean isAllLettersUpperCase(String word) {
        for(String l : word.split("")) {
            if(!l.equals(l.toUpperCase())) return false;
        }
        return true;
    }

    private static boolean hasNumbers(String word) {
        var regex = ".*[0-9].*";
        return word.matches(regex);
    }
}