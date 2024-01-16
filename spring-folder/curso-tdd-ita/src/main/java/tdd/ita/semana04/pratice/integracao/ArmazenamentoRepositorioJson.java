package tdd.ita.semana04.pratice.integracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.repository.ArmazenamentoRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ArmazenamentoRepositorioJson implements ArmazenamentoRepositorio {

    private final List<Usuario> usuarios;

    private final ObjectMapper objectMapper;

    private static final String PATH = "armazenamento.json";

    public ArmazenamentoRepositorioJson(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.objectMapper = new ObjectMapper();

        writeJson(usuarios);
    }

    @Override
    public void salvarPontosParaUsuario(Usuario usuario, Pontos pontos) {

        List<Usuario> usuariosFromJson = readJson();

        usuariosFromJson.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(usuario.getNome()))
                .peek(u -> u.addPontos(pontos));

        writeJson(usuariosFromJson);
    }

    @Override
    public Optional<Usuario> buscarUsuario(String nomeUsuario) {
        List<Usuario> usuariosFromJson = readJson();
        return usuariosFromJson.stream().filter(u -> u.getNome().equalsIgnoreCase(nomeUsuario)).findFirst();
    }

    @Override
    public List<Usuario> buscarUsuarios() {
        return readJson();
    }

    private void writeJson(List<Usuario> usuarios) {
        try {
            var outputFile = new File(PATH);

            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }

            this.objectMapper.writeValue(new File(PATH), usuarios);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Usuario> readJson() {
        try {
            return objectMapper.readValue(new File(PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
