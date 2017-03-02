package p2cg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.Jogo;
import loja.LojaController;
import usuario.Usuario;

public class FacadeTest {
	
	public LojaController loja;
	
	@Before
	public void setUp() {
		loja = new LojaController();
	}
	
	@Test
	public void testCriaUsuario() throws Exception {
		Usuario user = loja.criaUsuario("Tiberio Gadelha", "tib", "noob");
		assertEquals("Tiberio Gadelha", user.getNomeUsuario());
		assertEquals("tib", user.getNomeLogin());
		assertEquals("noob", user.getTipoDeUsuario());
		assertEquals(0, user.getSaldo());
		assertEquals(0, user.getX2p());
	}
	
	@Test
	public void testCriaUsuarioInvalido() {
		try {
			loja.criaUsuario(null, "kk", "noob");
		} catch(Exception e) {
			assertEquals("O nome do usuario nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		try {
			loja.criaUsuario("TIberio", "", "noob");
		} catch(Exception e) {
			assertEquals("O nome do usuario nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		try {
			loja.criaUsuario("tiberio", "tib", null);
		} catch(Exception e) {
			assertEquals("A experiencia nao pode ser nula ou vazia", e.getMessage());
		}
	}

	@Test
	public void testAdicionaUsuario() throws Exception {
		assertEquals(null, loja.procuraUsuario("tib"));
		loja.adicionaUsuario("Tiberio Gadelha", "tib", "noob");
		assertEquals(loja.criaUsuario("TIberio Gadelha", "tib", "noob"), loja.procuraUsuario("tib"));
		
	}
	
	@Test
	public void testAdicionaUsuarioCadastrado() throws Exception {
		loja.adicionaUsuario("Tiberio Gadelha", "tib", "noob");
		try {
			loja.adicionaUsuario("Tiberio Gadelha", "tib", "noob");
		} catch(Exception e) {
			assertEquals("O nome de login ja esta sendo utilizado. Escolha outro, por favor.", e.getMessage());
		}
	}
	
	@Test
	public void testCriaJogo() throws Exception {
		Jogo jogo = loja.criaJogo("Mortal", 100, "luta", "online cooperativo");
		assertEquals("Mortal", jogo.getNomeJogo());
		assertEquals(100, jogo.getPreco());
		assertEquals(0, jogo.getQtdJogado());
		assertEquals(0, jogo.getQtdZerado());
	}
	
	@Test
	public void testCriaJogoInvalido() {
		try {
			loja.criaJogo("", 200, "rpg", "online");
		} catch(Exception e) {
			assertEquals("A string nao pode ser nula ou vazia.", e.getMessage());
		}
		
		try {
			loja.criaJogo("Mortal", -20, "luta", "offline");
				
			} catch(Exception e) {
				assertEquals("O preco nao pode ser menor que zero.", e.getMessage());
			}
		
		try {
			loja.criaJogo("Mortal", 200, "transporte", "online");
		} catch(Exception e) {
			assertEquals("Tipo de jogo invalido.", e.getMessage());
		}
		
		try {
			loja.criaJogo("Mortal", 200, "luta", "");
		} catch(Exception e) {
			assertEquals("A string nao pode ser nula ou vazia.", e.getMessage());
		}
	}
	
	@Test
	public void testVendeJogo() throws Exception {
		loja.adicionaUsuario("Tiberio Gadelha", "tib", "noob");
		loja.adicionaCredito("tib", 500);
		loja.vendeJogo("Mortal", 200, "online cooperativo", "rpg", "tib");
		Jogo jogo = loja.criaJogo("Mortal", 200, "rpg", "online cooperativo");
		assertTrue(loja.procuraUsuario("tib").getBibliotecaDeJogos().contains(jogo));
		assertEquals(320, loja.confereCredito("tib"), 0.0001);
		assertEquals(2000, loja.getX2p("tib"));
	}
	
	@Test
	public void testVendeJogoSemSaldo() throws Exception {
		loja.adicionaUsuario("Tiberio Gadelha", "tib", "noob");
		assertFalse(loja.vendeJogo("Mortal", 200, "online cooperativo", "rpg", "tib"));
	}
	
	@Test
	public void testAdicionaCredito() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		loja.adicionaCredito("tiberio.gadelha", 100);
		assertEquals(100, loja.confereCredito("tiberio.gadelha"), 0.00001);
		loja.adicionaCredito("tiberio.gadelha", 350);
		assertEquals(450, loja.confereCredito("tiberio.gadelha"), 0.00001);
	}
	
	@Test
	public void testCreditaContaInvalida() {
		try {
			loja.adicionaCredito("Joaozin", 20);
		} catch (Exception e) {
			assertEquals("O usuario ainda nao foi cadastrado.", e.getMessage());
		}
	}
	
	@Test
	public void testCreditaValorInvalido() {
		try {
			loja.adicionaCredito("joaozin", -2);
		} catch (Exception e) {
			assertEquals("O valor nao pode ser negativo.", e.getMessage());
		}
	}


	@Test
	public void testUpgrade() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		loja.adicionaCredito("tiberio.gadelha", 1000);
		loja.vendeJogo("Mortal", 200, "online", "luta", "tiberio.gadelha");
		assertEquals(2000, loja.getX2p("tiberio.gadelha"));
		loja.upgrade("tiberio.gadelha");
		assertEquals("veterano", loja.procuraUsuario("tiberio.gadelha").getTipoDeUsuario());
		
	}
	
