package org.ufg.dao;

import org.ufg.dao.factory.BancoFactory;
import org.ufg.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoDAO {
    private final Connection connection;

    public CursoDAO(BancoFactory banco) {
        this.connection = banco.iniciarBancoDados().conectar();
    }

    public void salvarCurso(Curso curso) {
        String sql = "INSERT INTO curso(iden, ano, nome) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, curso.getIden());
            preparedStatement.setInt(2, curso.getAno());
            preparedStatement.setString(3, curso.getNome());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existeCurso(Integer iden) {
        String sql = "SELECT iden FROM curso WHERE iden = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, iden);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
