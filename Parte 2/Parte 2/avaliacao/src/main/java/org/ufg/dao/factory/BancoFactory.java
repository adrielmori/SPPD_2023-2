package org.ufg.dao.factory;

import org.ufg.dao.database.BancoDados;

public abstract class BancoFactory {
    public BancoDados iniciarBancoDados() {
        return criarBancoDados();
    }

    protected abstract BancoDados criarBancoDados();
}
