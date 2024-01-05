package tdd.ita.semana04.pratice.armazenamento.repository;

import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;

public interface ArmazenamentoRepositorio {

    void salvarPontosParaUsuario(Usuario usuario, Pontos pontos);
}
