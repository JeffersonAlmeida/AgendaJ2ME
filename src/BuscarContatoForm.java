
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefferson
 */
public class BuscarContatoForm extends MIDlet{
       private Display display;
       private Command commandoBuscar;
       private Command exitCommand;
       private TextField nomeBusca;
       private Form buscarContatoForm;
       
       public BuscarContatoForm(){
            this.buscarContatoForm = new Form("Buscar");
            commandoBuscar = new Command("Buscar", Command.OK, 1);     
            this.exitCommand = new Command("exit", Command.EXIT, 0);       
            nomeBusca = new TextField("NOME: ", "", 15, TextField.ANY);
            buscarContatoForm.append(nomeBusca);
            buscarContatoForm.addCommand(exitCommand);
            buscarContatoForm.addCommand(commandoBuscar);
            
       }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        
    }

    protected void pauseApp() {
        
    }

    protected void startApp() throws MIDletStateChangeException {
       BuscarContatoForm buscarContatoForm = new BuscarContatoForm();
       this.display = Display.getDisplay(buscarContatoForm);      
    }

    public Form getBuscarContatoForm() {
        return buscarContatoForm;
    }

    public void setBuscarContatoForm(Form buscarContatoForm) {
        this.buscarContatoForm = buscarContatoForm;
    }

    public Command getCommandoBuscar() {
        return commandoBuscar;
    }

    public void setCommandoBuscar(Command commandoBuscar) {
        this.commandoBuscar = commandoBuscar;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Command getExitCommand() {
        return exitCommand;
    }

    public void setExitCommand(Command exitCommand) {
        this.exitCommand = exitCommand;
    }

    public TextField getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(TextField nomeBusca) {
        this.nomeBusca = nomeBusca;
    }
    
    
}
