package loja;
import java.util.HashSet;
import java.util.Iterator;

import factory.FactoryDeJogo;
import factory.FactoryDeUsuario;
import jogo.Jogo;
import usuario.Usuario;
import validacao.ValidaJogo;
import validacao.ValidaUsuario;

public class LojaController {
	private HashSet<Usuario> bancoDeUsuarios;
	private ValidaJogo validaJogo = new ValidaJogo();
	private ValidaUsuario validaUsuario = new ValidaUsuario();
	
	public LojaController() {
		bancoDeUsuarios = new HashSet<>();
	}
	
	
	public boolean vendeJogo(String nomeJogo, int valor, String jogabilidade, String tipo, String nomeLogin) throws Exception{
		
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


	public Usuario criaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		FactoryDeUsuario factoryUsuario = new FactoryDeUsuario();
		Usuario novoUsuario = factoryUsuario.criaUsuario(nomeUsuario, nomeLogin);
		
		return novoUsuario;
		
	
	}
	
	public boolean adicionaUsuario(String nomeUsuario, String nomeLogin, String experiencia) throws Exception {
		validaUsuario.validaNome(nomeUsuario);
		validaUsuario.validaNome(nomeLogin);
		validaUsuario.validaExperiencia(experiencia);
		
		Usuario novoUsuario = criaUsuario(nomeUsuario, nomeLogin, experiencia);
		
		if(procuraUsuario(nomeLogin) == null) {
			bancoDeUsuarios.add(novoUsuario);
			return true;
			
		} else {
			throw new Exception("O nome de login ja esta sendo utilizado. Escolha outro, por favor.");
		}
	}
	
	
	public void adicionaCredito(String nomeLogin, int valor) throws Exception {
		
		if(valor < 0) {
			throw new Exception("O valor nao pode ser negativo.");
		}
		
		if(procuraUsuario(nomeLogin) != null) {
			procuraUsuario(nomeLogin).adicionaCredito(valor);
		} else {
			
		}
		
	}
	
	public double confereCredito(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			return user.getSaldo();
		}
		return 0;
	}
	
	public int getX2p(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			return user.getX2p();
		}
		return 0;
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
	
	
	public void upgrade(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		if(user != null) {
			if(user.getTipoDeUsuario().equalsIgnoreCase("noob")){
				if(user.getX2p() >= 1000) {
					user.setStatusDoUsuarioVeterano();
				}	
			} 
		}
			
		}
	
	public void downgrade(String nomeLogin) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			if(user.getTipoDeUsuario().equalsIgnoreCase("veterano")) {
				if(user.getX2p() <= 1000) {
					user.setStatusDoUsuarioNoob();
				}
			}
		}
			
	}
	
	public HashSet<Usuario> getUsuarios() {
		return bancoDeUsuarios;
	}
	
	public void recompensar(String nomeLogin, String nomeDoJogo, int score, boolean zerou) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		
		if(user != null) {
			user.recompensar(nomeDoJogo, score, zerou);
		}

	}


	public void punir(String nomeLogin, String nomeDoJogo, int score, boolean zerou) throws Exception {
		Usuario user = procuraUsuario(nomeLogin);
		if(user != null) {
			user.punir(nomeDoJogo, score, zerou);
		}
		
	}
}