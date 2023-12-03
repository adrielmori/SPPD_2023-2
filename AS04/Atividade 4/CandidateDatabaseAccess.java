import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidateDatabaseAccess {
    private final Connection connection;

    public CandidateAccess(BancoFactory bancoFactory) {
        connection = bancoFactory.iniciarBancoDados().conectar();
    }

    public void inserirCandidato(Candidato candidato) {
        String sql = "INSERT INTO candidato(nome, sexo, data_nascimento, cargo_pretendido, texto_curriculo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidato.getNome());
            preparedStatement.setString(2, String.valueOf(candidato.getSexo()));
            preparedStatement.setDate(3, new java.sql.Date(candidato.getDataNascimento().getTime()));
            preparedStatement.setString(4, candidato.getCargoPretendido());
            preparedStatement.setString(5, candidato.getTextoCurriculo());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Candidato> listarCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM candidato";
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Candidato candidato = new Candidato(
                            resultSet.getInt("codigo"),
                            resultSet.getString("nome"),
                            resultSet.getString("sexo").charAt(0),
                            resultSet.getDate("data_nascimento"),
                            resultSet.getString("cargo_pretendido"),
                            resultSet.getString("texto_curriculo"));
                    candidatos.add(candidato);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return candidatos;
    }
}