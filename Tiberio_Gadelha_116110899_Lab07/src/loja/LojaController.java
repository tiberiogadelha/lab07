package loja;
import java.util.HashSet;
import java.util.Iterator;

import factory.FactoryDeJogo;
import factory.FactoryDeUsuario;
import jogo.Jogo;
import usuario.Usuario;
import validacao.ValidaJogo;
import validacao.ValidaUsuario;

/**
 * 
 * @author Tiberio Gadelha
 *
 */
public class LojaController {
	private HashSet<Usuario> bancoDeUsuarios;
	private ValidaJogo validaJogo = new ValidaJogo();
	private ValidaUsuario validaUsuario = new ValidaUsuario();
	private int totalArrecadado;
	
	/**
	 * Cria um LojaController, contendo um HashSet de usuarios e o total arrecadado pela venda de jogos.
	 */
	public LojaController() {
		bancoDeUsuarios = new HashSet<>();
		totalArrecadado = 0;
	}
	
	/**
	 * O metodo tenta vender um determinado jogo (que sera criado) para um usuario.
	 * @param nomeJogo O nome do jogo a ser comprado.
	 * @param valor O valor do jogo.
	 * @param jogabilidade A jogabilidade.
	 * @param tipo O tipo do jogo(rpg/luta/plataforma).
	 * @param nomeLogin O login do comprador.
	 * @return Retorna um boolean confirmando/negando se a compra foi realizada com sucesso.
	 * @throws Exception
	 */
	
	public boolean vendeJogo(String nomeJogo, int valor, String jogabilidade, String tipo, String nomeLogin) throws Exception{
		
		Usuario user = procuraUsuario(nomeLogin);
		Jogo jogo = criaJogo(nomeJogo, valor, tipo, jogabilidade);
		
		if(user != null) {
			totalArrecadado += jogo.getPreco();
			// chamada polimorfica
			return user.compraJogo(jogo);
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
		
	}
	
	/**
	 * O metodo serve para jogar os dados do Jogo que sera criado para o seu factory.
	 * @param nomeJogo O nome do jogo que sera criado.
	 * @param valor O seu preco.
	 * @param tipo O tipo do jogo(rpg/plataforma/luta).
	 * @param jogabilidade A jogabilidade
	 * @return Retorna o jogo criado.
	 * @throws Exception
	 */
	
	public Jogo criaJogo(String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception {
		validaJogo.validaPreco(valor);
		validaJogo.validaString(nomeJogo);
		validaJogo.validaTipo(tipo);
		
		FactoryDeJogo factoryJogo = new FactoryDeJogo();
		Jogo jogo = factoryJogo.criaJogo(nomeJogo, valor, tipo, jogabilidade);
		return jogo;
		
	}
	
	/**
	 * O metodo serve para jogar os dados do usuario que sera criado para seu factory.
	 * @param nomeUsuario O nome do usuario que sera criado.
	 * @param nomeLogin O login do usuario que sera criado.
	 * @param experiencia A experiencia, que sera sempre 'noob'
	 * @return Retorna o objeto do usuario criado.
	 * @throws Exception
	 */

	public Usuario criaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		FactoryDeUsuario factoryUsuario = new FactoryDeUsuario();
		Usuario novoUsuario = factoryUsuario.criaUsuario(nomeUsuario, nomeLogin);
		
		return novoUsuario;
		
	
	}
	
	/**
	 * O metodo adiciona um novo usuario ao banco de usuarios, esse metodo liga-se ao 'criaUsuario' para pode criar o objeto.
	 * @param nomeUsuario Nome do usuario
	 * @param nomeLogin O seu login
	 * @param experiencia A experiecia, que sera 'noob'
	 * @return Retorna um boolean, afirmando o usuario foi ou nao criado.
	 * @throws Exception
	 */
	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		validaUsuario.validaNome(nomeUsuario);
		validaUsuario.validaNome(nomeLogin);
		validaUsuario.validaExperiencia(experiencia);
		
		Usuario novoUsuario = criaUsuario(nomeUsuario, nomeLogin, experiencia);
		
		if(procuraUsuario(nomeLogin) == null) {
			// chamada polimorfica
			bancoDeUsuarios.add(novoUsuario);
			return true;
			
		} else {
			throw new Exception("O nome de login ja esta sendo utilizado. Escolha outro, por favor.");
		}
	}
	
