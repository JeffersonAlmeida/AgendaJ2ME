/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import excecoes.CellPhoneException;
import excecoes.DigitosException;
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
    
    public void validarDigitos()throws DigitosException{
        String fone = this.contato.getFone();
        String cel = this.contato.getCelular();
        
        char[] caracteres = fone.toCharArray();
     
            
    }
    
    public void validarEmail(){
        
    }
            
    
}
