package p2cg;

public class Veterano extends Usuario{

	public Veterano(String nomeUsuario, String nomeLogin) throws Exception {
		super(nomeUsuario, nomeLogin);
		setX2p(1000);
	}
	
	/**
	 * O método compra um jogo com 20% de desconto. Se o usuário tiver saldo suficiente, o jogo é adicionado
	 * à sua biblioteca, ele ganha x2p e true é retornado. Se não, retorna false.
	 * @author Tibério
	 */
	
	@Override
	public boolean compraJogo(Jogo jogo) {
		if(saldo >= (jogo.getPreco()*0.8)) {
			setTotalGasto(getTotalGasto() + jogo.getPreco()*0.8);
			saldo -= jogo.getPreco()*0.8;
			bibliotecaDeJogos.add(jogo);
			adicionaX2p(jogo.getPreco() * 15);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return getNomeLogin() + FIM_DE_LINHA + getNomeUsuario() +" - Jogador Veterano" + FIM_DE_LINHA + "Lista de Jogos: "+ FIM_DE_LINHA +
				bibliotecaDeJogos.toString() + FIM_DE_LINHA + "Total de preco dos jogos: R$ " + getTotalGasto() + 
					FIM_DE_LINHA + "--------------------------------------------";
	}	

}