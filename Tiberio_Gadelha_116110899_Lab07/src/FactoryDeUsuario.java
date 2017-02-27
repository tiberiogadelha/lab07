
public class FactoryDeUsuario {
	
	private Usuario usuario;
	
	public Usuario criaUsuario(String nomeUsuario, String login, String tipo) throws Exception {
		return usuario = new Noob(nomeUsuario, login);
		
	}
	

}
