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
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.excecoes.UsuarioNaoEncontradoException;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
                new Pontos("diamante", 20),
                new Pontos("ticket", 0)
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

        verify(armazenamento, times(0)).armazenar(anyString(), any(Pontos.class));

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
        Assertions.assertThat(resultado).doesNotContain(String.format("- %s: %d pontos", pontosTeste.get(3).tipo(), pontosTeste.get(3).pontos()));
    }

    @Test
    void naoRetornarTodosOsPontosDeUmUsuarioParaUsuarioInesxisteteELancarExcecao() {
        when(armazenamento.recuperarPontosRegistradosParaUsuario(anyString()))
                .thenThrow(new UsuarioNaoEncontradoException(String.format("Usuário %s não encontrado", nomeUsuario)));

        verify(armazenamento, times(0)).recuperarPontosRegistradosParaUsuario(nomeUsuario);

        Assertions.assertThatThrownBy(() -> placar.retornarPontosUsuario(nomeUsuario))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage(String.format("Usuário %s não encontrado", nomeUsuario));
    }

    @Test
    void retornarRankingDePontos() {
        var tipoDePonto = "estrela";

        var usuarios = Arrays.asList(
                criarUsuarioComPontos("Fernandes", tipoDePonto, 19),
                criarUsuarioComPontos("Guerra", tipoDePonto, 25),
                criarUsuarioComPontos("Rodrigo", tipoDePonto, 17),
                criarUsuarioComPontos("Borges", "moeda", 55)
        );

        var usuariosOrdenados = ordenarListaUsuariosPorPontos(usuarios);

        var usuariosOrdenadosFiltrado = usuariosOrdenados.stream().filter(u -> {
            return u.getListaDePontos().stream().anyMatch(p -> p.tipo().equalsIgnoreCase(tipoDePonto));
        }).toList();

        when(armazenamento.recuperarUsuariosComPontuacao())
                .thenReturn(usuarios);

        var resultado = placar.rankingDePontos("estrela");

        var ranking = new StringBuilder("Ranking de pontos do tipo " + tipoDePonto + ":\n");
        for (int i = 0; i < usuariosOrdenadosFiltrado.size(); i++) {
            var usuario = usuariosOrdenadosFiltrado.get(i);
            var pontos = usuario.getListaDePontos().get(0);
            ranking.append(String.format("%d. %s com %d pontos", i + 1, usuario.getNome(), pontos.pontos())).append("\n");
        }

        Assertions.assertThat(resultado).isEqualTo(ranking.toString());
    }

    private static List<Usuario> ordenarListaUsuariosPorPontos(List<Usuario> usuarios) {
        var usuariosOrdenados = new ArrayList<>(usuarios);
        usuariosOrdenados.sort(Comparator.comparingInt(u -> {
            Pontos primeiroPonto = u.getListaDePontos().get(0);
            return (primeiroPonto != null) ? primeiroPonto.pontos() : 0;
        }));

        Collections.reverse(usuariosOrdenados);

        return usuariosOrdenados;
    }

    private static Usuario criarUsuarioComPontos(String nomeUsuario, String tipoPontos, int pontos) {
        Usuario usuario = new Usuario(nomeUsuario);
        usuario.addPontos(new Pontos(tipoPontos, pontos));
        return usuario;
    }
}
