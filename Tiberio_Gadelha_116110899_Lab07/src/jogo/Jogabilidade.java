package jogo;

/**
 * 
 * @author Tiberio Gadelha
 *
 */
public enum Jogabilidade {
	
	
	ONLINE("online"),
	OFFLINE("offline"),
	MULTIPLAYER("multiplayer"),
	COOPERATIVO("cooperativo"),
	COMPETITIVO("competitivo");
	
	private final String jogabilidade;
	
	/**
	 * Cria um objeto do tipo Jogabilidade.
	 * @param jogabilidade
	 */
	
	Jogabilidade(String jogabilidade) {
		this.jogabilidade = jogabilidade;
	}

	/**
	 * 
	 * @return Retorna a string da Jogabilidade.
	 */
	public String getJogabilidade() {
		return jogabilidade;
	}
}
