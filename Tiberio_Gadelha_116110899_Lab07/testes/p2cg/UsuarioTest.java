package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.RPG;
import usuario.Usuario;

public class UsuarioTest {

		
	public Usuario user;
	
	@Before
	public void setUp() {
		user = new Usuario("Joao Carlos", "jC1");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals("Joao Carlos", user.getNomeUsuario());
		assertEquals("jC1", user.getNomeLogin());
		assertEquals("noob", user.getTipoDeUsuario());
		assertEquals(0, user.getSaldo());
		assertEquals(0, user.getX2p());

	}
	
	@Test
	public void testAdicionaCredito() {
		assertEquals(0, user.getSaldo());
		user.adicionaCredito(100);
		assertEquals(100, user.getSaldo());
		user.adicionaCredito(200);
		user.adicionaCredito(10);
		assertEquals(310, user.getSaldo());
		
	}
	
	@Test
	public void testCompraJogoNoob() {
		
		Jogo jogo = new RPG("MOB", 100);
		assertFalse(user.compraJogo(jogo));
		user.adicionaCredito(200);
		assertTrue(user.compraJogo(jogo));
		assertEquals(110, user.getSaldo());
		assertEquals(1000, user.getX2p());
		assertFalse(user.compraJogo(jogo));
	}
	
	@Test
	public void testCompraJogoVeterano() {
		Jogo jogo = new RPG("MOB", 100);
		user.setStatusDoUsuarioVeterano();
		user.adicionaCredito(200);
		assertTrue(user.compraJogo(jogo));
		assertEquals(120, user.getSaldo());
		assertEquals(1500, user.getX2p());
	}
	
	@Test
	public void testProcuraJogo() {
		Jogo jogo = new Luta("Mortal", 100);
		user.adicionaCredito(200);
		assertEquals(null, user.procuraJogo("Mortal"));
		user.compraJogo(jogo);
		assertEquals(jogo, user.procuraJogo("Mortal"));
	}
	
	
	
	@Test
	public void testRecompensaNoob() throws Exception {
		Jogo jogo = new RPG("OO", 100);
		jogo.addJogabilidades("multiplayer");
		jogo.addJogabilidades("offline");
		user.adicionaCredito(200);
		user.compraJogo(jogo);
		user.recompensar("OO", 2000, true);
		assertEquals(1050, user.getX2p());
	}
	
	@Test
	public void testRecompensaVeterano() throws Exception {
		user.setStatusDoUsuarioVeterano();
		Jogo jogo = new RPG("OO", 100);
		jogo.addJogabilidades("online");
		jogo.addJogabilidades("cooperativo");
		user.adicionaCredito(200);
		user.compraJogo(jogo);
		user.recompensar("OO", 2500, false);
		assertEquals(1540, user.getX2p());
		
	}
	
	@Test
	public void testPunirNoob() throws Exception {
		Jogo jogo = new RPG("OO", 100);
		jogo.addJogabilidades("online");
		jogo.addJogabilidades("competitivo");
		user.adicionaCredito(200);
		user.compraJogo(jogo);
		user.punir("OO", 2000, false);
		assertEquals(980, user.getX2p());
		
	}
	
	@Test
	public void testPunirVeterano() throws Exception {
		user.setStatusDoUsuarioVeterano();
		Jogo jogo = new RPG("OO", 100);
		jogo.addJogabilidades("offline");
		jogo.addJogabilidades("competitivo");
		user.adicionaCredito(200);
		user.compraJogo(jogo);
		user.punir("OO", 50, false);
		assertEquals(1470, user.getX2p());

	}
	

	@Test
	public void testSetStatusDoUsuarioVeterano() {
		assertEquals("noob", user.getTipoDeUsuario());
		user.setStatusDoUsuarioVeterano();
		assertEquals("veterano", user.getTipoDeUsuario());
	}

	@Test
	public void testSetStatusDoUsuarioNoob() {
		user.setStatusDoUsuarioVeterano();
		assertNotEquals("noob", user.getTipoDeUsuario());
		user.setStatusDoUsuarioNoob();
		assertEquals("noob", user.getTipoDeUsuario());
	}
	
	@Test
	public void testToString() throws Exception {
		user.setStatusDoUsuarioVeterano();
		user.adicionaCredito(500);
		Jogo jogo = new Luta("MKR", 200);
		Jogo jogo2 = new Plataforma("K99",50);
		jogo.addJogabilidades("cooperativo");
		jogo2.addJogabilidades("online");
		user.compraJogo(jogo2);
		user.compraJogo(jogo);
		user.recompensar("MKR", 2900, false);
		user.recompensar("K99", 600, true);
		user.punir("MKR", 20, false);
		System.out.println(user.toString());
	}


}
