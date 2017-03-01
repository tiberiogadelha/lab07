package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.Jogo;
import jogo.Plataforma;

public class PlataformaTest {
	
	public Jogo jogo;
	
	@Before
	public void setUp() {
		jogo = new Plataforma("ddTank", 50);
	}
	
	@Test
	public void testConstrutor() {
		assertEquals("ddTank",jogo.getNomeJogo());
		assertEquals(50, jogo.getPreco());
	}

	@Test
	public void testRegistraJogada() throws Exception {
		assertEquals(0, jogo.registraJogada(1000, false));
		assertEquals(1, jogo.getQtdJogado());
		assertEquals(0, jogo.getQtdZerado());
		assertEquals(20, jogo.registraJogada(5000, true));
		assertEquals(2, jogo.getQtdJogado());
		assertEquals(1, jogo.getQtdZerado());
		assertEquals(5000, jogo.getBestScore());
		System.out.println(jogo.toString());
	}

	

}
