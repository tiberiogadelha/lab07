package loja;
import easyaccept.EasyAccept;
import jogo.Jogo;

public class LojaFacade {
	
	private LojaController loja = new LojaController();
	
	public boolean vendeJogo(String nomeJogo, int valor, String jogabilidade, String tipo, String nomeLogin) throws Exception {
		return loja.vendeJogo(nomeJogo, valor, jogabilidade, tipo, nomeLogin);
	}
	
	public Jogo criaJogo(String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception {
		return loja.criaJogo(nomeJogo, valor, tipo, jogabilidade);
	}
	
	public boolean criaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		return loja.adicionaUsuario(nomeUsuario, nomeLogin, experiencia);
	}
	
	public void adicionaCredito(String nomeLogin, int valor) throws Exception {
		loja.adicionaCredito(nomeLogin, valor);
	}
	
	public double confereCredito(String nomeLogin) throws Exception {
		 return loja.confereCredito(nomeLogin);
	}
	
	public int getX2p(String nomeLogin) throws Exception {
		return loja.getX2p(nomeLogin);
	}
	
	public void imprimeDados() {
		loja.imprimeDados();
	}
	
	public void upgrade(String nomeLogin) throws Exception {
		loja.upgrade(nomeLogin);
	}
	
	public void downgrade(String nomeLogin) throws Exception {
		loja.downgrade(nomeLogin);
	}
	
	public void punir(String nomeLogin, String nomeDoJogo, int score, boolean zerou) throws Exception {
		loja.punir(nomeLogin, nomeDoJogo, score, zerou);
	}
	
	public void recompensar(String nomeLogin, String nomeDoJogo, int score, boolean zerou) throws Exception {
		loja.recompensar(nomeLogin, nomeDoJogo, score, zerou);
	}
	
	public static void main(String[] args) {
		args = new String[] {"loja.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt", "acceptance_test/us3.txt"} ;
		EasyAccept.main(args);
		
	}
}
