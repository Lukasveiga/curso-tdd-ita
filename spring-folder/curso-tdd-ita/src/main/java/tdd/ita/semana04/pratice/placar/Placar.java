package tdd.ita.semana04.pratice.placar;

import tdd.ita.semana04.pratice.armazenamento.Armazenamento;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;

import java.util.List;

public class Placar {

    private final Armazenamento armazenamento;

    public Placar(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public String registrarPonto(String nomeUsuario, Pontos pontos) {
        return this.armazenamento.armazenar(nomeUsuario, pontos);
    }

    public String retornarPontosUsuario(String nomeUsuario) {
        var pontosUsuario = armazenamento
                .recuperarPontosRegistradosParaUsuario(nomeUsuario);

        var pontosFiltrados = pontosUsuario.stream()
                .filter(p -> p.pontos() > 0).toList();

        var pontuacaoGeral = quandroDePontuacao(nomeUsuario, pontosFiltrados);

        return pontuacaoGeral.toString();
    }

    private static StringBuilder quandroDePontuacao(String nomeUsuario, List<Pontos> pontosUsuario) {
        var pontuacaoGeral = new StringBuilder("Pontos do usuário " + nomeUsuario + " :");
        pontosUsuario.forEach(p -> {
            pontuacaoGeral.append("- ")
                    .append(p.tipo()).append(": ")
                    .append(p.pontos()).append(" pontos")
                    .append("\n");
        });
        return pontuacaoGeral;
    }

    public String rankingDePontos(String tipoPonto) {
        var usuariosComPontos = armazenamento.recuperarUsuariosComPontuacao();

        var usuariosComTipoPonto = usuariosComPontos.stream().filter(u -> {
            return u.getListaDePontos().stream().anyMatch(p -> p.tipo().equalsIgnoreCase(tipoPonto));
        }).toList();

        var ranking = new StringBuilder("Ranking de pontos do tipo " + tipoPonto + ":\n");
        for (int i = 0; i < usuariosComPontos.size(); i++) {
            var usuario = usuariosComTipoPonto.get(i);
            var pontos = usuario.getListaDePontos().get(0);
            ranking.append(String.format("%d. %s com %d pontos",i + 1, usuario.getNome(), pontos.pontos())).append("\n");
        }
        return ranking.toString();
    }
}
