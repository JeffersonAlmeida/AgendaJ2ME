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
            banco.writeRecord(c.getNome()+";"+c.getTwitter()+";"+c.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            System.out.println("Contato Inserido: ");
            c.imprimeContato();
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

    public void alterarContato(Contato c, int posicao) {
        Banco banco = Banco.getInstance();
        try {
            
            banco.openRecStore();
            // do something
            RecordStore recordStore = banco.getRecordStore();
            
            String contato = c.getNome()+";"+c.getTwitter();
            byte[] contatoByte = contato.getBytes();
            
            
            
            recordStore.setRecord(posicao, contatoByte, 0, contatoByte.length);
            
           
            
            System.out.println("Acho que alterou o contato");
                    
            /*
            * 
            *  recordId - the ID of the record to use in this operation
            newData - the new data to store in the record
            offset - the index into the data buffer of the first relevant byte for this record
            numBytes - the number of bytes of the data buffer to use for this record

            */            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            banco.closeRecStore();
        }    
    }

    public void excluirContato(Contato c) {
        
        System.out.println("Vamos Exluir o contato :: " + "ID/posicao : " + c.getId());
        c.imprimeContato();
        Banco banco = Banco.getInstance();
        try {
            banco.openRecStore();
            // do something            
            RecordStore recordStore = banco.getRecordStore();
            recordStore.deleteRecord(c.getId());            
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
            System.out.println("\nListar Todos os Contatos :: \n");
            while ( enum.hasNextElement()){
                //armazena o próximo registro em um String
                String contatoInteiro = new String(enum.nextRecord());
                String[] contatoQuebrado = this.split(contatoInteiro);                
                Contato c = new Contato(contatoQuebrado[0], contatoQuebrado[1]);    
                c.setId(Integer.parseInt(contatoQuebrado[2]));
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
                        }
                }
                return result;     
        }
  
    
}
