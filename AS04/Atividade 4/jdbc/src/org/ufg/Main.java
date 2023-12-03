import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        CandidateAccess candidateAccess = new CandidateAccess(new DatabaseFactory());
        while (true) {
            menu();
            int opcao = leitor.nextInt();
            switch (opcao) {
                case 1 -> candidateAccess.inserirCandidato(cadastrarEntidade());
                case 2 -> candidateAccess.listarCandidatos().forEach(System.out::println);
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    static void menu() {
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Sair");
    }

    static Entidade cadastrarEntidade() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o nome da entidade: ");
        String nome = leitor.nextLine();
        System.out.println("Digite a descrição da entidade: ");
        String descricao = leitor.nextLine();
        System.out.println("Digite a data de criação da entidade: ");
        String dataCriacao = leitor.nextLine();
        return new Entidade(nome, descricao, new Date(dataCriacao));
    }
}