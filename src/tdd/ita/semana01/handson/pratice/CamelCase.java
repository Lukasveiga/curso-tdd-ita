package tdd.ita.semana01.handson.pratice;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {
    public static List<String> convertCamelCase(String input) {
        var resultList = new ArrayList<String>();

        var splitedInput = input.trim().split("(?=[A-Z])|(?<=[a-z])(?=[A-Z])");

        for(String word : splitedInput) {
            word = word.trim();
            var result = word.substring(0,1).toLowerCase() + word.substring(1);
            resultList.add(result);
        }

        return resultList;
    }
}
