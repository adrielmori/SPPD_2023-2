import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomCandidatoDAO {
    private String customJdbcURL;
    private String customJdbcUsername;
    private String customJdbcPassword;
    private Connection customConnection;

    public CustomCandidatoDAO(String customJdbcURL, String customJdbcUsername, String customJdbcPassword) {
        this.customJdbcURL = customJdbcURL;
        this.customJdbcUsername = customJdbcUsername;
        this.customJdbcPassword = customJdbcPassword;
    }

    public void customConectar() throws SQLException {
        if (customConnection == null || customConnection.isClosed()) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            customConnection = DriverManager.getConnection(customJdbcURL, customJdbcUsername, customJdbcPassword);
        }
    }

    public void customDesconectar() throws SQLException {
        if (customConnection != null && !customConnection.isClosed()) {
            customConnection.close();
        }
    }

    public void incluirCandidato(Candidato customCandidato) {
        try {
            String customSql = "INSERT INTO Candidato (codigo, nome, sexo, data_nasc, cargo_pretendido, texto_curriculo) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement customPreparedStatement = customConnection.prepareStatement(customSql);
            customPreparedStatement.setInt(1, customCandidato.getCodigo());
            customPreparedStatement.setString(2, customCandidato.getNome());
            customPreparedStatement.setString(3, String.valueOf(customCandidato.getSexo()));
            customPreparedStatement.setDate(4, new java.sql.Date(customCandidato.getData_nasc().getTime()));
            customPreparedStatement.setString(5, customCandidato.getCargo_pretendido());
            customPreparedStatement.setString(6, customCandidato.getTexto_curriculo());

            customPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Candidato> customListarCandidatos() {
        List<Candidato> customCandidatos = new ArrayList<>();
        try {
            String customSql = "SELECT * FROM Candidato";
            PreparedStatement customPreparedStatement = customConnection.prepareStatement(customSql);
            ResultSet customResultSet = customPreparedStatement.executeQuery();

            while (customResultSet.next()) {
                Candidato customCandidato = new Candidato();
                customCandidato.setCodigo(customResultSet.getInt("codigo"));
                customCandidato.setNome(customResultSet.getString("nome"));
                customCandidato.setSexo(customResultSet.getString("sexo").charAt(0));
                customCandidato.setData_nasc(customResultSet.getDate("data_nasc"));
                customCandidato.setCargo_pretendido(customResultSet.getString("cargo_pretendido"));
                customCandidato.setTexto_curriculo(customResultSet.getString("texto_curriculo"));

                customCandidatos.add(customCandidato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customCandidatos;
    }

    public void customExcluirCandidato(int customCodigo) {
        try {
            String customDeleta = "DELETE FROM Candidato WHERE codigo = ?";

            PreparedStatement customPreparedStatement = customConnection.prepareStatement(customDeleta);
            customPreparedStatement.setInt(1, customCodigo);
            customPreparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void customAlterarCandidato(Candidato customCandidato) {
        try {
            String customSql = "UPDATE candidato SET nome = ?, sexo = ?, data_nasc = ?, cargo_pretendido = ?, texto_curriculo = ? WHERE codigo = ?";
            PreparedStatement customPreparedStatement = customConnection.prepareStatement(customSql);
            customPreparedStatement.setString(1, customCandidato.getNome());
            customPreparedStatement.setString(2, String.valueOf(customCandidato.getSexo()));
            customPreparedStatement.setDate(3, new java.sql.Date(customCandidato.getData_nasc().getTime()));
            customPreparedStatement.setString(4, customCandidato.getCargo_pretendido());
            customPreparedStatement.setString(5, customCandidato.getTexto_curriculo());
            customPreparedStatement.setInt(6, customCandidato.getCodigo());
            customPreparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Candidato customBuscar(Integer customId) {
        String customSql = "SELECT * FROM candidato WHERE codigo = ?";
        try {
            PreparedStatement customPreparedStatement = this.customConnection.prepareStatement(customSql);
            customPreparedStatement.setInt(1, customId);
            ResultSet customResultSet = customPreparedStatement.executeQuery();

            if (customResultSet.next()) {
                return new Candidato(
                        customResultSet.getInt("codigo"),
                        customResultSet.getString("nome"),
                        customResultSet.getString("sexo").charAt(0),
                        customResultSet.getDate("data_nasc"),
                        customResultSet.getString("cargo_pretendido"),
                        customResultSet.getString("texto_curriculo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