	/**
	 * Adiciona credito à uma conta de Usuario.
	 * @param nomeLogin O login do usuario.
	 * @param valor O valor a ser adicionado.
	 * @throws Exception
	 */
	
	public void adicionaCredito(String nomeLogin, int valor) throws Exception {
		
		if(valor < 0) {
			throw new Exception("O valor nao pode ser negativo.");
		}
		
		if(procuraUsuario(nomeLogin) != null) {
			procuraUsuario(nomeLogin).adicionaCredito(valor);
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
			
		}
		
	}
	
	/**
	 * O metodo retorna a quantidade de saldo de um usuario.
	 * @param nomeLogin O nome do login.
	 * @return
	 * @throws Exception
	 */
	public double confereCredito(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			return user.getSaldo();
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
		
	}
	
	/**
	 * O metodo retorna a quantidade de x2p total de um usuario
	 * @param nomeLogin
	 * @return 
	 * @throws Exception
	 */
	public int getX2p(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			return user.getX2p();
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
		
	}
		
	/**
	 * O metodo imprime os dados de todos os usuarios cadastrados na loja.
	 */
	
	public void imprimeDados() {
		System.out.println("=== Central P2-CG ===");
		Iterator<Usuario> i = bancoDeUsuarios.iterator();
		while(i.hasNext()) {
			Usuario user = i.next();
			System.out.println(user.toString());
		}
	}
	
	/**
	 * O metodo procura um usuario atraves do seu login no banco de usuarios.
	 * @param nomeLogin
	 * @return Retorna o usuario desejado, ou null, caso nao seja encontrado.
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
	 * Caso um usuario noob tenha mais de 1000 x2p, ele pode se tornar veterano. Essa 'e a funcao desse metodo.
	 * @param nomeLogin
	 * @throws Exception
	 */
	public void upgrade(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			if(user.getTipoDeUsuario().equalsIgnoreCase("noob")){
				if(user.getX2p() >= 1000) {
					user.setStatusDoUsuarioVeterano();
				} else {
					throw new Exception("O usuario nao possui a quantidade minima de x2p.");
				}
			} else {
				throw new Exception("O usuario ja e um veterano.");
			}
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
			
		}
	
	/**
	 * Caso um usuario veterano consiga ficar com menos que 1000 de x2p, ele pode ser rebaixado a noob. Essa 'e a funcao desse metodo.
	 * @param nomeLogin
	 * @throws Exception
	 */
	
	public void downgrade(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			if(user.getTipoDeUsuario().equalsIgnoreCase("veterano")) {
				if(user.getX2p() <= 1000) {
					user.setStatusDoUsuarioNoob();
				} else {
					throw new Exception("O usuario esta acima de 1000 de x2p.");
				}
			} else {
				throw new Exception("O usuario ja e um noob.");			}
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
			
	}
	
	public HashSet<Usuario> getUsuarios() {
		return bancoDeUsuarios;
	}
	
	/**
	 * O metodo recompensa um usuario, dependendo do seu tipo(noob/veterano) e de acordo com a jogabilidade do jogo.
	 * @param nomeLogin
	 * @param nomeDoJogo
	 * @param score
	 * @param zerou
	 * @throws Exception
	 */
	public void recompensar(String nomeLogin, String nomeDoJogo, int score, boolean zerou) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			// chamada polimorfica
			user.recompensar(nomeDoJogo, score, zerou);
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}

	}

	/**
	 * O metodo pune um usuario, dependendo do seu tipo(noob/veterano) e da jogabilidade do seu jogo.
	 * @param nomeLogin
	 * @param nomeDoJogo
	 * @param score
	 * @param zerou
	 * @throws Exception
	 */
	public void punir(String nomeLogin, String nomeDoJogo, int score, boolean zerou) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		if(user != null) {
			// chamada polimorfica
			user.punir(nomeDoJogo, score, zerou);
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
		
	}

	/**
	 * Retorna a quantidade total que a loja arrecadou na venda de jogos.
	 * @return
	 */
	public int getTotalArrecadado() {
		return totalArrecadado;
	}


	public void setTotalArrecadado(int totalArrecadado) {
		this.totalArrecadado = totalArrecadado;
	}
}