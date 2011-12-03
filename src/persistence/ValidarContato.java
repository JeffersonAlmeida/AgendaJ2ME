/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import excecoes.CellPhoneException;
import excecoes.EmailException;
import excecoes.HomePhoneException;
import excecoes.SobrenomeException;
import model.Contato;
import model.SplitString;

/**
 *
 * @author Jefferson
 */
public class ValidarContato {
    
    private Contato contato;
    
    public ValidarContato(Contato contato){
        this.contato = contato;
    }
    
    // jefferson Rodrigues -> valido
    // jefferson -> invalido.
    public void validarNomes()throws SobrenomeException {
        
        String[] nomes =  SplitString.getInstance().split(this.contato.getNome());
        if(!(nomes.length >1)){
            throw new SobrenomeException(this.contato);
        }              
    }
    
    // barrreto -> invalido
    // barreto -> valido
    public void validarLetras(){
        String nome = this.contato.getNome();
        char[] caracteres = nome.toCharArray();
    }
    
    public void validarFone()throws  HomePhoneException{
        String fone = this.contato.getFone();
        if(fone.length() != 8){
            throw new HomePhoneException(this.contato);
        }
    }
    
    public void validarCelular() throws CellPhoneException{
        String celular = this.contato.getCelular();
        if(celular.length() != 8){
            throw new CellPhoneException(this.contato);
        }
    }
    
    // jefferson@comp.ufu.br    
    // jefferson   comp.com
    public void validarEmail() throws EmailException{
        String email = this.contato.getEmail();        
        String[] palavras = SplitString.getInstance().split(email);
        String[] dominio =  SplitString.getInstance().split(palavras[1]);        
        if(!((palavras.length==2)&&(dominio.length>=2))){
            throw new EmailException(this.contato);
        }        
    }
            
    
}
