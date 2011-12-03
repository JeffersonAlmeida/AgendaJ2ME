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
public class SobrenomeException extends Exception{
  
    private Contato contato;

    public SobrenomeException(){
        super("SobreNome Exception!");
    }
    
    public SobrenomeException(Contato c) {
        super("SobreNome Exception!");
        this.contato = c;
    }
    
    public void imprimeMsg(){
        System.out.println("Não foi digitado o sobrenome de : " + this.contato.getNome());
    }
    
    
}
