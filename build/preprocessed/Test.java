/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.control.FramePositioningControl;
import javax.microedition.midlet.*;
import model.Contato;
import persistence.ContatoDaoImpl;

/**
 * @author Jefferson
 */
public class Test extends MIDlet implements CommandListener {
    
    private int contadorContatos =0;
    private Display display;
    private Form inserirContatoForm, apresentaContatoForm, buscarContatoForm;
    private Command exitCommand, salvarCommand, cancelCommand;
    
    private TextField nome;
    private TextField twitter;
    
    private Image incluirImage;

    private Image alterarImage;
    
    private Image excluirImage;
    
    private Image pesquisarImage;
    
    private StringItem apresentaContato;
    
    private List menu;
    
    
    // FORMULARIO DO MENU PRINCIPAL
    public void formPrincipal() throws IOException{        
        
       this.incluirImage = Image.createImage("/imagem/incluir.png");       
       this.alterarImage = Image.createImage("/imagem/alterar.png");       
       this.excluirImage =  Image.createImage("/imagem/excluir.png");       
       this.pesquisarImage = Image.createImage("/imagem/pesquisar.png");    
       this.exitCommand = new Command("exit", Command.EXIT, 0);    
       
       this.menu = new List("Agenda Menu", Choice.IMPLICIT);
       menu.append("INCLUIR", incluirImage);
       menu.append("ALTERAR", alterarImage);
       menu.append("EXCLUIR", excluirImage);
       menu.append("PESQUISAR", pesquisarImage);      
       
       menu.addCommand(exitCommand);
       menu.setCommandListener(this);   
       Display.getDisplay(this).setCurrent(menu);    
       
    }
      // FORMULARIO DO CONTATO     NOME, TEL, EMAIL, TWITTER      

      public void formularioContato(){

            this.nome = new TextField("nome", null, 20, TextField.ANY);
            this.twitter = new TextField("twitter", null, 25, TextField.ANY);
            
            // Comandos
            
            final Command comandoVoltar = new Command("Back", Command.BACK, 0);
            final Command salvarCommand = new Command("Salvar", Command.OK,1);
            final Command exitCommand = new Command("exit", Command.EXIT, 2);
            
            this.inserirContatoForm = new Form("Inserir Contato");
            this.inserirContatoForm.append(this.nome);
            this.inserirContatoForm.append(this.twitter);
            
            this.inserirContatoForm.addCommand(comandoVoltar);   
            this.inserirContatoForm.addCommand(salvarCommand);
            this.inserirContatoForm.addCommand(exitCommand); 
            
            this.inserirContatoForm.setCommandListener(new CommandListener() { 
            public void commandAction(Command c, Displayable d) {
               if(c==comandoVoltar){
                    Display.getDisplay(getThis()).setCurrent(menu);  
               }else if(c==salvarCommand){
                    Contato contato = new Contato(nome.getString().trim(), twitter.getString().trim());
                    salvarContato(contato);                  
                    Display.getDisplay(getThis()).setCurrent(apresentaContatoForm);
               }else if(c==exitCommand){
                   destroyApp(false);
                   notifyDestroyed();
               }
            }
        });
               
    } 
      
