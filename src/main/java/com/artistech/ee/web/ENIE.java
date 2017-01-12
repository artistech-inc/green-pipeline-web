/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author matta
 */
public class ENIE extends HttpServlet {

    private static class StreamGobbler extends Thread {

        InputStream is;
        String type;

        private StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        @Override
        public void run() {
            Logger logger = Logger.getLogger(ENIE.class.getName());
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    logger.log(Level.WARNING, line);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

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

        Part pipeline_id_part = request.getPart("pipeline_id");
        String pipeline_id = IOUtils.toString(pipeline_id_part.getInputStream(), "UTF-8");
        Data data = DataManager.getData(pipeline_id);
        String input_sgm = data.getInput();
        String file_list = data.getTestList();
        String enie_out = data.getPipelineDir() + File.separator + "enie_out";
        data.setJointEreOut(enie_out);
        File output_dir = new File(enie_out);
        output_dir.mkdirs();

        //TODO: need to know "$FILE_LIST", "$INPUT_SGM", "$ENIE_OUTP"
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, "-Xmx8g", "-Xms8g", "-server", "-DjetHome=./", "cuny.blender.englishie.ace.IETagger", enie_props, file_list, input_sgm, enie_out);
        pb.directory(new File(enie_path));
        //catch output...
        pb.redirectErrorStream(true);
        Process proc = pb.start();
        StreamGobbler sg = new StreamGobbler(proc.getInputStream(), "");
        sg.start();
        try {
            proc.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(JointEre.class.getName()).log(Level.SEVERE, null, ex);
        }

        Part part = request.getPart("step");
        String target = IOUtils.toString(part.getInputStream(), "UTF-8");

        // displays done.jsp page after upload finished
        getServletContext().getRequestDispatcher(target).forward(
                request, response);
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
