import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.RPG;

public class FactoryDeJogo {
	
	private Jogo jogo;
	
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
