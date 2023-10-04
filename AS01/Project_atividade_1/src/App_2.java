import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App_2 {

    static void addPerson(String nome, int idade, double salario, int idEstado, String pais) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("persons.dat", true);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {
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
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Pesquisar");
        System.out.println("3 - Sair");
    }

    static List<String[]> readStrings(String siglaEstado, int idEstado) {
        List<String[]> estados = new ArrayList<>();

        try (FileReader reader = new FileReader("../UF.csv");
                BufferedReader bufferedReader = new BufferedReader(reader)) {
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

    static void register(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());

        System.out.print("Sigla do seu estado: ");
        String siglaEstado = scanner.next();

        List<String[]> estados = readStrings(siglaEstado, 0);

        if (estados.size() >= 1) {
            System.out.println("Selecione o pais no sistema referente a sigla '" + siglaEstado + "': ");
            for (int i = 0; i < estados.size(); i++) {
                System.out.println(i + 1 + " - " + estados.get(i)[4]);
            }
            int opcaoEstado = scanner.nextInt();
            scanner.nextLine();
            addPerson(nome, idade, salario, Integer.parseInt(estados.get(opcaoEstado - 1)[0]),
                    estados.get(opcaoEstado - 1)[4]);
            System.out.println("Cadastro Finalizado!\n");
        } else {
            System.out.println("\nError: A sigla '" + siglaEstado + "' e invalida!\n");
        }

    }

    static void search(Scanner scanner) {
        List<String[]> persons = new ArrayList<>();
        boolean pessoaEncontrada = false;

        System.out.print("Informe o nome para a busca: ");
        String nome = scanner.nextLine();

        try (FileInputStream fileInputStream = new FileInputStream("persons.dat");
                DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
            while (true) {
                String nomePessoa = dataInputStream.readUTF();
                int idade = dataInputStream.readInt();
                double salario = dataInputStream.readDouble();
                int idEstado = dataInputStream.readInt();
                String pais = dataInputStream.readUTF();
                if (nomePessoa.equals(nome)) {
                    String siglaEstado = readStrings("", idEstado).get(0)[1];
                    persons.add(new String[] { nomePessoa, String.valueOf(idade), String.valueOf(salario), siglaEstado,
                            pais });
                    pessoaEncontrada = true;
                }
            }
        } catch (EOFException e) {
            // Fim do arquivo
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (pessoaEncontrada) {
            for (String[] person : persons) {
                System.out.print("\nNome: " + person[0] + ", " +
                        "Idade: " + person[1] + ", " +
                        "Salario: " + person[2] + ", " +
                        "Estado: " + person[3] + ", " +
                        "Pais: " + person[4] + "\n\n");
            }
        } else {
            System.out.println("\nPessoa nao encontrada.\n");
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
                    register(scanner);
                    break;
                case 2:
                    search(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opçao invalida");
                    break;
            }
        }
    }
}