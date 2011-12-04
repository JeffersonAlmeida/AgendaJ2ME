package test.validarContato;

import static org.junit.Assert.*;

import model.Contato;

import org.junit.Test;

import excecoes.CellPhoneException;
import excecoes.EmailException;
import excecoes.HomePhoneException;
import excecoes.SobrenomeException;

import persistence.ValidarContato;

public class TestValidarContato {
	
	private Contato contato;

	@Test
	public void testNome() throws SobrenomeException {
		Contato contato = new Contato("Jefferson r", "12345678", "87654321", "jefferson@comp.com");
		ValidarContato validarContato = ValidarContato.getInstance(contato);
		
		assertEquals(true, validarContato.validarNomes());
	}
	
	@Test
	public void testHomePhone() throws SobrenomeException, HomePhoneException {
		Contato contato = new Contato("Jefferson Rodrigues", "12", "87654321", "jefferson@comp.com");
		ValidarContato validarContato = ValidarContato.getInstance(contato);
		
		assertEquals(true, validarContato.validarFone());
	}

	@Test
	public void testHomeCelular() throws SobrenomeException, HomePhoneException, CellPhoneException {
		Contato contato = new Contato("Jefferson Rodrigues", "12", "8765445465465321", "jefferson@comp.com");
		ValidarContato validarContato = ValidarContato.getInstance(contato);
		
		assertEquals(true, validarContato.validarCelular());
	}
	
	@Test
	public void testEmail() throws SobrenomeException, HomePhoneException, CellPhoneException, EmailException {
		Contato contato = new Contato("Jefferson Rodrigues", "12", "8765445465465321", "jefferson");
		ValidarContato validarContato = ValidarContato.getInstance(contato);
		
		assertEquals(true, validarContato.validarEmail());
	}


}
