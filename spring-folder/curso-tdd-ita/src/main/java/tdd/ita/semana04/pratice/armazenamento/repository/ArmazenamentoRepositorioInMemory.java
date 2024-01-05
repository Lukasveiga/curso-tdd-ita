package tdd.ita.semana04.pratice.armazenamento.repository;

import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoRepositorioInMemory implements ArmazenamentoRepositorio {

    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void salvarUsuario(Usuario usuario) {

    }
}
