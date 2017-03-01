package p2cg;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import jogo.Jogo;
import jogo.RPG;

public class RPGTest {
	
	public Jogo jogo;
	
	@Before
	public void setUp() {
		jogo = new RPG("Diablo",100);
	}
	
	@Test
	public void testJogo() {
		assertEquals("Diablo", jogo.getNomeJogo());
		assertEquals(100, jogo.getPreco(),0.0001);
	}
	
	@Test
	public void testRegistraJogada() throws Exception {
		assertEquals(10, jogo.registraJogada(100, false));
		assertEquals(100,jogo.getBestScore());
		assertEquals(0, jogo.getQtdZerado());
		assertEquals(1, jogo.getQtdJogado());
		jogo.registraJogada(10000, true);
		assertEquals(1, jogo.getQtdZerado());
		assertEquals(2, jogo.getQtdJogado());
		assertEquals(10000, jogo.getBestScore());
		System.out.println(jogo.toString());
	}
	
}