import jogo.Jogo;

public class LojaFacade implements LojaInterface {
	
	private LojaController loja = new LojaController();
	
	public boolean vendeJogo(String nomeLogin, String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception {
		return loja.vendeJogo(nomeLogin, nomeJogo, valor, tipo, jogabilidade);
	}
	
	public Jogo criaJogo(String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception {
		return loja.criaJogo(nomeJogo, valor, tipo, jogabilidade);
	}
	
	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		return loja.adicionaUsuario(nomeUsuario, nomeLogin, experiencia);
	}
	
	public void creditaConta(String nomeLogin, int valor) throws Exception {
		loja.creditaConta(nomeLogin, valor);
	}
	
	public void imprimeDados() {
		loja.imprimeDados();
	}
}
