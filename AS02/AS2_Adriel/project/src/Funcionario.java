import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Funcionario {
    private String cpf;
    private String nome;
    private int idade;
    private double salario;
    private String cargo;
    private ArrayList<String> habilidades;

    public Funcionario(String cpf, String nome, int idade, double salario, String cargo,
            ArrayList<String> habilidades) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.cargo = cargo;
        this.habilidades = habilidades;
    }

    public void salvarEmArquivo() {
        try (FileWriter writer = new FileWriter(cpf + ".json")) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Funcionario carregarFuncionario(String cpf) {
        try (FileReader reader = new FileReader(cpf + ".json")) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Funcionario.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o CPF do funcionário: ");
        String cpf = scanner.next();

        System.out.println("Informe o nome do funcionário: ");
        String nome = scanner.next();

        System.out.println("Informe a idade do funcionário: ");
        int idade = scanner.nextInt();

        System.out.println("Informe o salário do funcionário: ");
        double salario = scanner.nextDouble();

        System.out.println("Informe o cargo do funcionário: ");
        String cargo = scanner.next();

        System.out.println("Informe as habilidades do funcionário (separadas por vírgula): ");
        String habilidadesInput = scanner.next();
        ArrayList<String> habilidades = new ArrayList<>();
        String[] habilidadesArray = habilidadesInput.split(",");
        for (String habilidade : habilidadesArray) {
            habilidades.add(habilidade.trim());
        }

        Funcionario funcionario = new Funcionario(cpf, nome, idade, salario, cargo, habilidades);
        funcionario.salvarEmArquivo();

        System.out.println("Funcionário salvo com sucesso!");

        System.out.println("Informe o CPF do funcionário a ser pesquisado: ");
        String cpfPesquisado = scanner.next();

        Funcionario funcionarioCarregado = Funcionario.carregarFuncionario(cpfPesquisado);

        if (funcionarioCarregado != null) {
            System.out.println("Funcionário encontrado: " + funcionarioCarregado.getNome());
        } else {
            System.out.println("Funcionário com CPF " + cpfPesquisado + " não encontrado.");
        }

        scanner.close();
    }
}