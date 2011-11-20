/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jefferson
 */
public class Contato {
    
    private String nome;
    private String twitter;

    public Contato(String nome, String twitter){
        this.nome = nome;
        this.twitter = twitter;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    
    
}
