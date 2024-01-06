package tdd.ita.semana04.pratice.armazenamento;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.repository.ArmazenamentoRepositorioInMemory;

public class ArmazenamentoTest {

    private Armazenamento armazenamento;

    @BeforeEach
    public void setUp() {
        ArmazenamentoRepositorioInMemory armazenamentoRepositorioInMemory = new ArmazenamentoRepositorioInMemory();
        armazenamento = new Armazenamento(armazenamentoRepositorioInMemory);
    }

    @Test
    public void armazenarPontuacaoParaUmUsuario() {
        var usuario = new Usuario("Guerra");
        var pontuacao = new Pontos("estrela",10);
        var resultado = armazenamento.armazenar(usuario, pontuacao);
        Assertions.assertThat(resultado)
                .isEqualTo(String.format("O usuário %s recebeu %d pontos do tipo %s",
                        usuario.getNome(), pontuacao.getPontos(), pontuacao.getTipo()));
    }

    @Test
    public void recuperarPontosUsuario() {
        var usuario = new Usuario("Guerra");
        var pontuacao = new Pontos("estrela",10);
        armazenamento.armazenar(usuario, pontuacao);
        var pontosUsuario = armazenamento.recuperarPontosDeUsuario(usuario, pontuacao.getTipo());
        Assertions.assertThat(pontosUsuario).isEqualTo(pontuacao.getPontos());
    }

    @Test
    public void recuperarPontosUsuarioValoresDobrados() {
        var usuario = new Usuario("Guerra");
        var pontuacao = new Pontos("estrela",10);
        var pontuacao2 = new Pontos("estrela",10);
        armazenamento.armazenar(usuario, pontuacao);
        armazenamento.armazenar(usuario, pontuacao2);
        var pontosUsuario = armazenamento.recuperarPontosDeUsuario(usuario, pontuacao.getTipo());
        Assertions.assertThat(pontosUsuario).isEqualTo(pontuacao.getPontos() + pontuacao2.getPontos());
    }
}
