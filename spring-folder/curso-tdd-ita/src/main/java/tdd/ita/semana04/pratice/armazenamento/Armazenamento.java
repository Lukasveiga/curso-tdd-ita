package tdd.ita.semana04.pratice.armazenamento;

import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.repository.ArmazenamentoRepositorio;

import java.util.Optional;

public class Armazenamento {

    private final ArmazenamentoRepositorio armazenamentoRepositorio;

    public Armazenamento(ArmazenamentoRepositorio armazenamentoRepositorio) {
        this.armazenamentoRepositorio = armazenamentoRepositorio;
    }

    public String armazenar(Usuario usuario, Pontos pontos) {
        usuario.addPontos(pontos);
        armazenamentoRepositorio.salvarUsuario(usuario);
        return String.format("O usuário %s recebeu %d pontos do tipo %s",
                usuario.getNome(), pontos.getPontos(), pontos.getTipo());
    }

    public int recuperarPontosDeUsuario(Usuario usuario, String tipo) {
        Optional<Pontos> pontuacao = usuario.getListaDePontos()
                .stream().filter(p -> p.getTipo().equals(tipo)).findFirst();

        return pontuacao.map(Pontos::getPontos).orElse(0);
    }
}
