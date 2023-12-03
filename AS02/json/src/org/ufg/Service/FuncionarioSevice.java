package org.ufg.Service;

import com.google.gson.Gson;
import org.ufg.Model.Funcionario;

import java.io.FileReader;
import java.io.FileWriter;

public class FuncionarioSevice {

    private final Funcionario funcionario;

    public FuncionarioSevice(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    private String converteEmJson() {
        Gson gson = new Gson();
        return gson.toJson(funcionario);
    }

    public void criaUmArquivoJson() {
        try (FileWriter fileWriter = new FileWriter(funcionario.getCpf() + ".json")) {
            fileWriter.write(converteEmJson());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Funcionario lerArquivoJson(String cpf) {
        try (FileReader fileReader = new FileReader(cpf + ".json")) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, Funcionario.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
