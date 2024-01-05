package tdd.ita.semana04.pratice.armazenamento.entities;

public class Pontos {

    private final String tipo;

    private int pontos;


    public Pontos(String tipo, int pontos) {
        this.tipo = tipo;
        this.pontos = pontos;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos += pontos;
    }
}
