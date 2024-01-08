package tdd.ita.semana04.pratice.armazenamento;

import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.excecoes.UsuarioNaoEncontradoException;
import tdd.ita.semana04.pratice.armazenamento.repository.ArmazenamentoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Armazenamento {

    private final ArmazenamentoRepositorio armazenamentoRepositorio;

    public Armazenamento(ArmazenamentoRepositorio armazenamentoRepositorio) {
        this.armazenamentoRepositorio = armazenamentoRepositorio;
    }

    public String armazenar(String nomeUsuario, Pontos pontos) {
        var usuario = buscarUsuario(nomeUsuario);
        armazenamentoRepositorio.salvarPontosParaUsuario(usuario, pontos);
        return String.format("O usuário %s recebeu %d pontos do tipo %s",
                usuario.getNome(), pontos.pontos(), pontos.tipo());
    }

    public int recuperarPontosDeUsuario(String nomeUsuario, String tipo) {
        var usuario = buscarUsuario(nomeUsuario);

        Optional<Pontos> pontuacao = usuario.getListaDePontos()
                .stream().filter(p -> p.tipo().equals(tipo)).findFirst();

        return pontuacao.map(Pontos::pontos).orElse(0);
    }

    private Usuario buscarUsuario(String nomeUsuario) {
        return armazenamentoRepositorio.buscarUsuario(nomeUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario " + nomeUsuario + " não encontrado."));
    }

    public List<Usuario> recuperarUsuariosComPontuacao() {
        return armazenamentoRepositorio.buscarUsuariosComPontuacao();
    }
}
