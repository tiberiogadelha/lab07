package factory;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.RPG;

/**
 * 
 * @author Tiberio Gadelha
 *
 */
public class FactoryDeJogo {
	
	
	private Jogo jogo;
	
	/**
	 * Trata-se de um criador de objeto do tipo Jogo. De acordo com o parametro 'String tipo', vai ser criado um diferente tipo de jogo.
	 * @param nomeJogo Nome do jogo que vai ser criado.
	 * @param preco O preco do jogo.
	 * @param tipo O tipo do jogo (rpg/luta/plataforma).
	 * @param jogabilidade A jogabilidade do jogo.
	 * @return Retorna o objeto Jogo criado.
	 * @throws Exception
	 */
	public Jogo criaJogo(String nomeJogo, int preco, String tipo, String jogabilidade) throws Exception {
		
		if(tipo.equalsIgnoreCase("luta")) {
			criaJogoLuta(nomeJogo, preco, jogabilidade);
			return jogo;
		} else if(tipo.equalsIgnoreCase("plataforma")) {
			criaJogoPlataforma(nomeJogo, preco, jogabilidade);
			return jogo;
 		} else if(tipo.equalsIgnoreCase("rpg")) {
 			criaJogoRPG(nomeJogo, preco, jogabilidade);
 			return jogo;
 		} else {
 			return null;
 		}
	}
	
	private void criaJogoRPG(String nomeJogo, int preco, String jogabilidade) throws Exception {
		jogo = new RPG(nomeJogo, preco);
		String[] jogabilidades = jogabilidade.trim().split(" ");
		for(String i: jogabilidades) {
			jogo.addJogabilidades(i);
		}
		
		
		
	}
	
	private void criaJogoPlataforma(String nomeJogo, int preco, String jogabilidade) throws Exception {
		jogo = new Plataforma(nomeJogo, preco);
		String[] jogabilidades = jogabilidade.trim().split(" ");
		for(String i: jogabilidades) {
			jogo.addJogabilidades(i);
		}
		
	}
	
	private void criaJogoLuta(String nomeJogo, int preco, String jogabilidade) throws Exception {
		jogo = new Luta(nomeJogo, preco);
		String[] jogabilidades = jogabilidade.trim().split(" ");
		for(String i: jogabilidades) {
			jogo.addJogabilidades(i);
		}
		
	}

	
	

}
