package usuario;
import java.util.HashSet;
import jogo.Jogabilidade;

import jogo.Jogo;

/**
 * 
 * @author Tiberio Gadelha
 *
 */
public class Veterano implements TipoDeUsuario {

	public Veterano()  {
	}
	
	/**
	 * O metodo vai punir um usuario em x2p, de acordo com a jogabilidade do jogo.
	 */
	public int punir(Jogo jogo) {	
		int punicao = 0;
		HashSet<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		for(Jogabilidade i: jogabilidades) {
			if(i.getJogabilidade().equalsIgnoreCase("offline")) {
				punicao += 20;
			} else if(i.getJogabilidade().equalsIgnoreCase("competitivo")) {
				punicao += 20;
			}
		}
		
		return punicao;
		
	}	
	
	public int recompensar(Jogo jogo) {
		int recompensa = 0;
		HashSet<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		for(Jogabilidade i: jogabilidades) {
			if(i.getJogabilidade().equalsIgnoreCase("online")) {
				recompensa += 10;
			} else if(i.getJogabilidade().equalsIgnoreCase("cooperativo")) {
				recompensa += 20;
			}
		}
		
		return recompensa;
	}

	public double desconto() {
		return 0.8;
	}

	public String tipoDeUsuario() {
		return "veterano";
	}

	public int x2pExtra() {
		return 15;
	}


}