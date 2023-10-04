import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void gravaPessoa(String nome, int idade, double salario, int idEstado, String pais) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("pessoas.dat", true);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)
        ) {
            dataOutputStream.writeUTF(nome);
            dataOutputStream.writeInt(idade);
            dataOutputStream.writeDouble(salario);
            dataOutputStream.writeInt(idEstado);
            dataOutputStream.writeUTF(pais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void menu() {
        System.out.println("1 - Cadastrar pessoa");
        System.out.println("2 - Pesquisar nome pessoa");
        System.out.println("3 - Sair");
    }

    static List<String[]> lerEstados(String siglaEstado, int idEstado) {
        List<String[]> estados = new ArrayList<>();

        try (FileReader reader = new FileReader("../UF.csv");
             BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] estado = line.split(",");
                if (estado[1].equals(siglaEstado) || estado[0].equals(String.valueOf(idEstado))) {
                    estados.add(estado);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return estados;
    }

    static void cadastarPessoa(Scanner scanner) {
        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Digite sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite seu salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite a sigla do seu estado: ");
        String siglaEstado = scanner.next();

        List<String[]> estados = lerEstados(siglaEstado, 0);

        if (estados.size() == 1) {
            gravaPessoa(nome, idade, salario, Integer.parseInt(estados.get(0)[0]), estados.get(0)[4]);
            System.out.println("Pessoa gravada com sucesso!");
        } else if (estados.size() > 1) {
            System.out.println("Selecione o país correto: ");
            for (int i = 0; i < estados.size(); i++) {
                System.out.println(i + 1 + " - " + estados.get(i)[4]);
            }
            int opcaoEstado = scanner.nextInt();
            scanner.nextLine();
            gravaPessoa(nome, idade, salario, Integer.parseInt(estados.get(opcaoEstado - 1)[0]), estados.get(opcaoEstado - 1)[4]);
            System.out.println("Pessoa gravada com sucesso!");
        } else {
            System.out.println("Estado não encontrado");
        }

    }

    static void pesquisarNome(Scanner scanner) {
        List<String[]> pessoas = new ArrayList<>();

        System.out.println("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();

        try (FileInputStream fileInputStream = new FileInputStream("pessoas.dat");
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)
        ) {
            while (true) {
                String nomePessoa = dataInputStream.readUTF();
                int idade = dataInputStream.readInt();
                double salario = dataInputStream.readDouble();
                int idEstado = dataInputStream.readInt();
                String pais = dataInputStream.readUTF();
                if (nomePessoa.equals(nome)) {
                    String siglaEstado = lerEstados("", idEstado).get(0)[1];
                    pessoas.add(new String[]{nomePessoa, String.valueOf(idade), String.valueOf(salario), siglaEstado, pais});
                }
            }
        } catch (EOFException e) {
            System.out.println("Fim do arquivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String[] pessoa : pessoas) {
            System.out.println("Nome: " + pessoa[0]);
            System.out.println("Idade: " + pessoa[1]);
            System.out.println("Salário: " + pessoa[2]);
            System.out.println("Estado: " + pessoa[3]);
            System.out.println("País: " + pessoa[4]);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastarPessoa(scanner);
                case 2 -> pesquisarNome(scanner);
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }

    }
}