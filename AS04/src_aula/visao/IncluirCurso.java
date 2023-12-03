package visao;

import java.util.Scanner;

import biblioteca.CursoDAO;
import negocio.Curso;

public class IncluirCurso {

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);
		int num;
		String nome;
		String tipo;
		
		System.out.println("Digite o codigo do curso: ");
		num = leitor.nextInt();
		leitor.nextLine();
		System.out.println("Digite o nome do curso");
		nome = leitor.nextLine();
		System.out.println("Digite o tipo (B, L ou T)");
		tipo = leitor.next();
		
		Curso curso = new Curso(num, nome, tipo);
		
		CursoDAO cdao = new CursoDAO();
		cdao.incluir(curso);
		System.out.println("Curso incluido com sucesso!!!");
		
	}

}
