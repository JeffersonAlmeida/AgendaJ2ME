/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excecoes;

import model.Contato;

/**
 *
 * @author Jefferson
 */
public class HomePhoneException extends Exception {
    private Contato contato;
   
    public HomePhoneException(Contato contato) {
        super("Home Phone Exception");
        this.contato = contato;
    }
    
    public void imprimeMsg(){
        System.out.println(" O Telefone deve ter apenas 8 digitos");
    }
    
}
