package tdd.ita.semana01.handson.pratice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CamelCaseTest {

    @Test
    public void convertCamelCaseSimpleString() {
        var input = "nome";
        var expectedOutput = List.of("nome");
        var output = CamelCase.convertCamelCase(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void convertCamelCaseDoubleString() {
        String input = "nomeComposto";
        var expectedOutput = List.of("nome", "composto");
        var output = CamelCase.convertCamelCase(input);
        assertEquals(expectedOutput, output);
        assertEquals(2, output.size());
    }

    @Test
    public void convertCamelCaseUpperCaseString() {
        String input = "CPF";
        var expectedOutput = List.of("CPF");
        var output = CamelCase.convertCamelCase(input);
        assertEquals(expectedOutput, output);
    }
}
