package com.atividade5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection connection;

    public CandidatoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public void conectar() throws SQLException{
        if (connection == null || connection.isClosed()){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e){
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void desconectar() throws SQLException{
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }

    public void incluirCandidato(Candidato candidato){
        try {
            String sql = "INSERT INTO Candidato (codigo, nome, sexo, data_nasc, cargo_pretendido, texto_curriculo) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, candidato.getCodigo());
            preparedStatement.setString(2, candidato.getNome());
            preparedStatement.setString(3, String.valueOf(candidato.getSexo()));
            preparedStatement.setDate(4, new java.sql.Date(candidato.getData_nasc().getTime()));
            preparedStatement.setString(5, candidato.getCargo_pretendido());
            preparedStatement.setString(6, candidato.getTexto_curriculo());

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Candidato> listarCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Candidato";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resulSet = preparedStatement.executeQuery();

            while(resulSet.next()){
                Candidato candidato = new Candidato();
                candidato.setCodigo(resulSet.getInt("codigo"));
                candidato.setNome(resulSet.getString("nome"));
                candidato.setSexo(resulSet.getString("sexo").charAt(0));
                candidato.setData_nasc(resulSet.getDate("data_nasc"));
                candidato.setCargo_pretendido(resulSet.getString("cargo_pretendido"));
                candidato.setTexto_curriculo(resulSet.getString("texto_curriculo"));

                candidatos.add(candidato);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return candidatos;
    }

    public void exluirCandidato(int codigo){
        try {
            String deleta = "DELETE FROM Candidato WHERE codigo = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleta);
            preparedStatement.setInt(1, codigo);
            preparedStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void alterarCandidato(Candidato candidato){
        try {
            String sql = "UPDATE candidato SET nome = ?, sexo = ?, data_nasc = ?, cargo_pretendido = ?, texto_curriculo = ? WHERE codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, candidato.getNome());
            preparedStatement.setString(2, String.valueOf(candidato.getSexo()));
            preparedStatement.setDate(3, new java.sql.Date(candidato.getData_nasc().getTime()));
            preparedStatement.setString(4, candidato.getCargo_pretendido());
            preparedStatement.setString(5, candidato.getTexto_curriculo());
            preparedStatement.setInt(6, candidato.getCodigo());
            preparedStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Candidato buscar(Integer id) {
        String sql = "SELECT * FROM candidato WHERE codigo = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Candidato(
                        resultSet.getInt("codigo"),
                        resultSet.getString("nome"),
                        resultSet.getString("sexo").charAt(0),
                        resultSet.getDate("data_nasc"),
                        resultSet.getString("cargo_pretendido"),
                        resultSet.getString("texto_curriculo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
