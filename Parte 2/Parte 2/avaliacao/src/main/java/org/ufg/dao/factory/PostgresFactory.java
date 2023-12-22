package org.ufg.dao.factory;

import org.ufg.dao.database.BancoDados;
import org.ufg.dao.database.Postgres;

public class PostgresFactory extends BancoFactory {
    @Override
    protected BancoDados criarBancoDados() {
        return Postgres.getInstance();
    }
}
