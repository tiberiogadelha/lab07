package p2cg;

public class Plataforma extends Jogo {

	public Plataforma(String nomeJogo, int preco) {
		super(nomeJogo, preco);
	}
	
	/**
	 * O método registra a jogada de um jogo tipo Plataforma. Se o usuário zerar o jogo, ele ganha 20 de x2p, se não, ganha 0.
	 * @author Tibério
	 */
	
	@Override
	public int registraJogada(int score, boolean zerou) {
		if (score > bestScore) {
			bestScore = score;
		}
		qtdJogado++;
		if (zerou == true) {
			qtdZerado++;
			return 20;
		}
		return 0;

	}
	
	@Override
	public String toString() {
		return FIM_DE_LINHA + "+ " + getNomeJogo() + " - Plataforma:" + FIM_DE_LINHA +  "==> Jogou " + qtdJogado + " vez(es)" + FIM_DE_LINHA  + "==> Zerou " + 
				qtdZerado + " vez(es)" + FIM_DE_LINHA + "==> Maior score: " + bestScore;
	}
	
}