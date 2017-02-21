package p2cg;

public class LojaFachada {
	
	/**
	 * Uma loja é criada. Com todos os métodos da classe Loja.
	 */
	
	private Loja loja = new Loja();
	
	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		return	loja.adicionaUsuario(nomeUsuario, nomeLogin, experiencia);
	}
	
	public void creditaConta(String nomeLogin, int valor) throws Exception {
		loja.creditaConta(nomeLogin, valor);
	}
	
	public void vendeJogo(String nomeLogin, Jogo jogo) throws Exception {
		loja.vendeJogo(nomeLogin, jogo);
	}
	
	public void imprimeDados() {
		loja.imprimeDados();
	}
	
	public Usuario procuraUsuario(String nomeLogin) {
		return loja.procuraUsuario(nomeLogin);
	}
	
	public boolean upgradeUsuario(String nomeLogin) throws Exception {
		return loja.upgradeUsuario(nomeLogin);
	}
}