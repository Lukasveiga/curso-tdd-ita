package tdd.ita.semana04.pratice.armazenamento.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private final String nome;

    private final List<Pontos> listaDePontos = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void addPontos(Pontos pontos) {
        for (Pontos p : listaDePontos) {
            if(p.getTipo().equals(pontos.getTipo())) {
                p.setPontos(pontos.getPontos());
                return;
            }
        }
        listaDePontos.add(pontos);
    }
}