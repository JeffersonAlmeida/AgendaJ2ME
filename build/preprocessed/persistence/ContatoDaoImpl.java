/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import model.Contato;

/**
 *
 * @author Jefferson
 */
public class ContatoDaoImpl implements ContatoDao {

     
    public void incluirContato(Contato c) {
        Banco banco = Banco.getInstance();
        try {
            banco.openRecStore();
            banco.writeRecord(c.getNome()+";"+c.getTwitter());
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
        }        
    }

    public void pesquisarContato(Contato c) {
        Banco banco = Banco.getInstance();
        try {
            banco.openRecStore();           
            RecordStore recordStore = banco.getRecordStore();
            RecordEnumeration enum = recordStore.enumerateRecords(null, null, false);
            while ( enum.hasNextElement()) {
                //armazena o próximo registro em um String
                String contatoString = new String(enum.nextRecord());
                System.out.println("Contato:: " + contatoString);
                
            }            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
        }    
    }

    public void alterarContato(Contato c) {
        Banco banco = Banco.getInstance();
        try {
            banco.openRecStore();
            // do something
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
        }    
    }

    public void excluirContato(Contato c) {
        Banco banco = Banco.getInstance();
        try {
            banco.openRecStore();
            // do something
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
        }    
    }

    public Vector listarTodos() {
        Banco banco = Banco.getInstance();
        Vector contatos = new Vector(); // Vetor de Strings por enquanto
        
        try {
            banco.openRecStore();
            RecordStore recordStore = banco.getRecordStore();
            RecordEnumeration enum = recordStore.enumerateRecords(null, null, false);
            while ( enum.hasNextElement()) {
                //armazena o próximo registro em um String
                String contato = new String(enum.nextRecord());
                contatos.addElement(contato);
                System.out.println(contato);               
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
            return contatos;
        }  
    }
    
    
  
    
}
