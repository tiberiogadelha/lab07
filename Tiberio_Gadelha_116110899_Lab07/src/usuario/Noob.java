package usuario;
import java.util.HashSet;

import jogo.Jogabilidade;
import jogo.Jogo;

/**
 * 
 * @author TiberioGadelha
 *
 */
public class Noob implements TipoDeUsuario {

	public Noob() {
	}
	
	public int punir(Jogo jogo) {
		int punicao = 0;
		HashSet<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		for(Jogabilidade i: jogabilidades) {
			if(i.getJogabilidade().equalsIgnoreCase("online")) {
				punicao += 10;
			} else if(i.getJogabilidade().equalsIgnoreCase("competitivo")) {
				punicao += 20;
			} else if(i.getJogabilidade().equalsIgnoreCase("cooperativo")) {
				punicao += 50;
			}
			
		}
		return  punicao;
	}	
	
	public int recompensar(Jogo jogo) {
		int recompensa = 0;
		HashSet<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		for(Jogabilidade i: jogabilidades) {
			if(i.getJogabilidade().equalsIgnoreCase("offline")) {
				recompensa += 30;
			} else if(i.getJogabilidade().equalsIgnoreCase("multiplayer")) {
				recompensa += 10;
			}
		}
		return recompensa;
	}
	
	public double desconto() {
		return 0.9;
	}

	public String tipoDeUsuario() {
		return "noob";
	}

	public int x2pExtra() {
		return 10;
	}
}