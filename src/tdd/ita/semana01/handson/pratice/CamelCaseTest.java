package tdd.ita.semana01.handson.pratice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void convertCamelCaseUpperCaseConcatLowerCaseString() {
        String input = "numeroCPFContribuinte";
        var expectedOutput = List.of("numero","CPF", "contribuinte");
        var output = CamelCase.convertCamelCase(input);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void convertCamelCaseWithNumberInTheMiddle() {
        String input = "recupera10Primeiros";
        var expectedOutput = List.of("recupera","10", "primeiros");
        var output = CamelCase.convertCamelCase(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void convertCamelCaseThrowsIfStartWithNumbers() {
        String input = "10Primeiros";
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> CamelCase.convertCamelCase(input));
        assertEquals( "Input cannot start with numbers",illegalArgumentException.getMessage());
    }
}
