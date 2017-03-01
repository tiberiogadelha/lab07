package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LojaFachadaTest {
	
	public Loja loja;
	
	@Before
	public void setUp() {
		loja = new Loja();
	}

	@Test
	public void testAdicionaUsuario() throws Exception {
		Usuario user = new Veterano("Tiberio", "tiberio.gadelha");
		assertTrue(loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "Veterano"));
		assertTrue(loja.getUsuarios().contains(user));
		
	}
	
	@Test
	public void testAdicionaUsuarioInvalido() {
		try {
			loja.adicionaUsuario(null, "tiberio.gadelha", "Veterano");
		} catch (Exception e) {
			assertEquals("O nome do usuario nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		try {
			loja.adicionaUsuario("Tibério", "", "Noob");
		} catch (Exception e) {
			assertEquals("O nome do login nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		try {
			loja.adicionaUsuario("Tibério", "ti.gadelha", "");
		} catch (Exception e) {
			assertEquals("A experiencia nao pode ser nula ou vazia", e.getMessage());
		}
		
	}
	
	@Test
	public void testAdicionaUsuarioRepetido() throws Exception {
		assertTrue(loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "Veterano"));
		try {
			loja.adicionaUsuario("Tib", "TIBERIO.gadelha", "Noob");
		} catch (Exception e) {
			assertEquals("O nome de login ja esta sendo utilizado. Escolha outro, por favor.", e.getMessage());
		}
		
	}
	
	@Test
	public void testProcuraUsuario() throws Exception {
		assertEquals(null, loja.procuraUsuario("tiberio.gadelha"));
		Usuario user = new Veterano("Tibério", "tiberio.gadelha");
		loja.adicionaUsuario("Tibério", "tiberio.gadelha", "Veterano");
		assertEquals(user, loja.procuraUsuario("tiberio.gadelha"));
	}

	@Test
	public void testCreditaConta() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "Veterano");
		loja.creditaConta("tiberio.gadelha", 100);
		assertEquals(100, loja.procuraUsuario("tiberio.gadelha").getSaldo(), 0.00001);
		loja.creditaConta("tiberio.gadelha", 350);
		assertEquals(450, loja.procuraUsuario("tiberio.gadelha").getSaldo(), 0.00001);
	}
	
	@Test
	public void testCreditaContaInvalida() {
		try {
			loja.creditaConta("Joaozin", 20);
		} catch (Exception e) {
			assertEquals("O usuario ainda nao foi cadastrado.", e.getMessage());
		}
	}
	
	@Test
	public void testCreditaValorInvalido() {
		try {
			loja.creditaConta("joaozin", -2);
		} catch (Exception e) {
			assertEquals("O valor nao pode ser negativo.", e.getMessage());
		}
	}
	
	
	@Test
	public void testVendeJogo() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "Veterano");
		Jogo jogo = new RPG("BOB", 250);
		loja.creditaConta("tiberio.gadelha", 100);
		assertFalse(loja.vendeJogo("tiberio.gadelha", jogo));
		loja.creditaConta("tiberio.gadelha", 200);
		assertTrue(loja.vendeJogo("tiberio.gadelha", jogo));
		assertEquals(200, loja.procuraUsuario("tiberio.gadelha").getTotalGasto(), 0.00001);
		assertEquals(100, loja.procuraUsuario("tiberio.gadelha").getSaldo());
		assertEquals(4750, loja.procuraUsuario("tiberio.gadelha").getX2p());
	}
	
	@Test
	public void testVendeJogoInvalido() {
		Jogo jogo = new RPG("BOB", 250);
		try {
			loja.vendeJogo("tiberio.gadelha", jogo);
		} catch (Exception e) {
			assertEquals("O usuario ainda nao foi cadastrado.", e.getMessage());
		}
	}
	
	@Test
	public void testImprimeDados() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "Veterano");
		Jogo jogo = new RPG("BOB", 100);
		Jogo jogo2 = new Luta("Mortal Kombat", 200);
		loja.creditaConta("tiberio.gadelha", 500);
		loja.vendeJogo("tiberio.gadelha", jogo);
		loja.vendeJogo("tiberio.gadelha", jogo2);
		loja.imprimeDados();
	}
	
	@Test
	public void testUpgradeUsuario() throws Exception {
		loja.adicionaUsuario("Tibério", "tiberio.gadelha", "noob");
		loja.procuraUsuario("tiberio.gadelha").adicionaX2p(2000);
		assertTrue(loja.upgradeUsuario("tiberio.gadelha"));
		Usuario veteranoTest = new Veterano("blabla","blablabal");
		assertTrue(veteranoTest.getClass().equals(loja.procuraUsuario("tiberio.gadelha").getClass()));
	}
	
	@Test
	public void testUpgradeUsuarioInvalido() {
		try {
			loja.upgradeUsuario("ti.gadelha");
		} catch (Exception e) {
			assertEquals("O usuario ainda nao foi cadastrado.", e.getMessage());
		}
	}
	
	@Test
	public void testUpgradeUsuarioSemX2p() throws Exception {
		loja.adicionaUsuario("Tibério", "tiberio.gadelha", "noob");
		try {
			loja.upgradeUsuario("tiberio.gadelha");
		} catch(Exception e) {
			assertEquals("O jogador ja e um veterano ou nao possui a quantidade de x2p necessaria.", e.getMessage());
		}
	}
	
	@Test
	public void testUpgradeUsuarioVeterano() throws Exception {
		loja.adicionaUsuario("Tibério", "tiberio.gadelha", "veterano");
		try {
			loja.upgradeUsuario("tiberio.gadelha");
		} catch (Exception e) {
			assertEquals("O jogador ja e um veterano ou nao possui a quantidade de x2p necessaria.", e.getMessage());
		}
	}
}