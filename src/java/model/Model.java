/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public class Model {
    
    
    public ArrayList<Error> erros = new ArrayList<Error>();
    
    
  public void setErro(String campo, String mensagem)
  {
      Error objeto_error = new Error(campo, mensagem);
      this.erros.add(objeto_error);
  }
    
}
