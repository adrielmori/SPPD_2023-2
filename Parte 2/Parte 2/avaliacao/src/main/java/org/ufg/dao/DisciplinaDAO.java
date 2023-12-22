package org.ufg.dao;

import org.ufg.dao.factory.BancoFactory;
import org.ufg.model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DisciplinaDAO {
    private final Connection connection;

    public DisciplinaDAO(BancoFactory banco) {
        this.connection = banco.iniciarBancoDados().conectar();
    }

    public void salvarDisciplina(Disciplina disciplina, Integer iden) {
        String sql = "INSERT INTO disciplina(nome, ch, iden_curso) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, disciplina.getNome());
            preparedStatement.setInt(2, disciplina.getCh());
            preparedStatement.setInt(3, iden);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
