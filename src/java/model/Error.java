/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jaime
 */
public class Error {
    
    private String campo;
    private String mensagem;
    
    
    public Error(String campo, String mensagem)
    {
        this.campo = campo;
        this.mensagem = mensagem;
    }
    
    public String getCampo()
    {
        return this.campo;
    }
    
    public String getMensagem()
    {
        return this.mensagem;
    }
    
    
}
