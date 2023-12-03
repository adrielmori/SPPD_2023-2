package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
	public static String usuario = "admin01";
	public static String senha = "admin01";
	public static String textoDeConexao = "jdbc:mariadb://127.0.0.1:3306/faculdade";
	
	
	public static Connection obterConexao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				textoDeConexao, usuario, senha);
			return con;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
