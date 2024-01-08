package tdd.ita.semana04.pratice.armazenamento.repository;

import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;

import java.util.List;
import java.util.Optional;

public class ArmazenamentoRepositorioInMemory implements ArmazenamentoRepositorio {

    private final List<Usuario> usuarios;

    public ArmazenamentoRepositorioInMemory(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public void salvarPontosParaUsuario(Usuario usuario, Pontos pontos) {
        usuario.addPontos(pontos);
    }

    public Optional<Usuario> buscarUsuario(String nomeUsuario) {
        return this.usuarios.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(nomeUsuario)).findFirst();
    }
}
