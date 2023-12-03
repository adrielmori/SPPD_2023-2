package testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import biblioteca.CursoDAO;
import biblioteca.FabricaDeConexao;
import negocio.Curso;

public class Principal {

	public static void main(String[] args) throws SQLException {

		Curso novo = new Curso(20, "Banco de Dados", "T");
		
		CursoDAO cdao = new CursoDAO();
		//cdao.incluir(novo);
		//System.out.println("Curso incluido!!!");
		
		//cdao.excluir(novo);
		
		for(Curso c : cdao.getCursos()) {
			System.out.println(c.getNome());
		}
		
	}

}
