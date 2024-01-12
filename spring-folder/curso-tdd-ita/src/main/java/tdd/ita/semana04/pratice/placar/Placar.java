package tdd.ita.semana04.pratice.placar;

import tdd.ita.semana04.pratice.armazenamento.Armazenamento;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;

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

        var pontuacaoGeral = new StringBuilder("Pontos do usuário " + nomeUsuario + " :");
        pontosUsuario.forEach(p -> {
            pontuacaoGeral.append("- ")
                    .append(p.tipo()).append(": ")
                    .append(p.pontos()).append(" pontos")
                    .append("\n");
        });

        return pontuacaoGeral.toString();
    }
}
