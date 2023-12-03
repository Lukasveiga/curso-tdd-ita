package tdd.ita.semana01.handson.pratice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CamelCaseTest {

    @Test
    public void convertCamelCaseToStringList() {
        String input = "nome";
        var output = CamelCase.convertCamelCase(input);
        assertEquals(List.of(input), output);
    }
}
