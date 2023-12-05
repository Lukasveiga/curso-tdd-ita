package tdd.ita.semana02.handson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TranslatorTest {

    private MyTranslator translator;

    @BeforeEach
    public void createMyTranslatorInstance() {
        translator = new MyTranslator();
    }

    @Test
    public void translatorWithoutTranslation() {
        assertTrue(translator.isEmpty());
    }

    @Test
    public void translatorWithOneTranslation() {
        translator.addWordTranslate("bom", "good");
        assertFalse(translator.isEmpty());
        assertEquals("good", translator.translate("bom"));
    }

    @Test
    public void translatorWithTwoTranslations() {
        translator.addWordTranslate("bom", "good");
        translator.addWordTranslate("mau", "bad");
        assertFalse(translator.isEmpty());
        assertEquals("good", translator.translate("bom"));
        assertEquals("bad", translator.translate("mau"));
    }

    @Test
    public void translatorWithTwoTranslationsSameWord() {
        translator.addWordTranslate("bom", "good");
        translator.addWordTranslate("bom", "nice");
        assertFalse(translator.isEmpty());
        assertEquals("good, nice", translator.translate("bom"));
    }
}
