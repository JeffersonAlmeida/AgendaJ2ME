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
        Vector contatosVector = new Vector(); // Vetor de Strings por enquanto
        
        try {
            banco.openRecStore();
            RecordStore recordStore = banco.getRecordStore();
            RecordEnumeration enum = recordStore.enumerateRecords(null, null, false);
            while ( enum.hasNextElement()) {
                //armazena o próximo registro em um String
                String contatoInteiro = new String(enum.nextRecord());
                String[] contatoQuebrado = this.split(contatoInteiro);
                
                Contato c = new Contato(contatoQuebrado[0], contatoQuebrado[1]);
                
                c.imprimeContato();
                
                contatosVector.addElement(c);
             
                             
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
            return contatosVector;
        }  
    }
    
    // metodo em para quebrar a string e retornar um vetor de strings
     private String[] split(String original) {
            
                Vector nodes = new Vector();
                String separator = ";";
                System.out.println("split start...................");
                // Parse nodes into vector
                int index = original.indexOf(separator);
                while(index>=0){
                        nodes.addElement( original.substring(0, index) );
                        original = original.substring(index+separator.length());
                        index = original.indexOf(separator);
                }
                // Get the last node
                nodes.addElement( original );
                // Create splitted string array
                String[] result = new String[ nodes.size() ];
                if( nodes.size()>0 ) {
                        for(int loop=0; loop<nodes.size(); loop++){
                            result[loop] = (String)nodes.elementAt(loop);
                            System.out.println(result[loop]);
                        }
                }
                return result;
                
        }
  
    
}
