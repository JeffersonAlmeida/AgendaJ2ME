/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import model.Contato;

/**
 *
 * @author 
 * 
•	Incluir Contato
•	Alterar Contato
•	Excluir Contato
•	Pesquisar Contatos

 */
public interface ContatoDao {
    
    public void incluirContato(Contato c);
    public void pesquisarContato(Contato c);
    public void alterarContato(Contato c);
    public void excluirContato(Contato c);
    
    public void listarTodos();
    
}
