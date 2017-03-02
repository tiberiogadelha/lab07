package usuario;

import jogo.Jogo;

/**
 * 
 * @author Tiberio Gadelha
 *
 */
public interface TipoDeUsuario {
	
	/**
	 * Vai recompensar um usuario, de acordo com seu tipo(noob/veterano) e com a jogabilidade do jogo.
	 * @param jogo
	 * @return Retorna a quantidade de x2p recompensada.
	 */
	public int recompensar(Jogo jogo);
	
	/**
	 * Vai punir um usuario em x2p, de acordo com seu tipo(noob/veterano) e com a jogabilidade do jogo.
	 * @param jogo
	 * @return Retorna a quantidade de x2p perdida.
	 */
	public int punir(Jogo jogo);
	
	/**
	 * A quantidade de descontro que o tipo de usuario possui.
	 * @return
	 */
	public double desconto();
	
	/**
	 * Retorna uma string que representa o tipo do usuario.
	 * @return
	 */
	public String tipoDeUsuario();
	
	/**
	 * Retorna a quantidade de x2p Extra que um tipo de usuario possui ao comprar um jogo.
	 * @return
	 */
	public int x2pExtra();
	
	

}
