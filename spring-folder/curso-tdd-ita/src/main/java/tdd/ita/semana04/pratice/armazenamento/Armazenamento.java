package tdd.ita.semana04.pratice.armazenamento;

import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;

import java.util.List;
import java.util.Set;

public interface Armazenamento {

    String armazenar(String nomeUsuario, Pontos pontos);

    int recuperarPontosDeUsuario(String nomeUsuario, String tipo);

    List<Usuario> recuperarUsuariosComPontuacao();

    Set<String> recuperarPontosRegistradosParaUsuario(String nomeUsuario);
}
