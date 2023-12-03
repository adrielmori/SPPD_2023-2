import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class FuncionarioXML {
    private String cpf;
    private String nome;
    private int idade;
    private double salario;
    private String cargo;
    private List<String> habilidades;
    private static final String XML_EXTENSION = ".xml";

    public FuncionarioXML(String cpf, String nome, int idade, double salario, String cargo, List<String> habilidades) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.cargo = cargo;
        this.habilidades = habilidades;
    }

    public static String convertToXml(FuncionarioXML funcionario) {
        XStream xStream = new XStream();
        xStream.alias("Funcionario", FuncionarioXML.class);
        return xStream.toXML(funcionario);
    }

    public static boolean saveToXml(FuncionarioXML funcionario) {
        try (FileWriter fileWriter = new FileWriter(funcionario.getCpf() + XML_EXTENSION)) {
            fileWriter.write(convertToXml(funcionario));
            fileWriter.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static FuncionarioXML readFromXml(String cpf) {
        try (FileReader reader = new FileReader(cpf + XML_EXTENSION)) {
            XStream xStream = new XStream();
            xStream.alias("Funcionario", FuncionarioXML.class);
            xStream.addPermission(AnyTypePermission.ANY);
            return (FuncionarioXML) xStream.fromXML(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    FuncionarioXML funcionario = cadastrarFuncionario();
                    saveToXml(funcionario);
                    System.out.println("Funcionário cadastrado com sucesso!");
                    break;
                case 2:
                    sourceFuncionario();
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

    public static FuncionarioXML cadastrarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a idade do funcionário: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o cargo do funcionário: ");
        String cargo = scanner.nextLine();
        System.out.println("Digite as habilidades do funcionário (exemplo: Java,Python,JavaScript):");
        String habilidade = scanner.nextLine();
        List<String> habilidades = Arrays.asList(habilidade.split(","));
        return new FuncionarioXML(cpf, nome, idade, 0.0, cargo, habilidades);
    }

    public static void sourceFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        FuncionarioXML funcionario = readFromXml(cpf);
        if (funcionario != null) {
            System.out.println(funcionario);
            System.out.println();
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
}