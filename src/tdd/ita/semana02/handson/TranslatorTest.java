package tdd.ita.semana02.handson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslatorTest {

    @Test
    public void translatorWithoutWords() {
        MyTranslator translator = new MyTranslator();
        String result = translator.translate("");
        assertEquals("", result);
    }
}
