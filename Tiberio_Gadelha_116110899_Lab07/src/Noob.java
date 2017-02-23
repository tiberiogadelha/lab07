public class Noob extends Usuario{

	public Noob(String nomeUsuario, String nomeLogin) throws Exception {
		super(nomeUsuario, nomeLogin);
	}
	
	/**
	 * O usu�rio compra um jogo com 10% de desconto, mas antes � verificado se o mesmo possui saldo para a compra. Se tiver, o jogo � adicionado
	 * � biblioteca, ganha x2p e retorna true, se n�o, retorna false.
	 * @author Tib�rio
	 */
	
	@Override
	public boolean compraJogo(Jogo jogo) {
		if(procuraJogo(jogo.getNomeJogo()) == null) {
			if(saldo >= (jogo.getPreco()*0.9)) {
				setTotalGasto(getTotalGasto() + jogo.getPreco()*0.9);
				saldo -= jogo.getPreco()*0.9;
				bibliotecaDeJogos.add(jogo);
				adicionaX2p(jogo.getPreco() * 10);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return getNomeLogin() + FIM_DE_LINHA + getNomeUsuario() +" - Jogador Noob" + FIM_DE_LINHA + "Lista de Jogos: "+ FIM_DE_LINHA +
				bibliotecaDeJogos.toString() + FIM_DE_LINHA + "Total de preco dos jogos: R$ " + getTotalGasto() + 
					FIM_DE_LINHA + "--------------------------------------------";
	}

}