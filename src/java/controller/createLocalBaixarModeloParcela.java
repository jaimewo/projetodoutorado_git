/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.LocalDao;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.read.biff.BiffException;
import model.Local;
import model.Parcela;

/**
 *
 * @author paulozeferino
 */
public class createLocalBaixarModeloParcela extends HttpServlet {

    
     private static final int BUFSIZE = 4096;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, BiffException, Exception {
      //  response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
       
          int idLocal = Integer.parseInt(request.getParameter("local_id")); //Pegar idLocal da tela    
            //Se pressionado botão "Baixa Arquivo Exemplo Parcelas"
         String filePath = gravarPlanilhaExemplo(idLocal);
        
//        File file = new File(filePath);
//        int length = 0;
//        ServletOutputStream outStream = response.getOutputStream();
//        response.setContentType("text/html");
//        response.setContentLength((int) file.length());
//        String fileName = (new File(filePath)).getName();
//        response.setHeader("Content-Disposition", "attachment; filename=\""
//                + fileName + "\"");
// 
//        byte[] byteBuffer = new byte[BUFSIZE];
//        DataInputStream in = new DataInputStream(new FileInputStream(file));
// 
//        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
//            outStream.write(byteBuffer, 0, length);
//        }
// 
//        in.close();
//        outStream.close();
        
        
        response.setContentType("text/html");  
        response.setContentType("APPLICATION/OCTET-STREAM");   
        response.setHeader("Content-Disposition","attachment; filename=\"teste.xls\"");   
  
FileInputStream fileInputStream = new FileInputStream(filePath);  
            
int i;   
while ((i=fileInputStream.read()) != -1) {  
out.write(i);   
}   
fileInputStream.close();   
out.close();   
        
        
        
        
        
        
        
        
        
         
         
         
            
        }
    }
    
       public String gravarPlanilhaExemplo(int idLocal) throws SQLException, BiffException, Exception {

        Local local = new Local();
        LocalDao localDao = new LocalDao();
        local = localDao.getLocal(idLocal);

        Parcela parcela = new Parcela();
        return parcela.gravarPlanilhaExemplo(local);        
    
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(createLocalBaixarModeloParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(createLocalBaixarModeloParcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
