package tdd.ita.semana04.pratice.placar;

import tdd.ita.semana04.pratice.armazenamento.Armazenamento;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        var pontosUsuarioFiltrados = pontosUsuario.stream()
                .filter(p -> p.pontos() > 0).toList();

        return gerarQuadroDePontuacao(nomeUsuario, pontosUsuarioFiltrados);
    }

    public String rankingDePontos(String tipoPonto) {
        var usuariosComPontos = armazenamento.recuperarUsuariosComPontuacao();

        var usuariosComTipoPonto = filtrarPontosDeUsuarioPorTipo(tipoPonto, usuariosComPontos);

        ordenarListaUsuariosPorPontos(usuariosComTipoPonto);

        return gerarQuandroDeRanking(tipoPonto, usuariosComTipoPonto);
    }

    private static ArrayList<Usuario> filtrarPontosDeUsuarioPorTipo(String tipoPonto, List<Usuario> usuariosComPontos) {
        return usuariosComPontos.stream()
                .filter(u -> u.getListaDePontos().stream()
                        .anyMatch(p -> p.tipo().equalsIgnoreCase(tipoPonto)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static String gerarQuandroDeRanking(String tipoPonto, ArrayList<Usuario> usuariosComTipoPonto) {
        var ranking = new StringBuilder("Ranking de pontos do tipo " + tipoPonto + ":\n");
        for (int i = 0; i < usuariosComTipoPonto.size(); i++) {
            var usuario = usuariosComTipoPonto.get(i);
            var pontos = usuario.getListaDePontos().get(0);
            ranking.append(String.format("%d. %s com %d pontos",i + 1, usuario.getNome(), pontos.pontos())).append("\n");
        }
        return ranking.toString();
    }

    private static String gerarQuadroDePontuacao(String nomeUsuario, List<Pontos> pontosUsuario) {
        var pontuacaoGeral = new StringBuilder("Pontos do usuário " + nomeUsuario + " :");
        pontosUsuario.forEach(p -> {
            pontuacaoGeral.append("- ")
                    .append(p.tipo()).append(": ")
                    .append(p.pontos()).append(" pontos")
                    .append("\n");
        });
        return pontuacaoGeral.toString();
    }

    private static void ordenarListaUsuariosPorPontos(List<Usuario> usuarios) {
        usuarios.sort(Comparator.comparingInt(u -> {
            Pontos primeiroPonto = u.getListaDePontos().get(0);
            return (primeiroPonto != null) ? primeiroPonto.pontos() : 0;
        }));

        Collections.reverse(usuarios);
    }
}
