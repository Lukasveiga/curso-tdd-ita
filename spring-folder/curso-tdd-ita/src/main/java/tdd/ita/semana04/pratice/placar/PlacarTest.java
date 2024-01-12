package tdd.ita.semana04.pratice.placar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.ita.semana04.pratice.armazenamento.Armazenamento;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.excecoes.UsuarioNaoEncontradoException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlacarTest {

    @Mock
    private Armazenamento armazenamento;

    @InjectMocks
    private Placar placar;

    private final String nomeUsuario = "Guerra";

    private List<Pontos> pontosTeste;

    @BeforeEach
    void setUp() {
        pontosTeste = Arrays.asList(
                new Pontos("moeda", 20),
                new Pontos("estrela", 20),
                new Pontos("diamante", 20)
        );
    }

    @Test
    void registrarPontoParaUsuario() {
        var pontos = pontosTeste.get(0);
        var resultadoEsperado = String.format("O usuaŕio %s recebeu %d pontos do tipo %s",
                nomeUsuario, pontos.pontos(), pontos.tipo());

        when(armazenamento.armazenar(anyString(), any(Pontos.class)))
                .thenReturn(resultadoEsperado);

        var resultado = placar.registrarPonto(nomeUsuario, pontos);
        Assertions.assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void naoRegistrarPontosParaUsuarioInesxisteteELancarExcecao() {
        var pontos = pontosTeste.get(0);
        when(armazenamento.armazenar(anyString(), any(Pontos.class)))
                .thenThrow(new UsuarioNaoEncontradoException(String.format("Usuário %s não encontrado", nomeUsuario)));

        Assertions.assertThatThrownBy(() -> placar.registrarPonto(nomeUsuario,pontos))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage(String.format("Usuário %s não encontrado", nomeUsuario));
    }

    @Test
    void retornarTodosOsPontosDeUmUsuario() {
        when(armazenamento.recuperarPontosRegistradosParaUsuario(nomeUsuario))
                .thenReturn(pontosTeste);

        var resultado = placar.retornarPontosUsuario(nomeUsuario);
        Assertions.assertThat(resultado).contains(String.format("Pontos do usuário %s :", nomeUsuario));
        Assertions.assertThat(resultado).contains(String.format("- %s: %d pontos", pontosTeste.get(0).tipo(), pontosTeste.get(0).pontos()));
        Assertions.assertThat(resultado).contains(String.format("- %s: %d pontos", pontosTeste.get(1).tipo(), pontosTeste.get(1).pontos()));
        Assertions.assertThat(resultado).contains(String.format("- %s: %d pontos", pontosTeste.get(2).tipo(), pontosTeste.get(2).pontos()));
    }

}
