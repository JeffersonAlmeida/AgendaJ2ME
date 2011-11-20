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
            this.salvarCommand = new Command("Salvar", Command.OK,1);
            this.cancelCommand = new Command("Cancelar", Command.CANCEL, 2);        
            this.inserirContatoForm = new Form("Inserir Contato");
            this.inserirContatoForm.append(this.nome);
            this.inserirContatoForm.append(this.twitter);
            this.inserirContatoForm.addCommand(exitCommand);   
            this.inserirContatoForm.addCommand(salvarCommand);
            this.inserirContatoForm.addCommand(cancelCommand);
            this.inserirContatoForm.setCommandListener(this);
            Display.getDisplay(this).setCurrent(inserirContatoForm);      
    } 
    // FORMULARIO PARA APRESENTAR O CONTATO DEPOIS DE INSERIDO!
    public void formApresentao(Contato c){
        this.apresentaContatoForm = new Form("Contato Inserido!");
        this.apresentaContato = new StringItem("Nome: " + c.getNome() + "\nTwitter: "+c.getTwitter(), "");
        this.apresentaContatoForm.append(this.apresentaContato);
        this.apresentaContatoForm.addCommand(exitCommand);
        this.apresentaContatoForm.setCommandListener(this);
    }
    
    // FORMULARIO PARA BUSCAR POR NOME
    
    public void formBucarContato(){        
        Command commandoBuscar = new Command("Buscar", Command.OK, 1);        
        TextField nomeBusca = new TextField("NOME: ", "", 15, TextField.ANY);
        this.buscarContatoForm = new Form("BUSCAR");
        this.buscarContatoForm.append(nomeBusca);
        this.buscarContatoForm.addCommand(exitCommand);
        this.buscarContatoForm.addCommand(commandoBuscar);
        this.buscarContatoForm.setCommandListener(this);
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
              this.salvarContato();                  
              Display.getDisplay(this).setCurrent(apresentaContatoForm);
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
        Display.getDisplay(this).setCurrent(buscarContatoForm);
        
    }

 
    
}


// this.apresentaContato.setLabel("Nome: " + contato.getNome() + "\nTwitter : " + contato.getTwitter());