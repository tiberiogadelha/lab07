package p2cg;

import java.util.HashSet;

public abstract class Jogo {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private String nomeJogo;
	private int preco;
	protected int bestScore;
	protected int qtdJogado;
	protected int qtdZerado;
	private HashSet<Jogabilidade> jogabilidades;
	private int x2p;
	
	/**
	 * O construtor de Jogo com todas as informações de um jogo.
	 * @param nomeJogo Nome do jogo
	 * @param preco Preço do jogo
	 * @author Tibério
	 */
	
	public Jogo(String nomeJogo, int preco) {
		this.nomeJogo = nomeJogo;
		this.setPreco(preco);
		jogabilidades = new HashSet<>();
		bestScore = 0;
		qtdJogado = 0;
		qtdZerado = 0;
		x2p = 0;
	}
	
	/**
	 * Registra a jogada do usuário em um jogo e retorna a quantidade de x2p obtida, de acordo com o tipo de jogo.
	 * @param score Score obtido
	 * @param zerou Pergunta se o usuário zerou.
	 * @return
	 * @throws Exception
	 * @author Tibério
	 */
	
	public abstract int registraJogada(int score, boolean zerou) throws Exception;
	
	public String getNomeJogo() {
		return nomeJogo;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}
	
	/**
	 * Adiciona um tipo de jogabilidade ao jogo.
	 * @param jogabilidade
	 * @throws Exception
	 * Se a jogabilidade for nula, uma exceção é lançada.
	 * @author Tibério
	 */
	
	public void addJogabilidades(Jogabilidade jogabilidade) throws Exception {
		if(jogabilidade == null) {
			throw new Exception("A jogabilidade nao pode ser nula");
		} else {
			jogabilidades.add(jogabilidade);
		}
	}

	public int getQtdJogado() {
		return qtdJogado;
	}

	public void setQtdJogado(int qtdJogado) {
		this.qtdJogado = qtdJogado;
	}

	public int getQtdZerado() {
		return qtdZerado;
	}

	public HashSet<Jogabilidade> getJogabilidade() {
		return jogabilidades;
	}

	public int getX2p() {
		return x2p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeJogo == null) ? 0 : nomeJogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (nomeJogo == null) {
			if (other.nomeJogo != null)
				return false;
		} else if (!nomeJogo.equalsIgnoreCase(other.nomeJogo))
			return false;
		return true;
	}

}