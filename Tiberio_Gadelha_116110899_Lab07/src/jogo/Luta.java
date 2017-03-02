package jogo;

public class Luta extends Jogo{

	public Luta(String nomeJogo, int preco ) {
		super(nomeJogo, preco);
	}
	
	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {
		if (score < 0 || score > 100000) {
			throw new Exception("O score nao pode ser menor que zerou ou maior que 100 mil.");
		}
		qtdJogado++;
		if (score >= bestScore) {
			bestScore = score;
			return score/1000;
		}
		
		if (zerou == true) {
			qtdZerado++;
		}
		if (score > bestScore) {
			this.setBestScore(score);
		}
	
		return 0;
	}
	
	
	@Override
	public String toString() {
		return FIM_DE_LINHA + "+ " + getNomeJogo() + " - Luta:" + FIM_DE_LINHA +  "==> Jogou " + qtdJogado + " vez(es)" + FIM_DE_LINHA  + "==> Zerou " + 
				qtdZerado + " vez(es)" + FIM_DE_LINHA + "==> Maior score: " + bestScore;
	}

}