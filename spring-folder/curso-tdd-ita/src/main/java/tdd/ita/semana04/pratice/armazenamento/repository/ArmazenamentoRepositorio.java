package tdd.ita.semana04.pratice.armazenamento.repository;

import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;

import java.util.Optional;

public interface ArmazenamentoRepositorio {

    void salvarPontosParaUsuario(Usuario usuario, Pontos pontos);

    Optional<Usuario> buscarUsuario(String nomeUsuario);
}
