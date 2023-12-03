import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDb implements BancoDados {
    public static String usuario = "root";
    public static String senha = "123";
    public static String textoDeConexao = "jdbc:mariadb://localhost:3306/candidatos";

    private static MariaDb instance;

    private MariaDb() {}

    public static MariaDb getInstance() {
        if (instance == null) {
            instance = new MariaDb();
        }
        return instance;
    }

    @Override
    public Connection conectar() {
        try {
            return DriverManager.getConnection(
                textoDeConexao, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
