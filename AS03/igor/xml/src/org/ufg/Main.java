package org.ufg;

import org.ufg.Model.Funcionario;
import org.ufg.Service.FuncionarioSevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    Funcionario funcionario = cadastrarFuncionario();
                    FuncionarioSevice funcionarioSevice = new FuncionarioSevice(funcionario);
                    funcionarioSevice.criaUmArquivoXml();
                    System.out.println("Funcionário cadastrado com sucesso!");
                    break;
                case 2:
                    lerFuncionario();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Ler funcionário");
        System.out.println("3 - Sair");
    }

    public static Funcionario cadastrarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a idade do funcionário: ");
        Integer idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o cargo do funcionário: ");
        String cargo = scanner.nextLine();
        System.out.println("Digite as habilidades do funcionário: exemplo: Java,Python,JavaScript");
        String habilidade = scanner.nextLine();
        List<String> habilidades = listHabilidades(habilidade);

        return new Funcionario(cpf, nome, idade, cargo, habilidades);
    }

    private static List<String> listHabilidades(String habilidade) {
        String[] habilidadesArray = habilidade.split(",");
        return new ArrayList<>(Arrays.asList(habilidadesArray));
    }

    public static void lerFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        Funcionario funcionario = FuncionarioSevice.lerArquivoXml(cpf);
        System.out.println(funcionario);
        System.out.println();
    }
}