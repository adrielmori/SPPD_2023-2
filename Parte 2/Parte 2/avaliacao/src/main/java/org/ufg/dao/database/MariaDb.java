package org.ufg.dao.database;

import org.ufg.dao.database.BancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDb implements BancoDados {
    public static String usuario = "root";
    public static String senha = "123";
    public static String textoDeConexao = "jdbc:mariadb://localhost:3306/avaliacao1";

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
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(
                textoDeConexao, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
