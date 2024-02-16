package tdd.ita.semana04.pratice.integracao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.ita.semana04.pratice.armazenamento.ArmazenamentoImp;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.excecoes.UsuarioNaoEncontradoException;
import tdd.ita.semana04.pratice.placar.Placar;

import java.util.Arrays;
import java.util.List;


public class PlacarTestInt {

    final Placar placar;
    final ArmazenamentoRepositorioJson armazenamentoRepositorioJson;

    final ArmazenamentoImp armazenamentoImp;

    private List<Pontos> pontosTeste;

    public PlacarTestInt() {
        this.armazenamentoRepositorioJson = new ArmazenamentoRepositorioJson();
        this.armazenamentoImp = new ArmazenamentoImp(armazenamentoRepositorioJson);
        this.placar = new Placar(armazenamentoImp);
    }

    @BeforeEach
    void setUp() {
        var usuarios = Arrays.asList(
                new Usuario("Carlos"),
                new Usuario("Carla"),
                new Usuario("Lukas")
        );

        this.armazenamentoRepositorioJson.writeJson(usuarios);

        pontosTeste = Arrays.asList(
                new Pontos("moeda", 20),
                new Pontos("estrela", 20),
                new Pontos("diamante", 20),
                new Pontos("ticket", 0)
        );
    }

    @Test
    void registrarPontoParaUsuario() {
        var nomeUsuario = "Carlos";
        var pontos = pontosTeste.get(0);
        var resultadoEsperado = String.format("O usuário %s recebeu %d pontos do tipo %s",
                nomeUsuario, pontos.pontos(), pontos.tipo());

        var resultado = placar.registrarPonto(nomeUsuario, pontos);
        Assertions.assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void naoRegistrarPontosParaUsuarioInesxisteteELancarExcecao() {
        var nomeUsuario = "João";
        var pontos = pontosTeste.get(0);

        Assertions.assertThatThrownBy(() -> placar.registrarPonto(nomeUsuario,pontos))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage(String.format("Usuário %s não encontrado.", nomeUsuario));
    }

    @Test
    void retornarTodosOsPontosDeUmUsuario() {
        var nomeUsuario = "Carlos";
        var pontos = pontosTeste.get(0);
        placar.registrarPonto(nomeUsuario, pontos);

        var resultado = placar.retornarPontosUsuario(nomeUsuario);

        Assertions.assertThat(resultado).contains(String.format("Pontos do usuário %s :", nomeUsuario));
        Assertions.assertThat(resultado).contains(String.format("- %s: %d pontos", pontosTeste.get(0).tipo(), pontosTeste.get(0).pontos()));
    }

    @Test
    void naoRetornarTodosOsPontosDeUmUsuarioParaUsuarioInesxisteteELancarExcecao() {
        var nomeUsuario = "João";
        Assertions.assertThatThrownBy(() -> placar.retornarPontosUsuario(nomeUsuario))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage(String.format("Usuário %s não encontrado.", nomeUsuario));
    }

    @Test
    void retornarRankingDePontos() {
        var tipoDePonto = "estrela";
        var pontos = pontosTeste.get(1);

        placar.registrarPonto("Carlos", pontos);
        placar.registrarPonto("Carlos", pontos);
        placar.registrarPonto("Carlos", pontos);

        placar.registrarPonto("Carla", pontos);
        placar.registrarPonto("Carla", pontos);

        placar.registrarPonto("Lukas", pontos);

        var ranking = this.placar.rankingDePontos(tipoDePonto);

        Assertions.assertThat(ranking).isEqualTo(
                "Ranking de pontos do tipo " + tipoDePonto + ":\n" +
                        "1. Carlos com 60 pontos\n" +
                        "2. Carla com 40 pontos\n" +
                        "3. Lukas com 20 pontos\n"
        );

    }
}
