/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matta
 */
public class ENIE extends HttpServlet {

//    private static final String CLASSPATH = "bin:lib/nametagging.jar:" +
//            "lib/stanford-postagger.jar:" +
//            "lib/colt-nohep.jar:" +
//            "lib/dbparser.jar:" +
//            "lib/dom4j-1.6.1.jar:" +
//            "lib/javelin.jar:" +
//            "lib/jaxen-1.1.1.jar:" +
//            "lib/joda-time-1.6.jar:" +
//            "lib/jyaml-1.3.jar:" +
//            "lib/log4j.jar:" +
//            "lib/mallet.jar:" +
//            "lib/mallet_old.jar:" +
//            "lib/opennlp-maxent-3.0.1-incubating.jar:" +
//            "lib/opennlp-tools-1.5.1-incubating.jar:" +
//            "lib/pnuts.jar:lib/RadixTree-0.3.jar:" +
//            "lib/stanford-parser.jar:" +
//            "lib/lucene-core-3.0.2.jar:" +
//            "lib/weka.jar:" + 
//            "lib/trove.jar:" +
//            "lib/indri.lib";
    
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
            throws ServletException, IOException {
        String enie_path = getInitParameter("path");
        String enie_props = enie_path + getInitParameter("property");
        String classpath = getInitParameter("classpath");
        
        //TODO: need to know "$FILE_LIST", "$INPUT_SGM", "$ENIE_OUTP"
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, "-Xmx8g", "-Xms8g", "-server", "-DjetHome=./", "cuny.blender.englishie.ace.IETagger", enie_props, "$FILE_LIST", "$INPUT_SGM", "$ENIE_OUTP");
        pb.directory(new File(enie_path));
        pb.redirectErrorStream(true);
        //catch output...

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ENIE</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ENIE @ " + enie_path + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
