package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VeteranoTest {
	
	public Usuario user;
	
	@Before
	public void setUp() throws Exception {
		user = new Veterano("Tiberio", "tiberio.gadelha");
	}
	
	@Test 
	public void testConstrutor() {
		assertEquals("Tiberio", user.getNomeUsuario());
		assertEquals("tiberio.gadelha", user.getNomeLogin());
		assertEquals(1000, user.getX2p());
	}
	
	@Test
	public void testCompraJogo() throws Exception {
		Jogo jogo = new RPG("Dota", 200);
		Jogo jogo2 = new Luta("The King of Fight", 1000);

		user.creditaConta(500);
		assertEquals(1000, user.getX2p());
		assertFalse(user.compraJogo(jogo2));
		assertEquals(500, user.getSaldo());
		assertTrue(user.compraJogo(jogo));
		assertEquals(340, user.getSaldo());
		assertEquals(4000, user.getX2p());
		
	} 
	
	@Test
	public void registraJogadaRPG() throws Exception {
		Jogo jogo = new RPG("MOB", 100);
		user.creditaConta(500);
		user.compraJogo(jogo);
		user.registraJogada("MOB", 250, true);
		assertEquals(2510, user.getX2p());
		user.registraJogada("MOB", 50, false);
		assertEquals(2520, user.getX2p());
	}
	
	@Test
	public void registraJogadaPlataforma() throws Exception {
		Jogo jogo = new Plataforma("WAR", 100);
		user.creditaConta(500);
		user.compraJogo(jogo);
		user.registraJogada("WAR", 500, false);
		assertEquals(2500, user.getX2p());
		user.registraJogada("WAR", 1500, true);
		assertEquals(2520, user.getX2p());
	}

	@Test
	public void registraJogadaLuta() throws Exception {
		Jogo jogo = new Luta("Mortal", 100);
		user.creditaConta(500);
		user.compraJogo(jogo);
		user.registraJogada("Mortal", 5000, false);
		assertEquals(2505, user.getX2p());
		user.registraJogada("Mortal", 2500, false);
		assertEquals(2505, user.getX2p());
	}
	
	@Test
	public void testEquals() throws Exception { 
		Usuario user2 = new Veterano("Joaozin", "joao.forte");
		Usuario user3 = new Veterano("Ti", "tiberio.gadelha");
		Usuario user4 = new Veterano("Tib", "tiBERIO.gadelha");
		assertFalse(user.equals(user2));
		assertTrue(user.equals(user3));
		assertTrue(user.equals(user4));	
	}

}