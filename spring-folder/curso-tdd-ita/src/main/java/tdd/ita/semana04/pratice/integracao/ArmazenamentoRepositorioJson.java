package tdd.ita.semana04.pratice.integracao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import tdd.ita.semana04.pratice.armazenamento.entities.Pontos;
import tdd.ita.semana04.pratice.armazenamento.entities.Usuario;
import tdd.ita.semana04.pratice.armazenamento.repository.ArmazenamentoRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ArmazenamentoRepositorioJson implements ArmazenamentoRepositorio {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String PATH = "src/main/java/tdd/ita/semana04/pratice/integracao/armazenamento.json";


    @Override
    public void salvarPontosParaUsuario(Usuario usuario, Pontos pontos) {
        List<Usuario> usuariosFromJson = readJson();

        usuariosFromJson.forEach(u -> {
            if(u.getNome().equals(usuario.getNome())) {
                u.addPontos(pontos);
            }
        });

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


    public void writeJson(List<Usuario> usuarios) {
        try {
            var outputFile = new File(PATH);

            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }

            this.objectMapper.writeValue(outputFile, usuarios);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Usuario> readJson() {
        try {
            var outputFile = new File(PATH);

            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }

            return objectMapper.readValue(outputFile, new TypeReference<List<Usuario>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
