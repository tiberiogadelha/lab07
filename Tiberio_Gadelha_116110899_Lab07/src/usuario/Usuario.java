package usuario;
import java.util.HashSet;

import jogo.Jogo;

public class Usuario {
	
	public static final String FIM_DE_LINHA = System.lineSeparator();
	
	private String nomeUsuario;
	private String nomeLogin;
	private int saldo;
	protected HashSet<Jogo> bibliotecaDeJogos;
	private int x2p;
	private double totalGasto;
	private TipoDeUsuario statusDoUsuario;
	
	
	/**
	 * O construtor cria um novo Usuario
	 * @param nomeUsuario O nome do Usuario.
	 * @param nomeLogin O login do usuario.
	 *
	 */
	
	public Usuario(String nomeUsuario, String nomeLogin) {
		this.nomeUsuario = nomeUsuario;
		this.nomeLogin= nomeLogin;
		saldo = 0;
		setX2p(0);
		setTotalGasto(0);
		bibliotecaDeJogos = new HashSet<>();
		statusDoUsuario = new Noob();
		
		
		
	}
	
	/**
	 * Credita a conta do usuario.
	 *
	 */
	
	public void adicionaCredito(int valor) {
		saldo += valor;
	}
	
	/**
	 * O metodo serve para o usuario comprar um jogo, recebendo um desconto e x2p extra, de acordo com seu tipo (noob/veterano)
	 * @param jogo Jogo que vai ser comprado.
	 * @return
	 * Retorna se o jogo foi ao nao comprado com sucesso.
	 */
	
	public boolean compraJogo(Jogo jogo) {
		if(!(bibliotecaDeJogos.contains(jogo))) {
			if(saldo >= jogo.getPreco() * statusDoUsuario.desconto()) {
				bibliotecaDeJogos.add(jogo);
				saldo -= jogo.getPreco() * statusDoUsuario.desconto();
				x2p += jogo.getPreco() * statusDoUsuario.x2pExtra();
				totalGasto += jogo.getPreco();
				return true;
				
			} 
		}
			return false;
	}
	
	/**
	 * O metodo vai recompensar em x2p um usuario, de acordo com o tipo de usuario e a jogabilidade do jogo.
	 * @param nomeDoJogo
	 * @param score
	 * @param zerou
	 * @throws Exception
	 */

	public void recompensar(String nomeDoJogo, int score, boolean zerou) throws Exception {
		Jogo jogo = procuraJogo(nomeDoJogo);
		if(jogo != null) {
			x2p += statusDoUsuario.recompensar(jogo);
			// chamada polimorfica
			x2p += jogo.registraJogada(score, zerou);
		} else {
			throw new Exception("O usuario nao possui o jogo");
		}
	}
	
	/**
	 * O metodo vai punir um usuario, diminuindo sua x2p, de acordo com seu tipo(noob/veterano) e a jogabilidade do jogo.
	 * @param nomeDoJogo
	 * @param score
	 * @param zerou
	 * @throws Exception
	 */
	
	public void punir(String nomeDoJogo, int score, boolean zerou) throws Exception {
		Jogo jogo = procuraJogo(nomeDoJogo);
		if(jogo != null) {
			x2p -= statusDoUsuario.punir(jogo);
			// chamada polimorfica
			x2p += jogo.registraJogada(score, zerou);
		} else {
			throw new Exception("O usuario nao possui o jogo");
		}
	}
	
	/**
	 * O metodo procura um jogo na biblioteca do usuario.
	 * @param nomeDoJogo Nome do jogo a ser procurado
	 * @return
	 * Se o usuario possui o jogo, o objeto 'e retornado. Caso contrario, retorna null.
	 * 
	 */
	
	public Jogo procuraJogo(String nomeDoJogo) {
		
		for (Jogo jogoProcurado: bibliotecaDeJogos) {
			if (jogoProcurado.getNomeJogo().equals(nomeDoJogo)) {
				return jogoProcurado;
			}
		}
		return null;
	}
	
	protected void adicionaX2p(int qtd) {
		x2p += qtd;
	}

	public int getSaldo() {
		return saldo;
	}

	public String getNomeLogin() {
		return nomeLogin;
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public HashSet<Jogo> getBibliotecaDeJogos() {
		return bibliotecaDeJogos;
	}

	public void setBibliotecaDeJogos(HashSet<Jogo> bibliotecaDeJogos) {
		this.bibliotecaDeJogos = bibliotecaDeJogos;
	}
	
	public int getX2p() {
		return x2p;
	}

	public void setX2p(int x2p) {
		this.x2p = x2p;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
		
	}
	
	public void setStatusDoUsuarioVeterano() {
		this.statusDoUsuario = new Veterano();
	}
	
	public void setStatusDoUsuarioNoob() {
		this.statusDoUsuario = new Noob();
	}
	
	
	public String getTipoDeUsuario() {
		return statusDoUsuario.tipoDeUsuario();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeLogin == null) ? 0 : nomeLogin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nomeLogin == null) {
			if (other.nomeLogin != null)
				return false;
		} else if (!nomeLogin.equalsIgnoreCase(other.nomeLogin))
			return false;
		return true;
	}

	public double getTotalGasto() {
		return totalGasto;
	}

	public void setTotalGasto(double valor) {
		this.totalGasto = valor;
	}
	
	@Override
	public String toString() {
		return "Jogador " + statusDoUsuario.tipoDeUsuario() + ": " + getNomeLogin() + FIM_DE_LINHA + getNomeUsuario() + " - " + 
				getX2p() + " x2p" + FIM_DE_LINHA + "Lista de jogos: " + FIM_DE_LINHA + bibliotecaDeJogos.toString() +
				FIM_DE_LINHA + "Total de preco dos jogos: R$ " + getTotalGasto() + FIM_DE_LINHA + "--------------------------------------------";
		
	}

}