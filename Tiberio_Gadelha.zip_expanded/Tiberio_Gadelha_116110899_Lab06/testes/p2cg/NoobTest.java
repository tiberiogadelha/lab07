package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NoobTest {
	
	public Usuario user;
	
	@Before
	public void setUp() throws Exception {
		user = new Noob("Tiberio","tiberio.gadelha");
	}
	
	@Test
	public void testConstrutor() {
		assertEquals("Tiberio", user.getNomeUsuario());
		assertEquals("tiberio.gadelha", user.getNomeLogin());
		assertEquals(0, user.getSaldo(), 0.00001);
		assertEquals(0, user.getX2p());
	}
	
	@Test 
	public void testCreditoInvalido() {
		try {
			user.creditaConta(-20000);
		} catch (Exception e) {
			assertEquals("Nao pode colocar uma quantidade negativa de credito", e.getMessage());
		}
		
	}
	
	@Test
	public void testCreditaConta() throws Exception {
		user.creditaConta(100);
		assertEquals(100, user.getSaldo(), 0.0001);
		
	}

	@Test
	public void testCompraJogo() throws Exception {
		Jogo jogo = new RPG("Dota", 200);
		Jogo jogo2 = new Luta("The King of Fight", 1000);

		user.creditaConta(500);
		assertEquals(0, user.getX2p());
		assertFalse(user.compraJogo(jogo2));
		assertEquals(500, user.getSaldo());
		assertTrue(user.compraJogo(jogo));
		assertEquals(320, user.getSaldo());
		assertEquals(2000, user.getX2p());
		
	}
	
	@Test
	public void registraJogadaRPG() throws Exception {
		Jogo jogo = new RPG("MOB", 100);
		user.creditaConta(500);
		user.compraJogo(jogo);
		user.registraJogada("MOB", 250, true);
		assertEquals(1010, user.getX2p());
		user.registraJogada("MOB", 50, false);
		assertEquals(1020, user.getX2p());
	}
	
	@Test
	public void registraJogadaPlataforma() throws Exception {
		Jogo jogo = new Plataforma("WAR", 100);
		user.creditaConta(500);
		user.compraJogo(jogo);
		user.registraJogada("WAR", 500, false);
		assertEquals(1000, user.getX2p());
		user.registraJogada("WAR", 1500, true);
		assertEquals(1020, user.getX2p());
	}

	@Test
	public void registraJogadaLuta() throws Exception {
		Jogo jogo = new Luta("Mortal", 100);
		user.creditaConta(500);
		user.compraJogo(jogo);
		user.registraJogada("Mortal", 5000, false);
		assertEquals(1005, user.getX2p());
		user.registraJogada("Mortal", 2500, false);
		assertEquals(1005, user.getX2p());
	}
	
}