import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class FuncionarioManager {
    public static void salvarFuncionario(Funcionario funcionario) {
        Gson gson = new Gson();
        String cpf = funcionario.getCpf();
        String json = gson.toJson(funcionario);

        try (FileWriter file = new FileWriter(cpf + ".json")) {
            file.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}