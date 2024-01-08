package tdd.ita.semana04.pratice.armazenamento;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.excecoes.UsuarioNaoEncontradoException;
import tdd.ita.semana04.pratice.armazenamento.repository.ArmazenamentoRepositorioInMemory;

import java.util.Arrays;

public class ArmazenamentoTest {

    private Armazenamento armazenamento;

    @BeforeEach
    public void setUp() {
        var listaUsuarios = Arrays.asList(
                new Usuario("Guerra"), new Usuario("Ana"), new Usuario("Jorge")
        );
        ArmazenamentoRepositorioInMemory armazenamentoRepositorioInMemory = new ArmazenamentoRepositorioInMemory(listaUsuarios);
        armazenamento = new Armazenamento(armazenamentoRepositorioInMemory);
    }

    @Test
    public void armazenarPontuacaoParaUmUsuario() {
        var nomeUsuario = "Guerra";
        var pontuacao = new Pontos("estrela",10);
        var resultado = armazenamento.armazenar(nomeUsuario, pontuacao);
        Assertions.assertThat(resultado)
                .isEqualTo(String.format("O usuário %s recebeu %d pontos do tipo %s",
                        nomeUsuario, pontuacao.pontos(), pontuacao.tipo()));
    }

    @Test
    public void naoArmazenarPontuacaoELancarExcecao() {
        var nomeInvalido = "Julia";
        var pontuacao = new Pontos("estrela",10);
        Assertions.assertThatThrownBy(() -> armazenamento.armazenar(nomeInvalido, pontuacao))
                .isInstanceOf(UsuarioNaoEncontradoException.class).hasMessage("Usuario " + nomeInvalido + " não encontrado.");
    }

    @Test
    public void recuperarPontosUsuario() {
        var nomeUsuario = "Guerra";
        var pontuacao = new Pontos("estrela",10);
        armazenamento.armazenar(nomeUsuario, pontuacao);
        var pontosUsuario = armazenamento.recuperarPontosDeUsuario(nomeUsuario, pontuacao.tipo());
        Assertions.assertThat(pontosUsuario).isEqualTo(pontuacao.pontos());
    }

    @Test
    public void recuperarPontosUsuarioValoresDobrados() {
        var nomeUsuario = "Guerra";
        var pontuacao = new Pontos("estrela",10);
        var pontuacao2 = new Pontos("estrela",10);
        armazenamento.armazenar(nomeUsuario, pontuacao);
        armazenamento.armazenar(nomeUsuario, pontuacao2);
        var pontosUsuario = armazenamento.recuperarPontosDeUsuario(nomeUsuario, pontuacao.tipo());
        Assertions.assertThat(pontosUsuario).isEqualTo(pontuacao.pontos() + pontuacao2.pontos());
    }

    @Test
    public void naoRecuperarPontosUsuarioELancarExcecao() {
        var nomeInvalido = "Julia";
        var pontuacao = new Pontos("estrela",10);
        Assertions.assertThatThrownBy(() -> armazenamento.recuperarPontosDeUsuario(nomeInvalido, pontuacao.tipo()))
                .isInstanceOf(UsuarioNaoEncontradoException.class).hasMessage("Usuario " + nomeInvalido + " não encontrado.");
    }

    @Test
    public void retornarUsuariosComPontuacao() {

    }
}
