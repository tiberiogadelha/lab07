package validacao;

public class ValidaUsuario {
	
	public void validaNome(String nome) throws Exception {
		if(nome == null || nome.trim().equals("")) {
			throw new Exception("O nome do usuario nao pode ser nulo ou vazio.");
		}
	}
	
	public void validaExperiencia(String experiencia) throws Exception {
		if(experiencia == null || experiencia.trim().equals("")) {
			throw new Exception("A experiencia nao pode ser nula ou vazia");
		}
		}
	}