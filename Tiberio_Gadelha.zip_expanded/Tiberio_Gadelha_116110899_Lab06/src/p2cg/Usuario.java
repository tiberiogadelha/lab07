package p2cg;

import java.util.HashSet;

public abstract class Usuario {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private String nomeUsuario;
	private String nomeLogin;
	protected int saldo;
	protected HashSet<Jogo> bibliotecaDeJogos;
	private int x2p;
	private double totalGasto;
	
	/**
	 * O construtor cria um novo Usuário.
	 * @param nomeUsuario O nome do usuário
	 * @param nomeLogin O login do usuário
	 * @author Tibério
	 */
	
	public Usuario(String nomeUsuario, String nomeLogin) {
		this.nomeUsuario = nomeUsuario;
		this.nomeLogin= nomeLogin;
		saldo = 0;
		setX2p(0);
		setTotalGasto(0);
		bibliotecaDeJogos = new HashSet<>();
		
	}
	
	/**
	 * Credita a conta do usuário.
	 * @author Tibério
	 */
	
	public void creditaConta(int valor) {
		saldo += valor;
	}
	
	/**
	 * O método serve para o usuário comprar um jogo.
	 * @param jogo Jogo que vai ser comprado.
	 * @return
	 * Retorna se o jogo foi ao não comprado com sucesso.
	 */
	
	public abstract boolean compraJogo(Jogo jogo);
	
	/**
	 * O método vai regristrar a jogada de um usuário, mas antes vai ser verificado se ele possui o jogo. Se ele possuir,
	 * a quantidade de x2p será acrescentada na conta.
	 * @param nomeDoJogo O nome do jogo
	 * @param score A pontuação atingida
	 * @param zerou Verifica se zerou ou não
	 * @throws Exception
	 * @author Tibério
	 */
	
	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws Exception {
		Jogo jogo = procuraJogo(nomeDoJogo);
		if (!(jogo == null)) {
			x2p += procuraJogo(nomeDoJogo).registraJogada(score, zerou);
		}
		
	}
	
	/**
	 * O método procura um jogo na biblioteca do usuário.
	 * @param nomeDoJogo Nome do jogo a ser procurado
	 * @return
	 * Se o usuário tiver esse jogo, o mesmo será retornado. Se não, null será retornado.
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

}