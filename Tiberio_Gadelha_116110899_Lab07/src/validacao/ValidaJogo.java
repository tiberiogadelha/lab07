package validacao;

public class ValidaJogo {
	
	public void validaString(String nome) throws Exception{
		if (nome.trim().equals("") || nome == null) {
			throw new Exception("A string nao pode ser nula ou vazia.");
		}
	}
	
	public void validaPreco(int preco) throws Exception {
		if(preco < 0) {
			throw new Exception("O preco nao pode ser menor que zero.");
		}
	}
	
	public void validaTipo(String tipo) throws Exception {
		if(!(tipo.equalsIgnoreCase("luta") || tipo.equalsIgnoreCase("plataforma") || tipo.equalsIgnoreCase("rpg"))){
			throw new Exception("Tipo de jogo invalido.");
		}
	}
	

}
