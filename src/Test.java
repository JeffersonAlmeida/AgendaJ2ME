/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
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
import javax.microedition.midlet.*;
import model.Contato;
import persistence.ContatoDaoImpl;

/**
 * @author Jefferson
 */
public class Test extends MIDlet implements CommandListener {
    
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
                    salvarContato();                  
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
    
    public void formBucarContato(){      
        
        // Comandos
        final Command exitCommand = new Command("exit", Command.EXIT, 0);
        final Command commandoBuscar = new Command("Buscar", Command.OK, 1);        
        
        final TextField nomeBusca = new TextField("NOME: ", "", 15, TextField.ANY);
        this.buscarContatoForm = new Form("BUSCAR");
        
        List contatosList = new List("Contatos", Choice.IMPLICIT);
        
        /*
         this.menu = new List("Agenda Menu", Choice.IMPLICIT);
           menu.append("INCLUIR", incluirImage);
           menu.append("ALTERAR", alterarImage);
           menu.append("EXCLUIR", excluirImage);
           menu.append("PESQUISAR", pesquisarImage);   */ 

        this.buscarContatoForm.append(nomeBusca);
        
        this.buscarContatoForm.addCommand(exitCommand);
        this.buscarContatoForm.addCommand(commandoBuscar);
        
        this.buscarContatoForm.setCommandListener(new CommandListener() {
        public void commandAction(Command c, Displayable d) {
                if(c==commandoBuscar){
                    String nome = nomeBusca.getString().trim();
                    ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
                    Contato contato = new Contato();
                    contato.setNome(nome);
                    System.out.println("Pesquisar Por " + contato.getNome());
                    contatoDaoImpl.pesquisarContato(contato);
                }
            }
        });
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

    public void salvarContato(){
        Contato contato = new Contato(this.nome.getString().trim(), this.twitter.getString().trim());
        ContatoDaoImpl contatoDaoImpl = new ContatoDaoImpl();
        contatoDaoImpl.incluirContato(contato);
        formApresentao(contato);
    }

    private void incluirContato() {   
        this.formularioContato();
        Display.getDisplay(this).setCurrent(inserirContatoForm);
    }

    private void alterarContato() {
       
    }

    private void excluirContato() {
       
    }

    private void pesquisarContato() {
        this.formBucarContato();
        Display.getDisplay(this).setCurrent(this.buscarContatoForm);
        
    }

 
    
}


// this.apresentaContato.setLabel("Nome: " + contato.getNome() + "\nTwitter : " + contato.getTwitter());