	@Test
	public void testUpgradeUsuarioVeterano() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		loja.procuraUsuario("tiberio.gadelha").setStatusDoUsuarioVeterano();
		loja.procuraUsuario("tiberio.gadelha").setX2p(2000);
		try {
			loja.upgrade("tiberio.gadelha");
		} catch(Exception e) {
			assertEquals("O usuario ja e um veterano.", e.getMessage());
		}
	}
	
	@Test
	public void testUpgradeUsuarioSemX2p() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		try {
			loja.upgrade("tiberio.gadelha");
		} catch(Exception e) {
			assertEquals("O usuario nao possui a quantidade minima de x2p.", e.getMessage());
		}
	}
	
	@Test
	public void testDowngrade() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		loja.procuraUsuario("tiberio.gadelha").setStatusDoUsuarioVeterano();
		assertEquals("veterano", loja.procuraUsuario("tiberio.gadelha").getTipoDeUsuario());
		loja.procuraUsuario("tiberio.gadelha").setX2p(50);
		loja.downgrade("tiberio.gadelha");
		assertEquals("noob", loja.procuraUsuario("tiberio.gadelha").getTipoDeUsuario());
	}
	
	@Test
	public void testDowngradeUsuarioNoob() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		try {
			loja.downgrade("tiberio.gadelha"); 
		} catch(Exception e) {
			assertEquals("O usuario ja e um noob.", e.getMessage());
		}
		
	}
	
	@Test
	public void testDowngradeUsuarioComMuitaX2p() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		loja.procuraUsuario("tiberio.gadelha").setStatusDoUsuarioVeterano();
		loja.procuraUsuario("tiberio.gadelha").setX2p(2000);
		try {
			loja.downgrade("tiberio.gadelha");
		} catch(Exception e) {
			assertEquals("O usuario esta acima de 1000 de x2p.", e.getMessage());
		}
	}
	
	@Test
	public void testToString() throws Exception {
		loja.adicionaUsuario("Tiberio", "tiberio.gadelha", "noob");
		loja.adicionaCredito("tiberio.gadelha", 500);
		loja.vendeJogo("Mortal", 50, "online", "luta", "tiberio.gadelha");
		loja.procuraUsuario("tiberio.gadelha").setStatusDoUsuarioVeterano();
		loja.procuraUsuario("tiberio.gadelha").setX2p(2000);
		loja.adicionaUsuario("Caio", "cc", "noob");
		loja.adicionaCredito("cc", 250);
		loja.vendeJogo("LOL", 25, "online", "rpg", "cc");
		
		loja.imprimeDados();
		
	}

}
