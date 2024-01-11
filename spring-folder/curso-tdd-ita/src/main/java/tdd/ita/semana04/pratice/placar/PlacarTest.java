package tdd.ita.semana04.pratice.placar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.ita.semana04.pratice.armazenamento.Armazenamento;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.excecoes.UsuarioNaoEncontradoException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlacarTest {

    @Mock
    private Armazenamento armazenamento;

    @InjectMocks
    private Placar placar;

    @Test
    void registrarPontoParaUsuario() {
        var nomeUsuario = "Guerra";
        var pontos = new Pontos("moeda", 20);
        var resultadoEsperado = String.format("O usuaŕio %s recebeu %d pontos do tipo %s",
                nomeUsuario, pontos.pontos(), pontos.tipo());

        when(armazenamento.armazenar(anyString(), any(Pontos.class)))
                .thenReturn(resultadoEsperado);

        var resultado = placar.registrarPonto(nomeUsuario, pontos);
        Assertions.assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void naoRegistrarPontosParaUsuarioInesxisteteELancarExcecao() {
        var nomeUsuario = "Guerra";
        var pontos = new Pontos("moeda", 20);

        when(armazenamento.armazenar(anyString(), any(Pontos.class)))
                .thenThrow(new UsuarioNaoEncontradoException(String.format("Usuário %s não encontrado", nomeUsuario)));

        Assertions.assertThatThrownBy(() -> placar.registrarPonto(nomeUsuario,pontos))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage(String.format("Usuário %s não encontrado", nomeUsuario));
    }

}
