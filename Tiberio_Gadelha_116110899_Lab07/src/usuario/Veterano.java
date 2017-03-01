package usuario;
import java.util.HashSet;
import jogo.Jogabilidade;

import jogo.Jogo;

public class Veterano implements TipoDeUsuario {

	public Veterano()  {
	}
	
	/**
	 * O m�todo compra um jogo com 20% de desconto. Se o usu�rio tiver saldo suficiente, o jogo � adicionado
	 * � sua biblioteca, ele ganha x2p e true � retornado. Se n�o, retorna false.
	 * @author Tib�rio
	 * @throws Exception 
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