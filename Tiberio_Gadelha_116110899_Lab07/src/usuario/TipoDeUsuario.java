package usuario;

import jogo.Jogo;

public interface TipoDeUsuario {
	
	public int recompensar(Jogo jogo);
	public int punir(Jogo jogo);
	public double desconto();
	public String tipoDeUsuario();
	public int x2pExtra();
	
	

}
