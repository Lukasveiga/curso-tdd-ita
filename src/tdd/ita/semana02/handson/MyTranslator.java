package tdd.ita.semana02.handson;

import java.util.HashMap;
import java.util.Map;

public class MyTranslator {

    private final Map<String, String> wordsTranslate = new HashMap<>();

    public boolean isEmpty() {
        return wordsTranslate.isEmpty();
    }

    public void addWordTranslate(String word, String translate) {
        if(wordsTranslate.containsKey(word)) {
            wordsTranslate.merge(word, ", " + translate, String::concat);
            return;
        }

        wordsTranslate.put(word, translate);
    }

    public String translate(String word) {
        return wordsTranslate.get(word);
    }
}
