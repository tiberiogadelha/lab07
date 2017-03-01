package factory;
import usuario.Usuario;

public class FactoryDeUsuario {
	
	public Usuario criaUsuario(String nomeUsuario, String login) throws Exception {
		Usuario usuario = new Usuario(nomeUsuario, login);
		return usuario;
		
	}
	

}
