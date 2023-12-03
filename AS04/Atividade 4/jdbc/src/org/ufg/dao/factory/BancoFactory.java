public abstract class BancoFactory {
    public BancoDados iniciarBancoDados() {
        return criarBancoDados();
    }

    protected abstract BancoDados criarBancoDados();
}
