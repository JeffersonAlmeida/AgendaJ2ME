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
public class EmailException extends Exception{

    private Contato contato;

    public EmailException(Contato contato) {
        super("Email Exception!");
        this.contato = contato;
    }
       
    public void imprimeMsg(){
        System.out.println("");
    }
}
