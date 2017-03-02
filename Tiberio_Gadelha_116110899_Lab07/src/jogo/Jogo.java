package jogo;
import java.util.HashSet;

/**
 * 
 * @author Tiberio Gadelha
 *
 */
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
	 * O construtor de Jogo com todas as informacoes de um jogo.
	 * @param nomeJogo Nome do jogo
	 * @param preco Preco do jogo.
	 * 
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
	 * Registra a jogada do usuarioo em um jogo e retorna a quantidade de x2p obtida, de acordo com o tipo de jogo.
	 * @param score Score obtido
	 * @param zerou Pergunta se o usuario zerou.
	 * @return Retorna a quantidade de x2p
	 * @throws Exception
	 * 
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
	
	/**
	 * Adiciona um tipo de Jogabilidade ao Jogo
	 * @param jogabilidade A string que será convertida em uma Jogabilidade.
	 * @throws Exception
	 */

	public void addJogabilidades(String jogabilidade) throws Exception {
		if(jogabilidade == null) {
			
			throw new Exception("A jogabilidade nao pode ser nula");
		} else {
			for(Jogabilidade i: Jogabilidade.values()) {
				if (i.getJogabilidade().equalsIgnoreCase(jogabilidade)) {
					jogabilidades.add(i);
					
				}
			}
		}
	}

}