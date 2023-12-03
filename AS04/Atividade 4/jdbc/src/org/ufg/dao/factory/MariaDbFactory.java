public class MariaDbFactory extends BancoFactory {
    @Override
    protected BancoDados criarBancoDados() {
        return MariaDb.getInstance();
    }
}
