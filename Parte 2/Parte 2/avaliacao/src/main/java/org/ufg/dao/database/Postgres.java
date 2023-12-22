package org.ufg.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres implements BancoDados {
    public static String usuario = "postgres";
    public static String senha = "12356";
    public static String textoDeConexao = "jdbc:postgresql://localhost:5432/avaliacao1";

    private static Postgres instance;

    private Postgres() {}

    public static Postgres getInstance() {
        if (instance == null) {
            instance = new Postgres();
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
