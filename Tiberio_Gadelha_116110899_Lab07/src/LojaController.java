import java.util.HashSet;
import java.util.Iterator;

public class LojaController {
	private HashSet<Usuario> bancoDeUsuarios;
	
	public LojaController() {
		bancoDeUsuarios = new HashSet<>();
	}
	
	/**
	 * O m�todo vende um jogo a um usu�rio, primeiro testando se o usu�rio existe no banco de dados da loja.
	 * @param nomeLogin Login do usuario que deseja comprar o jogo.
	 * @param jogo Jogo que vai ser vendido.
	 * @return
	 *  Retorna se o jogo foi ou n�o comprado pelo usu�rio.
	 *  @author Tib�rio
	 */
	
	public boolean vendeJogo(String nomeLogin, String nomeJogo, int valor, String tipo) throws Exception{
		if(tipo.equalsIgnoreCase("rpg")) {
			Jogo jogo = new RPG(nomeJogo, valor);
		} else if(tipo.equalsIgnoreCase("luta")) {
			Jogo jogo = new Luta(nomeJogo, valor);
		} else if(tipo.equals("plataforma")) {
			Jogo jogo = new Plataforma(nomeJogo, valor);
		} else {
			throw new Exception("Invalido");
		}
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			return user.compraJogo(jogo);
		}
		throw new Exception("O usuario ainda nao foi cadastrado.");
		
	}
	
	/**
	 * O m�todo ir� adicionar um usuario ao banco de dados. Primeiro, vai verificar se os nomes s�o validos; depois verificar
	 * se o usu�rio j� foi cadastrado anteriormente. Atrav�s do tipo de usu�rio, ser� criado um objeto do veterano ou noob
	 * para adicion�-lo ao banco de dados de usu�rios.
	 * 
	 * @param nomeUsuario O nome do usuario
	 * @param nomeLogin O nome do login
	 * @param experiencia Identificar se o usuario � noob ou veterano.
	 * @return 
	 * @throws Exception 
	 * Se o nome for inv�lido, uma exce��o ser� lan�ada.
	 * Se o usuario for v�lido, retornar� true.
	 * @author Tib�rio
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
	 * O m�todo primeiro verifica se o usu�rio existe no banco de dados para poder creditar a conta.
	 * @param nomeLogin O nome do login
	 * @param valor O valor a ser creditado
	 * @throws Exception
	 * Se o usu�rio nao existir ou o valor for inv�lido, uma exce��o � lan�ada.
	 * @author Tib�rio
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
	 * Imprime os dados de todos os usu�rios da loja. Informando nome, login, experiencia e os dados dos jogos
	 * que o mesmo possui na biblioteca.
	 * @author Tib�rio
	 */
	
	public void imprimeDados() {
		System.out.println("=== Central P2-CG ===");
		Iterator<Usuario> i = bancoDeUsuarios.iterator();
		while(i.hasNext()) {
			Usuario user = i.next();
			System.out.println(user.toString());
		}
	}
	
	/** O metodo ir� procurar o usuario no banco de dados.
	 * @param nomeLogin O nome do login
	 * @return
	 * Se o usu�rio for achado, o m�todo ir� retorn�-lo. Caso contr�rio, ir� retornar null.
	 * @author Tib�rio
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
	 * O m�todo ir� fazer com que um usu�rio noob passe a ser veterano. Mas antes � verificado se ele realmente � um noob
	 * e se sua quantidade de x2p � suficiente para torn�-lo um veterano.
	 * @param nomeLogin Login do usu�rio
	 * @return
	 * @throws Exception
	 * Se o usu�rio j� for um veterano ou n�o tiver a quantidade m�nima de x2p exigida, uma exce��o ser� lan�ada.
	 * @author Tib�rio
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