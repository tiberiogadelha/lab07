import java.util.HashSet;
import java.util.Iterator;

import jogo.Jogo;
import validacao.ValidaJogo;
import validacao.ValidaUsuario;

public class LojaController {
	private HashSet<Usuario> bancoDeUsuarios;
	private ValidaJogo validaJogo = new ValidaJogo();
	private ValidaUsuario validaUsuario = new ValidaUsuario();
	
	public LojaController() {
		bancoDeUsuarios = new HashSet<>();
	}
	
	
	public boolean vendeJogo(String nomeLogin, String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception{
		
		Usuario user = procuraUsuario(nomeLogin);
		Jogo jogo = criaJogo(nomeJogo, valor, tipo, jogabilidade);
		
		if(user != null) {
			return user.compraJogo(jogo);
		}
		throw new Exception("O usuario ainda nao foi cadastrado.");
		
	}
	
	public Jogo criaJogo(String nomeJogo, int valor, String tipo, String jogabilidade) throws Exception {
		validaJogo.validaPreco(valor);
		validaJogo.validaString(nomeJogo);
		validaJogo.validaTipo(tipo);
		
		FactoryDeJogo factoryJogo = new FactoryDeJogo();
		Jogo jogo = factoryJogo.criaJogo(nomeJogo, valor, tipo, jogabilidade);
		return jogo;
		
	}


	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		
		validaUsuario.validaNome(nomeUsuario);
		validaUsuario.validaNome(nomeLogin);
		validaUsuario.validaExperiencia(experiencia);
		
	
		
		Usuario veteranoUser = new Veterano(nomeUsuario, nomeLogin);
		Usuario noobUser = new Noob(nomeUsuario, nomeLogin);
		
		if(procuraUsuario(nomeLogin) == null) {
			if(experiencia.equalsIgnoreCase("noob")) {
				bancoDeUsuarios.add(noobUser);
				return true;
			} else if(experiencia.equalsIgnoreCase("veterano")) {
				bancoDeUsuarios.add(veteranoUser);
				return true;
			} else {
				throw new Exception("Tipo de experiencia invalida.");
			}
			
		} else {
			throw new Exception("O nome de login ja esta sendo utilizado. Escolha outro, por favor.");
		}
	}
	
	
	public void creditaConta(String nomeLogin, int valor) throws Exception {
		if(valor < 0) {
			throw new Exception("O valor nao pode ser negativo.");
		}
		
		if(procuraUsuario(nomeLogin) != null) {
			procuraUsuario(nomeLogin).creditaConta(valor);
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
		
	}
	
	
	public void imprimeDados() {
		System.out.println("=== Central P2-CG ===");
		Iterator<Usuario> i = bancoDeUsuarios.iterator();
		while(i.hasNext()) {
			Usuario user = i.next();
			System.out.println(user.toString());
		}
	}
	
	
	public Usuario procuraUsuario(String nomeLogin) {
		Iterator<Usuario> i = bancoDeUsuarios.iterator();
		while(i.hasNext()) {
			Usuario usuarioProcurado = i.next();
			if (usuarioProcurado.getNomeLogin().equals(nomeLogin)) {
				return usuarioProcurado;
			}
		}
		return null;
	}
	
	
	public boolean upgradeUsuario(String nomeLogin) throws Exception {
		Usuario OldUser = procuraUsuario(nomeLogin);
		if(OldUser != null) {
			Usuario user1 = new Noob("blabla", "blabalba");
			if(OldUser.getClass().equals(user1.getClass())){
				if(OldUser.getX2p() >= 1000) {
					Usuario newUser = new Veterano(OldUser.getNomeLogin(), OldUser.getNomeLogin());
					
					newUser.setBibliotecaDeJogos(OldUser.getBibliotecaDeJogos());
					newUser.setX2p(OldUser.getX2p());
					newUser.setSaldo(OldUser.getSaldo());
					bancoDeUsuarios.remove(OldUser);
					bancoDeUsuarios.add(newUser);
					return true;
				}	
			} 
			throw new Exception("O jogador ja e um veterano ou nao possui a quantidade de x2p necessaria.");		
		} else {
			throw new Exception("O usuario ainda nao foi cadastrado.");
		}
			
		}
	
	public HashSet<Usuario> getUsuarios() {
		return bancoDeUsuarios;
	}
}