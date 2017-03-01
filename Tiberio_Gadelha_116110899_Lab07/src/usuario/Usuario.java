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
	 * O construtor cria um novo Usu�rio.
	 * @param nomeUsuario O nome do usu�rio
	 * @param nomeLogin O login do usu�rio
	 * @author Tib�rio
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
	 * Credita a conta do usu�rio.
	 * @author Tib�rio
	 */
	
	public void adicionaCredito(int valor) {
		saldo += valor;
	}
	
	/**
	 * O m�todo serve para o usu�rio comprar um jogo.
	 * @param jogo Jogo que vai ser comprado.
	 * @return
	 * Retorna se o jogo foi ao n�o comprado com sucesso.
	 */
	
	public boolean compraJogo(Jogo jogo) {
		if(!(bibliotecaDeJogos.contains(jogo))) {
			if(saldo >= jogo.getPreco() * statusDoUsuario.desconto()) {
				bibliotecaDeJogos.add(jogo);
				saldo -= jogo.getPreco() * statusDoUsuario.desconto();
				x2p += jogo.getPreco() * statusDoUsuario.x2pExtra();
				return true;
				
			} 
		}
			return false;
	}
	
	/**
	 * O m�todo vai regristrar a jogada de um usu�rio, mas antes vai ser verificado se ele possui o jogo. Se ele possuir,
	 * a quantidade de x2p ser� acrescentada na conta.
	 * @param nomeDoJogo O nome do jogo
	 * @param score A pontua��o atingida
	 * @param zerou Verifica se zerou ou n�o
	 * @throws Exception
	 * @author Tib�rio
	 */

	public void recompensar(String nomeDoJogo, int score, boolean zerou) throws Exception {
		Jogo jogo = procuraJogo(nomeDoJogo);
		if(jogo != null) {
			x2p += statusDoUsuario.recompensar(jogo);
			x2p += jogo.registraJogada(score, zerou);
		} else {
			throw new Exception("O usuario nao possui o jogo");
		}
	}
	
	public void punir(String nomeDoJogo, int score, boolean zerou) throws Exception {
		Jogo jogo = procuraJogo(nomeDoJogo);
		if(jogo != null) {
			x2p -= statusDoUsuario.punir(jogo);
			x2p += jogo.registraJogada(score, zerou);
		} else {
			throw new Exception("O usuario nao possui o jogo");
		}
		
	}
	
	
	/**
	 * O m�todo procura um jogo na biblioteca do usu�rio.
	 * @param nomeDoJogo Nome do jogo a ser procurado
	 * @return
	 * Se o usu�rio tiver esse jogo, o mesmo ser� retornado. Se n�o, null ser� retornado.
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
		return getNomeLogin() + FIM_DE_LINHA + getNomeUsuario() +" - Jogador " + statusDoUsuario.tipoDeUsuario() + FIM_DE_LINHA + "Lista de Jogos: "+ FIM_DE_LINHA +
				bibliotecaDeJogos.toString() + FIM_DE_LINHA + "Total de preco dos jogos: R$ " + getTotalGasto() + 
					FIM_DE_LINHA + "--------------------------------------------";
	}

}