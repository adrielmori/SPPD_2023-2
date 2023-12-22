package org.ufg.dao.factory;

import org.ufg.dao.database.BancoDados;
import org.ufg.dao.database.MariaDb;

public class MariaDbFactory extends BancoFactory {
    @Override
    protected BancoDados criarBancoDados() {
        return MariaDb.getInstance();
    }
}
