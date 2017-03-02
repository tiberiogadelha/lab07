package factory;
import usuario.Usuario;

/**
 * 
 * @author Tiberio Gadelha
 *
 */

public class FactoryDeUsuario {
	
	/**
	 * Trata-se de um criador de objeto do tipo Usuario. Vai sempre criar um usuario do tipo Noob.
	 * @param nomeUsuario O nome do usuario.
	 * @param login O login do usuario.
	 * @return Retorna o objeto Usuario criado.
	 * @throws Exception
	 */
	public Usuario criaUsuario(String nomeUsuario, String login) throws Exception {
		Usuario usuario = new Usuario(nomeUsuario, login);
		return usuario;
		
	}
	

}
