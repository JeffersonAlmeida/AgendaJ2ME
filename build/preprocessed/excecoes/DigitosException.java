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
public class DigitosException extends Exception{
    
    private Contato contato;
    
    public DigitosException(Contato c) {
        super("Digitos Exception!");
        this.contato = c;
    }
    
       public void imprimeMsg(){
        System.out.println("Os Campos de telefone deve conter apenas digitos!");
    }
}
