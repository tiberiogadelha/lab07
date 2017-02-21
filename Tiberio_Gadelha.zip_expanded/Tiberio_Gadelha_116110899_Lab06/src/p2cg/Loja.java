package p2cg;

import java.util.HashSet;
import java.util.Iterator;

public class Loja {
	private HashSet<Usuario> bancoDeUsuarios;
	
	public Loja() {
		bancoDeUsuarios = new HashSet<>();
	}
	
	/**
	 * O método vende um jogo a um usuário, primeiro testando se o usuário existe no banco de dados da loja.
	 * @param nomeLogin Login do usuario que deseja comprar o jogo.
	 * @param jogo Jogo que vai ser vendido.
	 * @return
	 *  Retorna se o jogo foi ou não comprado pelo usuário.
	 *  @author Tibério
	 */
	
	public boolean vendeJogo(String nomeLogin, Jogo jogo) throws Exception{
		Usuario user = procuraUsuario(nomeLogin);
		if(user != null) {
			return user.compraJogo(jogo);
		}
		throw new Exception("O usuario ainda nao foi cadastrado.");
		
	}
	
	/**
	 * O método irá adicionar um usuario ao banco de dados. Primeiro, vai verificar se os nomes são validos; depois verificar
	 * se o usuário já foi cadastrado anteriormente. Através do tipo de usuário, será criado um objeto do veterano ou noob
	 * para adicioná-lo ao banco de dados de usuários.
	 * 
	 * @param nomeUsuario O nome do usuario
	 * @param nomeLogin O nome do login
	 * @param experiencia Identificar se o usuario é noob ou veterano.
	 * @return 
	 * @throws Exception 
	 * Se o nome for inválido, uma exceção será lançada.
	 * Se o usuario for válido, retornará true.
	 * @author Tibério
	 */

	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		
		if(nomeUsuario == null || nomeUsuario.trim().equals("")) {
			throw new Exception("O nome do usuario nao pode ser nulo ou vazio.");
		}
		if(nomeLogin == null || nomeLogin.trim().equals("")) {
			throw new Exception("O nome do login nao pode ser nulo ou vazio.");
		}
		if(experiencia == null || experiencia.trim().equals("")) {
			throw new Exception("A experiencia nao pode ser nula ou vazia");
		}
		
		Usuario veteranoUser = new Veterano(nomeUsuario, nomeLogin);
		Usuario noobUser = new Noob(nomeUsuario, nomeLogin);
		
		if(procuraUsuario(nomeLogin) == null) {
			if(experiencia.equalsIgnoreCase("noob")) {
				bancoDeUsuarios.add(noobUser);
				return true;
			} else if(experiencia.equalsIgnoreCase("veterano")) {
				bancoDeUsuarios.add(veteranoUser);
				return true;
			} else {
				throw new Exception("Tipo de experiencia invalida.");
			}
			
		} else {
			throw new Exception("O nome de login ja esta sendo utilizado. Escolha outro, por favor.");
		}
	}
	
	/** 
	 * O método primeiro verifica se o usuário existe no banco de dados para poder creditar a conta.
	 * @param nomeLogin O nome do login
	 * @param valor O valor a ser creditado
	 * @throws Exception
	 * Se o usuário nao existir ou o valor for inválido, uma exceção é lançada.
	 * @author Tibério
	 */
	
	public void creditaConta(String nomeLogin, int valor) throws Exception {
		if(valor < 0) {
			throw new Exception("O valor nao pode ser negativo.");
		}
		
		if(procuraUsuario(nomeLogin) != null) {
			procuraUsuario(nomeLogin).creditaConta(valor);
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
		
	}
	
	/**
	 * Imprime os dados de todos os usuários da loja. Informando nome, login, experiencia e os dados dos jogos
	 * que o mesmo possui na biblioteca.
	 * @author Tibério
	 */
	
	public void imprimeDados() {
		System.out.println("=== Central P2-CG ===");
		Iterator<Usuario> i = bancoDeUsuarios.iterator();
		while(i.hasNext()) {
			Usuario user = i.next();
			System.out.println(user.toString());
		}
	}
	
	/** O metodo irá procurar o usuario no banco de dados.
	 * @param nomeLogin O nome do login
	 * @return
	 * Se o usuário for achado, o método irá retorná-lo. Caso contrário, irá retornar null.
	 * @author Tibério
	 */
	
	public Usuario procuraUsuario(String nomeLogin) {
		Iterator<Usuario> i = bancoDeUsuarios.iterator();
		while(i.hasNext()) {
			Usuario usuarioProcurado = i.next();
			if (usuarioProcurado.getNomeLogin().equals(nomeLogin)) {
				return usuarioProcurado;
			}
		}
		return null;
	}
	
	/**
	 * O método irá fazer com que um usuário noob passe a ser veterano. Mas antes é verificado se ele realmente é um noob
	 * e se sua quantidade de x2p é suficiente para torná-lo um veterano.
	 * @param nomeLogin Login do usuário
	 * @return
	 * @throws Exception
	 * Se o usuário já for um veterano ou não tiver a quantidade mínima de x2p exigida, uma exceção será lançada.
	 * @author Tibério
	 */
	
	public boolean upgradeUsuario(String nomeLogin) throws Exception {
		Usuario OldUser = procuraUsuario(nomeLogin);
		if(OldUser != null) {
			Usuario user1 = new Noob("blabla", "blabalba");
			if(OldUser.getClass().equals(user1.getClass())){
				if(OldUser.getX2p() >= 1000) {
					Usuario newUser = new Veterano(OldUser.getNomeLogin(), OldUser.getNomeLogin());
					
					newUser.setBibliotecaDeJogos(OldUser.getBibliotecaDeJogos());
					newUser.setX2p(OldUser.getX2p());
					newUser.setSaldo(OldUser.getSaldo());
					bancoDeUsuarios.remove(OldUser);
					bancoDeUsuarios.add(newUser);
					return true;
				}	
			} 
			throw new Exception("O jogador ja e um veterano ou nao possui a quantidade de x2p necessaria.");		
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
			
		}
	
	public HashSet<Usuario> getUsuarios() {
		return bancoDeUsuarios;
	}
}