    // FORMULARIO PARA APRESENTAR O CONTATO DEPOIS DE INSERIDO!
    public void formApresentao(Contato c){
        
        // Comandos
        final Command exitCommand = new Command("exit", Command.EXIT, 0);
        final Command comandoVoltar = new Command("Back", Command.BACK, 1);
        final Command comandoAlterarContato = new Command("Alterar", Command.ITEM, 2);   
        
        
        this.apresentaContatoForm = new Form("Contato Inserido!");
        this.apresentaContato = new StringItem("Nome: " + c.getNome() + "\nTwitter: "+c.getTwitter(), "");
        this.apresentaContatoForm.append(this.apresentaContato);
        
        this.apresentaContatoForm.addCommand(comandoVoltar);
        this.apresentaContatoForm.addCommand(comandoAlterarContato);
        this.apresentaContatoForm.addCommand(exitCommand);      
      
        this.apresentaContatoForm.setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
               if(c==comandoVoltar){
                 Display.getDisplay(getThis()).setCurrent(inserirContatoForm);  
               }else if(c==comandoAlterarContato){
                   // Implementar
               }
            }
        });
    }
    
    public MIDlet getThis(){
        return this;
    }
    
    // FORMULARIO PARA BUSCAR POR NOME
    
    public void formListarTodos(){      
        
        // Comandos
        
        final Command comandoRemover = new Command("Remover", Command.ITEM, 1);
        final Command commandoBuscar = new Command("Buscar", Command.OK, 1);    
        final Command comandoVoltar = new Command("Back", Command.BACK, 2);
        final Command comandoAlterarContato = new Command("Alterar", Command.ITEM, 3); 
       
        
        // campo usado para o usuario digitar a busca
        final TextField nomeBusca = new TextField("NOME: ", "", 15, TextField.ANY);
        
        ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
        final Vector contatos = contatoDaoImpl.listarTodos(); // vetor de contatos (Contato)
        
        // Lista - de contatos
        List contatosList = new List("Contatos", Choice.IMPLICIT);
        
        // adiciona os contatos na lista.
        int i=0;
        while(i<contatos.size()){
            Contato contato = (Contato) contatos.elementAt(i);
            contatosList.append(contato.getNome(), null);
            i++;
        }
        
        contatosList.addCommand(comandoVoltar);
        contatosList.addCommand(comandoRemover);
        contatosList.addCommand(commandoBuscar);
      
        contatosList.addCommand(comandoAlterarContato);
       
        
        Display.getDisplay(this).setCurrent(contatosList); 
                
        contatosList.setCommandListener(new CommandListener() {
        public void commandAction(Command c, Displayable d) {
                if(c==commandoBuscar){
                    String nome = nomeBusca.getString().trim();
                    ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
                    Contato contato = new Contato();
                    contato.setNome(nome);
                    System.out.println("Pesquisar Por " + contato.getNome());
                    contatoDaoImpl.pesquisarContato(contato);
                }else if(c==comandoVoltar){
                     Display.getDisplay(getThis()).setCurrent(menu);  
                }else if(c==exitCommand){
                    destroyApp(false);
                    notifyDestroyed();
                }else{
                     List down = (List)display.getCurrent();   
                     mostraContatoNaTela((Contato)contatos.elementAt(down.getSelectedIndex()));
                }
            }

        });
    }
    
  
    public void updateContato(Contato c){
         ContatoDaoImpl  contatoDaoImpl = new ContatoDaoImpl();
         contatoDaoImpl.alterarContato(c);
    }
        
    public void startApp() {    
        try {
            this.formPrincipal();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       this.display = Display.getDisplay(this);      
    }
    
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
          if (c == exitCommand) {
                destroyApp(false);
                notifyDestroyed();
          }else if (c == salvarCommand){
             
          }else if(c==cancelCommand){
              
          }else{
                 List down = (List)display.getCurrent();
                 switch(down.getSelectedIndex()) {
                       case 0: incluirContato();break;
                       case 1: alterarContato();break;
                       case 2: excluirContato();break;
                       case 3: pesquisarContato();break;
                 }
         }
    }

    public void salvarContato(Contato contato){
        setContadorContatos(getContadorContatos()+1);   
        contato.setId(getContadorContatos());
        ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
        contatoDaoImpl.incluirContato(contato);
        formApresentao(contato);
    }

    private void incluirContato() {   
        this.formularioContato();
        Display.getDisplay(this).setCurrent(inserirContatoForm);
    }

    private void alterarContato() {
        this.formListarTodos();
    }

    private void excluirContato() {
        this.formularioDeExclsao();
    }

    private void pesquisarContato() {
        this.formListarTodos();      
    }
    
    
    public void mostraContatoNaTela(final Contato contato){
        
        Form mostraContatoNaTela = new Form("# Contato # ");        
        //Comandos

        final Command exitCommand = new Command("exit", Command.EXIT, 0); 
        final Command comandoVoltar = new Command("Back", Command.BACK, 1);
        final Command comandoAlterarContato = new Command("Alterar", Command.ITEM,2); 

        mostraContatoNaTela.addCommand(comandoVoltar);
        mostraContatoNaTela.addCommand(comandoAlterarContato);
        mostraContatoNaTela.addCommand(exitCommand);

        System.out.println("Mostra contato inteiro na tela do CellPHONE");
        contato.imprimeContato();
        final TextField nome = new TextField("nome", contato.getNome(), 20, TextField.ANY);
        final TextField twitter = new TextField("twitter", contato.getTwitter(), 25, TextField.ANY);
        
        mostraContatoNaTela.append(nome);
        mostraContatoNaTela.append(twitter);
        
        Display.getDisplay(this).setCurrent(mostraContatoNaTela);
         
        mostraContatoNaTela.setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                 if(c==comandoVoltar){
                        formListarTodos();                  
                 }else if(c==comandoAlterarContato){
                       contato.setNome(nome.getString());
                       contato.setTwitter(twitter.getString());                       
                       updateContato(contato);
                       formListarTodos(); 
                 }else if(c==exitCommand){
                      destroyApp(false);
                      notifyDestroyed();
                 }
            }
        });
      
    }

    public void formularioDeExclsao(){
        
        System.out.println("Formulario de Exclusao");
        
        final Command comandoVoltar = new Command("Back", Command.BACK, 0);

        
        ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
        final Vector contatos = contatoDaoImpl.listarTodos(); // vetor de contatos (Contato)
        
        // Lista - de contatos
        List contatosList = new List("Contatos", Choice.IMPLICIT);
        
        // adiciona os contatos na lista.
        int i=0;
        System.out.println("\nTodos os Contatos ::\n");
        while(i<contatos.size()){
            Contato contato = (Contato) contatos.elementAt(i);
            contato.imprimeContato();
            contatosList.append(contato.getNome(), null);
            i++;
        }
        
        contatosList.addCommand(comandoVoltar);
        Display.getDisplay(this).setCurrent(contatosList); 
                
        contatosList.setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
                if(c==comandoVoltar){
                    try {
                        formPrincipal();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                     List down = (List)display.getCurrent();   
                     ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
                     Contato contato = (Contato) contatos.elementAt(down.getSelectedIndex());
                     contatoDaoImpl.excluirContato(contato);
                     formularioDeExclsao();
                   }                   
           }
        });        
    }

    public int getContadorContatos() {
        return contadorContatos;
    }

    public void setContadorContatos(int contadorContatos) {
        this.contadorContatos = contadorContatos;
    }
    
    
}
