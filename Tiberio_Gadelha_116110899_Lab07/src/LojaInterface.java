import jogo.Jogo;

public interface LojaInterface {
	
	public boolean vendeJogo(String nomeLogin, String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception;
	
	public Jogo criaJogo(String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception;
	
	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception;
	
	public void creditaConta(String nomeLogin, int valor) throws Exception;
	
	public void imprimeDados();

}
