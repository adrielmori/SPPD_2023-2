package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Curso;

public class CursoDAO {

	private Connection conexao;

	public CursoDAO() {
		conexao = FabricaDeConexao.obterConexao();
	}
	
	public void incluir(Curso c) {
		try {
			String sql = "INSERT INTO curso(numero, nome, tipo) VALUES (?, ?, ?)";
			PreparedStatement instrucao = this.conexao.prepareStatement(sql);
			instrucao.setInt(1, c.getNumero());
			instrucao.setString(2, c.getNome());
			instrucao.setString(3, c.getTipo());
			instrucao.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Curso c) {
		try {
			String sql = "UPDATE curso SET nome = ?, tipo = ? WHERE numero = ?";
			PreparedStatement instrucao = this.conexao.prepareStatement(sql);
			instrucao.setString(1, c.getNome());
			instrucao.setString(2, c.getTipo());
			instrucao.setInt(3, c.getNumero());
			instrucao.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Curso c) {
		try {
			String sql = "DELETE FROM curso WHERE numero = ?";
			PreparedStatement instrucao = this.conexao.prepareStatement(sql);
			instrucao.setInt(1, c.getNumero());
			instrucao.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Curso getCurso(int num) {
		try {
			String sql = "SELECT * FROM curso WHERE numero = ?";
			PreparedStatement instrucao = this.conexao.prepareStatement(sql);
			instrucao.setInt(1, num);
			ResultSet rs = instrucao.executeQuery();
			
			if (rs.next()) {
				Curso curso = new Curso(
						rs.getInt("numero"),
						rs.getString("nome"),
						rs.getString("tipo"));
				return curso;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<Curso> getCursos() {
		List<Curso> listaCursos = new ArrayList<Curso>();
		try {
			String sql = "SELECT * FROM curso";
			PreparedStatement instrucao = this.conexao.prepareStatement(sql);
			ResultSet rs = instrucao.executeQuery();
			
			while (rs.next()) {
				Curso curso = new Curso(
						rs.getInt("numero"),
						rs.getString("nome"),
						rs.getString("tipo"));
				listaCursos.add(curso);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCursos;
	}

	
	
}
