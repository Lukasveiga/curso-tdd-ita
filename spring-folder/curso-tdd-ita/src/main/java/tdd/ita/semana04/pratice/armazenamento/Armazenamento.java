package tdd.ita.semana04.pratice.armazenamento;

public class Armazenamento {

    private final ArmazenamentoRepositorio armazenamentoRepositorio;

    public Armazenamento(ArmazenamentoRepositorio armazenamentoRepositorio) {
        this.armazenamentoRepositorio = armazenamentoRepositorio;
    }

    public String armazenar(Usuario usuario, Pontos pontos) {
        armazenamentoRepositorio.salvarPontosParaUsuario(usuario, pontos);
        return String.format("O usuário %s recebeu %d pontos", usuario.nome(), pontos.pontuacao());
    }
}
