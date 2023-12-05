package tdd.ita.semana02.handson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TranslatorTest {

    @Test
    public void translatorWithoutTranslation() {
        MyTranslator translator = new MyTranslator();
        assertTrue(translator.isEmpty());
    }

    @Test
    public void translatorWithOneTranslation() {
        MyTranslator translator = new MyTranslator();
        translator.addWordTranslate("bom", "good");
        assertFalse(translator.isEmpty());
        assertEquals("good", translator.translate("bom"));
    }

    @Test
    public void translatorWithTwoTranslations() {
        MyTranslator translator = new MyTranslator();
        translator.addWordTranslate("bom", "good");
        assertFalse(translator.isEmpty());
        assertEquals("good", translator.translate("bom"));
    }
}
