public class Luta extends Jogo{

	public Luta(String nomeJogo, int preco ) {
		super(nomeJogo, preco);
	}
	
	/**
	 * Registra a jogada de um jogo de tipo Luta. Primeiro � validado se o score � v�lido, depois verifica se o usu�rio zerou.
	 * Se o score for maior que o record, o usu�rio ganha o score/1000 de x2p. Se n�o, recebe 0 x2p.
	 * @author Tib�rio
	 */
	
	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {
		if (score < 0 || score > 100000) {
			throw new Exception("O score nao pode ser menor que zerou ou maior que 100 mil.");
		}
		
		if (zerou == true) {
			qtdZerado++;
		}
		
		qtdJogado++;
		
		if (score > bestScore) {
			bestScore = score;
			return score/1000;
		}
	
		return 0;
	}
	
	@Override
	public String toString() {
		return FIM_DE_LINHA + "+ " + getNomeJogo() + " - Luta:" + FIM_DE_LINHA +  "==> Jogou " + qtdJogado + " vez(es)" + FIM_DE_LINHA  + "==> Zerou " + 
				qtdZerado + " vez(es)" + FIM_DE_LINHA + "==> Maior score: " + bestScore;
	}

}