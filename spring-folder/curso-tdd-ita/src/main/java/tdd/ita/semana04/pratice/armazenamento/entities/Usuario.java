package tdd.ita.semana04.pratice.armazenamento.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;

    private final List<Pontos> listaDePontos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Pontos> getListaDePontos() {
        return listaDePontos;
    }

    public void addPontos(Pontos pontos) {
        for (Pontos p : listaDePontos) {
            if(p.tipo().equals(pontos.tipo())) {
                var index = listaDePontos.indexOf(p);
                listaDePontos.set(index, new Pontos(p.tipo(), p.pontos() + pontos.pontos()));
                return;
            }
        }
        listaDePontos.add(pontos);
    }
}
