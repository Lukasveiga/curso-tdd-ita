package tdd.ita.semana04.pratice.armazenamento;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmazenamentoTest {

    private Armazenamento armazenamento;

    private ArmazenamentoRepositorio usuarioRepositorio;

    @BeforeEach
    public void setUp() {
        armazenamento = new Armazenamento(usuarioRepositorio);
    }

    @Test
    public void armazenarPontuacaoParaUmUsuario() {
        var usuario = new Usuario("Guerra");
        var pontuacao = new Pontos("estrela",10);
        var resultado = armazenamento.armazenar(usuario, pontuacao);
        Assertions.assertThat(resultado)
                .isEqualTo(String.format("O usuário %s recebeu %d pontos", usuario.nome(), pontuacao.pontuacao()));
    }
}
