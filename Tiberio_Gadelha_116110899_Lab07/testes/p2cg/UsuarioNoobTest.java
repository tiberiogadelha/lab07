package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.Jogo;
import jogo.RPG;
import usuario.Usuario;

public class UsuarioNoobTest {

		
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
	public void testCompraJogo() {
		Jogo jogo = new RPG("Mortal", 100);
		assertFalse(user.compraJogo(jogo));
		user.adicionaCredito(200);
		assertTrue(user.compraJogo(jogo));
		assertEquals(110, user.getSaldo());
		assertEquals(1000, user.getX2p());
		assertFalse(user.compraJogo(jogo));
	}
	
	@Test
	public void testProcuraJogo() {
		Jogo jogo = new RPG("Mortal", 100);
		user.adicionaCredito(200);
		assertEquals(null, user.procuraJogo("Mortal"));
		user.compraJogo(jogo);
		assertEquals(jogo, user.procuraJogo("Mortal"));
	}
	
	@Test
	public void testRecompensar() throws Exception {
		Jogo jogo = new RPG("OO", 100);
		jogo.addJogabilidades("multiplayer");
		jogo.addJogabilidades("offline");
		user.adicionaCredito(200);
		user.compraJogo(jogo);
		user.recompensar("OO", 2000, true);
		assertEquals(1050, user.getX2p());
		
	}
	
	/*

	@Test
	public void testRecompensar() {
		fail("Not yet implemented");
	}


	@Test
	public void testAdicionaX2p() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testSetStatusDoUsuarioVeterano() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStatusDoUsuarioNoob() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTipoDeUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalGasto() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTotalGasto() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}*/

}
