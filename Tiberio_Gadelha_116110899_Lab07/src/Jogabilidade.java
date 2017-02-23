public enum Jogabilidade {
	
	ONLINE("online"),
	OFFLINE("offline"),
	MULTIPLAYER("multiplayer"),
	COOPERATIVO("cooperativo"),
	COMPETITIVO("competitivo");
	
	private final String jogabilidade;
	
	Jogabilidade(String jogabilidade) {
		this.jogabilidade = jogabilidade;
	}

	public String getJogabilidade() {
		return jogabilidade;
	}
}
